package cliente.SysTrayClient;

import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import forms.FormListFriends;

public class TrayManagerFormListFriendsListener implements MouseListener, ActionListener {
    private TrayIcon trayIcon;
    private FormListFriends listaContatos;
    private SystemTray tray;
    

    public TrayManagerFormListFriendsListener(TrayIcon trayIcon, JFrame frame, SystemTray tray) {
        this.trayIcon = trayIcon;
        listaContatos = (FormListFriends) frame;
        this.tray = tray;
    
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() % 2 == 0) {
            listaContatos.setVisible(true);
            tray.remove(trayIcon);
            listaContatos.setExtendedState(JFrame.NORMAL);
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

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == null) {
            listaContatos.setVisible(true);
            tray.remove(trayIcon);
            listaContatos.setExtendedState(JFrame.NORMAL);
        } else if (e.getActionCommand().equals("Exit")) {
            listaContatos.removeCliente();
            System.exit(0);
        } else if (e.getActionCommand().equals("Abrir")) {
            listaContatos.setVisible(true);
            tray.remove(trayIcon);
            listaContatos.setExtendedState(JFrame.NORMAL);
        } 
    }

}
