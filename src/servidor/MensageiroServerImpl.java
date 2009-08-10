package servidor;

import interfaces.IMensageiroCliente;
import interfaces.IMensageiroServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import servidor.ThreadsServidor.ThreadArquivo;
import servidor.ThreadsServidor.ThreadChamarAtencao;
import servidor.ThreadsServidor.ThreadMensagemEnviada;
import servidor.ThreadsServidor.ThreadMensagemEnviou;
import util.Criptografia;
import cliente.EnviaArquivo;
import cliente.Mensagem;
import contatos.Contatos;

/**
 * 
 * @author evandro.tartari
 * 
 */
public class MensageiroServerImpl extends UnicastRemoteObject implements
        IMensageiroServer {

    /**
     * 
     */
    private static final long serialVersionUID = -5493710291205128452L;
    private Registry registro;
    private Map<String, IMensageiroCliente> clientes;
    private List<Contatos> listaContatos;
    private List<String> permissoes;

    public MensageiroServerImpl(Integer porta) throws RemoteException {
        super(porta);
    }

    public String registra(IMensageiroCliente mensageiro)
            throws RemoteException {
        StringBuilder sb = new StringBuilder();
        String login = Criptografia.decripto(mensageiro.getContatos().getLogin());
        sb.append(login);
        if (getPermissoes().contains(login)) {
            if (getClientes().get(login) == null) {
                getClientes().put(login,
                        mensageiro);
                sb.append(" Connectado");
                getContatos().add(mensageiro.getContatos());
                System.out.println(sb.toString());
                return "OK";
            } else {
                return "Usuario Já conectado";
            }
        } else {
            return "Usuario não Cadastrado";
        }
    }

    public void inicializar(Integer porta) throws RemoteException {
        try {
            registro = getRegistry(porta);
            registro.bind("MensageiroServer", this);
            JOptionPane.showMessageDialog(null, "Servidor Iniciado "
                    + getClientes().size() + " " + getContatos().size(),
                    "Servidor Rodando", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Registry getRegistry(Integer porta) throws Exception {
        if (registro == null) {
            registro = LocateRegistry.createRegistry(porta);
        }
        return registro;
    }

    public void enviarNotificacao(IMensageiroCliente cliente)
            throws RemoteException {
        if (getContatos().size() > 0) {
            for (Contatos contato : getContatos()) {
                getClientes().get(Criptografia.decripto(contato.getLogin())).carregaContatos(
                        getContatos());
            }
        }
    }

    /**
     * Getters and Setters
     * 
     * @param clientes
     */
    public void setClientes(Map<String, IMensageiroCliente> clientes) {
        this.clientes = clientes;
    }

    public Map<String, IMensageiroCliente> getClientes() {
        if (clientes == null) {
            clientes = new HashMap<String, IMensageiroCliente>();

        }
        return clientes;
    }

    public void setListaConexao(List<Contatos> con) {
        this.listaContatos = con;
    }

    public List<Contatos> getContatos() {
        if (listaContatos == null) {
            listaContatos = new ArrayList<Contatos>();
        }
        return listaContatos;
    }

    public void setPermissoes(List<String> permissoes) {
        this.permissoes = permissoes;
    }

    public List<String> getPermissoes() {
        if (permissoes == null) {
            permissoes = new ArrayList<String>();
            permissoes.add("evandro.tartari");
            permissoes.add("gustavo.bergamim");
            permissoes.add("venilton.junior");
            permissoes.add("andre.freitas");
            permissoes.add("andre.bacaglini");
            permissoes.add("harry.pereira");
            permissoes.add("carlos.amud");
        }
        return permissoes;

    }

    public void removeCliente(IMensageiroCliente mensageiro)
            throws RemoteException {
        try {
            String login = Criptografia.decripto(mensageiro.getContatos().getLogin());
            if (getClientes().get(login) != null) {
                getClientes().remove(login);
                getContatos().remove(mensageiro.getContatos());
                for (Contatos contato : getContatos()) {
                    getClientes().get(Criptografia.decripto(contato.getLogin())).removeContato(
                            mensageiro.getContatos());
                }
            }
            System.out.println("Saida: " + login);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parar() {
        try {
            registro.unbind("MensageiroServer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enviarMensagem(Mensagem mensagem) throws RemoteException {
        if (getClientes().get(Criptografia.decripto(mensagem.getContatoRecebe())) != null) {
            new ThreadMensagemEnviada(this, mensagem).start();
            new ThreadMensagemEnviou(this, mensagem).start();
        }
    }

    public void clean() throws RemoteException {
        getClientes().clear();
        getContatos().clear();
    }

    public void chamarAtencao(Mensagem mensagem, Contatos contato)
            throws RemoteException {
        new ThreadChamarAtencao(this, mensagem, contato).start();
    }

    public void enviaArquivo(EnviaArquivo arquivo) throws RemoteException {
        new ThreadArquivo(this, arquivo).start();
    }

    public void enviaAvisoEnvioCompleto(EnviaArquivo arquivo)
            throws RemoteException {
        getClientes().get(Criptografia.decripto(arquivo.getContatoEnvia().getLogin()))
                .recebeAvisoEnvioCompleto(arquivo);

    }

    public void finalizar() throws RemoteException {
        try {
            if (getClientes().size() > 0) {
                for (Contatos contato : getContatos()) {
                    getClientes().get(Criptografia.decripto(contato.getLogin())).servidorFechando();
                }
                getClientes().clear();
                getContatos().clear();    
            }
        } catch (Exception e) {
            getClientes().clear();
            getContatos().clear();
        }

    }

    public void fecharTudo() {
        System.exit(0);
    }

}
