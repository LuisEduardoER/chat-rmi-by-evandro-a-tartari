package servidor.acao;

import interfaces.IMensageiroServer;

import java.awt.Color;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;

import javax.swing.JFrame;

import servidor.MensageiroServerImpl;
import servidor.forms.FormServidor;

public class AcaoFormServidor implements ActionListener, KeyListener , WindowListener {
    private FormServidor servidor;
    private IMensageiroServer servico;

    public AcaoFormServidor(JFrame servidor) {
        this.servidor = (FormServidor) servidor;
    }

    /**
     * ActionListener
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Inicia")) {
            if (isValid()) {
                try {
                    servico = (IMensageiroServer) new MensageiroServerImpl(
                            Integer.parseInt(servidor.getTxtPortaServidor()
                                    .getText()));
                    Integer porta = Integer.parseInt(servidor
                            .getTxtPortaServidor().getText());
                    servico.inicializar(porta);
                    servidor.getLblResposta().setText(
                            "Server is running : " + porta.toString());
                    servidor.getBtnInicializar().setEnabled(false);
                } catch (RemoteException e1) {
                    servidor.getLblResposta()
                            .setText("Erro Iniciando Servidor");
                    servidor = null;
                }
            }
        } else if (e.getActionCommand().equals("Fechar")) {
            servidor.dispose();
            System.exit(0);
        }
    }

    /**
     * KeyListener
     */
    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == Event.ENTER) {
            if (e.getModifiers() == 0) {
                if (isValid()) {

                    try {
                        servico = (IMensageiroServer) new MensageiroServerImpl(
                                Integer.parseInt(servidor.getTxtPortaServidor()
                                        .getText()));
                        Integer porta = Integer.parseInt(servidor
                                .getTxtPortaServidor().getText());
                        servico.inicializar(porta);
                        servidor.getLblResposta().setText(
                                "Server is running : " + porta.toString());
                        servidor.getBtnInicializar().setEnabled(false);

                    } catch (RemoteException e1) {
                        servidor.getLblResposta().setText(
                                "Erro Iniciando Servidor");
                        servidor = null;
                    }
                }
            }
        } else if (e.getKeyCode() == Event.ESCAPE) {
            if (e.getModifiers() == 0) {
                servidor.dispose();
                System.exit(0);
            }
        } else {
            servidor.getTxtPortaServidor().setBackground(Color.white);
            servidor.getLblResposta().setText("");
            servidor.getLblVPortaServidor().setText("");
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    private boolean isValid() {
        if (!isValidEmpyt()) {
            String strNumero = servidor.getTxtPortaServidor().getText();
            try {
                Integer.parseInt(strNumero);
                return true;
            } catch (Exception e) {
                servidor.getLblResposta().setText("*Campo Numerico");
                servidor.getTxtPortaServidor().setBackground(
                        Color.getHSBColor(80, 50, 25));
                servidor.getLblVPortaServidor().setText("*");
                servidor.getTxtPortaServidor().requestFocus();
                return false;
            }
        } else {
            servidor.getLblResposta().setText("*Campo obrigatorio");
            servidor.getTxtPortaServidor().setBackground(
                    Color.getHSBColor(80, 50, 25));
            servidor.getLblVPortaServidor().setText("*");
            servidor.getTxtPortaServidor().requestFocus();
            return false;
        }

    }

    private boolean isValidEmpyt() {
        return servidor.getTxtPortaServidor().getText().equals("");
    }
    
    /**
     * WindowsListener
     */
    public void windowActivated(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
        
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
        servidor.createTrayIcon();
        servidor.setVisible(false);
    }

    public void windowOpened(WindowEvent e) {
    }


}
