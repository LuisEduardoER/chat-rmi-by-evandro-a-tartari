package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import cliente.EnviaArquivo;
import cliente.Mensagem;
import contatos.Contatos;

/**
 * 
 * @author evandro.tartari
 * 
 */
public interface IMensageiroServer extends Remote {

    public String registra(IMensageiroCliente mensageiro)
            throws RemoteException;

    public void inicializar(Integer porta) throws RemoteException;

    public void parar() throws RemoteException;

    public void removeCliente(IMensageiroCliente mensageiro)
            throws RemoteException;

    public void enviarNotificacao(IMensageiroCliente cliente)
            throws RemoteException;

    public void enviarMensagem(Mensagem mensagem) throws RemoteException;

    public void clean() throws RemoteException;

    public void chamarAtencao(Mensagem mensagem, Contatos contato)
            throws RemoteException;

    public void enviaArquivo(EnviaArquivo arquivo) throws RemoteException;

    public void enviaAvisoEnvioCompleto(EnviaArquivo arquivo)
            throws RemoteException;

}
