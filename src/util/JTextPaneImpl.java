package util;

import java.awt.Component;

import javax.swing.JTextPane;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class JTextPaneImpl extends JTextPane {
    /**
     * 
     */
    private static final long serialVersionUID = -3294172749626511633L;

    public boolean getScrollableTracksViewportWidth() {
        Component parent = getParent();
        ComponentUI ui = getUI();

        return parent != null ? (ui.getPreferredSize(this).width <= parent
                .getSize().width) : true;
    }

    public void append(String str) {
        Document doc = getDocument();
        if (doc != null) {
            try {
                doc.insertString(doc.getLength(), str, getCharacterAttributes());
            } catch (BadLocationException e) {
            }
        }
    }
}
