package util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class ControladorJTextArea extends PlainDocument {
    private static final long serialVersionUID = -6614192618672859135L;
    private int iMaxLengthForLine;
    private int acumulador;

    public ControladorJTextArea(int maxlenForLine) {
        super();
        iMaxLengthForLine = maxlenForLine;
        acumulador = maxlenForLine;
    }

    public void insertString(int offset, String str, AttributeSet attr)
            throws BadLocationException {
        if (str == null)
            return;

        if (iMaxLengthForLine <= 0) {
            super.insertString(offset, str, attr);
            return;
        }

        int ilen = (getLength() + str.length());
        if (ilen <= iMaxLengthForLine)
            super.insertString(offset, str, attr);
        else {
            super.insertString(offset, "\n", attr);
            iMaxLengthForLine += acumulador + 1;
        }
    }
}
