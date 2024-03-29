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
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import util.Criptografia;
import cliente.EnviaArquivo;
import cliente.MensageiroClienteImpl;
import cliente.Mensagem;
import cliente.ThreadsCliente.ThreadMensagemEnviada;
import cliente.ThreadsCliente.ThreadPiscaJanela;
import cliente.ThreadsCliente.ThreadRecebeMensagem;
import contatos.Contatos;

/**
 * 
 * @author evandro.tartari
 * 
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
                    formListFriends.config();
                    formListFriends.inicializa();
                    formListFriends.createMenuBar();
                    formListFriends.criarBordaPainel(Criptografia
                            .decripto(cliente.getContatos().getNome()));
                    formListFriends.adicionaListener();
                    formListFriends.buscaContatos();
                    formListFriends.renderiza();
                    connect.dispose();
                }

            } catch (RemoteException e) {
                frame.getExcessao().lancaExcessao(e.getMessage());
                frame.enableButtonConnect();
            }
        } else {
            frame.getExcessao().setaListaExcessao(listaExcessaoConnectForm);
            frame.getExcessao().lancaExcessao("*Campo numerico");
            frame.enableButtonConnect();
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
        getCon().setIconUsuario(form.getIconUsuario());
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
    
    public void habilitaBotaoConnect() {
        connect.habilitaBotaoConnect();
        
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
        String name = Criptografia.decripto(cliente.getContatos().getLogin())
                + Criptografia.decripto(contato.getLogin());
        if (listaConversa.get(name) != null) {
            FormConversa conversa = listaConversa.get(name);
            conversa.setExtendedState(JFrame.NORMAL);
        } else {
            FormConversa conversa = new FormConversa(this, cliente);
            conversa.setNomeConversa(name);
            conversa.config();
            conversa.inicializar(contato, cliente.getContatos());
            conversa.setContato(contato);
            conversa.renderiza();
            listaConversa.put(name, conversa);
        }

    }

    public void carregaContatos(List<Contatos> contatos) {
        getFormListFriends().carregaContatos(contatos);

    }

    public void adicionaUsuario(Contatos contatos) {
        getFormListFriends().adicionaUsuario(contatos);
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
        getFormListFriends().activeToaster(mensagem);
        new ThreadRecebeMensagem(this, mensagem).start();
    }

    public void mensagemEnviada(Mensagem mensagem) {
        new ThreadMensagemEnviada(this, mensagem).start();

    }

    public void iniciaConversa(Contatos contato, Mensagem mensagem) {
        try {
            String name = Criptografia.decripto(cliente.getContatos().getLogin())
            + Criptografia.decripto(contato.getLogin());
            if (listaConversa.get(name) != null) {
                FormConversa conversa = listaConversa.get(name);
                if (conversa.getExtendedState() == JFrame.ICONIFIED)
                    new ThreadPiscaJanela(conversa).start();
            } else {
                FormConversa conversa = new FormConversa(this, cliente);
                conversa.setNomeConversa(name);
                conversa.config();
                conversa.inicializar(contato, cliente.getContatos());
                conversa.setContato(contato);
                conversa.recebeMensagem(mensagem);
                conversa.setExtendedState(JFrame.ICONIFIED);
                conversa.renderiza();
                listaConversa.put(name, conversa);
                new ThreadPiscaJanela(conversa).start();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
    
    public void abreConversa(Contatos contato) {
        try {
            String name = Criptografia.decripto(cliente.getContatos().getLogin())
            + Criptografia.decripto(contato.getLogin());
            if (listaConversa.get(name) != null) {
                FormConversa conversa = listaConversa.get(name);
                conversa.setExtendedState(JFrame.NORMAL);
            } else {
                FormConversa conversa = new FormConversa(this, cliente);
                conversa.setNomeConversa(name);
                conversa.config();
                conversa.inicializar(contato, cliente.getContatos());
                conversa.setContato(contato);
                conversa.setExtendedState(JFrame.NORMAL);
                conversa.renderiza();
                listaConversa.put(name, conversa);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    private void iniciaConversa(EnviaArquivo arquivo) {
        try {
            String name = Criptografia.decripto(cliente.getContatos().getLogin())
                    + Criptografia.decripto(arquivo.getContatoEnvia().getLogin());
            if (listaConversa.get(name) != null) {
                FormConversa conversa = listaConversa.get(name);
                conversa.setExtendedState(JFrame.ICONIFIED);
            } else {
                FormConversa conversa = new FormConversa(this, cliente);
                conversa.setNomeConversa(name);
                conversa.config();
                conversa.inicializar(arquivo.getContatoEnvia(), cliente
                        .getContatos());
                conversa.setContato(arquivo.getContatoEnvia());
                conversa.recebeArquivo(arquivo);
                conversa.renderiza();
                listaConversa.put(name, conversa);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    private void iniciaReciboAviso(EnviaArquivo arquivo) {
        try {
            String name = Criptografia.decripto(cliente.getContatos().getLogin())
                    + Criptografia.decripto(arquivo.getContatoEnvia().getLogin());
            if (listaConversa.get(name) != null) {
                FormConversa conversa = listaConversa.get(name);
                conversa.setExtendedState(JFrame.ICONIFIED);
                new ThreadPiscaJanela(conversa).start();
            } else {
                FormConversa conversa = new FormConversa(this, cliente);
                conversa.setNomeConversa(name);
                conversa.config();
                conversa.inicializar(arquivo.getContatoEnvia(), cliente
                        .getContatos());
                conversa.setContato(arquivo.getContatoEnvia());
                conversa.recebeAviso(arquivo);
                new ThreadPiscaJanela(conversa).start();
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
            String name = Criptografia.decripto(cliente.getContatos().getLogin()) + 
            Criptografia.decripto(contato.getLogin());
            String name2 = Criptografia.decripto(contato.getLogin())
                    + Criptografia.decripto(cliente.getContatos().getLogin());
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

    public void chamarAtencao(Mensagem mensagem, Contatos contato) {
        try {
            cliente.chamarAtencao(mensagem, contato);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receberChamadaAtencao(Mensagem mensagem) {
        String nome = Criptografia.decripto(mensagem.getContatoRecebe()) + 
        Criptografia.decripto(mensagem.getUsuarioEnvia());
        if (getListaConversa().get(nome) != null) {
            getListaConversa().get(nome).recebeMensagem(mensagem);
            getListaConversa().get(nome).disparaThread();
        } else {
            Contatos contato = new Contatos();
            contato.setLogin(Criptografia.decripto(mensagem.getUsuarioEnvia()));
            int posicao = getFormListFriends().getContatos().indexOf(contato);
            if (posicao != -1) {
                contato = (Contatos) getFormListFriends().getContatos().get(
                        posicao);
                try {
                    this.iniciaConversa(contato, mensagem);
                    nome = Criptografia.decripto(cliente.getContatos().getLogin())
                            + Criptografia.decripto(contato.getLogin());
                    getListaConversa().get(nome).disparaThread();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void enviaArquivo(Contatos contatoCliente, Contatos contatoRecebe,
            String url) {
        try {
            EnviaArquivo arquivo = new EnviaArquivo(url, contatoRecebe,
                    contatoCliente);
            cliente.enviaArquivo(arquivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recebeArquivo(EnviaArquivo arquivo) {
        try {
            String nome = Criptografia.decripto(arquivo.getContatoRecebe().getLogin())
                    + Criptografia.decripto(arquivo.getContatoEnvia().getLogin());
            if (getListaConversa().get(nome) != null) {
                getListaConversa().get(nome).recebeArquivo(arquivo);
            } else {
                int posicao = getFormListFriends().getContatos().indexOf(
                        arquivo.getContatoEnvia());
                if (posicao != -1) {
                    Contatos contato = (Contatos) getFormListFriends()
                            .getContatos().get(posicao);
                    this.iniciaConversa(arquivo);
                    nome = cliente.getContatos().getLogin()
                            + contato.getLogin();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recebeAvisoEnvioCompleto(EnviaArquivo arquivo) {
        try {
            String name = Criptografia.decripto(arquivo.getContatoEnvia().getLogin())
                    + Criptografia.decripto(arquivo.getContatoRecebe().getLogin());
            if (getListaConversa().get(name) != null) {
                getListaConversa().get(name).recebeAviso(arquivo);
            } else {
                int posicao = getFormListFriends().getContatos().indexOf(
                        arquivo.getContatoEnvia());
                if (posicao != -1) {
                    Contatos contato = (Contatos) getFormListFriends()
                            .getContatos().get(posicao);
                    this.iniciaReciboAviso(arquivo);
                    name = Criptografia.decripto(cliente.getContatos().getLogin())
                            + Criptografia.decripto(contato.getLogin());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void servidorFechando() {
        getFormListFriends().setExtendedState(JFrame.NORMAL);
        JOptionPane.showMessageDialog(null,
                "Servidor Fechou! Esta Janela será encerrada",
                "Aviso de Fechamento!!!", JOptionPane.QUESTION_MESSAGE);
        getFormListFriends().dispose();
        System.exit(0);
    }

    public void contatoConectou(Contatos contatos) {
        getFormListFriends().contatoConectou(contatos);
        
    }

 

}
