package toaster;

import forms.FormListFriends;
import gerenteDeTelas.Gerente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import contatos.Contatos;

public class ToasterActionListener implements ActionListener {
    private Gerente gerente;
    private FormListFriends formList;

    public ToasterActionListener(Gerente gerente, FormListFriends formList) {
        this.gerente = gerente;
        this.formList = formList;
    }

    public void actionPerformed(ActionEvent e) {
        Contatos contato = new Contatos();
        contato.setLogin(e.getActionCommand());
        int posicao = formList.getContatos().indexOf(contato);
        if (posicao != -1) {
            contato = (Contatos) formList.getContatos().getElementAt(posicao);
            gerente.abreConversa(contato);
        }
    }

}
