package forms;

import java.awt.Container;
import java.awt.Dimension;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.border.Border;

import org.jvnet.substance.SubstanceDefaultLookAndFeel;

import acao.FormConversaListener;

public class FormConversa extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 6449977848107919009L;
    private JTextPane txtReceptorMensagem;
    private JTextPane txtDescritorMensagem;
    private JScrollPane scrollPaneDescritor;
    private JScrollPane scroolPanelReceptor;
    private JButton btnEnviarMensagem;
    private FormConversaListener listener;

    public FormConversa() {
        listener = new FormConversaListener(this);
    }

    /**
     * Inicializa o componente
     */
    public void inicializar(String urlImagemContato, String urlImagemUsuario) {
        txtReceptorMensagem = newJTextPane();
        txtReceptorMensagem.setEditable(false);
        txtDescritorMensagem = newJTextPane();
        txtDescritorMensagem.addKeyListener(listener);
        txtReceptorMensagem.setContentType("text/html");
        scrollPaneDescritor = newJScrollPane(txtDescritorMensagem);
        scroolPanelReceptor = newJScrollPane(txtReceptorMensagem);
        btnEnviarMensagem = newJButton("imagens/btnEnviar.png",
                "imagens/btnEnviarpressionado.png");
        adicionaTela(scroolPanelReceptor, 5, 5, 340, 220);
        adicionaTela(scrollPaneDescritor, 5, 230, 340, 120);
        adicionaTela(getImagemIcon(urlImagemContato, 100, 120), 360, 5, 100,
                120);
        adicionaTela(getImagemIcon(urlImagemUsuario, 100, 80), 360, 230, 100,
                80);
        adicionaTela(btnEnviarMensagem, 365, 305, 90, 50);
    }

    /**
     * configuracao do JFrame
     */
    public void config() {
        setTitle("Titulo a colocar");
        setSize(480, 390);
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

    /**
     * Instancia JTextArea
     * 
     * @param rows
     * @param columns
     * @return
     */
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

    public JTextPane getTxtDescritorMensagens() {
        return txtDescritorMensagem;
    }

    public JTextPane getTxtReceptorMensagens() {
        return txtReceptorMensagem;
    }

    /**
     * Main/ETC TO REMOVE
     * 
     * @param args
     */
    public static void main(String[] args) {
        FormConversa conversa = new FormConversa();
        conversa.setLookAndFeel();
        conversa.config();
        conversa.inicializar("imagens/teste.png", "imagens/teste.png");
        conversa.renderiza();
    }

    private void setLookAndFeel() {
        try {
            UIManager
                    .setLookAndFeel((LookAndFeel) new SubstanceDefaultLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * END TO REMOVE
     */

}
