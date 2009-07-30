package forms;

import gerenteDeTelas.Gerente;
import interfaces.IMensageiroCliente;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import util.JTextPaneI;
import acao.FormConversaListener;
import cliente.Mensagem;
import contatos.Contatos;

/**
 * 
 * @author evandro.tartari
 * 
 */
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
    private JToggleButton btnNegrito;
    private JToggleButton btnItalico;
    private JComboBox comboTamanhofonte;
    private JComboBox comboTipoFonte;
    private FormConversaListener listener;
    private IMensageiroCliente cliente;
    private Contatos contato;
    private Gerente gerente;
    private Font font;
    private SimpleAttributeSet simpleAttributeSet;
    private Boolean isBold = false;
    private Boolean isItalic = false;
    private String nomeConversa;

    public FormConversa(Gerente gerente, IMensageiroCliente cliente) {
        this.gerente = gerente;
        this.cliente = cliente;

        listener = new FormConversaListener(this, this.gerente);
    }

    /**
     * Inicializa o componente
     */
    public void inicializar(Contatos contato, Contatos usuario) {
        try {
//            setTitle(contato.getLogin());
            txtReceptorMensagem = newJTextAreaA();
            txtReceptorMensagem.setEditable(false);
            txtDescritorMensagem = newJTextPane();
            simpleAttributeSet = new SimpleAttributeSet();
            txtDescritorMensagem.setCharacterAttributes(simpleAttributeSet,
                    true);
            btnNegrito = newJToggleButton(getImageIcon("imagens/negrito.png"),
                    "Negrito");
            btnItalico = newJToggleButton(getImageIcon("imagens/italico.png"),
                    "Italico");
            comboTamanhofonte = newJComboBox("Fonte", getValoresComboFont());
            comboTipoFonte = newJComboBox("TipoFonte", getTipoFonte());
            setFont(txtDescritorMensagem.getFont());
            txtDescritorMensagem.addKeyListener(listener);
            scrollPaneDescritor = newJScrollPane(txtDescritorMensagem);
            scroolPanelReceptor = newJScrollPane(txtReceptorMensagem);
            btnEnviarMensagem = newJButton("imagens/btnEnviar.png",
                    "imagens/btnEnviarpressionado.png");
            adicionaTela(scroolPanelReceptor, 5, 5, 340, 200);
            adicionaTela(btnNegrito, 50, 207, 20, 20);
            adicionaTela(btnItalico, 80, 207, 20, 20);
            adicionaTela(comboTamanhofonte, 110, 207, 40, 20);
            adicionaTela(comboTipoFonte, 155, 207, 110, 20);
            adicionaTela(scrollPaneDescritor, 5, 230, 340, 120);
//            adicionaTela(getImagemIcon(contato.getImage(), 100, 120), 360, 5,
//                    100, 120);
//            adicionaTela(getImagemIcon(usuario.getImage(), 100, 80), 360, 230,
//                    100, 80);
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
        try {
            setSize(480, 390);
            setIconImage(getIcon());
            setLocationRelativeTo(null);
            setResizable(false);
            setContentPane(new Container());
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            setExtendedState(NORMAL);
            addWindowListener(listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    
    private JComboBox newJComboBox(String action, String[] valores){
        JComboBox combo = new JComboBox(valores);
        combo.setActionCommand("Fonte");
        combo.addActionListener(listener);
        return combo;
    }

    private JToggleButton newJToggleButton(ImageIcon icon, String action) {
        JToggleButton toggle = new JToggleButton(icon);
        toggle.setActionCommand(action);
        toggle.addActionListener(listener);
        return toggle;
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

    private JButton getImagemIcon(ImageIcon imagem, int width, int heigth) {
        ImageIcon icon = imagem;
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

    private ImageIcon getImageIcon(String urlImage) {
        ClassLoader clazz = this.getClass().getClassLoader();
        URL res = clazz.getResource(urlImage);
        ImageIcon icon = new ImageIcon(res);
        return icon;
    }
    
    private String[] getValoresComboFont(){
        return new String[]{"8", "9", "11", "12", "13", "14", 
        "15","16", "17", "18", "19", "20", "21", "22", "23", 
        "24"};
    }
    
    private String[] getTipoFonte(){
        return new String[]{"Arial", "Times New Roman"};
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

    public void setContato(Contatos contato) {
        this.contato = contato;
    }

    public Contatos getContato() {
        return contato;
    }

    public Boolean getIsBold() {
        return isBold;
    }

    public Boolean getIsItalic() {
        return isItalic;
    }

    public void setFont(Font font) {
        StyleConstants.setFontSize(simpleAttributeSet, font.getSize());
        String text = txtDescritorMensagem.getText();
        txtDescritorMensagem.setText("");
        txtDescritorMensagem.setCharacterAttributes(simpleAttributeSet, true);
        txtDescritorMensagem.setText(text);
        this.font = font;
    }

    public Font getFont() {
        return font;
    }

    public void setIsBold(Boolean isBold) {
        StyleConstants.setBold(simpleAttributeSet, isBold);
        String text = txtDescritorMensagem.getText();
        txtDescritorMensagem.setText("");
        txtDescritorMensagem.setCharacterAttributes(simpleAttributeSet, true);
        txtDescritorMensagem.setText(text);
        this.isBold = isBold;
    }

    public void setIsItalic(Boolean isItalic) {
        StyleConstants.setItalic(simpleAttributeSet, isItalic);
        String text = txtDescritorMensagem.getText();
        txtDescritorMensagem.setText("");
        txtDescritorMensagem.setCharacterAttributes(simpleAttributeSet, true);
        txtDescritorMensagem.setText(text);
        this.isItalic = isItalic;
    }

    public void recebeMensagem(Mensagem mensagem) {
        try {
            if (this.cliente.getContatos().getLogin().equals(
                    mensagem.getUsuarioEnvia())) {
                txtReceptorMensagem.append(mensagem.getNomeEnvia() + ": ",
                        Color.BLUE, mensagem.getIsBold(), mensagem
                                .getIsItalic());
                txtReceptorMensagem.append(mensagem.getMensagem() + "\n",
                        Color.BLUE, mensagem.getIsBold(), mensagem
                                .getIsItalic());
            } else {
                txtReceptorMensagem
                        .append(mensagem.getNomeEnvia() + ": ", Color.RED,
                                mensagem.getIsBold(), mensagem.getIsItalic());
                txtReceptorMensagem
                        .append(mensagem.getMensagem() + "\n", Color.RED,
                                mensagem.getIsBold(), mensagem.getIsItalic());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setNomeConversa(String nomeConversa) {
        this.nomeConversa = nomeConversa;
    }

    public String getNomeConversa() {
        return nomeConversa;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((nomeConversa == null) ? 0 : nomeConversa.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FormConversa other = (FormConversa) obj;
        if (nomeConversa == null) {
            if (other.nomeConversa != null)
                return false;
        } else if (!nomeConversa.equals(other.nomeConversa))
            return false;
        return true;
    }

    public void disableChat() {
        this.txtDescritorMensagem.setEnabled(false);
        this.btnEnviarMensagem.setEnabled(false);

    }

    public JToggleButton getBtnNegrito() {
        return btnNegrito;
    }

    public JToggleButton getBtnItalico() {
        return btnItalico;
    }

     /**
     * TO REMOVE
     */
     public static void main(String[] args) {
     FormConversa conversa = new FormConversa();
     conversa.config();
     conversa.inicializar(null, null);
     conversa.renderiza();
     }
    
     public FormConversa() {
     listener = new FormConversaListener(this, null);
     }
}
