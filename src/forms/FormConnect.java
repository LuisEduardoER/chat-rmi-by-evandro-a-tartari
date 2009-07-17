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
    private JLabel lblIpServidor;
    private JLabel lblPortaServico;
    private JLabel lblPortaCliente;
    private JLabel lblLogin;
    private JLabel lblSenha;
    private JLabel lblSkin;
    
    //TextFields e combobox
    private JTextField txtIpServidor;
    private JTextField txtPortaServico;
    private JTextField txtPortaCliente;
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JComboBox comboSkin;
    //Botoes    
    private JButton btnConnectar;
    private JButton btnCancelar;
    
    public FormConnect() {
        configJFrame();
        inicializar();
        renderizaTela();
    }
    
    private void inicializar(){
        //Labels
        newJLabel(lblIpServidor,"Ip servidor:", 5);
        newJLabel(lblPortaServico,"Porta servico:", 35);
        newJLabel(lblPortaCliente, "Porta resposta:", 65);
        newJLabel(lblLogin,"Login:", 95);
        newJLabel(lblSenha,"Senha:", 125);
        newJLabel(lblSkin,"Skin:", 155);
        //Fields
        newJTextField(txtIpServidor,100,5);
        newJTextField(txtPortaServico,100,35);
        newJTextField(txtPortaCliente,100,65);
        newJTextField(txtLogin,100,95);
        newJPasswordField(txtSenha,100, 125);
        newJCombobox(comboSkin, 100, 157);
        newJButton(btnCancelar, "Fechar", 15, 195);
        newJButton(btnConnectar, "Connectar", 130, 195);
    }
    
    private void configJFrame(){
        setTitle("MsRamister");
        setResizable(false);
        setSize(250, 260);
        setLocationRelativeTo(null);
        setContentPane(new Container());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private JButton newJButton(JButton button, String textButton, Integer x, Integer y){
        button = new JButton(textButton);
        button.setBounds(x, y, 100, 20);
        add(button);
        return button;
    }
    
    private JPasswordField newJPasswordField(JPasswordField txtPass, Integer x, Integer y){
        txtPass = new JPasswordField();
        txtPass.setBounds(x, y, 130, 25);
        add(txtPass);
        return txtPass;
    }
    
    private JTextField newJTextField(JTextField txt, Integer x, Integer y){
        txt = new JTextField();
        txt.setBounds(x, y, 130, 25);
        add(txt);
        return txt;
    }
    private JLabel newJLabel(JLabel lbl, String textLabel, Integer y){
        lbl = new JLabel(textLabel);
        lbl.setBounds(5, y, 90, 25);
        add(lbl);
        return lbl;
    }
    
    private void renderizaTela(){
        setVisible(true);
    }
    
    private JComboBox newJCombobox(JComboBox combo, Integer x, Integer y){
        combo = new JComboBox();
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
    
    
    /**
     *
     * Sessão Excluir
     *
     * 
     */
    
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        FormConnect connect = new FormConnect();
    }
}
