package excessao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import forms.FormConnect;

public class FormConnectException {

    private FormConnect connect;
    private List<Integer> listaIdExcessaoLancar;

    public FormConnectException(JFrame frame) {
        connect = (FormConnect) frame;
    }

    public void lancaExcessao(String excessao) {
        getTelaResposta().setText(excessao);
        if (getListaIdExcessaoLancar().size() > 0) {
            for (int i = 0; i < getListaIdExcessaoLancar().size(); i++) {
                retornaJLabelResponsavel(getListaIdExcessaoLancar().get(i))
                        .setText("*");
            }
        }
        connect.getIpServidor().requestFocus();
    }

    public void limpaExcessao(int id) {
        int posicao = getListaIdExcessaoLancar().indexOf(id);
        if(posicao!=-1){
            connect.getValidadores().get(id).setText("");
            getListaIdExcessaoLancar().remove(posicao);
        }else{
            if(getListaIdExcessaoLancar().size()==0){
                getTelaResposta().setText("");
            }
        }
    }

    private JLabel getTelaResposta() {
        return connect.getLblResposta();
    }

    public void adicionaIdExcessaoLancar(Integer id) {
        if(!getListaIdExcessaoLancar().contains(id))
            getListaIdExcessaoLancar().add(id);
    }

    public void setListaIdExcessaoLancar(List<Integer> listaIdExcessaoLancar) {
        this.listaIdExcessaoLancar = listaIdExcessaoLancar;
    }

    public List<Integer> getListaIdExcessaoLancar() {
        if (listaIdExcessaoLancar == null) {
            listaIdExcessaoLancar = new ArrayList<Integer>();

        }
        return listaIdExcessaoLancar;
    }

    public JLabel retornaJLabelResponsavel(Integer id) {
        return connect.getValidadores().get(id);
    }

}
