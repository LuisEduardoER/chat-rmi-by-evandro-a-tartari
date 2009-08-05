package cliente.ThreadsCliente;

import forms.FormConversa;
import gerenteDeTelas.Gerente;

import javax.swing.JFrame;

public class ThreadEnviaArquivo extends Thread{
    private Gerente gerente;
    private FormConversa conversa;
    private String url;
    
    public ThreadEnviaArquivo(JFrame frame, Gerente gerente) {
        this.conversa = (FormConversa) frame;
        this.gerente = gerente;
    }
    public void run() {
        try{
        url = conversa.getFileChooser().getSelectedFile().getPath();
        gerente.enviaArquivo(conversa.getCliente().getContatos(), conversa.getContato(), url);
        conversa.fechaEnviaArquivo();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
