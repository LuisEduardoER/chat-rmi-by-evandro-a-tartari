package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import conexao.Conexao;

public interface IMensageiroCliente extends Remote {

    public Boolean findServidor() throws RemoteException;

    public Conexao getConexao() throws RemoteException;

}
