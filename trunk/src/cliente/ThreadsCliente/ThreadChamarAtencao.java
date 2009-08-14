package cliente.ThreadsCliente;

import cliente.Mensagem;
import forms.FormConversa;
import gerenteDeTelas.Gerente;
/**
 * 
 * @author evandro.tartari
 *
 */
public class ThreadChamarAtencao extends Thread {
    private FormConversa conversa;
    private Gerente gerente;
    private Mensagem mensagem;

    public ThreadChamarAtencao(FormConversa conversa, Gerente gerente,
            Mensagem mensagem) {
        this.conversa = conversa;
        this.gerente = gerente;
        this.mensagem = mensagem;
    }

    public void run() {
        gerente.chamarAtencao(mensagem, conversa.getContato());
        setEnableButton();
    }

    @SuppressWarnings("static-access")
    private void setEnableButton() {
        if (!conversa.getBtnAlerta().isEnabled()) {
            try {
                this.sleep(5000);
                conversa.getBtnAlerta().setEnabled(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
