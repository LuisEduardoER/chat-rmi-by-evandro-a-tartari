package servidor.forms;

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

import servidor.acao.AcaoFormServidor;

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
    public void inicializar() {
        setTitle("Servidor");
        setIconImage(getIcon());
        setSize(200, 150);
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
    }

    private void renderizaTela() {
        setVisible(true);
        txtPortaServidor.requestFocus();
    }

    public static void main(String[] args) throws Exception {
        FormServidor form = new FormServidor();
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

    public Image getIcon(){
        ClassLoader clazz = this.getClass().getClassLoader();
        URL res = clazz.getResource("imagens/imageIcon.png");
        ImageIcon icon = new ImageIcon(res);
        return icon.getImage();
    }
}
