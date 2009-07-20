package servidor.trayicon;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import servidor.forms.FormServidor;

public class TrayManager {

    private TrayIcon trayIcon;
    private SystemTray tray;
    private PopupMenu popup;
    private SysTrayImpls listenerSysTray;
    private FormServidor servidor;

    public TrayManager(JFrame frame) {
        this.servidor = (FormServidor) frame;
    }

    public void createTrayIcon(String mensagem) {
        if (SystemTray.isSupported()) {
            tray = SystemTray.getSystemTray();
            Image image = getIcon();
            trayIcon = getTrayCon(image, mensagem);
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

    private SysTrayImpls getListener(TrayIcon trayIcon) {
        if (listenerSysTray == null) {
            listenerSysTray = new SysTrayImpls(trayIcon, servidor);
        }
        return listenerSysTray;
    }

    private PopupMenu getPopupMenu() {
        if (popup == null) {
            popup = new PopupMenu();
        }
        return popup;
    }

    public void criaMenu(String texto) {
        MenuItem defaultItem = new MenuItem(texto);
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
    
    public void removeTrayIcon(){
        tray.remove(trayIcon);
    }

    private Image getIcon() {
        ClassLoader clazz = this.getClass().getClassLoader();
        URL res = clazz.getResource("imagens/imageIcon.png");
        ImageIcon icon = new ImageIcon(res);
        return icon.getImage();
    }

}
