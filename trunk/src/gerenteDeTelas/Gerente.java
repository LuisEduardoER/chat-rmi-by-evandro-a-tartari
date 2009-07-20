package gerenteDeTelas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTextField;

import forms.FormConnect;

public class Gerente {

    /**
     * Contrutor Default do Gerente de Telas
     */
    private List<JComponent> listaExcessaoConnectForm = new ArrayList<JComponent>();

    public Gerente() {
    }

    /**
     * Metodo de inicialização do Gerente de Telas
     */
    private void init() {
        FormConnect connect = new FormConnect(this);
        connect.configJFrame();
        connect.inicializar();
        connect.grupoValidador();
        connect.renderizaTela();
    }

    /**
     * Metodo connectar para instanciar um cliente e procurar um servico
     * @param frame
     */
    public void connectar(FormConnect frame) {
        if (isValidData(frame))
            System.out.println("Implementar Metodo no gerente");
        else {
            frame.getExcessao().setaListaExcessao(listaExcessaoConnectForm);
            frame.getExcessao().lancaExcessao("*Campo numerico");
        }
    }

    /**
     * Validador dos componentes numericos
     * @param frame
     * @return
     */
    private Boolean isValidData(FormConnect frame) {
        Boolean retorna = true;
        if (!isValidaCampoNumerico(frame.getIpServidor(), frame))
            retorna = false;
        if (!isValidaCampoNumerico(frame.getPortaServico(), frame))
            retorna = false;
        if (!isValidaCampoNumerico(frame.getPortaCliente(), frame))
            retorna = false;
        return retorna;
    }

    /**
     * Validador Campo numerico
     * 
     * @param t
     * @param frame
     * @return Boolean
     */
    public Boolean isValidaCampoNumerico(JTextField t, FormConnect frame) {
        String strNumero = t.getText();
        strNumero = strNumero.replace(".", "");
        try {
            Integer.parseInt(strNumero);
            return true;
        } catch (Exception e) {
            listaExcessaoConnectForm.add(t);
            return false;
        }

    }

    /**
     * Metodo Main do Gerente de Telas
     * 
     * @param args
     */
    public static void main(String[] args) {
        Gerente gerente = new Gerente();
        gerente.init();
    }

}
