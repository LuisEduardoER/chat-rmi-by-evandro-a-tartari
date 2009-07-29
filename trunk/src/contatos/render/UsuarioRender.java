package contatos.render;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

import contatos.Contatos;

public class UsuarioRender extends JLabel implements ListCellRenderer {
    /**
     * 
     */
    private static final long serialVersionUID = -2995353638159933716L;
    
    
    public UsuarioRender() {
        setOpaque(true);
        setHorizontalAlignment(JLabel.CENTER);
        setIconTextGap(20);
    }
    
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        Border rbbBorda = BorderFactory.createRaisedBevelBorder();       
        Contatos contatos = (Contatos) value;
        setIcon(contatos.getImage());
        setVerticalAlignment(JLabel.PREVIOUS);
        setBorder(rbbBorda);
        setForeground(Color.blue);
        setFont(new Font("verdana", Font.BOLD, 16));
        setText(contatos.getNome());
        return this;
    }

}
