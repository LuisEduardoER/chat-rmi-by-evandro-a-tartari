package util.componente;

import java.awt.Color;
import java.net.URL;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import util.Util;
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
    private SimpleAttributeSet attr = new SimpleAttributeSet();

    /** constructor */
    public JTextPaneI() {
        this.setDocument(m_defaultStyledDocument);
    }

    /** append text */
    public void append(Mensagem mensagem, Boolean isContato) {
        try {
            if (verificador(mensagem.getMensagem())) {
                if (isContato) {
                    adicionaMensagemComIcone(mensagem, Color.RED);
                } else {
                    adicionaMensagemComIcone(mensagem, Color.blue);
                }
            } else {
                if (isContato) {
                    adicionaMensagemSemIcone(mensagem, Color.RED);
                } else {
                    adicionaMensagemSemIcone(mensagem, Color.blue);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean verificador(String mensagem) {
        Set<String> key = Util.Emotions.getEmotions().keySet();
        for (String chave : key) {
            if (mensagem.contains(chave)) {
                return true;
            }
        }
        return false;
    }

    private void adicionaMensagemSemIcone(Mensagem mensagem, Color color) {
        try {
            StyleConstants.setForeground(attr, color);
            StyleConstants.setFontSize(attr, mensagem.getFontSize());
            m_defaultStyledDocument.insertString(m_defaultStyledDocument
                    .getLength(), mensagem.getDataHora() + " "
                    + mensagem.getNomeEnvia() + ": ", attr);
            newSimpleAttributeSet();
            StyleConstants.setFontFamily(attr, mensagem.getFontFamily());
            StyleConstants.setForeground(attr, mensagem.getColor());
            StyleConstants.setFontSize(attr, mensagem.getFontSize());
            StyleConstants.setBold(attr, mensagem.getIsBold());
            StyleConstants.setItalic(attr, mensagem.getIsItalic());
            StyleConstants.setUnderline(attr, mensagem.getIsSublinhado());
            m_defaultStyledDocument.insertString(m_defaultStyledDocument
                    .getLength(), mensagem.getMensagem(), attr);
            newSimpleAttributeSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void adicionaMensagemComIcone(Mensagem mensagem, Color color) {
        String[] msg = mensagem.getMensagem().split("<");
        montaHeader(mensagem, color);
        for (int i = 0; i < msg.length; i++) {
            if (msg[i].contains(">")) {
                String[] msg2 = msg[i].split(">");
                for (int j = 0; j < msg2.length; j++) {
                    if (j == 0)
                        adicionaIcon(mensagem, color, msg2[j]);
                    else
                        adicionaText(mensagem, color, msg2[j]);
                }
            } else {
                adicionaText(mensagem, color, msg[i]);
            }
        }

    }

    private void montaHeader(Mensagem mensagem, Color color) {
        try {
            StyleConstants.setForeground(attr, color);
            StyleConstants.setFontSize(attr, mensagem.getFontSize());
            m_defaultStyledDocument.insertString(m_defaultStyledDocument
                    .getLength(), mensagem.getDataHora() + " "
                    + mensagem.getNomeEnvia() + ": ", attr);
            newSimpleAttributeSet();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void adicionaText(Mensagem mensagem, Color color, String text) {
        try {
            StyleConstants.setFontFamily(attr, mensagem.getFontFamily());
            StyleConstants.setForeground(attr, mensagem.getColor());
            StyleConstants.setFontSize(attr, mensagem.getFontSize());
            StyleConstants.setBold(attr, mensagem.getIsBold());
            StyleConstants.setItalic(attr, mensagem.getIsItalic());
            StyleConstants.setUnderline(attr, mensagem.getIsSublinhado());
            m_defaultStyledDocument.insertString(m_defaultStyledDocument
                    .getLength(), text, attr);
            newSimpleAttributeSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void adicionaIcon(Mensagem mensagem, Color color, String urlMapIcon) {
        newSimpleAttributeSet();
        ClassLoader clazz = this.getClass().getClassLoader();
        URL res = clazz.getResource(Util.Emotions.getEmotions().get(
                "<" + urlMapIcon + ">"));
        ImageIcon icon = new ImageIcon(res);
        if (icon != null) {
            try {
                StyleConstants.setIcon(attr, icon);
                m_defaultStyledDocument.insertString(m_defaultStyledDocument
                        .getLength(), " ", attr);
                newSimpleAttributeSet();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            adicionaText(mensagem, color, urlMapIcon);
        }
    }

    private void newSimpleAttributeSet() {
        attr = new SimpleAttributeSet();
    }

}
