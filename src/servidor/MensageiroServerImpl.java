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

import conexao.Conexao;

public class MensageiroServerImpl extends UnicastRemoteObject implements
        IMensageiroServer {

    /**
     * 
     */
    private static final long serialVersionUID = -5493710291205128452L;
    private Registry registro;
    private Map<String, IMensageiroCliente> clientes;
    private List<Conexao> listaConexao;
    private List<String> permissoes;

    public MensageiroServerImpl(Integer porta) throws RemoteException {
        super(porta);
    }

    public String registra(IMensageiroCliente mensageiro)
            throws RemoteException {
        if (getPermissoes().contains(mensageiro.getConexao().getLogin())) {
            if (!getClientes().containsValue(mensageiro)) {
                getClientes().put(mensageiro.getConexao().getLogin(),
                        mensageiro);
                getListaConexao().add(mensageiro.getConexao());
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Registry getRegistry(Integer porta) throws Exception {
        if(registro==null){
            registro = LocateRegistry.createRegistry(porta);
        }
        return registro;
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
        if (listaConexao == null) {
            listaConexao = new ArrayList<Conexao>();
        }
        return listaConexao;
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

        }
        return permissoes;

    }

    public void parar() {
        try {
            registro.unbind("MensageiroServer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
