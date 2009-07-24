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

import contatos.Contatos;

public class MensageiroServerImpl extends UnicastRemoteObject implements
        IMensageiroServer {

    /**
     * 
     */
    private static final long serialVersionUID = -5493710291205128452L;
    private Registry registro;
    private Map<String, IMensageiroCliente> clientes;
    private List<Contatos> listaConexao;
    private List<String> permissoes;

    public MensageiroServerImpl(Integer porta) throws RemoteException {
        super(porta);
    }

    public String registra(IMensageiroCliente mensageiro)
            throws RemoteException {
        StringBuilder sb = new StringBuilder();
        sb.append(mensageiro.getContatos().getLogin());
        if (getPermissoes().contains(mensageiro.getContatos().getLogin())) {
            if (!getClientes().containsValue(mensageiro)) {
                getClientes().put(mensageiro.getContatos().getLogin(),
                        mensageiro);
                sb.append(" Connectado");
                getListaConexao().add(mensageiro.getContatos());
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

    public void setListaConexao(List<Contatos> con) {
        this.listaConexao = con;
    }

    public List<Contatos> getListaConexao() {
        if (listaConexao == null) {
            listaConexao = new ArrayList<Contatos>();
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
