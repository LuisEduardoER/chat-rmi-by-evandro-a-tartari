package util;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import cliente.Mensagem;

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
    public void append(Mensagem mensagem, Boolean isContato) {
        try {
            if(isContato){
                SimpleAttributeSet attr = new SimpleAttributeSet();
                StyleConstants.setForeground(attr, Color.RED);
                StyleConstants.setFontSize(attr, mensagem.getFontSize());
                m_defaultStyledDocument.insertString(m_defaultStyledDocument
                        .getLength(), mensagem.getUsuarioEnvia(), attr);
                attr = new SimpleAttributeSet();
                StyleConstants.setFontFamily(attr, mensagem.getFontFamily());
                StyleConstants.setForeground(attr, mensagem.getColor());
                StyleConstants.setFontSize(attr, mensagem.getFontSize());
                StyleConstants.setBold(attr, mensagem.getIsBold());
                StyleConstants.setItalic(attr, mensagem.getIsItalic());
                m_defaultStyledDocument.insertString(m_defaultStyledDocument
                        .getLength(), mensagem.getMensagem()+"\n", attr);
            }else{
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
