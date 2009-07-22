package forms;

import java.awt.Container;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FormConversa extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 6449977848107919009L;
    private JTextArea txtReceptorMensagem;
    private JTextArea txtDescritorMensagem;
    private JPanel painelFotoUsuario;
    private JPanel painelFotoContato;
    private JButton btnEnviarMensagem;
    

    /**
     * Inicializa o componente
     */
    public void inicializar() {
        txtReceptorMensagem = newJTextArea(10, 48);
        txtReceptorMensagem.setEditable(false);
        txtDescritorMensagem = new JTextArea(10, 48);
        painelFotoUsuario = newJPanel();
        painelFotoContato = newJPanel();
        btnEnviarMensagem = newJButton("imagens/btnEnviar.png",
                "imagens/btnEnviarpressionado.png");
        adicionaTela(txtReceptorMensagem, 5, 5, 340, 220);
        adicionaTela(txtDescritorMensagem,5, 230, 340, 120);
        adicionaTela(painelFotoContato, 360, 5, 100, 120);
        adicionaTela(painelFotoUsuario, 360, 230, 100, 80);
        adicionaTela(btnEnviarMensagem, 370, 305, 70, 60);
    }

    /**
     * configuracao do JFrame
     */
    public void config() {
        setTitle("Titulo a colocar");
        setSize(480, 390);
        setLocationRelativeTo(null);
        setContentPane(new Container());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    private JTextArea newJTextArea(int rows, int columns) {
        return new JTextArea("", rows, columns);
    }

    /**
     * Instancia um JPanel
     * 
     * @return
     */
    private JPanel newJPanel() {
        return new JPanel();
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
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setIcon(icone);
        URL res2 = clazz.getResource(urlImagem2);
        button.setPressedIcon(new ImageIcon(res2));
        return button;
    }

  
}
