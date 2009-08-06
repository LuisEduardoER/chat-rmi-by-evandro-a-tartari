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

public class FormEmotionsListener implements ActionListener, MouseListener{
    private Map<String, String> mapaEmotions;
    private ClassLoader clazz = this.getClass().getClassLoader();
    private URL res;
    
    public FormEmotionsListener(Map<String, String> mapaEmotions) {
        this.mapaEmotions = mapaEmotions;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(mapaEmotions.get(e.getActionCommand()));
        res = clazz.getResource(mapaEmotions.get(e.getActionCommand()));
        Icon icon = new ImageIcon(res); 
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        ((JButton)e.getComponent()).setBorderPainted(true);
    }

    public void mouseExited(MouseEvent e) {
        ((JButton)e.getComponent()).setBorderPainted(false);
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

}
