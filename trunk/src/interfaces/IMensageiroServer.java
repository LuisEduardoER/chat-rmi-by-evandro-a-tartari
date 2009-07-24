package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMensageiroServer extends Remote{

    public String registra(IMensageiroCliente mensageiro) throws RemoteException;
    public void inicializar(Integer porta) throws RemoteException;
    public void parar()throws RemoteException;
    public void removeCliente(IMensageiroCliente mensageiro) throws RemoteException;
    public void enviarNotificacao(IMensageiroCliente cliente) throws RemoteException;
}
