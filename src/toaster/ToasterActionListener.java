package toaster;

import forms.FormListFriends;
import gerenteDeTelas.Gerente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import contatos.Contatos;

public class ToasterActionListener implements ActionListener, MouseListener{
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

    public void mouseClicked(MouseEvent e) {
        Contatos contato = new Contatos();
        /**
         * Arruma Logica pra isso!!
         */
        int posicao = formList.getContatos().indexOf(contato);
        if (posicao != -1) {
            contato = (Contatos) formList.getContatos().getElementAt(posicao);
            gerente.abreConversa(contato);
        }
    }

    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

}
