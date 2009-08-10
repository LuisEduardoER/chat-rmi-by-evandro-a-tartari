package servidor.ThreadsServidor;

import servidor.MensageiroServerImpl;
import util.Criptografia;
import cliente.Mensagem;

public class ThreadMensagemEnviou extends Thread {
    private MensageiroServerImpl servidor;
    private Mensagem mensagem;

    public ThreadMensagemEnviou(MensageiroServerImpl servidor, Mensagem mensagem) {
        this.servidor = servidor;
        this.mensagem = mensagem;
    }

    public void run() {
        try {
            servidor.getClientes().get(Criptografia.decripto(mensagem.getUsuarioEnvia()))
                    .mensagemEnviada(mensagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
