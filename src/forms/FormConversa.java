package forms;

import gerenteDeTelas.Gerente;
import interfaces.IMensageiroCliente;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.net.URL;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import util.JTextPaneI;
import ThreadsCliente.ThreadAlerta;
import ThreadsCliente.ThreadRecebeArquivo;
import acao.FormConversaListener;
import cliente.EnviaArquivo;
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
    private JLabel lblNomeContato;
    private JLabel lblNomeUsuario;
    private JTextPane txtDescritorMensagem;
    private JScrollPane scrollPaneDescritor;
    private JScrollPane scroolPanelReceptor;
    private JButton btnEnviarMensagem;
    private JButton btnPaletaCores;
    private JToggleButton btnNegrito;
    private JToggleButton btnItalico;
    private JComboBox comboTamanhofonte;
    private JComboBox comboTipoFonte;
    private FormConversaListener listener;
    private IMensageiroCliente cliente;
    private Contatos contato;
    private Gerente gerente;
    private String fontFamily;
    private SimpleAttributeSet simpleAttributeSet;
    private Boolean isBold = false;
    private Boolean isItalic = false;
    private String nomeConversa;
    private Color color;
    private Integer fontSize;
    private JFrame paletaCores;
    private JColorChooser jColorChooser;
    private JButton btnPaleta;
    private JButton btnSendFile;
    private JButton btnAlerta;
    private JFrame enviaArquivo;
    private JFileChooser fileChooser;

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
            setTitle(contato.getLogin());
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
            btnPaletaCores = newJButtonImagem("imagens/cores.png", "cores");
            btnAlerta = newJButtonImagem("imagens/atencaoNormal.png",
                    "imagens/atencaoDesabilitado.png", "alerta");
            btnSendFile = newJButtonImagem("imagens/enviarArquivo.png",
                    "imagens/enviarArquivoDesabilitado.png", "sendFile");
            comboTamanhofonte = newJComboBox(getValoresComboFont());
            comboTamanhofonte.setActionCommand("TamFonte");
            comboTipoFonte = newJComboBox(getTipoFonte());
            setSelectedItem();
            comboTipoFonte.setActionCommand("TipoFonte");
            txtDescritorMensagem.addKeyListener(listener);
            scrollPaneDescritor = newJScrollPane(txtDescritorMensagem);
            scroolPanelReceptor = newJScrollPane(txtReceptorMensagem);
            btnEnviarMensagem = newJButton("imagens/btnEnviar.png",
                    "imagens/btnEnviarpressionado.png");
            lblNomeContato = newJLabel(contato.getNome(), true);
            lblNomeUsuario = newJLabel(usuario.getNome(), false);
            adicionaTela(scroolPanelReceptor, 5, 5, 340, 200);
            adicionaTela(btnNegrito, 6, 207, 20, 20);
            adicionaTela(btnItalico, 31, 207, 20, 20);
            adicionaTela(btnPaletaCores, 56, 207, 20, 20);
            adicionaTela(btnSendFile, 81, 207, 20, 20);
            adicionaTela(btnAlerta, 106, 207, 20, 20);
            adicionaTela(comboTamanhofonte, 130, 207, 40, 20);
            adicionaTela(comboTipoFonte, 175, 207, 169, 20);
            adicionaTela(scrollPaneDescritor, 5, 230, 340, 120);
            adicionaTela(getImagemIcon(contato.getIconContato(), 100, 120),
                    360, 5, 100, 120);
            adicionaTela(lblNomeContato, 360, 130, 100, 20);
            adicionaTela(getImagemIcon(usuario.getIconUsuario(), 100, 80), 360,
                    230, 100, 80);
            adicionaTela(btnEnviarMensagem, 365, 305, 90, 50);
            txtDescritorMensagem.requestFocus();
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

    private JLabel newJLabel(String nome, Boolean isContato) {
        JLabel lbl = new JLabel(nome, JLabel.CENTER);
        if (isContato)
            lbl.setForeground(Color.RED);
        else
            lbl.setForeground(Color.BLUE);
        return lbl;
    }

    private JComboBox newJComboBox(String[] valores) {
        JComboBox combo = new JComboBox(valores);
        combo.addActionListener(listener);
        return combo;
    }

    private JToggleButton newJToggleButton(ImageIcon icon, String action) {
        JToggleButton toggle = new JToggleButton(icon);
        toggle.setActionCommand(action);
        toggle.addActionListener(listener);
        return toggle;
    }

    private void setSelectedItem() {
        Integer tamFonte = txtDescritorMensagem.getFont().getSize();
        comboTamanhofonte.setSelectedItem(tamFonte.toString());
        comboTipoFonte.setSelectedItem(txtDescritorMensagem.getFont()
                .getFamily());
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

    private JButton newJButtonImagem(String urlImagem, String actionCommand) {
        ClassLoader clazz = this.getClass().getClassLoader();
        URL res = clazz.getResource(urlImagem);
        ImageIcon icone = new ImageIcon(res);
        JButton button = new JButton();
        button.setBorderPainted(false);
        button.setSize(new Dimension(20, 20));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setIcon(icone);
        button.setActionCommand(actionCommand);
        button.addActionListener(listener);
        button.addMouseListener(listener);
        return button;
    }

    private JButton newJButtonImagem(String urlImagem, String urlImagem2,
            String actionCommand) {
        ClassLoader clazz = this.getClass().getClassLoader();
        URL res = clazz.getResource(urlImagem);
        ImageIcon icone = new ImageIcon(res);
        JButton button = new JButton();
        button.setBorderPainted(false);
        button.setSize(new Dimension(20, 20));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setIcon(icone);
        res = clazz.getResource(urlImagem2);
        icone = new ImageIcon(res);
        button.setDisabledIcon(icone);
        button.setActionCommand(actionCommand);
        button.addActionListener(listener);
        button.addMouseListener(listener);
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

    private String[] getValoresComboFont() {
        return new String[] { "8", "9", "10", "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20", "21", "22", "23", "24" };
    }

    private String[] getTipoFonte() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames();
    }

    public JTextPane getTxtDescritorMensagens() {
        return txtDescritorMensagem;
    }

    public Integer getFontSize() {
        if (fontSize == null) {
            fontSize = txtDescritorMensagem.getFont().getSize();
        }
        return fontSize;
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
        txtDescritorMensagem.requestFocus();
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

    public String getFontFamily() {
        if (fontFamily == null) {
            fontFamily = txtDescritorMensagem.getFont().getFamily();
        }
        return fontFamily;
    }

    public void setFontFamily() {
        StyleConstants.setFontFamily(simpleAttributeSet,
                (String) comboTipoFonte.getSelectedItem());
        String text = txtDescritorMensagem.getText();
        txtDescritorMensagem.setText("");
        txtDescritorMensagem.setCharacterAttributes(simpleAttributeSet, true);
        txtDescritorMensagem.setText(text);
    }

    public void setFontSize() {
        Integer size = Integer.parseInt((String) comboTamanhofonte
                .getSelectedItem());
        StyleConstants.setFontSize(simpleAttributeSet, size);
        String text = txtDescritorMensagem.getText();
        txtDescritorMensagem.setText("");
        txtDescritorMensagem.setCharacterAttributes(simpleAttributeSet, true);
        txtDescritorMensagem.setText(text);
        this.fontSize = size;
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

    public void addBorderBtnPaletaCores(JButton button) {
        if (button.getActionCommand().equals("cores")) {
            btnPaletaCores.setBorderPainted(true);
        } else if (button.getActionCommand().equals("alerta")) {
            btnAlerta.setBorderPainted(true);
        } else if (button.getActionCommand().equals("sendFile")) {
            btnSendFile.setBorderPainted(true);
        }

    }

    public void removeBorderBtnPaletaCores(JButton button) {
        if (button.getActionCommand().equals("cores")) {
            btnPaletaCores.setBorderPainted(false);
        } else if (button.getActionCommand().equals("alerta")) {
            btnAlerta.setBorderPainted(false);
        } else if (button.getActionCommand().equals("sendFile")) {
            btnSendFile.setBorderPainted(false);
        }
    }

    public void instanciaPaletaCores() {
        btnPaletaCores.setEnabled(false);
        getPaletaCores().setVisible(true);
    }

    public void instanciaEnviaArquivo() {
        btnSendFile.setEnabled(false);
        getEnviaArquivo().setVisible(true);
    }

    public void fechaPaletaCores() {
        txtDescritorMensagem.requestFocus();
        paletaCores.dispose();
        jColorChooser = null;
        btnPaleta = null;
    }

    public void fechaEnviaArquivo() {
        txtDescritorMensagem.requestFocus();
        enviaArquivo.dispose();
        fileChooser = null;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    public JFrame getPaleta() {
        return paletaCores;
    }

    public JFrame getPaletaCores() {
        paletaCores = new JFrame();
        paletaCores.setDefaultCloseOperation(ICONIFIED);
        paletaCores.setExtendedState(NORMAL);
        paletaCores.setSize(310, 335);
        paletaCores.setContentPane(new Container());
        paletaCores.setLocationRelativeTo(btnPaletaCores);
        jColorChooser = new JColorChooser();
        jColorChooser.setBounds(5, 5, 300, 300);
        btnPaleta = new JButton("OK");
        btnPaleta.addActionListener(listener);
        btnPaleta.setActionCommand("btnPaleta");
        btnPaleta.setBounds(125, 310, 60, 20);
        jColorChooser.requestFocus();
        paletaCores.add(jColorChooser);
        paletaCores.add(btnPaleta);
        paletaCores.setUndecorated(true);
        return paletaCores;
    }

    public JFrame getEnviaArquivo() {
        enviaArquivo = new JFrame();
        enviaArquivo.setDefaultCloseOperation(ICONIFIED);
        enviaArquivo.setExtendedState(NORMAL);
        enviaArquivo.setSize(510, 300);
        enviaArquivo.setContentPane(new Container());
        enviaArquivo.setLocationRelativeTo(txtReceptorMensagem);
        fileChooser = new JFileChooser();
        fileChooser.setBounds(0, 0, 510, 300);
        fileChooser.addActionListener(listener);
        fileChooser.requestFocus();
        enviaArquivo.add(fileChooser);
        enviaArquivo.setUndecorated(true);
        return enviaArquivo;
    }

    public JButton getBtnSendFile() {
        return btnSendFile;
    }

    public JColorChooser getJColorChooser() {
        return jColorChooser;
    }

    public JButton getBtnAlerta() {
        return btnAlerta;
    }

    public void recebeMensagem(Mensagem mensagem) {
        try {
            txtReceptorMensagem.append(mensagem, isContato(mensagem
                    .getUsuarioEnvia()));
            txtDescritorMensagem.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setNomeConversa(String nomeConversa) {
        this.nomeConversa = nomeConversa;
    }

    public Color getColor() {
        if (color == null) {
            color = Color.BLACK;
        }
        return color;
    }

    public void setColor(Color color) {
        StyleConstants.setForeground(simpleAttributeSet, color);
        String text = txtDescritorMensagem.getText();
        txtDescritorMensagem.setText("");
        txtDescritorMensagem.setCharacterAttributes(simpleAttributeSet, true);
        txtDescritorMensagem.setText(text);
        this.color = color;
    }

    public void recebeAviso(EnviaArquivo arquivo) {
        Mensagem m = new Mensagem(arquivo.getContatoEnvia().getLogin(), arquivo
                .getContatoEnvia().getNome(), arquivo.getRetorno(), "",
                getFontSize(), getFontFamily(), getColor(), getIsBold(),
                getIsItalic(), arquivo.getContatoRecebe().getNome());
        txtReceptorMensagem.append(m, true);
        btnSendFile.setEnabled(true);

    }

    public void avisaArquivoRecebido(EnviaArquivo arquivo) {
        Mensagem m = new Mensagem(arquivo.getContatoEnvia().getLogin(), arquivo
                .getContatoEnvia().getNome(),
                "Arquivo recebido com sucesso no caminho : C:\\MsMundica\\"
                        + arquivo.getNomeArquivo(), "", getFontSize(),
                getFontFamily(), getColor(), getIsBold(), getIsItalic(),
                arquivo.getContatoRecebe().getNome());
        txtReceptorMensagem.append(m, false);

    }

    public void recebeArquivo(EnviaArquivo arquivo) {
        new ThreadRecebeArquivo(arquivo, this, cliente).start();
    }

    public void chamarAtencao(Mensagem mensagem) {
        gerente.chamarAtencao(mensagem, getContato());

    }

    public void disparaThread() {
        this.setExtendedState(NORMAL);
        new ThreadAlerta(this).start();
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
        this.btnSendFile.setEnabled(false);
        this.btnAlerta.setEnabled(false);
    }

    public Boolean isContato(String login) {
        try {
            return !cliente.getContatos().getLogin().equals(login);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JToggleButton getBtnNegrito() {
        return btnNegrito;
    }

    public JToggleButton getBtnItalico() {
        return btnItalico;
    }
    //
    // /**
    // * TO REMOVE
    // */
    // public static void main(String[] args) {
    // FormConversa conversa = new FormConversa();
    // conversa.config();
    // conversa.inicializar(null, null);
    // conversa.renderiza();
    // }
    //
    // public FormConversa() {
    // listener = new FormConversaListener(this, null);
    // }
    //
    // /**
    // * END TO REMOVE
    // */

}
