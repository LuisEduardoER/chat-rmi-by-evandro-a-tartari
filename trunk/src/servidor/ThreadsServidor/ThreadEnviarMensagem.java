package servidor.ThreadsServidor;

import servidor.MensageiroServerImpl;
import cliente.Mensagem;

public class ThreadEnviarMensagem extends Thread {
    private MensageiroServerImpl servidor;
    private Mensagem mensagem;

    public ThreadEnviarMensagem(MensageiroServerImpl servidor, Mensagem mensagem) {
        this.servidor = servidor;
        this.mensagem = mensagem;
    }

    public void run() {
        try {
            if (servidor.getClientes().get(mensagem.getContatoRecebe()) != null) {
                new ThreadEnviarMensagem(servidor, mensagem).start();
                new ThreadMensagemEnviou(servidor, mensagem).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
