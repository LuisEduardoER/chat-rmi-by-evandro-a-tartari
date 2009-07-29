package contatos.render;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import contatos.Contatos;

public class UsuarioRender extends JLabel implements ListCellRenderer {
    /**
     * 
     */
    private static final long serialVersionUID = -2995353638159933716L;
    public UsuarioRender() {
        setOpaque(true);
        setHorizontalAlignment(JLabel.CENTER);
        setIconTextGap(12);
    }
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        Contatos contatos = (Contatos) value;
        setText(contatos.getNome());
        setIcon(contatos.getImage());
        return this;
    }

}
