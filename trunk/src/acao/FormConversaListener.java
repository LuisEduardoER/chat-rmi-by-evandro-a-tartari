package acao;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
public class FormConversaListener implements ActionListener, KeyListener {
	private FormConversa conversa;
	private Gerente gerente;

	public FormConversaListener(JFrame frame, Gerente gerente) {
		this.conversa = (FormConversa) frame;
		this.gerente = gerente;
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
		Mensagem m = null;
		try {
			m = new Mensagem(conversa.getCliente().getContatos().getLogin(),
					text, conversa.getFont(), conversa.getIsBold(),
					conversa.getIsItalic(), conversa.getContato().getLogin());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return m;
	}

}
