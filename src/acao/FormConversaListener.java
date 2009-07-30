package acao;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import cliente.Mensagem;
import forms.FormConversa;
import gerenteDeTelas.Gerente;

/**
 * 
 * @author evandro.tartari
 * 
 */
public class FormConversaListener implements ActionListener, KeyListener,
        WindowListener, MouseListener {
    private FormConversa conversa;
    private Gerente gerente;

    public FormConversaListener(JFrame frame, Gerente gerente) {
        this.conversa = (FormConversa) frame;
        this.gerente = gerente;

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("enviar")) {
            if (isValid()) {
                enviarMensagem(getText());
            }

        } else if (e.getActionCommand().equals("Negrito")) {
            if (conversa.getBtnNegrito().isSelected()) {
                conversa.setIsBold(true);
                conversa.getTxtDescritorMensagens().requestFocus();
            } else if (!conversa.getBtnNegrito().isSelected()) {
                conversa.setIsBold(false);
                conversa.getTxtDescritorMensagens().requestFocus();
            }
        } else if (e.getActionCommand().equals("Italico")) {
            if (conversa.getBtnItalico().isSelected()) {
                conversa.setIsItalic(true);
                conversa.getTxtDescritorMensagens().requestFocus();
            } else if (!conversa.getBtnItalico().isSelected()) {
                conversa.setIsItalic(false);
                conversa.getTxtDescritorMensagens().requestFocus();
            }
        } else if (e.getActionCommand().equals("TamFonte")) {
            conversa.setFontSize();
        } else if (e.getActionCommand().equals("TipoFonte")) {
            conversa.setFontFamily();

        } else if (e.getActionCommand().equals("cores")) {
            conversa.instanciaPaletaCores();
        }

    }

    private boolean isValid() {
        return !getDescritor().getText().trim().equals("");
    }

    private JTextPane getDescritor() {
        return conversa.getTxtDescritorMensagens();
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

    public void enviarMensagem(String text) {
        try {
            gerente.enviarMensagem(getMensagem(text));
            zeraDescritor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Mensagem getMensagem(String text) {
        try {
            Mensagem m = new Mensagem(conversa.getCliente().getContatos()
                    .getLogin(), conversa.getCliente().getContatos().getNome(),
                    text, conversa.getFontSize(), conversa.getFontFamily(),
                    conversa.getColor(), conversa.getIsBold(), conversa
                            .getIsItalic(), conversa.getContato().getLogin());
            return m;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void windowClosing(WindowEvent e) {
        gerente.fechouConversa(conversa);
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowOpened(WindowEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        conversa.addBorderBtnPaletaCores();
    }

    public void mouseExited(MouseEvent e) {
        conversa.removeBorderBtnPaletaCores();
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

}
