package servidor.forms;

import interfaces.IMensageiroServer;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.net.InetAddress;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import org.jvnet.substance.SubstanceDefaultLookAndFeel;

import servidor.MensageiroServerImpl;
import servidor.acao.AcaoFormServidor;
import servidor.trayicon.TrayManager;
import util.NumeroMaximoCaracteres;

/**
 * 
 * @author evandro.tartari
 * 
 */
public class FormServidor extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1695317070736526919L;
    private JLabel lblIpServidor = new JLabel("Ip:");
    private JLabel lblPortaServidor = new JLabel("Porta:");
    private JTextField txtIpServidor = new JTextField();
    private JTextField txtPortaServidor = new JTextField();
    private JButton btnInicializar = new JButton("Inicia");
    private JButton btnFechar = new JButton("Fechar");
    private JLabel lblResposta = new JLabel("");
    private JLabel lblVPortaServidor = new JLabel("");
    private IMensageiroServer servico;
    private String urlImagem = "imagens/serverNotRunning.png";
    private ImageIcon icon;
    private String mensagem = "Server not Running";
    private Boolean activeStop = false;
    private Boolean activeRun = true;
    private TrayManager manager;

    public void inicializar() {
        setTitle("Servidor");
        setIconImage(getIcon());
        setSize(190, 115);
        setUndecorated(true);
        setContentPane(new Container());
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void adicionaComponentes() throws Exception {
        adiciona(lblIpServidor, 25, 5, 40, 25);
        txtIpServidor.setEditable(false);
        txtIpServidor.setText(InetAddress.getLocalHost().getHostAddress());
        adiciona(txtIpServidor, 70, 5, 100, 25);
        adiciona(lblPortaServidor, 25, 35, 40, 25);
        txtPortaServidor.setDocument(new NumeroMaximoCaracteres(6));
        adiciona(txtPortaServidor, 70, 35, 100, 25);
        adiciona(btnInicializar, 100, 90, 80, 18);
        adiciona(btnFechar, 10, 90, 80, 18);
        lblResposta.setForeground(Color.RED);
        adiciona(lblResposta, 20, 65, 180, 25);
        lblVPortaServidor.setForeground(Color.RED);
        adiciona(lblVPortaServidor, 172, 35, 10, 20);
    }

    private void adiciona(JComponent comp, Integer x, Integer y, Integer size,
            Integer altura) {
        comp.setBounds(x, y, size, altura);
        add(comp);
    }

    private void adicionaListeners() {
        AcaoFormServidor acao = new AcaoFormServidor(this);
        txtPortaServidor.addKeyListener(acao);
        btnFechar.addActionListener(acao);
        btnInicializar.addActionListener(acao);
        addWindowListener(acao);
    }

    private void renderizaTela() {
        setVisible(true);
        txtPortaServidor.requestFocus();
    }

    public static void main(String[] args) throws Exception {
        FormServidor form = new FormServidor();
        form.setLookAndFeel();
        form.inicializar();
        form.adicionaComponentes();
        form.adicionaListeners();
        form.renderizaTela();
    }

    public JLabel getLblResposta() {
        return lblResposta;
    }

    public JTextField getTxtPortaServidor() {
        return txtPortaServidor;
    }

    public JLabel getLblVPortaServidor() {
        return lblVPortaServidor;
    }

    public JButton getBtnInicializar() {
        return btnInicializar;
    }

    public Image getIcon() {
        ClassLoader clazz = this.getClass().getClassLoader();
        URL res = clazz.getResource(urlImagem);
        icon = new ImageIcon(res);
        return icon.getImage();
    }

    public void createTrayIcon() {
        if (manager == null) {
            manager = new TrayManager(this);
            manager.createTrayIcon("Mensageiro Server is Running", getIcon());
            manager.criaMenu("Exit", true);
            manager.criaMenu("Abrir", true);
            manager.criaMenu("Stop", getActiveStop());
            manager.criaMenu("Run", getActiveRun());
            manager.adicionaEvento();
            manager.addTrayIcon();
        } else {
            manager.refresh(icon.getImage(), mensagem);
        }
    }

    public void refreshIcon(String urlImagem) {
        if (manager != null)
            manager = null;
        this.urlImagem = urlImagem;
        if (urlImagem.equals("imagens/serverRunning.png")) {
            mensagem = "Server is Running";
        } else if (urlImagem.equals("imagens/serverNotRunning.png")) {
            mensagem = "Server not is Running";
        }
        ClassLoader clazz = this.getClass().getClassLoader();
        URL res = clazz.getResource(urlImagem);
        icon = new ImageIcon(res);
        setIconImage(icon.getImage());
    }

    public void setManager(TrayManager manager) {
        this.manager = manager;
    }

    public TrayManager getManager() {
        return manager;
    }

    public void parar() {
        try {
            servico.parar();
            getLblResposta().setText("Server stoped");
            btnInicializar.setEnabled(true);
        } catch (Exception e) {
            getLblResposta().setText("Erro parando o servico");
            e.printStackTrace();
        }
    }

    public void setServico(IMensageiroServer servico) {
        this.servico = servico;
    }

    public void setLookAndFeel() {
        try {
            UIManager
                    .setLookAndFeel((LookAndFeel) new SubstanceDefaultLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clean() {
        try {
            servico.clean();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void inabilitarMenuRun() {
        try {
            if(servico==null&&txtPortaServidor.getText().equals("")){
               servico = (IMensageiroServer) new MensageiroServerImpl(8080);
               servico.inicializar(8080);
               btnInicializar.setEnabled(false);
               txtPortaServidor.setEditable(false);
               lblResposta.setText("Server is running : " + 8080);
               manager.inabilitaMenuRun();
            }else if(servico==null && !txtPortaServidor.getText().equals("")){
                Integer porta = Integer.parseInt(txtPortaServidor.getText());
                servico = (IMensageiroServer) new MensageiroServerImpl(porta);
                servico.inicializar(porta);
                btnInicializar.setEnabled(false);
                txtPortaServidor.setEditable(false);
                lblResposta.setText("Server is running : " + porta.toString());
                manager.inabilitaMenuRun();
            }else{
                if(txtPortaServidor.getText().equals("")){
                    servico.clean();
                    servico.inicializar(8080);
                    txtPortaServidor.setEditable(false);
                    btnInicializar.setEnabled(false);
                    lblResposta.setText("Server is running : " + 8080);
                    manager.inabilitaMenuRun();
                }else{
                    Integer porta = Integer.parseInt(txtPortaServidor.getText());
                    servico.clean();
                    servico.inicializar(porta);
                    txtPortaServidor.setEditable(false);
                    btnInicializar.setEnabled(false);
                    lblResposta.setText("Server is running : " + porta.toString());
                    manager.inabilitaMenuRun();
                }
            }
        } catch (Exception e) {
            lblResposta.setText(e.getMessage());
        }
    }

    public void habilitaMenuRun() {
        manager.habilitaMenuRun();

    }

    public void setActiveStop(Boolean activeStop) {
        this.activeStop = activeStop;
    }

    public Boolean getActiveStop() {
        return activeStop;
    }

    public void setActiveRun(Boolean activeRun) {
        this.activeRun = activeRun;
    }

    public Boolean getActiveRun() {
        return activeRun;
    }

}
