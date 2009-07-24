package gerenteDeTelas;

import forms.FormConnect;
import forms.FormConversa;
import forms.FormListFriends;
import interfaces.IMensageiroCliente;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JTextField;

import cliente.MensageiroClienteImpl;
import contatos.Contatos;

public class Gerente {

    /**
     * Contrutor Default do Gerente de Telas
     */
    private List<JComponent> listaExcessaoConnectForm = new ArrayList<JComponent>();
    private IMensageiroCliente cliente;
    private Contatos con;
    private FormConnect connect;
    private FormListFriends formListFriends;
    private Map<String, FormConversa> listaConversa;

    public Gerente() {
    }

    /**
     * Metodo de inicialização do Gerente de Telas
     */
    private void init() {
        listaConversa = new HashMap<String, FormConversa>();
        FormConnect connect = new FormConnect(this);
        connect.configJFrame();
        connect.inicializar();
        connect.grupoValidador();
        connect.renderizaTela();
    }

    /**
     * Metodo connectar para instanciar um cliente e procurar um servico
     * 
     * @param frame
     */
    public void connectar(FormConnect frame) {
        if (isValidData(frame)) {
            try {
                connect = frame;
                setCon(frame);
                cliente = new MensageiroClienteImpl(getCon(), this);
                Boolean retorno = cliente.findServidor();
                if (retorno == true) {
                    formListFriends = getFormListFriends();
                    formListFriends.setCliente(cliente);
                    formListFriends.inicializa();
                    formListFriends.config();
                    formListFriends.createMenuBar();
                    formListFriends.criarBordaPainel(cliente.getContatos()
                            .getNome());
                    formListFriends.adicionaListener();
                    formListFriends.renderiza();
                }

            } catch (RemoteException e) {
                frame.getExcessao().lancaExcessao(e.getMessage());
            }
        } else {
            frame.getExcessao().setaListaExcessao(listaExcessaoConnectForm);
            frame.getExcessao().lancaExcessao("*Campo numerico");
        }
    }

    /**
     * Validador dos componentes numericos
     * 
     * @param frame
     * @return
     */
    private Boolean isValidData(FormConnect frame) {
        Boolean retorna = true;
        if (!isValidaCampoNumerico(frame.getIpServidor(), frame))
            retorna = false;
        if (!isValidaCampoNumerico(frame.getPortaServico(), frame))
            retorna = false;
        if (!isValidaCampoNumerico(frame.getPortaCliente(), frame))
            retorna = false;
        return retorna;
    }

    /**
     * Validador Campo numerico
     * 
     * @param t
     * @param frame
     * @return Boolean
     */
    public Boolean isValidaCampoNumerico(JTextField t, FormConnect frame) {
        String strNumero = t.getText();
        strNumero = strNumero.replace(".", "");
        try {
            Integer.parseInt(strNumero);
            return true;
        } catch (Exception e) {
            listaExcessaoConnectForm.add(t);
            return false;
        }

    }

    /**
     * Metodo Main do Gerente de Telas
     * 
     * @param args
     */
    public static void main(String[] args) {
        Gerente gerente = new Gerente();
        gerente.init();
    }

    /**
     * Seta dados da conexao
     * 
     * @param form
     */
    public void setCon(FormConnect form) {
        getCon().setLogin(form.getLogin().getText());
        getCon().setSenha(form.getPassWord().getPassword().toString());
        getCon().setIpServidor(form.getIpServidor().getText());
        getCon().setPortaServico(
                Integer.parseInt(form.getPortaServico().getText()));
        getCon().setPortaCliente(
                Integer.parseInt(form.getPortaCliente().getText()));
    }

    /**
     * Pega dados de uma conexao
     * 
     * @return
     */
    public Contatos getCon() {
        if (con == null) {
            con = new Contatos();
        }
        return con;
    }

    /**
     * Lanca uma excessao
     * 
     * @param texto
     */
    public void lancaExcessao(String texto) {
        connect.getExcessao().lancaExcessaoSimple(texto);
    }

    /**
     * Cria e retorna caso necessario uma instancia do FormListFriends
     * 
     * @return
     */
    public FormListFriends getFormListFriends() {
        if (formListFriends == null) {
            formListFriends = new FormListFriends(this);
        }
        return formListFriends;

    }

    public void controladorConversa(Contatos contato) throws Exception {
        String name = cliente.getContatos().getNome() + contato.getNome();
        String name2 = contato.getNome() + cliente.getContatos().getNome();
        if (listaConversa.get(name) != null) {
            FormConversa conversa = listaConversa.get(name);
            conversa.renderiza();
        } else if (listaConversa.get(name2) != null) {
            FormConversa conversa = listaConversa.get(name2);
            conversa.renderiza();
        } else {
            FormConversa conversa = new FormConversa();
            conversa.setName(cliente.getContatos().getNome()
                    + contato.getNome());
            conversa.config();
            conversa.inicializar(cliente.getContatos().getUrlImage(), contato
                    .getUrlImage());
            conversa.renderiza();
            listaConversa.put(name, conversa);
        }

    }

}
