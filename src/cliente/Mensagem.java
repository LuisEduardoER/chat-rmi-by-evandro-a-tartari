package cliente;

import java.awt.Font;
import java.io.Serializable;

public class Mensagem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3250781268865777280L;

	private String usuarioEnvia;
	private String mensagem;
	private Font font;
	private Boolean isBold;
	private Boolean isItalic;
	private String contatoRecebe;
	private String nomeEnvia;

	public Mensagem(String usuarioEnvia, String nomeEnvia, String mensagem, Font font,
			Boolean isBold, Boolean isItalic, String contatoRecebe) {
		this.setUsuarioEnvia(usuarioEnvia);
		this.setNomeEnvia(nomeEnvia);
		this.setMensagem(mensagem);
		this.setFont(font);
		this.setIsBold(isBold);
		this.setIsItalic(isItalic);
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
		this.mensagem = mensagem;
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

	public void setFont(Font font) {
		this.font = font;
	}

	public Font getFont() {
		return font;
	}

    public void setNomeEnvia(String nomeEnvia) {
        this.nomeEnvia = nomeEnvia;
    }

    public String getNomeEnvia() {
        return nomeEnvia;
    }

}
