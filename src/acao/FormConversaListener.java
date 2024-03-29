package acao;

import java.awt.Color;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;

import util.Util;
import cliente.Mensagem;
import cliente.ThreadsCliente.ThreadEnviaArquivo;
import cliente.ThreadsCliente.ThreadEnviarMensagem;
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
                new ThreadEnviarMensagem(this).start();
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
        } else if (e.getActionCommand().equals("Sublinhado")) {
            if (conversa.getBtnSublinhado().isSelected()) {
                conversa.setIsSublinhado(true);
                conversa.getTxtDescritorMensagens().requestFocus();
            } else if (!conversa.getBtnSublinhado().isSelected()) {
                conversa.setIsSublinhado(false);
                conversa.getTxtDescritorMensagens().requestFocus();
            }
        } else if (e.getActionCommand().equals("TamFonte")) {
            conversa.setFontSize();
        } else if (e.getActionCommand().equals("TipoFonte")) {
            conversa.setFontFamily();

        } else if (e.getActionCommand().equals("cores")) {
            conversa.instanciaPaletaCores();
        } else if (e.getActionCommand().equals("btnPaleta")) {
            if (conversa.getJColorChooser().getColor() != null) {
                if (conversa.getJColorChooser().getColor().equals(Color.WHITE)) {
                    conversa.setColor(Color.black);
                    conversa.fechaPaletaCores();
                } else {
                    conversa.setColor(conversa.getJColorChooser().getColor());
                    conversa.fechaPaletaCores();
                }
            }
        } else if (e.getActionCommand().equals("alerta")) {
            conversa.getTxtDescritorMensagens().requestFocus();
            conversa.getBtnAlerta().setEnabled(false);
            conversa.chamarAtencao(getMensagem("está pedindo sua atenção\n"));
        } else if (e.getActionCommand().equals("sendFile")) {
            conversa.instanciaEnviaArquivo();
        } else if (e.getActionCommand().equals("ApproveSelection")) {
            new ThreadEnviaArquivo(conversa, gerente).start();
        } else if (e.getActionCommand().equals("CancelSelection")) {
            conversa.fechaEnviaArquivo();
        } else if (e.getActionCommand().equals("emotions")) {
            conversa.instanciaEmotions();
        }

    }

    private boolean isValid() {
        return !getDescritor().getText().trim().equals("");
    }

    private JTextPane getDescritor() {
        return conversa.getTxtDescritorMensagens();
    }

    public String getText() {
        return conversa.getTxtDescritorMensagens().getText();
    }

    private void zeraDescritor() {
        conversa.getTxtDescritorMensagens().setText("");
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == Event.ENTER) {
            if (e.getModifiers() == 0) {
                e.consume();
                enviarMensagem(getText());
            } else if (e.getModifiers() == 1) {
                e.consume();
                conversa.getTxtDescritorMensagens().setText(
                        conversa.getTxtDescritorMensagens().getText() + "\n");
            }
        } else if (e.getKeyCode() == Event.ESCAPE) {
            if (e.getModifiers() == 0) {
                e.consume();
                if (conversa.getPaleta() != null)
                    conversa.fechaPaletaCores();
            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void enviarMensagem(String text) {
        try {
            if (!text.trim().equals("")) {
                gerente.enviarMensagem(getMensagem(text));
                zeraDescritor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Mensagem getMensagem(String text) {
        try {
            Mensagem m = Util.newMensagem(conversa, text);
            return m;
        } catch (Exception e) {
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
        JButton button = (JButton) e.getComponent();
        if (button.isEnabled()) {
            conversa.addBorderBtnPaletaCores(button);
        }
    }

    public void mouseExited(MouseEvent e) {
        JButton button = (JButton) e.getComponent();
        conversa.removeBorderBtnPaletaCores(button);
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

}
