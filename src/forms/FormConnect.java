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
    private JLabel lblResposta;
    
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
        newJLabel(lblResposta, "", 225);
        // Fields
        txtIpServidor = newJTextField(100, 5);
        txtPortaServico = newJTextField(100, 35);
        txtPortaCliente = newJTextField(100, 65);
        txtLogin = newJTextField(100, 95);
        txtSenha = newJPasswordField(100, 125);
        comboSkin = newJCombobox(100, 157);
        btnCancelar = newJButton("Fechar", 15, 195);
        btnConnectar = newJButton("Connectar", 130, 195);
        adicionaListenerAcao();
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
     * @return 
     * 
     */
    private JButton newJButton(String textButton, Integer x,
            Integer y) {
        JButton button = new JButton(textButton);
        button.setBounds(x, y, 100, 20);
        add(button);
        return button;
    }

    /**
     * Metodo para instanciação de um JPasswordField
     * 
     * @param txtPass
     * @param x
     * @param y
     * @return 
     */
    private JPasswordField newJPasswordField(Integer x, Integer y) {
        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(x, y, 130, 25);
        add(txtPass);
        return txtPass;
    }

    /**
     * Metodo para instanciação de um JTextField
     * 
     * @param txt
     * @param x
     * @param y
     * @return 
     */
    private JTextField newJTextField(Integer x, Integer y) {
        JTextField txt = new JTextField();
        txt.setBounds(x, y, 130, 25);
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
    private JComboBox newJCombobox(Integer x, Integer y) {
        JComboBox combo = new JComboBox();
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
    public JPasswordField getPassWord() {
        return txtSenha;
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
     * @return JComboBox
     */
    public JComboBox getComboBox(){
        return comboSkin;
    }
    
    /**
     * Metodo para retornar o btnConnectar 
     * @return JButton
     */
    public JButton getConnectar() {
        return btnConnectar;
    }

    
    /**
     * Metodo para adicão de KeyListener e ActionListener do FormConnect 
     */
    private void adicionaListenerAcao() {
        AcaoFormConnect acao = new AcaoFormConnect(this);
        btnCancelar.addActionListener(acao);
        btnConnectar.addActionListener(acao);
        txtIpServidor.addKeyListener(acao);
        txtPortaServico.addKeyListener(acao);
        txtPortaCliente.addKeyListener(acao);
        txtLogin.addKeyListener(acao);
        txtSenha.addKeyListener(acao);
        comboSkin.addActionListener(acao);
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
