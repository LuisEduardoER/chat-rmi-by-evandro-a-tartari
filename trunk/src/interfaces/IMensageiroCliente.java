package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import cliente.EnviaArquivo;
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

    public void comunicaSaida() throws RemoteException;

    public void adicionaUsuario(Contatos contatos) throws RemoteException;

    public void adicionaContato(Contatos contatos) throws RemoteException;

    public void carregaContatos(List<Contatos> contatos) throws RemoteException;

    public void removeContato(Contatos contato) throws RemoteException;

    public void buscaContatos(IMensageiroCliente contatos)
            throws RemoteException;

    public void enviarMensagem(Mensagem mensagem) throws RemoteException;

    public void receberMensagem(Mensagem mensagem) throws RemoteException;

    public void chamarAtencao(Mensagem mensagem, Contatos contato)
            throws RemoteException;

    public void receberChamadaAtencao(Mensagem mensagem) throws RemoteException;

    public void enviaArquivo(EnviaArquivo arquivo) throws RemoteException;

    public void recebeArquivo(EnviaArquivo arquivo) throws RemoteException;

    public void enviaAvisoEnvioCompleto(EnviaArquivo arquivo)
            throws RemoteException;

    public void recebeAvisoEnvioCompleto(EnviaArquivo arquivo)
            throws RemoteException;

    public void mensagemEnviada(Mensagem mensagem) throws RemoteException;

}
