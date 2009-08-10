package servidor.ThreadsServidor;

import servidor.MensageiroServerImpl;
import util.Criptografia;
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
            servidor.getClientes().get(Criptografia.decripto(contato.getLogin()))
                    .receberChamadaAtencao(mensagem);
            mensagem.setMensagem("Você pediu a atenção de " + Criptografia.decripto(contato.getNome())
                    + "\n");
            servidor.getClientes().get(Criptografia.decripto(mensagem.getUsuarioEnvia()))
                    .mensagemEnviada(mensagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
