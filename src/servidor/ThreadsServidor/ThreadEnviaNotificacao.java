package servidor.ThreadsServidor;

import interfaces.IMensageiroCliente;
import servidor.MensageiroServerImpl;
import contatos.Contatos;

public class ThreadEnviaNotificacao extends Thread {
    private MensageiroServerImpl servidor;
    private IMensageiroCliente cliente;

    public ThreadEnviaNotificacao(MensageiroServerImpl servidor,
            IMensageiroCliente cliente) {
        this.servidor = servidor;
        this.cliente = cliente;
    }

    public void run() {
        try {
            if (servidor.getContatos().size() > 0) {
                for (Contatos contato : servidor.getContatos()) {
                    if(cliente.getContatos().equals(contato)){
                        cliente.carregaContatos(servidor.getContatos());
                    }else{
                        servidor.getClientes().get(contato.getLogin()).adicionaContato(contato);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
