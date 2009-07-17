package acao;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import forms.FormConnect;

public class AcaoFormConnect implements ActionListener, KeyListener {
    private FormConnect connect;

    /**
     * Construtor padrao passando o JFrame ao qual ele responde
     * 
     * @param frame
     */
    public AcaoFormConnect(JFrame frame) {
        connect = (FormConnect) frame;
    }

    /**
     * Metodos para o Onclick nos botoes tela
     */
    public void actionPerformed(ActionEvent e) {
        if (verificarAcaoBotao(e.getActionCommand(), "Connectar")) {
            if (isValid())
                System.out.println("Action Connectar a implementar");
            else
                LancaExcessao();
        } else if (verificarAcaoBotao(e.getActionCommand(), "Fechar")) {
            connect.dispose();
        } else if (verificarAcaoBotao(e.getActionCommand(), "comboBoxChanged")) {
            System.out.println("Action ComboBox a implementar");
        }

    }

    /**
     * Metodos Controlador de Eventos do teclado
     */
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Metodos Controlador de Eventos do teclado
     */
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == Event.ENTER) {
            if (e.getModifiers() == 0) {
                if (verificador(getIpServidor()))
                    getPortaServico().requestFocus();
                else if (verificador(getPortaServico()))
                    getPortaCliente().requestFocus();
                else if (verificador(getPortaCliente()))
                    getLogin().requestFocus();
                else if (verificador(getLogin()))
                    getPassWord().requestFocus();
                else if (verificador(getPassWord())) {
                    if (isValid())
                        System.out.println("KeyEvent Connectar a implementar");
                    else
                        LancaExcessao();
                }

            }
        } else if (e.getKeyCode() == Event.ESCAPE) {
            if (e.getModifiers() == 0) {
                connect.dispose();
            }
        } else if (e.getKeyCode() == 38) {
            if (e.getModifiers() == 0) {
                if (verificador(getIpServidor()))
                    getPassWord().requestFocus();
                else if (verificador(getPortaServico()))
                    getIpServidor().requestFocus();
                else if (verificador(getPortaCliente()))
                    getPortaServico().requestFocus();
                else if (verificador(getLogin()))
                    getPortaCliente().requestFocus();
                else if (verificador(getPassWord())) {
                    getLogin().requestFocus();
                }
            }
        } else if (e.getKeyCode() == 40) {
            if (e.getModifiers() == 0) {
                if (verificador(getIpServidor()))
                    getPortaServico().requestFocus();
                else if (verificador(getPortaServico()))
                    getPortaCliente().requestFocus();
                else if (verificador(getPortaCliente()))
                    getLogin().requestFocus();
                else if (verificador(getLogin()))
                    getPassWord().requestFocus();
                else if (verificador(getPassWord())) {
                    getIpServidor().requestFocus();
                }
            }
        }
    }

    /**
     * Metodos Controlador de Eventos do teclado
     */
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Metodos Verifica se o JComponente esta com o cursos do mouse
     * 
     * @param t
     *            JComponent
     * @return Boolean
     */
    public Boolean verificador(JComponent t) {
        if (t.isFocusOwner())
            return true;
        else
            return false;
    }

    /**
     * Metodo para facilitar leitura do codigo
     * 
     * @return JTextField
     */
    private JTextField getIpServidor() {
        return connect.getIpServidor();
    }

    /**
     * Metodo para facilitar leitura do codigo
     * 
     * @return JTextField
     */
    private JTextField getPortaServico() {
        return connect.getPortaServico();
    }

    /**
     * Metodo para facilitar leitura do codigo
     * 
     * @return JTextField
     */
    private JTextField getPortaCliente() {
        return connect.getPortaCliente();
    }

    /**
     * Metodo para facilitar leitura do codigo
     * 
     * @return JTextField
     */
    private JTextField getLogin() {
        return connect.getLogin();
    }

    /**
     * Metodo para facilitar leitura do codigo
     * 
     * @return JTextField
     */
    private JPasswordField getPassWord() {
        return connect.getPassWord();
    }

    /**
     * Metodo para facilitar leitura do codigo
     * 
     * @return JTextField
     */
    @SuppressWarnings("unused")
    private JComboBox getComboSkin() {
        return connect.getComboBox();
    }

    /**
     * Metodo para facilitar leitura do codigo
     * 
     * @return JTextField
     */
    @SuppressWarnings("unused")
    private JButton getConnectar() {
        return connect.getConnectar();
    }

    /**
     * @param actionComand
     *            String comandoAcao
     * @param esperado
     *            String comandoEsperado
     * @return Boolean
     */
    public Boolean verificarAcaoBotao(String actionComand, String esperado) {
        return actionComand.equals(esperado);
    }

    /**
     * Metodo para lancar excessao na tela
     */
    private void LancaExcessao() {
        System.out.println("Excessao Tela a implementar");
    }

    /**
     * Metodo para verificar validacao dos dados do form
     * 
     * @return Boolean
     */
    private Boolean isValid() {
        if (ver(getIpServidor()) && ver(getPortaServico())
                && ver(getPortaCliente()) && ver(getLogin())
                && ver(getPassWord()))
            return true;
        else
            return false;
    }

    /**
     * Metodo auxiliar na validacao dos dados
     * 
     * @param t
     * @return Boolean
     */
    private Boolean ver(JTextField t) {
        return !t.getText().equals("");
    }
}
