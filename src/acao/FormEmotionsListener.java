package acao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import util.Util;

import forms.FormConversa;

public class FormEmotionsListener implements ActionListener, MouseListener {
    private FormConversa conversa;

    public FormEmotionsListener(FormConversa conversa) {
        this.conversa = conversa;
    }

    public void actionPerformed(ActionEvent e) {
        ClassLoader clazz = this.getClass().getClassLoader();
        URL res = clazz.getResource(Util.Emotions.getEmotions().get(e.getActionCommand()));
        ImageIcon icon = new ImageIcon(res);
        conversa.addImagem(icon, e.getActionCommand());
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
