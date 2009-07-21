package contatos;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class Contatos implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4798815528129019468L;

    private String nome;
    private String ipServidor;
    private Integer portaServico;
    private Integer portaCliente;
    private String login;
    private String senha;
    private ImageIcon icon;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIpServidor() {
        return ipServidor;
    }

    public void setIpServidor(String ipServidor) {
        this.ipServidor = ipServidor;
    }

    public Integer getPortaServico() {
        return portaServico;
    }

    public void setPortaServico(Integer portaServico) {
        this.portaServico = portaServico;
    }

    public Integer getPortaCliente() {
        return portaCliente;
    }

    public void setPortaCliente(Integer portaCliente) {
        this.portaCliente = portaCliente;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public ImageIcon getImage() {
        return icon;
    }

}
