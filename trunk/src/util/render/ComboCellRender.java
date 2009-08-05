package util.render;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ComboCellRender extends JLabel implements ListCellRenderer {

    /**
     * 
     */
    private static final long serialVersionUID = -4137464347975467442L;
    
    public ComboCellRender() {
        setOpaque(true);
        setIconTextGap(12);
    }
    
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        Object[] combo = (Object[]) value;
        setText((String)combo[0]);
        setIcon((ImageIcon)combo[1]);
        return this;
    }

}
