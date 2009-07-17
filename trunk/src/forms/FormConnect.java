package forms;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import acao.AcaoFormConnect;

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
    private JPasswordField txtSenha;
    private JComboBox comboSkin;
    // Botoes
    private JButton btnConnectar;
    private JButton btnCancelar;

    /**
     * Formulario Responsavel pela conexão que será efetuada entre o cliente e o
     * server em rmi
     */
    public FormConnect() {
        configJFrame();
        inicializar();
        renderizaTela();
        adicionaListenerAcao();
    }

    /**
     * inicialização dos componentes
     */
    private void inicializar() {
        // Labels
        newJLabel(lblIpServidor, "Ip servidor:", 5);
        newJLabel(lblPortaServico, "Porta servico:", 35);
        newJLabel(lblPortaCliente, "Porta resposta:", 65);
        newJLabel(lblLogin, "Login:", 95);
        newJLabel(lblSenha, "Senha:", 125);
        newJLabel(lblSkin, "Skin:", 155);
        // Fields
        newJTextField(txtIpServidor, 100, 5);
        newJTextField(txtPortaServico, 100, 35);
        newJTextField(txtPortaCliente, 100, 65);
        newJTextField(txtLogin, 100, 95);
        newJPasswordField(txtSenha, 100, 125);
        newJCombobox(comboSkin, 100, 157);
        newJButton(btnCancelar, "Fechar", 15, 195);
        newJButton(btnConnectar, "Connectar", 130, 195);
    }

    /**
     * configuração do JFrame
     */
    private void configJFrame() {
        setTitle("MsRamister");
        setResizable(false);
        setSize(250, 260);
        setLocationRelativeTo(null);
        setContentPane(new Container());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 
     * metodo para instanciação de um JButton
     * 
     * @param button
     * @param textButton
     * @param x
     * @param y
     * 
     */
    private void newJButton(JButton button, String textButton, Integer x,
            Integer y) {
        button = new JButton(textButton);
        button.setBounds(x, y, 100, 20);
        add(button);
    }

    /**
     * Metodo para instanciação de um JPasswordField
     * 
     * @param txtPass
     * @param x
     * @param y
     */
    private void newJPasswordField(JPasswordField txtPass, Integer x, Integer y) {
        txtPass = new JPasswordField();
        txtPass.setBounds(x, y, 130, 25);
        add(txtPass);
    }

    /**
     * Metodo para instanciação de um JTextField
     * 
     * @param txt
     * @param x
     * @param y
     */
    private void newJTextField(JTextField txt, Integer x, Integer y) {
        txt = new JTextField();
        txt.setBounds(x, y, 130, 25);
        add(txt);
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
     * Metodo para renderizar a tela depois de todos componentes inclusos na
     * JFrame.
     */
    private void renderizaTela() {
        setVisible(true);
    }

    /**
     * Metodo para instanciação de uma JComboBox
     * 
     * @param combo
     * @param x
     * @param y
     */
    private void newJCombobox(JComboBox combo, Integer x, Integer y) {
        combo = new JComboBox();
        combo.setBounds(x, y, 130, 20);
        add(combo);
    }

    /**
     * Retorna uma String do campo txtIpServidor
     * 
     * @return String
     */
    public String getIpServidor() {
        return txtIpServidor.getText();
    }

    /**
     * Retorna uma String do campo txtPortaServico
     * 
     * @return String
     */
    public String getPortaServico() {
        return txtPortaServico.getText();
    }

    /**
     * Retorna uma String do campo txtPortaCliente
     * 
     * @return String
     */
    public String getPortaCliente() {
        return txtPortaCliente.getText();
    }

    /**
     * Retorna uma String do campo txtLogin
     * 
     * @return String
     */
    public String getLogin() {
        return txtLogin.getText();
    }

    /**
     * Retorna uma String do campo txtSenha
     * 
     * @return String
     */
    public String getPassWord() {
        return txtSenha.getPassword().toString();
    }

    /**
     * Retorna uma String do campo comboSkin
     * 
     * @return String
     */
    public String getItemSelectedCombo() {
        return (String) comboSkin.getSelectedItem();
    }

    private void adicionaListenerAcao() {
        btnCancelar.addActionListener(new AcaoFormConnect());
        btnConnectar.addActionListener(new AcaoFormConnect());
        txtIpServidor.addKeyListener(new AcaoFormConnect());
        txtPortaServico.addKeyListener(new AcaoFormConnect());
        txtPortaCliente.addKeyListener(new AcaoFormConnect());
        txtLogin.addKeyListener(new AcaoFormConnect());
        txtSenha.addKeyListener(new AcaoFormConnect());
        comboSkin.addActionListener(new AcaoFormConnect());
    }

    /**
     * 
     * Sessão Excluir
     * 
     * 
     */
    @Deprecated
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        FormConnect connect = new FormConnect();
    }
}
