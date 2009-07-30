package util;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
/**
 * 
 * @author evandro.tartari
 *
 */
public class JTextPaneI extends JTextPane {
    /**
     * 
     */
    private static final long serialVersionUID = -2460848497705216042L;
    private DefaultStyledDocument m_defaultStyledDocument = new DefaultStyledDocument();

    /** constructor */
    public JTextPaneI() {
        this.setDocument(m_defaultStyledDocument);
    }

    /** append text */
    public void append(String string, Color color, Boolean isBold, Boolean isItalic) {
        try {
            SimpleAttributeSet attr = new SimpleAttributeSet();
            StyleConstants.setForeground(attr, color);
            StyleConstants.setBold(attr, isBold);
            StyleConstants.setItalic(attr, isItalic);
            m_defaultStyledDocument.insertString(m_defaultStyledDocument
                    .getLength(), string, attr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** append text in default color */
    protected void append(String string) {
        append(string, Color.BLUE, false, false);
    }
}
