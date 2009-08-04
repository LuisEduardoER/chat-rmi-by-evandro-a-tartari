package ThreadsCliente;

import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;

import contatos.Contatos;
import contatos.ContatosComparator;
import forms.FormListFriends;

public class ThreadCarregaContatos extends Thread {
    private List<Contatos> contatos;
    private DefaultListModel modelContatos;
    private List<Contatos> listaApresentacao;
    private List<Contatos> modelAux;
    private Boolean isListaAberta;
    private FormListFriends form;

    public ThreadCarregaContatos(List<Contatos> contatos,
            DefaultListModel modelContatos, List<Contatos> listaApresentacao,
            List<Contatos> modelAux, boolean isListaAberta, FormListFriends form) {
        this.contatos = contatos;
        this.modelContatos = modelContatos;
        this.listaApresentacao = listaApresentacao;
        this.modelAux = modelAux;
        this.isListaAberta = isListaAberta;
        this.form = form;
    }

    public void run() {
        for (Contatos contato : contatos) {
            if (!listaApresentacao.contains(contato)
                    || !modelAux.contains(contato)) {
                try {
                    if (contato.equals(form.getCliente().getContatos())) {
                        if (form.getLblUsuario() == null)
                            form.adicionaUsuario(contato);
                    } else {
                        if (isListaAberta == true) {
                            Contatos first = new Contatos(" Friends");
                            listaApresentacao.add(contato);
                            Collections.sort(listaApresentacao, new ContatosComparator());
                            modelContatos.clear();
                            modelContatos.addElement(first);
                            for (int i = 0; i < listaApresentacao.size(); i++) {
                                modelContatos.addElement(listaApresentacao.get(i));
                            }
                        } else if (isListaAberta == false) {
                            modelAux.add(contato);
                            Collections
                                    .sort(modelAux, new ContatosComparator());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
