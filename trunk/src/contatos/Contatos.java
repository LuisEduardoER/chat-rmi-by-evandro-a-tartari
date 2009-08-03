package contatos;

import java.io.Serializable;
import java.net.URL;

import javax.swing.ImageIcon;

import util.RedimencionaImagemIcon;

/**
 * 
 * @author evandro.tartari
 * 
 */
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
    private ImageIcon iconUsuario;
    private ImageIcon iconContato;
    
    
    public Contatos() {
    }
    
    public Contatos(String nome){
        setNome(nome);
    }

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
        this.icon = RedimencionaImagemIcon.redimencionaImagem(icon, 50, 50, 1500);
    }

    public ImageIcon getIconUsuario() {
        return iconUsuario;
    }

    public void setIconUsuario(ImageIcon iconUsuario) {
        this.iconUsuario = RedimencionaImagemIcon.redimencionaImagem(iconUsuario, 110, 80, 1000);
        this.iconContato = iconUsuario;
    }

    public ImageIcon getIconContato() {
        return iconContato;
    }

    public void setIconContato(ImageIcon iconContato) {
        this.iconContato = iconContato;
    }

    public ImageIcon getImage() {
        if (icon == null) {
            ClassLoader clazz = this.getClass().getClassLoader();
            URL res = clazz.getResource("imagens/grupoAberto.png");
            icon = new ImageIcon(res);
        }
        return icon;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Contatos) {
            Contatos other = (Contatos) obj;
            return other.getLogin().equals(this.getLogin());
        }
        return false;
    }

}
