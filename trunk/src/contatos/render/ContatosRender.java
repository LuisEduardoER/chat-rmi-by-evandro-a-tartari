package contatos.render;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import util.Criptografia;
import contatos.Contatos;

/**
 * 
 * @author evandro.tartari
 * 
 */
public class ContatosRender extends JLabel implements ListCellRenderer {

    private static final long serialVersionUID = -8711692757853640701L;

    public ContatosRender() {
        setOpaque(true);
        setIconTextGap(12);
    }

    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        Contatos contatos = (Contatos) value;
        setText(Criptografia.decripto(contatos.getNome()));
        setFont(new Font("verdana", Font.BOLD, 12));
        setIcon(contatos.getImage());
        return this;
    }
}
