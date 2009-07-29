package servidor.trayicon;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;

import javax.swing.JFrame;

import servidor.forms.FormServidor;
/**
 * 
 * @author evandro.tartari
 *
 */
public class TrayManager {

    private TrayIcon trayIcon;
    private SystemTray tray;
    private PopupMenu popup;
    private SysTrayImpls listenerSysTray;
    private FormServidor servidor;

    public TrayManager(JFrame frame) {
        this.servidor = (FormServidor) frame;
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

    private SysTrayImpls getListener(TrayIcon trayIcon) {
        if (listenerSysTray == null) {
            listenerSysTray = new SysTrayImpls(trayIcon, servidor, tray);
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
    
    public void inabilitaMenuRun(){
        popup.getItem(3).setEnabled(false);
        popup.getItem(2).setEnabled(true);
    }

    public void habilitaMenuRun() {
        popup.getItem(3).setEnabled(true);
        
    }
    

}
