package cliente.ThreadsCliente;

import javax.swing.JFrame;

import forms.FormConversa;
/**
 * 
 * @author evandro.tartari
 *
 */
public class ThreadPiscaJanela extends Thread {
    private FormConversa conversa;

    public ThreadPiscaJanela(FormConversa conversa) {
        this.conversa = conversa;
        
    }

    @SuppressWarnings("static-access")
    public void run() {
        conversa.setExtendedState(JFrame.ICONIFIED);
        try{
        int i = 0;
        while (i < 5) {
            conversa.renderiza();
            this.sleep(200);
            i++;
        }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
