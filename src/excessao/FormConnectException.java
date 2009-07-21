package excessao;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import forms.FormConnect;

public class FormConnectException {

    private FormConnect connect;
    private List<JComponent> componentes;
    private List<JComponent> excessoesLancadas;
    private Color oldColor;

    /**
     * Construtor defaul passando o frame que ele responde
     * 
     * @param frame
     */
    public FormConnectException(JFrame frame) {
        connect = (FormConnect) frame;
        setComponentes(new ArrayList<JComponent>());
        setExcessoesLancadas(new ArrayList<JComponent>());
    }

    /**
     * Metodo para lancar excessao na Tela do FormConnect
     * @param excessao
     */
    public void lancaExcessao(String excessao) {
        if (getComponentes().size() != 0) {
            for (JComponent componente : getComponentes()) {
                connect.getValidadores().get(
                        Integer.parseInt(componente.getName())).setText("*");
                oldColor = componente.getBackground();
                componente.setBackground(Color.getHSBColor(80, 50, 25));
                excessoesLancadas.add(componente);
            }
            getComponentes().get(0).requestFocus();
            getTelaResposta().setText(excessao);
            getComponentes().clear();
        }

    }

    /**
     * Metodo que lanca uma excessao simple na tela do form
     * @param excessao
     */
    public void lancaExcessaoSimple(String excessao) {
        getTelaResposta().setText(excessao);
    }

    /**
     * Metodo que limpa a excessao
     * @param componente
     */
    public void limpaExcessao(JComponent componente) {
        connect.getValidadores().get(Integer.parseInt(componente.getName()))
                .setText("");
        if(oldColor==null)
            oldColor = componente.getBackground();
        componente.setBackground(oldColor);
        getExcessoesLancadas().remove(componente);
        if (getExcessoesLancadas().size() == 0) {
            getTelaResposta().setText("");
        }
    }

    /**
     * Retorna o Label para resposta da tela
     * @return
     */
    private JLabel getTelaResposta() {
        return connect.getLblResposta();
    }

    /**
     * Seta dados da lista de excessao a ser apresentada na tela
     * @param componentes
     */
    public void setaListaExcessao(List<JComponent> componentes) {
        this.setComponentes(componentes);
    }

    /**
     * Setada lista de componentes
     * @param componentes
     */
    public void setComponentes(List<JComponent> componentes) {
        this.componentes = componentes;
    }

    /**
     * Pega lista de componentes
     * @return
     */
    private List<JComponent> getComponentes() {
        return componentes;
    }

    /**
     * Seta lista de excessoes lancadas
     * @param excessoesLancadas
     */
    public void setExcessoesLancadas(List<JComponent> excessoesLancadas) {
        this.excessoesLancadas = excessoesLancadas;
    }

    /**
     * Pega lista de excessoes lancadas
     * @return
     */
    public List<JComponent> getExcessoesLancadas() {
        return excessoesLancadas;
    }

}
