package servidor.trayicon;

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
    
    public SysTrayImpls(TrayIcon trayIcon,JFrame frame) {
        this.trayIcon = trayIcon;
        servidor = (FormServidor) frame;
    }
    
    public void mouseClicked(MouseEvent e) {
        System.out.println("Tray Icon - Mouse clicked!");
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
            System.out.println("A Implementar");
        }else if(e.getActionCommand().equals("Exit")){
            System.out.println("Exiting...");
            System.exit(0);
        }else if(e.getActionCommand().equals("Abrir")){
            System.out.println("A Implementar");
        }
    }

}
