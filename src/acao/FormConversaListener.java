package acao;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import forms.FormConversa;

public class FormConversaListener implements ActionListener, KeyListener {
    private FormConversa conversa;

    public FormConversaListener(JFrame frame) {
        this.conversa = (FormConversa) frame;

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().endsWith("enviar")) {
            if (isValid()) {
                enviarMensagem(getText());
            }

        }

    }

    private boolean isValid() {
        return !getDescritor().getText().trim().equals("");
    }

    private JTextPane getDescritor() {
        return conversa.getTxtDescritorMensagens();
    }

    private JTextArea getReceptor() {
        return conversa.getTxtReceptorMensagens();
    }

    private String getText() {
        return conversa.getTxtDescritorMensagens().getText();
    }

    private void zeraDescritor() {
        conversa.getTxtDescritorMensagens().setText("");
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == Event.ENTER) {
            if (e.getModifiers() == 0) {
                e.consume();
                enviarMensagem(getText());
            }
        }

    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    @Deprecated
    public void enviarMensagem(String text) {
        try {
            getReceptor().append(text);
            zeraDescritor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
