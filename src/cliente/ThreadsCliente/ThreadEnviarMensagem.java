package cliente.ThreadsCliente;

import acao.FormConversaListener;
/**
 * 
 * @author evandro.tartari
 *
 */
public class ThreadEnviarMensagem extends Thread {
    private FormConversaListener listener;

    public ThreadEnviarMensagem(FormConversaListener listener) {
        this.listener = listener;
    }
    
    public void run() {
        listener.enviarMensagem(listener.getText());
    }

}
