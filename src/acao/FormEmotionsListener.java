package acao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import forms.FormConversa;

public class FormEmotionsListener implements ActionListener, MouseListener {
    private FormConversa conversa;

    public FormEmotionsListener(FormConversa conversa) {
        this.conversa = conversa;
    }

    public void actionPerformed(ActionEvent e) {
        conversa.addImagen("<" + e.getActionCommand() + ">");
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        ((JButton) e.getComponent()).setBorderPainted(true);
    }

    public void mouseExited(MouseEvent e) {
        ((JButton) e.getComponent()).setBorderPainted(false);
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

}
