package cliente;

import java.io.Serializable;

public class Mensagem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3250781268865777280L;

	private String usuarioEnvia;
	private String mensagem;
	private Integer fontType;
	private Boolean isBold;
	private Boolean isItalic;
	private String contatoRecebe;

	public Mensagem(String usuarioEnvia, String mensagem, Integer fontType,
			Boolean isBold, Boolean isItalic, String contatoRecebe) {
		this.setUsuarioEnvia(usuarioEnvia);
		this.setMensagem(mensagem);
		this.setFontType(fontType);
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

	public Integer getFontType() {
		return fontType;
	}

	public void setFontType(Integer fontType) {
		this.fontType = fontType;
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

}
