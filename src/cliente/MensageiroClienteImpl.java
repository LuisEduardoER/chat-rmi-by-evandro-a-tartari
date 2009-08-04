package cliente;

import gerenteDeTelas.Gerente;
import interfaces.IMensageiroCliente;
import interfaces.IMensageiroServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import contatos.Contatos;

/**
 * 
 * @author evandro.tartari
 * 
 */
public class MensageiroClienteImpl extends UnicastRemoteObject implements
		IMensageiroCliente {

	/**
     * 
     */
	private static final long serialVersionUID = -1849358136989976908L;
	private Contatos con;
	private IMensageiroServer servidor;
	private Registry registroRMI;
	private Gerente gerente;

	/**
	 * Construtor padrao do cliente
	 * 
	 * @param con
	 * @param gerente
	 * @throws RemoteException
	 */
	public MensageiroClienteImpl(Contatos con, Gerente gerente)
			throws RemoteException {
		super(con.getPortaCliente());
		this.con = con;
		this.gerente = gerente;
	}

	/**
	 * Metodo de busca ao servico
	 */
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

	public void comunicaSaida() throws RemoteException {
		servidor.removeCliente(this);
	}

	public Contatos getContatos() throws RemoteException {
		return con;
	}

	public void adicionaContato(Contatos contatos) throws RemoteException {
		gerente.adicionaContato(contatos);
	}
	
    public void carregaContatos(List<Contatos> contatos) throws RemoteException {
        gerente.carregaContatos(contatos);
        
    }

	public void adicionaUsuario(Contatos contatos) throws RemoteException {
		gerente.adicionaUsuario(contatos);
	}

	public void removeContato(Contatos contatos) throws RemoteException {
		gerente.removeContato(contatos);
	}

	public void buscaContatos(IMensageiroCliente cliente)
			throws RemoteException {
		servidor.enviarNotificacao(cliente);
	}

	public void enviarMensagem(Mensagem mensagem) throws RemoteException {
		servidor.enviarMensagem(mensagem);
	}

	public void receberMensagem(Mensagem mensagem) throws RemoteException {
		gerente.recebeMensagem(mensagem);
		
	}

    public void chamarAtencao(Mensagem mensagem, Contatos contato)
            throws RemoteException {
        servidor.chamarAtencao(mensagem, contato);
        
    }

    public void receberChamadaAtencao(Mensagem mensagem) throws RemoteException {
        gerente.receberChamadaAtencao(mensagem);
        
    }

    public void enviaArquivo(EnviaArquivo arquivo) throws RemoteException {
        servidor.enviaArquivo(arquivo);
        
    }

    public void recebeArquivo(EnviaArquivo arquivo) throws RemoteException {
        gerente.recebeArquivo(arquivo);
        
    }

    public void enviaAvisoEnvioCompleto(EnviaArquivo arquivo)
            throws RemoteException {
        servidor.enviaAvisoEnvioCompleto(arquivo);
        
    }

    public void recebeAvisoEnvioCompleto(EnviaArquivo arquivo) throws RemoteException {
       gerente.recebeAvisoEnvioCompleto(arquivo);
        
    }




    
    

 
}
