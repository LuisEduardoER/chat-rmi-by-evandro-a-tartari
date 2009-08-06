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
        boolean head = true;
        while (mensagem.getMensagem().length() > 0) {
            int posFinal = mensagem.getMensagem().indexOf("<");
            if (posFinal != -1) {
                if (posFinal != 0) {
                    String text = mensagem.getMensagem().substring(0,
                            posFinal - 1);
                    int posIcon = mensagem.getMensagem().indexOf(">");
                    if (posIcon != -1) {
                        String icon = mensagem.getMensagem().substring(
                                posFinal + 1, posIcon);
                        String rest = mensagem.getMensagem().substring(
                                posIcon + 1);
                        compondoMensagemSemIcone(mensagem, color, text, head);
                        head = false;
                        compondoMensagemComIcone(mensagem, color, icon, head);
                        mensagem.setMensagem(rest);
                    } else {
                        compondoMensagemSemIcone(mensagem, color, mensagem
                                .getMensagem(), head);
                    }
                } else if (posFinal == 0) {
                    int posIcon = mensagem.getMensagem().indexOf(">");
                    if (posIcon != -1) {
                        String icon = mensagem.getMensagem().substring(
                                posFinal, posIcon);
                        String rest = mensagem.getMensagem().substring(
                                posIcon + 1);
                        compondoMensagemComIcone(mensagem, color, icon, head);
                        head = false;
                        mensagem.setMensagem(rest);
                    } else {
                        compondoMensagemSemIcone(mensagem, color, mensagem
                                .getMensagem(), head);
                    }
                }

            } else {
                compondoMensagemSemIcone(mensagem, color, mensagem
                        .getMensagem(), head);
            }
        }
    }

    private void compondoMensagemSemIcone(Mensagem mensagem, Color cor,
            String text, boolean head) {
        try {
            if (head == true) {

                StyleConstants.setForeground(attr, cor);
                StyleConstants.setFontSize(attr, mensagem.getFontSize());
                m_defaultStyledDocument.insertString(m_defaultStyledDocument
                        .getLength(), mensagem.getDataHora() + " "
                        + mensagem.getNomeEnvia() + ": ", attr);
                newSimpleAttributeSet();
            } else {
                StyleConstants.setFontFamily(attr, mensagem.getFontFamily());
                StyleConstants.setForeground(attr, mensagem.getColor());
                StyleConstants.setFontSize(attr, mensagem.getFontSize());
                StyleConstants.setBold(attr, mensagem.getIsBold());
                StyleConstants.setItalic(attr, mensagem.getIsItalic());
                StyleConstants.setUnderline(attr, mensagem.getIsSublinhado());
                m_defaultStyledDocument.insertString(m_defaultStyledDocument
                        .getLength(), text, attr);
                newSimpleAttributeSet();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void compondoMensagemComIcone(Mensagem mensagem, Color cor,
            String icon, boolean head) {
        URL res = this.getClass().getClassLoader().getResource(
                Util.Emotions.getEmotions().get(icon));
        if (res != null) {
            insertIcon(new ImageIcon(res));
        } else {
            compondoMensagemSemIcone(mensagem, cor, icon, head);
        }
    }

    private void newSimpleAttributeSet() {
        attr = new SimpleAttributeSet();
    }

}
