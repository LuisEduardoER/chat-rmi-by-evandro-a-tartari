package acao;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import org.jvnet.substance.SubstanceDefaultLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessLookAndFeel;
import org.jvnet.substance.skin.SubstanceCremeCoffeeLookAndFeel;
import org.jvnet.substance.skin.SubstanceFieldOfWheatLookAndFeel;
import org.jvnet.substance.skin.SubstanceGreenMagicLookAndFeel;
import org.jvnet.substance.skin.SubstanceMistSilverLookAndFeel;
import org.jvnet.substance.skin.SubstanceModerateLookAndFeel;
import org.jvnet.substance.skin.SubstanceNebulaBrickWallLookAndFeel;
import org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel;
import org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel;

import forms.FormConnect;
import gerenteDeTelas.Gerente;

public class FormConnectListener implements ActionListener, KeyListener {
    private List<JComponent> componentes;
    private FormConnect connect;
    private Gerente gerente;
    private Map<String, LookAndFeel> mapaSubstance;

    /**
     * Construtor padrao passando o JFrame ao qual ele responde
     * 
     * @param frame
     * @param gerente
     */
    public FormConnectListener(JFrame frame, Gerente gerente) {
        connect = (FormConnect) frame;
        this.gerente = gerente;
        componentes = new ArrayList<JComponent>();

    }

    /**
     * Metodos para o Onclick nos botoes tela
     */
    public void actionPerformed(ActionEvent evento) {
        if (verificarAcaoBotao(evento.getActionCommand(), "Connectar")) {
            if (isValid()){
                gerente.connectar(connect);
            }
            else
                LancaExcessao("*Campos Obrigatorios");
        } else if (verificarAcaoBotao(evento.getActionCommand(), "Fechar")) {
            connect.dispose();
        } else if (verificarAcaoBotao(evento.getActionCommand(), "comboBoxChanged")) {
            try{
                UIManager.setLookAndFeel(getMapaSubstance().get(getSelectedItem()));
            }catch (Exception e) {
                connect.getExcessao().lancaExcessaoSimple("Erro no substance");
            }
        }else if(verificarAcaoBotao(evento.getActionCommand(), "Imagem")){
            System.out.println("OK");
        }

    }

    /**
     * Metodos Controlador de Eventos do teclado
     */
    public void keyPressed(KeyEvent e) {
        if (verificador(getIpServidor())) {
            if (isVer(getIpServidor()))
                connect.getExcessao().limpaExcessao(getIpServidor());
        } else if (verificador(getPortaServico())) {
            if (isVer(getPortaServico()))
                connect.getExcessao().limpaExcessao(getPortaServico());
        } else if (verificador(getPortaCliente())) {
            if (isVer(getPortaCliente()))
                connect.getExcessao().limpaExcessao(getPortaCliente());
        } else if (verificador(getLogin())) {
            if (isVer(getLogin()))
                connect.getExcessao().limpaExcessao(getLogin());
        } else if (verificador(getPassWord()))
            if (isVer(getPassWord()))
                connect.getExcessao().limpaExcessao(getPassWord());
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
                        gerente.connectar(connect);
                    else
                        LancaExcessao("*Campos Obrigatorios");
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

    public String getSelectedItem(){
        System.out.println((String) connect.getComboBox().getSelectedItem());
        return (String) connect.getComboBox().getSelectedItem();
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
    private void LancaExcessao(String excessao) {
        connect.getExcessao().lancaExcessao(excessao);
        componentes.clear();
    }

    /**
     * Metodo para verificar validacao dos dados do form
     * 
     * @return Boolean
     */
    private Boolean isValid() {
        if (isVer(getIpServidor()) && isVer(getPortaServico())
                && isVer(getPortaCliente()) && isVer(getLogin())
                && isVer(getPassWord()))
            return true;
        else {
            validadorIndividual();
            return false;
        }
    }
    /**
     *  Validador Individual
     */
    private void validadorIndividual() {
        if (!isVer(getIpServidor()))
            componentes.add(getIpServidor());
        if (!isVer(getPortaServico()))
            componentes.add(getPortaServico());
        if (!isVer(getPortaCliente()))
            componentes.add(getPortaCliente());
        if (!isVer(getLogin()))
            componentes.add(getLogin());
        if (!isVer(getPassWord()))
            componentes.add(getPassWord());
        connect.getExcessao().setaListaExcessao(componentes);
    }

    /**
     * Metodo auxiliar na validacao dos dados
     * 
     * @param t
     * @return Boolean
     */
    private Boolean isVer(JTextField t) {
        return !t.getText().trim().equals("");
    }
    
    /**
     * Mapa de Temas
     * @return LookAndFeel
     */
    private Map<String, LookAndFeel> getMapaSubstance(){
        if(mapaSubstance == null){
            mapaSubstance = new HashMap<String, LookAndFeel>();
            mapaSubstance.put("Dark Cyan", new SubstanceBusinessLookAndFeel());
            mapaSubstance.put("Ligth Cyan", new SubstanceBusinessBlackSteelLookAndFeel());
            mapaSubstance.put("Creme/Café", new SubstanceCremeCoffeeLookAndFeel());
            mapaSubstance.put("Default Theme", new SubstanceDefaultLookAndFeel());
            mapaSubstance.put("Yellow Theme", new SubstanceFieldOfWheatLookAndFeel());
            mapaSubstance.put("Green Theme", new SubstanceGreenMagicLookAndFeel());
            mapaSubstance.put("Silver Theme", new SubstanceMistSilverLookAndFeel());
            mapaSubstance.put("Silver2 Theme", new SubstanceModerateLookAndFeel());
            mapaSubstance.put("Ligth Theme", new SubstanceNebulaBrickWallLookAndFeel());
            mapaSubstance.put("Oficce Silver", new SubstanceOfficeSilver2007LookAndFeel());
            mapaSubstance.put("Oficce Blue", new SubstanceOfficeBlue2007LookAndFeel());
        }
        return mapaSubstance;
    }


}
