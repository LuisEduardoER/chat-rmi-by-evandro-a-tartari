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
import servidor.ThreadsServidor.ThreadEnviarMensagem;
import servidor.ThreadsServidor.ThreadXException;
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
        sb.append(mensageiro.getContatos().getLogin());
        if (getPermissoes().contains(mensageiro.getContatos().getLogin())) {
            if (getClientes().get(mensageiro.getContatos().getLogin()) == null) {
                getClientes().put(mensageiro.getContatos().getLogin(),
                        mensageiro);
                sb.append(" Connectado");
                getContatos().add(mensageiro.getContatos());
                System.out.println(sb.toString());
                return "OK";
            } else {
                System.out.println(sb.toString());
                return "Usuario Já conectado";
            }
        } else {
            System.out.println(sb.toString());
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
                getClientes().get(contato.getLogin()).carregaContatos(
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

        }
        return permissoes;

    }

    public void removeCliente(IMensageiroCliente mensageiro)
            throws RemoteException {
        try {
            if (getClientes().get(mensageiro.getContatos().getLogin()) != null) {
                getClientes().remove(mensageiro.getContatos().getLogin());
                getContatos().remove(mensageiro.getContatos());
                for (Contatos contato : getContatos()) {
                    getClientes().get(contato.getLogin()).removeContato(
                            mensageiro.getContatos());
                }
            }
            System.out.println("Saida: " + mensageiro.getContatos().getLogin());
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
        new ThreadEnviarMensagem(this, mensagem).start();
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
        getClientes().get(arquivo.getContatoEnvia().getLogin())
                .recebeAvisoEnvioCompleto(arquivo);

    }

    public void finalizar() throws RemoteException {
        if (getClientes().size() > 0) {
            for (Contatos contato : getContatos()) {
                new ThreadXException(getClientes(), contato);
            }

        }
        getClientes().clear();
        getContatos().clear();
    }

    public void fecharTudo() {
        System.exit(0);
    }

}
