package excessao;

import javax.swing.JLabel;

public class FormConnectException {

    private JLabel lbl;

    public FormConnectException(JLabel lbl) {
        this.lbl = lbl;
    }

    public void lancaExcessao(String excessao) {
        lbl.setText(excessao);
    }

    public void limpaExcessao() {
        lbl.setText("");
    }
}
