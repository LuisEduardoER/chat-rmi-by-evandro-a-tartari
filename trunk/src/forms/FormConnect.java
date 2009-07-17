package forms;

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
    
    private JTextField txtIpServidor;
    private JTextField txtPortaServico;
    private JTextField txtPortaCliente;
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JComboBox comboSkin;
    
    
    public FormConnect() {

    }
    
    public void inicializar(){
        //Labels
        lblIpServidor = new JLabel("Ip servidor:");
        lblPortaServico = new JLabel("Porta servico:");
        lblPortaCliente = new JLabel("Porta resposta:");
        lblLogin = new JLabel("Login:");
        lblSenha = new JLabel("Senha:");
        lblSkin = new JLabel("Skin:");
        //Fields
        txtIpServidor = new JTextField();
        txtPortaServico = new JTextField();
        txtPortaCliente = new JTextField();
        txtLogin = new JTextField();
        txtSenha = new JPasswordField();
        comboSkin = getComboboxSkin();
    }
    
    private JComboBox getComboboxSkin(){
        if (comboSkin == null) {
            comboSkin = new JComboBox();
            comboSkin.addItem("");
        }
        return comboSkin;
    }
}
