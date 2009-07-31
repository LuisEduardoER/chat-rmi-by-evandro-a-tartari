package ThreadsCliente;

import cliente.Mensagem;
import contatos.Contatos;
import gerenteDeTelas.Gerente;

public class ThreadRecebeMensagem extends Thread {
    private Gerente gerente;
    private Mensagem mensagem;

    public ThreadRecebeMensagem(Gerente gerente, Mensagem mensagem) {
        this.gerente = gerente;
        this.mensagem = mensagem;
    }

    public void run() {
        String name = mensagem.getContatoRecebe() + mensagem.getUsuarioEnvia();
        String name2 = mensagem.getUsuarioEnvia() + mensagem.getContatoRecebe();
        if (gerente.getListaConversa().get(name) != null) {
            gerente.getListaConversa().get(name).recebeMensagem(mensagem);
        } else if (gerente.getListaConversa().get(name2) != null) {
            gerente.getListaConversa().get(name2).recebeMensagem(mensagem);
        } else {
            Contatos contato = new Contatos();
            contato.setLogin(mensagem.getUsuarioEnvia());
            int posicao = gerente.getFormListFriends().getContatos().indexOf(contato);
            if (posicao != -1) {
                contato = (Contatos) gerente.getFormListFriends().getContatos().get(
                        posicao);
                try {
                    gerente.iniciaConversa(contato, mensagem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
