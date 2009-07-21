package acao;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import forms.FormListFriends;

public class FormListFriendsListener implements ActionListener, KeyListener, MouseListener{
    private FormListFriends formContatos;
    
    public FormListFriendsListener(JFrame formContatos) {
        this.formContatos = (FormListFriends) formContatos;
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Minimizar")){
            formContatos.setExtendedState(JFrame.ICONIFIED);
        }else if(e.getActionCommand().equals("Sair")){
            formContatos.dispose();
            System.exit(0);
        }
        
    }

    public void keyPressed(KeyEvent evento) {
    }

    public void keyReleased(KeyEvent evento) {
        if(evento.getKeyCode()==Event.ESCAPE){
            if(evento.getModifiers()==0){
                formContatos.setExtendedState(JFrame.ICONIFIED);
            }
        }else if(evento.getKeyCode()==Event.F4){
            if(evento.getModifiers()==Event.ALT_MASK){
                formContatos.dispose();
                System.exit(0);
            }
        }
    }

    public void keyTyped(KeyEvent evento) {
    }

    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
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
