package contatos.render;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import contatos.Contatos;
import forms.FormListFriends;

public class ContatosRender extends JLabel implements ListCellRenderer {

    
    private static final long serialVersionUID = -8711692757853640701L;
    private FormListFriends formListFriends;
    
    public ContatosRender(JFrame frame) {
        setOpaque(true);
        setIconTextGap(12);
        formListFriends = (FormListFriends) frame;
    }

    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        Contatos contatos = (Contatos) value;
        setText(contatos.getNome());
        setIcon(contatos.getImage());
        setForeground(formListFriends.getForeground());
        setBackground(formListFriends.getBackground());
        return this;
    }

}
