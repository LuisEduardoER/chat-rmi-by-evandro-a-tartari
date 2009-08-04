package ThreadsCliente;

import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;

import contatos.Contatos;
import contatos.ContatosComparator;

public class ThreadAdicionaContato extends Thread {
    private Contatos contato;
    private DefaultListModel modelContatos;
    private List<Contatos> apresentacao;

    public ThreadAdicionaContato(DefaultListModel modelContatos,
            Contatos contato, List<Contatos> apresentacao) {
        this.contato = contato;
        this.modelContatos = modelContatos;
        this.apresentacao = apresentacao;
    }

    public void run() {
        Contatos first = new Contatos(" Friends");
        Contatos last = new Contatos(" Offline");
        apresentacao.add(contato);
        Collections.sort(apresentacao, new ContatosComparator());
        modelContatos.clear();
        modelContatos.addElement(first);
        for (int i = 0; i < apresentacao.size(); i++) {
            modelContatos.addElement(apresentacao.get(i));
        }
        modelContatos.addElement(last);
    }
}
