package acao;

import forms.FormListFriends;
import gerenteDeTelas.Gerente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import contatos.Contatos;

public class ToasterListener implements ActionListener {
    private Gerente gerente;
    private FormListFriends listFriends;

    public ToasterListener(Gerente gerente, FormListFriends listFriends) {
        this.gerente = gerente;
        this.listFriends = listFriends;
    }

    public void actionPerformed(ActionEvent e) {
        Contatos contato = new Contatos();
        contato.setLogin(e.getActionCommand());
        int posicao = listFriends.getContatos().indexOf(contato);
        if(posicao!=-1){
            contato = (Contatos) listFriends.getContatos().getElementAt(posicao);
            gerente.abreConversa(contato);
        }
    }

}
