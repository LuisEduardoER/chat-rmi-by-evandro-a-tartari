package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import contatos.Contatos;

public interface IMensageiroCliente extends Remote {

    public Boolean findServidor() throws RemoteException;
    public Contatos getContatos() throws RemoteException;
    public void comunicaSaida()throws RemoteException;
	public void adicionaUsuario(Contatos contatos)throws RemoteException;
	public void adicionaContato(Contatos contatos)throws RemoteException;
	public void removeContato(Contatos contato) throws RemoteException;
	public void buscaContatos(Contatos contatos)throws RemoteException;

}
