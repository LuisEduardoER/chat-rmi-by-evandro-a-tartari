package servidor.ThreadsServidor;

import interfaces.IMensageiroCliente;

import java.util.List;

import servidor.MensageiroServerImpl;
import contatos.Contatos;

public class ThreadEnviaNotificacao extends Thread {
    private MensageiroServerImpl servidor;
    

    public ThreadEnviaNotificacao(MensageiroServerImpl servidor,
            IMensageiroCliente cliente) {
        this.servidor = servidor;
    }

    public void run() {
        try {
            if (servidor.getContatos().size() > 0) {
                for (Contatos contato : servidor.getContatos()) {
                    List<Contatos> contatos = servidor.getContatos();
                    servidor.getClientes().get(contato.getLogin())
                            .carregaContatos(contatos);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
