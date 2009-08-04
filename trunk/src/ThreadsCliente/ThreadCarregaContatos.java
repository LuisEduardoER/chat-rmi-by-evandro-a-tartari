package ThreadsCliente;

import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;

import contatos.Contatos;
import contatos.ContatosComparator;

public class ThreadCarregaContatos extends Thread {
    private List<Contatos> contatos;
    private DefaultListModel modelContatos;
    private List<Contatos> listaApresentacao;
    private List<Contatos> modelAux;
    private Boolean isListaAberta;

    public ThreadCarregaContatos(List<Contatos> contatos,
            DefaultListModel modelContatos, List<Contatos> listaApresentacao,
            List<Contatos> modelAux, boolean isListaAberta) {
        this.contatos = contatos;
        this.modelContatos = modelContatos;
        this.listaApresentacao = listaApresentacao;
        this.modelAux = modelAux;
        this.isListaAberta = isListaAberta;
    }

    public void run() {
        for (Contatos contato : contatos) {
            if (isListaAberta == true) {
                new ThreadAdicionaContato(modelContatos, contato,
                        listaApresentacao).start();
            } else if (isListaAberta == false) {
                modelAux.add(contato);
                Collections.sort(modelAux, new ContatosComparator());
            }
        }
    }
}
