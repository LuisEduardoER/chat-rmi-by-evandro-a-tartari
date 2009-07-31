package servidor.ThreadsServidor;

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
                    if (contato.getLogin().equals(
                            cliente.getContatos().getLogin())) {
                        cliente.adicionaUsuario(cliente.getContatos());
                    } else {
                        cliente.adicionaContato(contato);
                        servidor.getClientes().get(contato.getLogin())
                                .adicionaContato(cliente.getContatos());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
