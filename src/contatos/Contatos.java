package contatos;

import java.io.Serializable;
import java.net.URL;

import javax.swing.ImageIcon;

import util.Criptografia;
import util.Util;

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
    private ImageIcon icon;
    private ImageIcon iconUsuario;
    private ImageIcon iconContato;
    
    
    public Contatos() {
    }
    
    public Contatos(String nome){
        setNome(nome);
        setLogin("");
    }
    
    public Contatos(String nome, String urlImagem){
        URL res = this.getClass().getClassLoader().getResource(urlImagem);
        setIcon(new ImageIcon(res));
        setIconUsuario(new ImageIcon(res));
        setNome(nome);
        setLogin("");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = Criptografia.cripto(nome);
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
        this.login = Criptografia.cripto(login);
    }

    public void setIcon(ImageIcon icon) {
        this.icon = Util.RedimencionaImagemIcon.redimencionaImagem(icon, 25, 25, 1500);
    }

    public ImageIcon getIconUsuario() {
        return iconUsuario;
    }

    public void setIconUsuario(ImageIcon iconUsuario) {
        this.iconUsuario = Util.RedimencionaImagemIcon.redimencionaImagem(iconUsuario, 110, 80, 1000);
        this.iconContato = iconUsuario;
        this.icon = Util.RedimencionaImagemIcon.redimencionaImagem(iconUsuario, 25, 25, 1500);
    }
    
    public void setIconListaFechada(){
        ClassLoader clazz = this.getClass().getClassLoader();
        URL res = clazz.getResource("imagens/grupoFechado.png");
        icon = new ImageIcon(res);
    }
    
    public void setIconListaAberta() {
        ClassLoader clazz = this.getClass().getClassLoader();
        URL res = clazz.getResource("imagens/grupoAberto.png");
        icon = new ImageIcon(res);
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
