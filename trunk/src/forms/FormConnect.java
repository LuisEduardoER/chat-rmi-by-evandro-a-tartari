package forms;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FormConnect extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 7662142711284402861L;
    @SuppressWarnings("unused")
    private JLabel lblIpServidor;
    @SuppressWarnings("unused")
    private JLabel lblPortaServico;
    @SuppressWarnings("unused")
    private JLabel lblPortaCliente;
    @SuppressWarnings("unused")
    private JLabel lblLogin;
    @SuppressWarnings("unused")
    private JLabel lblSenha;
    @SuppressWarnings("unused")
    private JLabel lblSkin;
    
    private JTextField txtIpServidor;
    private JTextField txtPortaServico;
    private JTextField txtPortaCliente;
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JComboBox comboSkin;
    
    @SuppressWarnings("unused")
    private JButton btnConnectar;
    @SuppressWarnings("unused")
    private JButton btnCancelar;
    
    public FormConnect() {
        configJFrame();
        inicializar();
        renderizaTela();
    }
    
    private void inicializar(){
        //Labels
        lblIpServidor = newJLabel("Ip servidor:", 5);
        lblPortaServico = newJLabel("Porta servico:", 35);
        lblPortaCliente = newJLabel("Porta resposta:", 65);
        lblLogin = newJLabel("Login:", 95);
        lblSenha = newJLabel("Senha:", 125);
        lblSkin = newJLabel("Skin:", 155);
        //Fields
        txtIpServidor = newJTextField(100,5);
        txtPortaServico = newJTextField(100,35);
        txtPortaCliente = newJTextField(100,65);
        txtLogin = newJTextField(100,95);
        txtSenha = newJPasswordField(100, 125);
        comboSkin = newJCombobox(100, 157);
        btnCancelar = newJButton("fechar", 15, 195);
        btnConnectar = newJButton("connect", 130, 195);
    }
    
    private void configJFrame(){
        setTitle("MsRamister");
        setResizable(false);
        setSize(250, 260);
        setLocationRelativeTo(null);
        setContentPane(new Container());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private JButton newJButton(String textButton, Integer x, Integer y){
        JButton button = new JButton(textButton);
        button.setBounds(x, y, 100, 20);
        add(button);
        return button;
    }
    
    private JPasswordField newJPasswordField(Integer x, Integer y){
        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(x, y, 130, 25);
        add(txtPass);
        return txtPass;
    }
    
    private JTextField newJTextField(Integer x, Integer y){
        JTextField txt = new JTextField();
        txt.setBounds(x, y, 130, 25);
        add(txt);
        return txt;
    }
    private JLabel newJLabel(String textLabel, Integer y){
        JLabel lbl = new JLabel(textLabel);
        lbl.setBounds(5, y, 90, 25);
        add(lbl);
        return lbl;
    }
    
    private void renderizaTela(){
        setVisible(true);
    }
    
    private JComboBox newJCombobox(Integer x, Integer y){
        JComboBox combo = new JComboBox();
        combo.setBounds(x, y, 130, 20);
        add(combo);
        return combo;
    }
    
    public String getIpServidor(){
        return txtIpServidor.getText();
    }
    
    public String getPortaServico(){
        return txtPortaServico.getText();
    }
    
    public String getPortaCliente(){
        return txtPortaCliente.getText();
    }
    
    public String getLogin(){
        return txtLogin.getText();
    }
    
    public String getPassWord(){
        return txtSenha.getPassword().toString();
    }
    
    public String getItemSelectedCombo(){
        return (String) comboSkin.getSelectedItem();
    }
    
    
    
    
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        FormConnect connect = new FormConnect();
    }
}
