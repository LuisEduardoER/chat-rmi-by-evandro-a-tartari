package cliente.ThreadsCliente;

import javax.swing.JFrame;

import forms.FormConversa;
/**
 * 
 * @author evandro.tartari
 *
 */
public class ThreadAlerta extends Thread {
    private FormConversa conversa;
    private Integer x;
    private Integer y;

    public ThreadAlerta(FormConversa conversa) {
        this.conversa = conversa;
    }

    public void setXY() {
        conversa.setExtendedState(JFrame.NORMAL);
        if (x == null && y == null) {
            x = this.conversa.getLocation().x;
            y = this.conversa.getLocation().y;
        }
    }

    @SuppressWarnings("static-access")
    public void run() {
        try {
            this.setXY();
            int i = 0;
            int xI = -8;
            int yI = -8;
            int var = 16;
            while (i < 150) {
                conversa.setLocation(x + xI, y + yI);
                xI += var;
                yI += var;
                var *= -1;
                i++;
                this.sleep(20);
            }
            x = null;
            y = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
