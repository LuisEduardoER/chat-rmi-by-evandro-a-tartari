package servidor.ThreadsServidor;

import servidor.MensageiroServerImpl;
import contatos.Contatos;

public class ThreadRemove extends Thread{
    private MensageiroServerImpl servidor;
    private Contatos contatos;

    public ThreadRemove(MensageiroServerImpl servidor, Contatos contatos) {
        this.servidor = servidor;
        this.contatos = contatos;
        
    }
    
    public void run(){
        try{
        if (servidor.getClientes().get(contatos.getLogin()) != null) {
            servidor.getClientes().remove(contatos.getLogin());
            servidor.getContatos().remove(contatos);
            for (Contatos contato : servidor.getContatos()) {
                servidor.getClientes().get(contato.getLogin()).removeContato(
                        contatos);
            }
        }
        System.out.println("Saida: " + contatos.getLogin());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
