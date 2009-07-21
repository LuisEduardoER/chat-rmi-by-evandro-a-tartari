package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import contatos.Contatos;

public interface IMensageiroCliente extends Remote {

    public Boolean findServidor() throws RemoteException;
    public Contatos getConexao() throws RemoteException;

}
