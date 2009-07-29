package contatos.render;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class UsuarioRender implements ListCellRenderer {
    /**
     * 
     */
    private static final long serialVersionUID = -2995353638159933716L;

    public UsuarioRender() {
    }

    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof ImageIcon) {
            JLabel lbl = new JLabel("", JLabel.CENTER);
            lbl.setOpaque(true);
            lbl.setIconTextGap(20);
            ImageIcon imagem = (ImageIcon) value;
            lbl.setIcon(imagem);
            return lbl;
        } else if (value instanceof String) {
            JLabel lbl = new JLabel((String) value, JLabel.CENTER);
            lbl.setForeground(Color.blue);
            lbl.setFont(new Font("verdana", Font.BOLD, 16));
            return lbl;
        }
        return new JLabel();
    }

}
