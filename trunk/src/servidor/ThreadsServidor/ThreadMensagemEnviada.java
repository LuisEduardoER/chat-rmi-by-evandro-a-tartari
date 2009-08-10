package servidor.ThreadsServidor;

import cliente.Mensagem;
import servidor.MensageiroServerImpl;
import util.Criptografia;

public class ThreadMensagemEnviada extends Thread{
    private MensageiroServerImpl servidor;
    private Mensagem mensagem;
    public ThreadMensagemEnviada(MensageiroServerImpl servidor, Mensagem mensagem){
        this.servidor = servidor; 
        this.mensagem  =mensagem;
    }
    
    public void run() {
        try{
        servidor.getClientes().get(Criptografia.decripto(mensagem.getContatoRecebe()))
        .receberMensagem(mensagem);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    