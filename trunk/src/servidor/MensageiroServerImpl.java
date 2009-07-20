package servidor;

import interfaces.IMensageiroServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MensageiroServerImpl extends UnicastRemoteObject implements IMensageiroServer{

    /**
     * 
     */
    private static final long serialVersionUID = -5493710291205128452L;
    protected MensageiroServerImpl() throws RemoteException {
        super(/*pode passar a porta*/);
    }

}
