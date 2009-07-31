package servidor.ThreadsServidor;

import interfaces.IMensageiroCliente;
import servidor.MensageiroServerImpl;
import contatos.Contatos;

public class ThreadRemove extends Thread{
    private MensageiroServerImpl servidor;
    private IMensageiroCliente mensageiro;

    public ThreadRemove(MensageiroServerImpl servidor, IMensageiroCliente mensageiro) {
        this.servidor = servidor;
        this.mensageiro = mensageiro;
    }
    
    public void run(){
        try{
        if (servidor.getClientes().get(mensageiro.getContatos().getLogin()) != null) {
            servidor.getClientes().remove(mensageiro.getContatos().getLogin());
            servidor.getContatos().remove(mensageiro.getContatos());
            for (Contatos contato : servidor.getContatos()) {
                servidor.getClientes().get(contato.getLogin()).removeContato(
                        mensageiro.getContatos());
            }
        }
        System.out.println("Saida: " + mensageiro.getContatos().getLogin());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
