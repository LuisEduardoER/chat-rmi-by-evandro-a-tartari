package servidor.ThreadsServidor;

import servidor.MensageiroServerImpl;
import cliente.EnviaArquivo;

public class ThreadArquivo extends Thread {
    private MensageiroServerImpl servidor;
    private EnviaArquivo arquivo;

    public ThreadArquivo(MensageiroServerImpl servidor, EnviaArquivo arquivo) {
        this.servidor = servidor;
        this.arquivo = arquivo;
    }
    public void run() {
        try{
        servidor.getClientes().get(arquivo.getContatoRecebe().getLogin()).recebeArquivo(
                arquivo);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
