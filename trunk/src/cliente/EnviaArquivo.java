package cliente;

import java.io.Serializable;

import contatos.Contatos;

public class EnviaArquivo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 7529325815460234292L;
    private String url;
    private Contatos contatoRecebe;
    private Contatos contatoEnvia;
    private byte[] file;

    public EnviaArquivo(String url, Contatos contatoRecebe, Contatos contatoEnvia) {
        this.setUrl(url);
        this.setContatoRecebe(contatoRecebe);
        this.setContatoEnvia(contatoEnvia);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Contatos getContatoRecebe() {
        return contatoRecebe;
    }

    public void setContatoRecebe(Contatos contatoRecebe) {
        this.contatoRecebe = contatoRecebe;
    }

    public Contatos getContatoEnvia() {
        return contatoEnvia;
    }

    public void setContatoEnvia(Contatos contatoEnvia) {
        this.contatoEnvia = contatoEnvia;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

}
