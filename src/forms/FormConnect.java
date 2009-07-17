package forms;

import java.awt.Container;

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
        configJFrame();
        inicializar();
        criaTela();
    }
    
    private void inicializar(){
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
    
    private void configJFrame(){
        setTitle("MsRamister");
        setResizable(false);
        setContentPane(new Container());
        setSize(200, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void criaTela(){
        adicionaLabels(lblIpServidor, 5);
        adicionaLabels(lblPortaServico, 35);
        adicionaLabels(lblPortaCliente, 55);
        adicionaLabels(lblLogin, 85);
        adicionaLabels(lblSenha, 115);
        adicionaLabels(lblSkin, 145);
    }
    
    private void adicionaLabels(JLabel t, Integer y){
        t.setBounds(5, y, 90, 25);
        add(t);
    }
    
    
    
    private JComboBox getComboboxSkin(){
        if (comboSkin == null) {
            comboSkin = new JComboBox();
            comboSkin.addItem("");
        }
        return comboSkin;
    }
    public static void main(String[] args) {
        FormConnect connect = new FormConnect();
    }
}
