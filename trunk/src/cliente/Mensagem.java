package cliente;

import java.awt.Color;
import java.io.Serializable;
import java.util.Set;

import util.Util;

public class Mensagem implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 3250781268865777280L;

    private String usuarioEnvia;
    private String mensagem;
    private String contatoRecebe;
    private String nomeEnvia;
    private String fontFamily;
    private String dataHora;
    private Integer fontSize;
    private Boolean isBold;
    private Boolean isItalic;
    private Boolean isSublinhado;
    private Color color;

    public Mensagem(String usuarioEnvia, String nomeEnvia, String mensagem,
            String dataHora, Integer fontSize, String fontFamily, Color color,
            Boolean isBold, Boolean isItalic, Boolean isSublinhado,
            String contatoRecebe) {
        this.setUsuarioEnvia(usuarioEnvia);
        this.setNomeEnvia(nomeEnvia);
        this.setMensagem(mensagem);
        this.setDataHora(dataHora);
        this.setFontSize(fontSize);
        this.setFontFamily(fontFamily);
        this.setColor(color);
        this.setIsBold(isBold);
        this.setIsItalic(isItalic);
        this.setIsSublinhado(isSublinhado);
        this.setContatoRecebe(contatoRecebe);
    }

    public String getUsuarioEnvia() {
        return usuarioEnvia;
    }

    public void setUsuarioEnvia(String usuarioEnvia) {
        this.usuarioEnvia = usuarioEnvia;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        String msg = findTags(mensagem).trim();
        if (msg.trim().endsWith("\r\n")) {
            int posicao = msg.lastIndexOf("\r\n");
            if(posicao==-1)
                posicao = msg.lastIndexOf("\n\n");
            msg = msg.substring(0, posicao - 1);
        } else if (msg.trim().endsWith("\n") || msg.trim().endsWith("\r")) {
            int posicao = msg.lastIndexOf("\n");
            if (posicao == -1)
                posicao = msg.lastIndexOf("\r");
            msg = msg.substring(0, posicao - 1);
        }
        this.mensagem = msg;
    }

    public Boolean getIsBold() {
        return isBold;
    }

    public void setIsBold(Boolean isBold) {
        this.isBold = isBold;
    }

    public Boolean getIsItalic() {
        return isItalic;
    }

    public void setIsItalic(Boolean isItalic) {
        this.isItalic = isItalic;
    }

    public String getContatoRecebe() {
        return contatoRecebe;
    }

    public void setContatoRecebe(String contatoRecebe) {
        this.contatoRecebe = contatoRecebe;
    }

    public void setNomeEnvia(String nomeEnvia) {
        this.nomeEnvia = nomeEnvia;
    }

    public String getNomeEnvia() {
        return nomeEnvia;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setIsSublinhado(Boolean isSublinhado) {
        this.isSublinhado = isSublinhado;
    }

    public Boolean getIsSublinhado() {
        return isSublinhado;
    }

    private String findTags(String mensagem) {
        String[] msg = mensagem.split(" ");
        String msgFormatada = "";
        for (int i = 0; i < msg.length; i++) {
            msgFormatada += getMsg(msg[i]) + " ";
        }
        return msgFormatada;
    }

    private String getMsg(String msg) {
        Set<String> key = Util.Emotions.getEmotions().keySet();
        for (String keyWord : key) {
            if (msg.contains(keyWord)) {
                String replacement = keyWord.replace("<", " ");
                replacement = replacement.replace(">", " ");
                msg = msg.replace(keyWord, replacement);
            }
        }
        msg = msg.replace("<", "&lt;");
        msg = msg.replace(">", "&gt;");
        for (String keyWord : key) {
            String replacement = keyWord.replace("<", " ");
            replacement = replacement.replace(">", " ");
            if (msg.contains(replacement)) {
                msg = msg.replace(replacement, keyWord);
            }

        }
        return msg;
    }

}
