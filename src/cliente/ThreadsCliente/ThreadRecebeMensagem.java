package cliente.ThreadsCliente;

import gerenteDeTelas.Gerente;

import javax.swing.JFrame;

import util.Criptografia;
import cliente.Mensagem;
import contatos.Contatos;
/**
 * 
 * @author evandro.tartari
 *
 */
public class ThreadRecebeMensagem extends Thread {
    private Gerente gerente;
    private Mensagem mensagem;

    public ThreadRecebeMensagem(Gerente gerente, Mensagem mensagem) {
        this.gerente = gerente;
        this.mensagem = mensagem;
    }

    public void run() {
        String name = Criptografia.decripto(mensagem.getContatoRecebe()) + 
        Criptografia.decripto(mensagem.getUsuarioEnvia());
        if (gerente.getListaConversa().get(name) != null) {
            gerente.getListaConversa().get(name).recebeMensagem(mensagem);
            if(gerente.getListaConversa().get(name).getExtendedState()==JFrame.ICONIFIED)
            new ThreadPiscaJanela(gerente.getListaConversa().get(name)).start();
        } else {
            Contatos contato = new Contatos();
            contato.setLogin(Criptografia.decripto(mensagem.getUsuarioEnvia()));
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
