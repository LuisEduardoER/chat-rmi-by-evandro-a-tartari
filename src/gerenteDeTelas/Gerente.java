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
import javax.swing.JFrame;
import javax.swing.JTextField;

import cliente.MensageiroClienteImpl;
import cliente.Mensagem;
import contatos.Contatos;

/**
 * 
 * @author evandro.tartari
 * 
 */
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
                    formListFriends.buscaContatos();
                    formListFriends.renderiza();
                    connect.dispose();

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
        getCon().setNome(form.getNome().getText());
        getCon().setIpServidor(form.getIpServidor().getText());
        getCon().setPortaServico(
                Integer.parseInt(form.getPortaServico().getText()));
        getCon().setPortaCliente(
                Integer.parseInt(form.getPortaCliente().getText()));
        getCon().setIcon(form.getImagemContato());
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
        String name = cliente.getContatos().getLogin() + contato.getLogin();
        if (listaConversa.get(name) != null) {
            FormConversa conversa = listaConversa.get(name);
            conversa.setExtendedState(JFrame.NORMAL);
        } else {
            FormConversa conversa = new FormConversa(this, cliente);
            conversa.setNomeConversa(cliente.getContatos().getLogin()
                    + contato.getLogin());
            conversa.config();
            conversa.inicializar(contato, cliente.getContatos());
            conversa.setContato(contato);
            conversa.renderiza();
            listaConversa.put(name, conversa);
        }

    }

    public void adicionaContato(Contatos contatos) {
        getFormListFriends().adicionaContato(contatos);
    }

    public void adicionaUsuario(Contatos contatos) {
        getFormListFriends().adicinalUsuario(contatos);
    }

    public void removeContato(Contatos contatos) {
        getFormListFriends().removeContato(contatos);
    }

    public void enviarMensagem(Mensagem mensagem) {
        try {
            this.cliente.enviarMensagem(mensagem);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void recebeMensagem(Mensagem mensagem) {
        String name = mensagem.getContatoRecebe() + mensagem.getUsuarioEnvia();
        String name2 = mensagem.getUsuarioEnvia() + mensagem.getContatoRecebe();
        if (getListaConversa().get(name) != null) {
            getListaConversa().get(name).recebeMensagem(mensagem);
        } else if (getListaConversa().get(name2) != null) {
            getListaConversa().get(name2).recebeMensagem(mensagem);
        } else {
            Contatos contato = new Contatos();
            contato.setLogin(mensagem.getUsuarioEnvia());
            int posicao = getFormListFriends().getContatos().indexOf(contato);
            if (posicao != -1) {
                contato = (Contatos) getFormListFriends().getContatos().get(
                        posicao);
                try {
                    this.iniciaConversa(contato, mensagem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void iniciaConversa(Contatos contato, Mensagem mensagem) {
        try {
            String name = cliente.getContatos().getLogin() + contato.getLogin();
            if (listaConversa.get(name) != null) {
                FormConversa conversa = listaConversa.get(name);
                conversa.setExtendedState(JFrame.ICONIFIED);
            } else {
                FormConversa conversa = new FormConversa(this, cliente);
                conversa.setNomeConversa(name);
                conversa.config();
                conversa.inicializar(contato, cliente.getContatos());
                conversa.setContato(contato);
                conversa.recebeMensagem(mensagem);
                conversa.renderiza();
                listaConversa.put(name, conversa);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public Map<String, FormConversa> getListaConversa() {
        if (listaConversa == null) {
            listaConversa = new HashMap<String, FormConversa>();
        }
        return listaConversa;
    }

    public void setListaConversa(Map<String, FormConversa> listaConversa) {
        this.listaConversa = listaConversa;
    }

    public void fechouConversa(FormConversa conversa) {
        getListaConversa().remove(conversa.getNomeConversa());

    }

    public void verificaInstanciaConversa(Contatos contato) {
        try {
            String name = cliente.getContatos().getLogin() + contato.getLogin();
            String name2 = contato.getLogin()
                    + cliente.getContatos().getLogin();
            if (getListaConversa().get(name) != null) {
                getListaConversa().get(name).disableChat();
                getListaConversa().remove(getListaConversa().get(name));
            } else if (getListaConversa().get(name2) != null) {
                getListaConversa().get(name2).disableChat();
                getListaConversa().remove(getListaConversa().get(name2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
