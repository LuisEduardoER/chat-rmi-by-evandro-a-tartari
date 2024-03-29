package cliente.SysTrayClient;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;

import javax.swing.JFrame;

import forms.FormListFriends;
/**
 * 
 * @author evandro.tartari
 *
 */
public class TrayManagerFormListFriend {
    private TrayIcon trayIcon;
    private SystemTray tray;
    private PopupMenu popup;
    private TrayManagerFormListFriendsListener listenerSysTray;
    private FormListFriends servidor;

    public TrayManagerFormListFriend(JFrame frame) {
        this.servidor = (FormListFriends) frame;
    }

    public void createTrayIcon(String mensagem, Image icon) {
        if (SystemTray.isSupported()) {
            tray = SystemTray.getSystemTray();
            trayIcon = getTrayCon(icon, mensagem);
        } else {
            System.err.println("System tray is currently not supported.");
        }

    }

    private TrayIcon getTrayCon(Image image, String mensagem) {
        if (trayIcon == null) {
            trayIcon = new TrayIcon(image, mensagem, getPopupMenu());
        }
        return trayIcon;
    }

    private TrayManagerFormListFriendsListener getListener(TrayIcon trayIcon) {
        if (listenerSysTray == null) {
            listenerSysTray = new TrayManagerFormListFriendsListener(trayIcon,
                    servidor, tray);
        }
        return listenerSysTray;
    }

    private PopupMenu getPopupMenu() {
        if (popup == null) {
            popup = new PopupMenu();
        }
        return popup;
    }

    public void criaMenu(String texto, boolean enable) {
        MenuItem defaultItem = new MenuItem(texto);
        defaultItem.setEnabled(enable);
        defaultItem.addActionListener(getListener(trayIcon));
        getPopupMenu().add(defaultItem);
    }

    public void adicionaEvento() {
        trayIcon.setImageAutoSize(true);
        trayIcon.addActionListener(getListener(trayIcon));
        trayIcon.addMouseListener(getListener(trayIcon));
    }

    public void addTrayIcon() {
        try {
            tray.add(trayIcon);
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
    }

    public void removeTrayIcon() {
        tray.remove(trayIcon);
    }

    public void refresh(Image imagem, String mensagem) {
        try {
            tray.remove(trayIcon);
            trayIcon = getTrayCon(imagem, mensagem);
            tray.add(trayIcon);
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
    }

}
