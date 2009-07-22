package acao;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import forms.FormConversa;

public class FormConversaListener implements ActionListener , KeyListener{
    private FormConversa conversa;
    private StringBuilder sb;
    public FormConversaListener(JFrame frame) {
        this.conversa = (FormConversa) frame;
        sb = new StringBuilder();
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().endsWith("enviar")){
            if(isValid()){
                enviarMensagem();
            }
        }
        
    }

    private boolean isValid() {
        return !getDescritor().trim().equals("");
    }
    
    private String getDescritor(){
        return conversa.getTxtDescritorMensagens().getText();
    }
    
    private void zeraDescritor(){
        conversa.getTxtDescritorMensagens().setText("");
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==Event.ENTER){
            e.consume();
            enviarMensagem();
        }
        
    }

    public void keyReleased(KeyEvent e) {
        
        
    }

    public void keyTyped(KeyEvent e) {
        
        
    }
    
    @Deprecated
    public void enviarMensagem(){
        sb.append(getDescritor()+"\n");
        conversa.getTxtReceptorMensagens().setText(sb.toString());
        zeraDescritor();
    }
    
    

}
