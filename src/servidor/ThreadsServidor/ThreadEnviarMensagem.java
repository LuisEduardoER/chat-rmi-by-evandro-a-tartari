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
                servidor.getClientes().get(mensagem.getContatoRecebe())
                        .receberMensagem(mensagem);
                servidor.getClientes().get(mensagem.getUsuarioEnvia())
                        .mensagemEnviada(mensagem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
