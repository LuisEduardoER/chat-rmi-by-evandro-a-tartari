package cliente;

import gerenteDeTelas.Gerente;
import interfaces.IMensageiroCliente;
import interfaces.IMensageiroServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import conexao.Conexao;

public class MensageiroClienteImpl extends UnicastRemoteObject implements
        IMensageiroCliente {

    /**
     * 
     */
    private static final long serialVersionUID = -1849358136989976908L;
    private Conexao con;
    private IMensageiroServer servidor;
    private Registry registroRMI;
    private Gerente gerente;

    public MensageiroClienteImpl(Conexao con, Gerente gerente) throws RemoteException {
        super(con.getPortaCliente());
        this.con = con;
        this.gerente = gerente;
    }

    public Boolean findServidor() {
        try {
            registroRMI = LocateRegistry.getRegistry(con.getIpServidor(), con
                    .getPortaServico());
            servidor = (IMensageiroServer) registroRMI
                    .lookup("MensageiroServer");
            String resposta = servidor.registra(this);
            if (resposta.equals("OK"))
                return true;
            else {
                gerente.lancaExcessao(resposta);
                return false;
            }
        } catch (Exception e) {
            gerente.lancaExcessao("Erro buscando servidor");
            return false;
        }
    }

    public Conexao getConexao() throws RemoteException {
        return con;
    }

}
