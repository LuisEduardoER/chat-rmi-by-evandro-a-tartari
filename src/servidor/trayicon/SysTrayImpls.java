package servidor.trayicon;

import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import servidor.forms.FormServidor;

public class SysTrayImpls implements MouseListener, ActionListener {
    private TrayIcon trayIcon;
    private FormServidor servidor;
    private SystemTray tray;
    
    public SysTrayImpls(TrayIcon trayIcon,JFrame frame, SystemTray tray) {
        this.trayIcon = trayIcon;
        servidor = (FormServidor) frame;
        this.tray = tray;
    }
    
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()%2==0){
            servidor.setVisible(true);
            tray.remove(trayIcon);
            servidor.setExtendedState(JFrame.NORMAL);
            
        }
    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("Tray Icon - Mouse entered!");
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("Tray Icon - Mouse exited!");
    }

    public void mousePressed(MouseEvent e) {
        System.out.println("Tray Icon - Mouse pressed!");
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("Tray Icon - Mouse released!");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand()==null) {
            servidor.setVisible(true);
            tray.remove(trayIcon);
            servidor.setExtendedState(JFrame.NORMAL);
        }else if(e.getActionCommand().equals("Exit")){
            System.out.println("Exiting...");
            System.exit(0);
        }else if(e.getActionCommand().equals("Abrir")){
            servidor.setVisible(true);
            tray.remove(trayIcon);
            servidor.setExtendedState(JFrame.NORMAL);
        }
    }

}
