package ThreadsCliente;

import forms.FormConversa;

public class ThreadPiscaJanela extends Thread {
    private FormConversa conversa;

    public ThreadPiscaJanela(FormConversa conversa) {
        this.conversa = conversa;
    }

    @SuppressWarnings("static-access")
    public void run() {
        try{
        int i = 0;
        while (i < 2) {
            this.sleep(500);
            conversa.setVisible(true);
            i++;
        }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
