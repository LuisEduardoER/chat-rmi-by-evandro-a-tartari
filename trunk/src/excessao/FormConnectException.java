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
    public FormConnectException(JFrame frame) {
        connect = (FormConnect) frame;
        setComponentes(new ArrayList<JComponent>());
        setExcessoesLancadas(new ArrayList<JComponent>());
    }

    public void lancaExcessao(String excessao) {
        if (getComponentes().size() != 0) {
            for (JComponent componente : getComponentes()) {
                connect.getValidadores().get(
                        Integer.parseInt(componente.getName())).setText("*");
                componente.setBackground(Color.getHSBColor(80, 50, 25));
                excessoesLancadas.add(componente);
            }
            getComponentes().get(0).requestFocus();
            getTelaResposta().setText(excessao);
            getComponentes().clear();
        }

    }
    
    public void lancaExcessaoSimple(String excessao){
        getTelaResposta().setText(excessao);
    }

    public void limpaExcessao(JComponent componente) {
        connect.getValidadores().get(Integer.parseInt(componente.getName()))
                .setText("");
        componente.setBackground(Color.WHITE);
        getExcessoesLancadas().remove(componente);
        if(getExcessoesLancadas().size()==0){
            getTelaResposta().setText("");
        }
    }

    private JLabel getTelaResposta() {
        return connect.getLblResposta();
    }

    public void setaListaExcessao(List<JComponent> componentes) {
        this.setComponentes(componentes);
    }

    public JLabel retornaJLabelResponsavel(Integer id) {
        return connect.getValidadores().get(id);
    }

    public void setComponentes(List<JComponent> componentes) {
        this.componentes = componentes;
    }

    private List<JComponent> getComponentes() {
        return componentes;
    }

    public void setExcessoesLancadas(List<JComponent> excessoesLancadas) {
        this.excessoesLancadas = excessoesLancadas;
    }

    public List<JComponent> getExcessoesLancadas() {
        return excessoesLancadas;
    }

}
