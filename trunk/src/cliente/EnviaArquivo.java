package cliente;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;

import contatos.Contatos;
/**
 * 
 * @author evandro.tartari
 *
 */
public class EnviaArquivo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7529325815460234292L;
    private String url;
    private String nomeArquivo;
    private Contatos contatoRecebe;
    private Contatos contatoEnvia;
    private byte[] file;
    private String retorno;

    public EnviaArquivo(String url, Contatos contatoRecebe,
            Contatos contatoEnvia) {
        this.setUrl(url);
        this.setContatoRecebe(contatoRecebe);
        this.setContatoEnvia(contatoEnvia);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        try {
            File file = new File(url);
            byte[] buffer = new byte[(int) file.length()];
            DataInputStream ds;
            ds = new DataInputStream(new FileInputStream(file));
            ds.readFully(buffer);
            ds.close();
            this.setFile(buffer);
            this.setNomeArquivo(file.getName());
            this.url = url;
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    public String getRetorno() {
        return retorno;
    }

}
