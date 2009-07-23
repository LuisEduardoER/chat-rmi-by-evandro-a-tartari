package util;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import forms.FormConversa;

public class DocumentStyle extends DefaultStyledDocument {

    /**
     * 
     */
    private static final long serialVersionUID = 5241225336544770519L;
    private Element rootElement;
    private MutableAttributeSet style;
    private FormConversa form;
    private int lenght;
    private Color color;

    public DocumentStyle(JFrame frame) {
        putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
        rootElement = getDefaultRootElement();
        style = new SimpleAttributeSet();
        this.form = (FormConversa) frame;
    }

    public void insertString(int offset, String str, AttributeSet attr)
            throws BadLocationException {
        super.insertString(offset, str, attr);
        try {
            if(str.contains(form.getCliente().getContatos().getNome())){
                setColor(Color.RED);
            }else{
                setColor(Color.BLUE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        processChangedLines(offset, str.length());
    }

    public void remove(int offset, int length) throws BadLocationException {
        super.remove(offset, length);
        processChangedLines(offset, length);
    }

    public void processChangedLines(int offset, int length)
            throws BadLocationException {
        try {
            this.lenght = form.getCliente().getContatos().getNome().length()+6;
            highlightString(getColor(), 0, lenght, true, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color){
        this.color = color;
    }

    public void highlightString(Color col, int begin, int length, boolean flag,
            boolean bold) {
        StyleConstants.setForeground(style, col);
        StyleConstants.setBold(style, bold);
        setCharacterAttributes(begin, length, style, flag);
    }

    public String getLineString(String content, int line) {
        Element lineElement = rootElement.getElement(line);
        return content.substring(lineElement.getStartOffset(), lineElement
                .getEndOffset() - 1);
    }
}
