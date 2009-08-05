package cliente.ThreadsCliente;

import contatos.Contatos;
import gerenteDeTelas.Gerente;
import cliente.Mensagem;

public class ThreadMensagemEnviada extends Thread {
    private Gerente gerente;
    private Mensagem mensagem;

    public ThreadMensagemEnviada(Gerente gerente, Mensagem mensagem) {
        this.gerente = gerente;
        this.mensagem = mensagem;
    }

    public void run() {
        String name = mensagem.getUsuarioEnvia() + mensagem.getContatoRecebe();
        if (gerente.getListaConversa().get(name) != null) {
            gerente.getListaConversa().get(name).recebeMensagem(mensagem);
        } else {
            Contatos contato = new Contatos();
            contato.setLogin(mensagem.getUsuarioEnvia());
            int posicao = gerente.getFormListFriends().getContatos().indexOf(
                    contato);
            if (posicao != -1) {
                contato = (Contatos) gerente.getFormListFriends().getContatos()
                        .get(posicao);
                try {
                    gerente.iniciaConversa(contato, mensagem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
