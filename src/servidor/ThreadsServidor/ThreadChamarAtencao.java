package servidor.ThreadsServidor;

import servidor.MensageiroServerImpl;
import cliente.Mensagem;
import contatos.Contatos;

public class ThreadChamarAtencao extends Thread {

    private MensageiroServerImpl servidor;
    private Mensagem mensagem;
    private Contatos contato;

    public ThreadChamarAtencao(MensageiroServerImpl servidor,
            Mensagem mensagem, Contatos contato) {
        this.servidor = servidor;
        this.mensagem = mensagem;
        this.contato = contato;
    }

    public void run() {
        try {
            servidor.getClientes().get(contato.getLogin())
                    .receberChamadaAtencao(mensagem);
            mensagem
                    .setMensagem("Você pediu a atenção de " + contato.getNome());
            servidor.getClientes().get(mensagem.getUsuarioEnvia())
                    .receberMensagem(mensagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
