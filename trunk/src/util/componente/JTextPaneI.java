package util.componente;

import java.awt.Color;
import java.awt.Event;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import util.Util;
import cliente.Mensagem;
import forms.FormConversa;

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
                    .getLength(), getTextFormat(mensagem.getMensagem()), attr);
            insertTabulacao();
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
        insertTabulacao();
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
                    .getLength(), getTextFormat(text), attr);
            newSimpleAttributeSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void adicionaText(String mensagem, Object[] config) {
        try {
            StyleConstants.setFontFamily(attr, (String) config[0]);
            StyleConstants.setForeground(attr, (Color) config[1]);
            StyleConstants.setFontSize(attr, (Integer) config[2]);
            StyleConstants.setBold(attr, (Boolean) config[3]);
            StyleConstants.setItalic(attr, (Boolean) config[4]);
            StyleConstants.setUnderline(attr, (Boolean) config[5]);
            m_defaultStyledDocument.insertString(m_defaultStyledDocument
                    .getLength(), getTextFormat(mensagem), attr);
            newSimpleAttributeSet(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void appendMsgIcon(String mensagem, Object[] config) {
        String[] msg = mensagem.split("<");
        for (int i = 0; i < msg.length; i++) {
            if (msg[i].contains(">")) {
                String[] msg2 = msg[i].split(">");
                for (int j = 0; j < msg2.length; j++) {
                    if (j == 0)
                        adicionaIcon(mensagem, config, msg2[j]);
                    else
                        adicionaText(msg2[j], config);
                }
            } else {
                adicionaText(msg[i], config);
            }
        }
    }

    private void adicionaIcon(String mensagem, Object[] config,
            String urlMapIcon) {
        newSimpleAttributeSet();
        ClassLoader clazz = this.getClass().getClassLoader();
        URL res = clazz.getResource(Util.Emotions.getEmotions().get(
                "<" + urlMapIcon + ">"));
        ImageIcon icon = new ImageIcon(res);
        if (icon != null) {
            try {
                StyleConstants.setFontFamily(attr, (String) config[0]);
                StyleConstants.setForeground(attr, (Color) config[1]);
                StyleConstants.setFontSize(attr, (Integer) config[2]);
                StyleConstants.setBold(attr, (Boolean) config[3]);
                StyleConstants.setItalic(attr, (Boolean) config[4]);
                StyleConstants.setUnderline(attr, (Boolean) config[5]);
                StyleConstants.setIcon(attr, icon);
                m_defaultStyledDocument.insertString(m_defaultStyledDocument
                        .getLength(), "<" + urlMapIcon + ">", attr);
                newSimpleAttributeSet(config);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            adicionaText(urlMapIcon, config);
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

    private void newSimpleAttributeSet(Object[] config) {
        attr = new SimpleAttributeSet();
        StyleConstants.setFontFamily(attr, (String) config[0]);
        StyleConstants.setForeground(attr, (Color) config[1]);
        StyleConstants.setFontSize(attr, (Integer) config[2]);
        StyleConstants.setBold(attr, (Boolean) config[3]);
        StyleConstants.setItalic(attr, (Boolean) config[4]);
        StyleConstants.setUnderline(attr, (Boolean) config[5]);
    }

    private String getTextFormat(String mensagem) {
        String text = mensagem.replace("&lt;", "<");
        text = text.replace("&gt;", ">");
        text = text.replace("\r\n", "");
        return text;
    }

    private void insertTabulacao() {
        try {
            newSimpleAttributeSet();
            m_defaultStyledDocument.insertString(m_defaultStyledDocument
                    .getLength(), "\r\n", attr);
            newSimpleAttributeSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void renderComponente() {
        String text = Util.FormatedText.findTags(getText()).trim();
        setText("");
        appendMsgIcon(text, FormConversa.getConfig());
    }
    public void addListenersTag() {
        addKeyListener(new KeyListener() {

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 37) {
                    if (e.getModifiers() == 0) {
                        ((JTextPaneI) e.getComponent())
                                .setCaretPosition(newPosition(((JTextPaneI) e
                                        .getComponent()).getCaretPosition(),
                                        getText(), true));
                    }
                    if (e.getModifiers() == 1) {
                        selectTextPrevious((JTextPaneI) e.getComponent());
                    }
                } else if (e.getKeyCode() == 39) {
                    if (e.getModifiers() == 0) {
                        ((JTextPaneI) e.getComponent())
                                .setCaretPosition(newPosition(((JTextPaneI) e
                                        .getComponent()).getCaretPosition(),
                                        getText(), false));
                    } else if (e.getModifiers() == 1) {
                        selectTextNext((JTextPaneI) e.getComponent());
                    }
                } else if (e.getKeyCode() == Event.BACK_SPACE) {
                    ((JTextPaneI) e.getComponent()).setText(deleteTag(
                            ((JTextPaneI) e.getComponent()).getCaretPosition(),
                            getText()));
                    ((JTextPaneI) e.getComponent()).renderComponente();
                }
            }

            private void selectTextPrevious(JTextPaneI comp) {
                String text = comp.getText();
                int position = comp.getCaretPosition();
                if (PreviusIsEndTag(position, text)) {
                    int positionInit = pegaTag(text, position, true);
                    if (position != positionInit) {
                        comp.moveCaretPosition(positionInit);
                    }
                }
            }

            private void selectTextNext(JTextPaneI comp) {
                String text = comp.getText();
                int position = comp.getCaretPosition();
                if (NextStartTag(position, text)) {
                    int positionEnd = pegaTag(text, position, false);
                    if (position != positionEnd) {
                        comp.moveCaretPosition(positionEnd);
                    }
                }
            }

            private String deleteTag(int position, String text) {
                if (position == 0)
                    return text;
                else {
                    if (PreviusIsEndTag(position, text)) {
                        int positionInit = pegaTag(text, position, true) - 1;
                        if (positionInit == -1)
                            positionInit = 0;
                        text = text.substring(0, positionInit + 1);
                        return text;
                    } else
                        return text;
                }
            }

            private int newPosition(int position, String text, boolean back) {
                if (back) {
                    if (position == 0)
                        return 0;
                    else {
                        if (PreviusIsEndTag(position, text))
                            return pegaTag(text, position, back);
                        else
                            return position;
                    }
                } else {
                    if (position == text.length())
                        return position;
                    else {
                        if (NextStartTag(position, text))
                            return pegaTag(text, position, back);
                        else
                            return position;
                    }
                }
            }

            private int pegaTag(String text, int position, boolean back) {
                if (back) {
                    Integer positionInit = null;
                    for (int i = position - 1; i >= 0; i--) {
                        if (((Character) text.charAt(i)).toString().equals("<")) {
                            positionInit = i;
                            break;
                        }
                    }
                    if (positionInit != null) {
                        String tag = text.substring(positionInit, position);
                        if (verificaTag(tag))
                            if (positionInit > 0)
                                return positionInit + 1;
                            else
                                return positionInit;
                        else
                            return position;
                    } else {
                        return position;
                    }
                } else {
                    Integer positionEnd = null;
                    for (int i = position; i < text.length(); i++) {
                        if (((Character) text.charAt(i)).toString().equals(">")) {
                            positionEnd = i;
                            break;
                        }
                    }
                    if (positionEnd != null) {
                        String tag = text.substring(position, positionEnd + 1);
                        if (verificaTag(tag))
                            return positionEnd;
                        else
                            return position;
                    } else {
                        return position;
                    }
                }
            }

            private boolean verificaTag(String tag) {
                Set<String> keyWord = Util.Emotions.getEmotions().keySet();
                for (String key : keyWord) {
                    if (key.equals(tag))
                        return true;

                }
                return false;
            }

            private boolean NextStartTag(int position, String text) {
                if (((Character) text.charAt(position)).toString().equals("<"))
                    return true;
                else
                    return false;
            }

            private boolean PreviusIsEndTag(int position, String text) {
                if (((Character) text.charAt(position - 1)).toString().equals(
                        ">"))
                    return true;
                else
                    return false;
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 46) {
                    if (e.getModifiers() == 1) {
                        ((JTextPaneI) e.getComponent()).renderComponente();
                    }
                }
            }

            public void keyTyped(KeyEvent e) {
            }
        });
        addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                int position = ((JTextPaneI) e.getComponent())
                        .getCaretPosition();
                String text = ((JTextPaneI) e.getComponent()).getText();
                if (position == 0) {
                    ((JTextPaneI) e.getComponent()).setCaretPosition(position);
                }
                if (position == text.length()) {
                    ((JTextPaneI) e.getComponent()).setCaretPosition(position);
                } else {
                    if (isTag(position, text)) {
                        if (isVerificaTag(position, text)) {
                            ((JTextPaneI) e.getComponent())
                                    .setCaretPosition(newPosition(position,
                                            text));
                        } else {
                            ((JTextPaneI) e.getComponent())
                                    .setCaretPosition(position);
                        }
                    } else {
                        ((JTextPaneI) e.getComponent())
                                .setCaretPosition(position);
                    }
                }
            }

            private boolean isVerificaTag(int position, String text) {
                Integer positionEnd = null;
                Integer positionInit = null;
                for (int i = position; i < text.length(); i++) {
                    if (((Character) text.charAt(i)).toString().equals(">")) {
                        positionEnd = i;
                        break;
                    } else if (((Character) text.charAt(i)).toString().equals(
                            "<")) {
                        positionEnd = null;
                        break;
                    }
                }
                for (int i = position; i >= 0; i--) {
                    if (((Character) text.charAt(i)).toString().equals("<")) {
                        positionInit = i;
                        break;
                    } else if (((Character) text.charAt(i)).toString().equals(
                            ">")) {
                        positionInit = null;
                        break;
                    }
                }
                if (positionInit != null && positionEnd != null) {
                    String tag = text.substring(positionInit, positionEnd + 1);
                    if (verificaTag(tag)) {
                        return true;
                    }
                    return false;
                }
                return false;
            }

            private boolean verificaTag(String tag) {
                Set<String> keyWord = Util.Emotions.getEmotions().keySet();
                for (String key : keyWord) {
                    if (key.equals(tag))
                        return true;
                }
                return false;
            }

            private Integer newPosition(int position, String text) {
                for (int i = position; i < text.length(); i++) {
                    if (((Character) text.charAt(i)).toString().equals(">")) {
                        return i + 1;
                    }
                }
                return null;
            }

            private boolean isTag(int position, String text) {
                for (int i = position; i < text.length(); i++) {
                    if (((Character) text.charAt(i)).toString().equals(">")) {
                        return true;
                    } else if (((Character) text.charAt(i)).toString().equals(
                            "<")) {
                        return false;
                    }
                }
                for (int i = position; i >= 0; i--) {
                    if (((Character) text.charAt(i)).toString().equals(">")) {
                        return false;
                    } else if (((Character) text.charAt(i)).toString().equals(
                            "<")) {
                        return true;
                    }
                }
                return false;
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

        });
    }

}
