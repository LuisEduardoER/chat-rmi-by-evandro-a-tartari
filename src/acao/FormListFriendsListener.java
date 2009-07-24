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

import javax.swing.JFrame;

import contatos.Contatos;
import forms.FormListFriends;
import gerenteDeTelas.Gerente;

public class FormListFriendsListener implements ActionListener, KeyListener,
        MouseListener, WindowListener {
    private FormListFriends formContatos;
    private Gerente gerente;

    public FormListFriendsListener(JFrame formContatos, Gerente gerente) {
        this.formContatos = (FormListFriends) formContatos;
        this.gerente = gerente;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Minimizar")) {
            formContatos.setExtendedState(JFrame.ICONIFIED);
        } else if (e.getActionCommand().equals("Sair")) {
            formContatos.removeCliente();
            formContatos.dispose();
            System.exit(0);
        }

    }

    public void keyPressed(KeyEvent evento) {
    }

    public void keyReleased(KeyEvent evento) {
        if (evento.getKeyCode() == Event.ESCAPE) {
            if (evento.getModifiers() == 0) {
                formContatos.setExtendedState(JFrame.ICONIFIED);
            }
        } else if (evento.getKeyCode() == Event.F4) {
            if (evento.getModifiers() == Event.ALT_MASK) {
                formContatos.removeCliente();
                formContatos.dispose();
                System.exit(0);
            }
        }
    }

    public void keyTyped(KeyEvent evento) {
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() % 2 == 0) {
            if (formContatos.getListaContatos().getSelectedIndex() != -1) {
                try {
                    gerente.controladorConversa((Contatos) formContatos
                            .getListaContatos().getSelectedValue());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
        formContatos.removeCliente();
    }

    public void windowClosing(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowOpened(WindowEvent e) {
    }

}