package acao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import forms.FormConversa;

public class FormEmotionsListener implements ActionListener, MouseListener {
    private Map<String, String> mapaEmotions;
    private FormConversa conversa;
    private ClassLoader clazz = this.getClass().getClassLoader();
    private URL res;

    public FormEmotionsListener(Map<String, String> mapaEmotions,
            FormConversa conversa) {
        this.mapaEmotions = mapaEmotions;
        this.conversa = conversa;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(mapaEmotions.get(e.getActionCommand()));
        res = clazz.getResource(mapaEmotions.get(e.getActionCommand()));
        Icon icon = new ImageIcon(res);
        conversa.addImagen("<" + e.getActionCommand() + ">", icon);
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
