package cliente;

import interfaces.IMensageiroCliente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class MensageiroClienteImpl extends UnicastRemoteObject implements
        IMensageiroCliente {

    /**
     * 
     */
    private static final long serialVersionUID = -1849358136989976908L;

    protected MensageiroClienteImpl() throws RemoteException {
        super(/*pode passar a porta*/);
    }
}
