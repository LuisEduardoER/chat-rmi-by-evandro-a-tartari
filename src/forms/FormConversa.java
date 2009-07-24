package forms;

import interfaces.IMensageiroCliente;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;

import util.JTextPaneI;
import acao.FormConversaListener;

public class FormConversa extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 6449977848107919009L;
    private JTextPaneI txtReceptorMensagem;
    private JTextPane txtDescritorMensagem;
    private JScrollPane scrollPaneDescritor;
    private JScrollPane scroolPanelReceptor;
    private JButton btnEnviarMensagem;
    private FormConversaListener listener;
    private IMensageiroCliente cliente;

    public FormConversa() {
        listener = new FormConversaListener(this);
    }

    /**
     * Inicializa o componente
     */
    public void inicializar(String urlImagemContato, String urlImagemUsuario) {
        /**
         * 
         */
        urlImagemContato = "imagens/boi.gif";
        urlImagemUsuario = urlImagemContato;
        /**
         * 
         */
        try {
            txtReceptorMensagem = newJTextAreaA();
            txtReceptorMensagem.setEditable(false);
            txtDescritorMensagem = newJTextPane();
            txtDescritorMensagem.addKeyListener(listener);
            scrollPaneDescritor = newJScrollPane(txtDescritorMensagem);
            scroolPanelReceptor = newJScrollPane(txtReceptorMensagem);
            btnEnviarMensagem = newJButton("imagens/btnEnviar.png",
                    "imagens/btnEnviarpressionado.png");
            adicionaTela(scroolPanelReceptor, 5, 5, 340, 220);
            adicionaTela(scrollPaneDescritor, 5, 230, 340, 120);
            adicionaTela(getImagemIcon(urlImagemContato, 100, 120), 360, 5,
                    100, 120);
            adicionaTela(getImagemIcon(urlImagemUsuario, 100, 80), 360, 230,
                    100, 80);
            adicionaTela(btnEnviarMensagem, 365, 305, 90, 50);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JTextPaneI newJTextAreaA() {
        JTextPaneI area = new JTextPaneI();
        return area;
    }

    /**
     * configuracao do JFrame
     */
    public void config() {
        setTitle("Titulo a colocar");
        setSize(480, 390);
        setIconImage(getIcon());
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(new Container());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(NORMAL);
    }

    /**
     * renderizar componentes da tela
     */
    public void renderiza() {
        setVisible(true);
    }

    /**
     * Adiciona Componentes na Tela
     * 
     * @param c
     * @param x
     * @param y
     * @param size
     * @param alt
     */
    public void adicionaTela(JComponent c, int x, int y, int size, int alt) {
        c.setBounds(x, y, size, alt);
        getContentPane().add(c);
    }

    private JTextPane newJTextPane() {
        return new JTextPane();
    }

    private JScrollPane newJScrollPane(JComponent c) {
        JScrollPane painel = new JScrollPane(c);
        painel
                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        painel
                .setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return painel;
    }

    /**
     * Instancia um JButton
     * 
     * @param text
     * @return
     */
    private JButton newJButton(String urlImagem, String urlImagem2) {
        ClassLoader clazz = this.getClass().getClassLoader();
        URL res = clazz.getResource(urlImagem);
        ImageIcon icone = new ImageIcon(res);
        JButton button = new JButton();
        button.setBorderPainted(false);
        button.setSize(new Dimension(100, 50));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setIcon(icone);
        URL res2 = clazz.getResource(urlImagem2);
        button.setPressedIcon(new ImageIcon(res2));
        button.setActionCommand("enviar");
        button.addActionListener(listener);
        return button;
    }

    private JButton getImagemIcon(String urlImagem, int width, int heigth) {
        ClassLoader clazz = this.getClass().getClassLoader();
        URL res = clazz.getResource(urlImagem);
        ImageIcon icon = new ImageIcon(res);
        JButton button = new JButton();
        Border border = BorderFactory.createCompoundBorder();
        button.setBorder(border);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setMaximumSize(new Dimension(width, heigth));
        button.setIcon(icon);
        return button;
    }

    /**
     * Cria Uma imagemIcon
     * 
     * @return
     */
    private Image getIcon() {
        ClassLoader clazz = this.getClass().getClassLoader();
        URL res = clazz.getResource("imagens/serverRunning.png");
        ImageIcon icon = new ImageIcon(res);
        return icon.getImage();
    }

    public JTextPane getTxtDescritorMensagens() {
        return txtDescritorMensagem;
    }

    public JTextPaneI getTxtReceptorMensagens() {
        return txtReceptorMensagem;
    }

    public void setCliente(IMensageiroCliente cliente) {
        this.cliente = cliente;
    }

    public IMensageiroCliente getCliente() {
        return this.cliente;
    }

    public static void main(String[] args) {
        FormConversa conversa = new FormConversa();
        conversa.config();
        conversa.inicializar("", "");
        conversa.renderiza();
    }

}
