package servidor;

import interfaces.IMensageiroCliente;
import interfaces.IMensageiroServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import conexao.Conexao;

public class MensageiroServerImpl extends UnicastRemoteObject implements
        IMensageiroServer {

    /**
     * 
     */
    private static final long serialVersionUID = -5493710291205128452L;
    private Map<String, IMensageiroCliente> clientes;
    private List<Conexao> listaConexao;

    protected MensageiroServerImpl() throws RemoteException {
        super(/* pode passar a porta */);
    }

    public String registra(IMensageiroCliente mensageiro)
            throws RemoteException {
        if (!getClientes().containsValue(mensageiro)) {
            getClientes().put(mensageiro.getConexao().getLogin(), mensageiro);
            getListaConexao().add(mensageiro.getConexao());
            return "OK";
        } else {
            return "Usuario Já conectado";
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

    public void setListaConexao(List<Conexao> con) {
        this.listaConexao = con;
    }

    public List<Conexao> getListaConexao() {
        return listaConexao;
    }

}
