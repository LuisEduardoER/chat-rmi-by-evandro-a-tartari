package servidor.ThreadsServidor;

import java.util.List;

import interfaces.IMensageiroCliente;
import servidor.MensageiroServerImpl;
import contatos.Contatos;

public class ThreadEnviaNotificacao extends Thread {
    private MensageiroServerImpl servidor;
    private IMensageiroCliente cliente;

    public ThreadEnviaNotificacao(MensageiroServerImpl servidor,
            IMensageiroCliente cliente) {
        this.servidor = servidor;
        this.cliente = cliente;
    }

    public void run() {
        try {
            if (servidor.getContatos().size() > 0) {
                for (Contatos contato : servidor.getContatos()) {
                    if (cliente.getContatos().equals(contato)) {
                        cliente.adicionaUsuario(contato);
                    } else {
                        List<Contatos> contatos = servidor.getContatos();
                        contatos.remove(contato);
                        servidor.getClientes().get(contato.getLogin())
                                .carregaContatos(contatos);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
