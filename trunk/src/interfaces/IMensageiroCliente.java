package interfaces;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

import cliente.Mensagem;

import contatos.Contatos;
/**
 * 
 * @author evandro.tartari
 *
 */
public interface IMensageiroCliente extends Remote {

    public Boolean findServidor() throws RemoteException;
    public Contatos getContatos() throws RemoteException;
    public void comunicaSaida()throws RemoteException;
	public void adicionaUsuario(Contatos contatos)throws RemoteException;
	public void adicionaContato(Contatos contatos)throws RemoteException;
	public void removeContato(Contatos contato) throws RemoteException;
	public void buscaContatos(IMensageiroCliente contatos)throws RemoteException;
	public void enviarMensagem(Mensagem mensagem) throws RemoteException;
	public void receberMensagem(Mensagem mensagem) throws RemoteException;
    public void chamarAtencao(Mensagem mensagem, Contatos contato) throws RemoteException;
    public void receberChamadaAtencao(Mensagem mensagem)throws RemoteException;
    public void enviaArquivo(Contatos contato, File file)throws RemoteException;

}
