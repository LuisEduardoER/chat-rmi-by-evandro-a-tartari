package forms;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import org.jvnet.substance.SubstanceDefaultLookAndFeel;

import util.NumeroMaximoCaracteres;
import util.RedimencionaImagemIcon;
import acao.FormConnectListener;
import excessao.FormConnectException;
import gerenteDeTelas.Gerente;

/**
 * 
 * @author evandro.tartari
 * 
 */
public class FormConnect extends JFrame {
    // serialização
    private static final long serialVersionUID = 7662142711284402861L;
    // Labels
    private JLabel lblIpServidor;
    private JLabel lblPortaServico;
    private JLabel lblPortaCliente;
    private JLabel lblLogin;
    private JLabel lblSenha;
    private JLabel lblSkin;

    // TextFields e combobox
    private JTextField txtIpServidor;
    private JTextField txtPortaServico;
    private JTextField txtPortaCliente;
    private JTextField txtLogin;
    private JTextField txtNome;
    private JComboBox comboSkin;
    private JComboBox imagem;
    // Botoes
    private JButton btnConnectar;
    private JButton btnCancelar;
    private JButton btnFileChooser;

    // Excessao
    private FormConnectException excessao;
    private JLabel lblResposta;
    private JLabel lblVIpServ;
    private JLabel lblVPortServ;
    private JLabel lblVPortaClie;
    private JLabel lblVLog;
    private JLabel lblVSenha;

    // Gerente de Telas
    private Gerente gerente;

    // Lista dos validadores
    private List<JLabel> lblLista;
    private ImageIcon iconContatos;
    private String urlImagem;
    private ImageIcon iconUsuario;

    /**
     * Formulario Responsavel pela conexão que será efetuada entre o cliente e o
     * server em rmi
     * 
     * @param gerente
     */
    public FormConnect(Gerente gerente) {
        this.gerente = gerente;
    }

    /**
     * inicialização dos componentes
     */
    public void inicializar() {
        // Labels
        newJLabel(lblIpServidor, "Ip servidor:", 5);
        newJLabel(lblPortaServico, "Porta servico:", 35);
        newJLabel(lblPortaCliente, "Porta resposta:", 65);
        newJLabel(lblLogin, "Login:", 95);
        newJLabel(lblSenha, "Nome:", 125);
        newJLabel(lblSkin, "Skin:", 155);
        lblResposta = newJLabel(25, 215, 180, Color.RED);
        lblResposta.setHorizontalAlignment(JLabel.CENTER);
        // Fields
        txtIpServidor = newJTextField(100, 5, 0);
        txtPortaServico = newJTextField(100, 35, 1);
        txtPortaCliente = newJTextField(100, 65, 2);
        txtLogin = newJTextField(100, 95, 3);
        txtNome = newJTextField(100, 125, 4);
        txtNome.setDocument(new NumeroMaximoCaracteres(23));
        txtNome.setToolTipText("Numero maximo de caracteres nome '15'");
        comboSkin = newJCombobox(100, 157);
        btnCancelar = newJButton("Fechar", 15, 195);
        btnConnectar = newJButton("Connectar", 130, 195);
        btnFileChooser = newJButton("Imagem", 75, 240, 100, 120);
        adicionaListenerAcao();
    }

    /**
     * configuração do JFrame
     */
    public void configJFrame() {
        setTitle("MsRamister");
        setResizable(false);
        setSize(250, 400);
        setIconImage(getImage());
        setLocationRelativeTo(null);
        setContentPane(new Container());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager
                    .setLookAndFeel((LookAndFeel) new SubstanceDefaultLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * metodo para instanciação de um JButton
     * 
     * @param button
     * @param textButton
     * @param x
     * @param y
     * @return
     * 
     */
    private JButton newJButton(String textButton, Integer x, Integer y) {
        JButton button = new JButton(textButton);
        button.setBounds(x, y, 100, 20);
        add(button);
        return button;
    }

    private JButton newJButton(String textButton, Integer x, Integer y,
            Integer size, Integer alt) {
        JButton button = new JButton(textButton);
        button.setBounds(x, y, size, alt);
        add(button);
        return button;
    }

    /**
     * Metodo para instanciação de um JTextField
     * 
     * @param txt
     * @param x
     * @param y
     * @return
     */
    private JTextField newJTextField(Integer x, Integer y, Integer id) {
        JTextField txt = new JTextField();
        txt.setBounds(x, y, 130, 25);
        txt.setName(id.toString());
        add(txt);
        return txt;
    }

    /**
     * Metodo para instanciação de um JLabel
     * 
     * @param lbl
     * @param textLabel
     * @param y
     */
    private void newJLabel(JLabel lbl, String textLabel, Integer y) {
        lbl = new JLabel(textLabel);
        lbl.setBounds(5, y, 90, 25);
        add(lbl);
    }

    /**
     * Cria uma JLabel para validacao
     * 
     * @param x
     * @param y
     * @param size
     * @param cor
     * @return JLabel
     */
    private JLabel newJLabel(Integer x, Integer y, Integer size, Color cor) {
        JLabel lbl = new JLabel("");
        lbl.setBounds(x, y, size, 25);
        lbl.setForeground(cor);
        add(lbl);
        return lbl;
    }

    /**
     * Cria a adiciona na tela o grupo de validadores
     */
    public void grupoValidador() {
        lblVIpServ = newJLabel(232, 5, 5, Color.RED);
        lblVPortServ = newJLabel(232, 35, 5, Color.RED);
        lblVPortaClie = newJLabel(232, 65, 5, Color.RED);
        lblVLog = newJLabel(232, 95, 5, Color.RED);
        lblVSenha = newJLabel(232, 125, 5, Color.RED);
    }

    /**
     * Retorna o grupo de validadores
     */
    public List<JLabel> getValidadores() {
        if (getLblLista().size() == 0) {
            getLblLista().add(lblVIpServ);
            getLblLista().add(lblVPortServ);
            getLblLista().add(lblVPortaClie);
            getLblLista().add(lblVLog);
            getLblLista().add(lblVSenha);
        }
        return getLblLista();
    }

    /**
     * Metodo para renderizar a tela depois de todos componentes inclusos na
     * JFrame.
     */
    public void renderizaTela() {
        setVisible(true);
    }

    /**
     * Metodo para instanciação de uma JComboBox
     * 
     * @param combo
     * @param x
     * @param y
     */
    private JComboBox newJCombobox(Integer x, Integer y) {
        JComboBox combo = new JComboBox(getItensComboBox());
        combo.setBounds(x, y, 130, 20);
        add(combo);
        return combo;
    }

    /**
     * Retorna uma String do campo txtIpServidor
     * 
     * @return JTextField
     */
    public JTextField getIpServidor() {
        return txtIpServidor;
    }

    /**
     * Retorna uma String do campo txtPortaServico
     * 
     * @return JTextField
     */
    public JTextField getPortaServico() {
        return txtPortaServico;
    }

    /**
     * Retorna uma String do campo txtPortaCliente
     * 
     * @return JTextField
     */
    public JTextField getPortaCliente() {
        return txtPortaCliente;
    }

    /**
     * Retorna uma String do campo txtLogin
     * 
     * @return JTextField
     */
    public JTextField getLogin() {
        return txtLogin;
    }

    /**
     * Retorna uma String do campo txtSenha
     * 
     * @return String
     */
    public JTextField getNome() {
        return txtNome;
    }

    /**
     * Retorna uma String do campo comboSkin
     * 
     * @return String
     */
    public String getItemSelectedCombo() {
        return (String) comboSkin.getSelectedItem();
    }

    /**
     * Metodo para retornar a comboBox
     * 
     * @return JComboBox
     */
    public JComboBox getComboBox() {
        return comboSkin;
    }

    /**
     * Metodo para retornar o btnConnectar
     * 
     * @return JButton
     */
    public JButton getConnectar() {
        return btnConnectar;
    }

    /**
     * Metodo para adicão de KeyListener e ActionListener do FormConnect
     */
    private void adicionaListenerAcao() {
        FormConnectListener acao = new FormConnectListener(this, gerente);
        btnCancelar.addActionListener(acao);
        btnConnectar.addActionListener(acao);
        txtIpServidor.addKeyListener(acao);
        txtPortaServico.addKeyListener(acao);
        txtPortaCliente.addKeyListener(acao);
        txtLogin.addKeyListener(acao);
        txtNome.addKeyListener(acao);
        comboSkin.addActionListener(acao);
        btnFileChooser.addActionListener(acao);
    }

    /**
     * Retorn a instancia do FormConnectException
     * 
     * @return FormConnectException
     */
    public FormConnectException getExcessao() {
        if (excessao == null) {
            excessao = new FormConnectException(this);

        }
        return excessao;
    }

    /**
     * Retorna a lista
     * 
     * @return List<JLabel>
     */
    private List<JLabel> getLblLista() {
        if (lblLista == null) {
            lblLista = new ArrayList<JLabel>();

        }
        return lblLista;
    }

    /**
     * Retorna o label para resposta na tela
     * 
     * @return JLabel
     */
    public JLabel getLblResposta() {
        return lblResposta;
    }

    /**
     * Items da Combobox
     */
    public String[] getItensComboBox() {
        return new String[] { "Default Theme", "Creme/Café", "Dark Cyan",
                "Green Theme", "Ligth Cyan", "Ligth Theme", "Oficce Silver",
                "Oficce Blue", "Silver Theme", "Silver2 Theme", "Yellow Theme" };

    }

    public void setImagem(JComboBox imagem) {
        this.imagem = imagem;
    }

    public JComboBox getImagem() {
        return imagem;
    }

    public JButton getButtonFileChooser() {
        return btnFileChooser;
    }

    public void setImagemContatos(ImageIcon icon) {
        this.iconContatos = icon;

    }

    public ImageIcon getImagemContato() {
        if (iconContatos == null) {
            ClassLoader clazz = this.getClass().getClassLoader();
            iconContatos = RedimencionaImagemIcon.redimencionaImagem(clazz
                    .getResourceAsStream("imagens/teste.png"), 60, 60, 1000);
            setUrlImagem(clazz.getResource("imagens/teste.png").getFile());
        }
        return iconContatos;
    }

    public void setUrlImagem(String url) {
        this.urlImagem = url;

    }

    public String getUrlImagem() {
        return urlImagem;
    }

    private Image getImage() {
        ClassLoader clazz = this.getClass().getClassLoader();
        URL res = clazz.getResource("imagens/serverRunning.png");
        ImageIcon iconImage = new ImageIcon(res);
        return iconImage.getImage();
    }

    public void setImagemUsuario(ImageIcon icon) {
        this.iconUsuario = icon;

    }

    public void setIconUsuario(ImageIcon iconUsuario) {
        this.iconUsuario = iconUsuario;
    }

    public ImageIcon getIconUsuario() {
        if (iconUsuario == null) {
            iconUsuario = RedimencionaImagemIcon.redimencionaImagem(
                    getImagemContato(), 110, 130, 1200);
        }
        return iconUsuario;
    }

}
