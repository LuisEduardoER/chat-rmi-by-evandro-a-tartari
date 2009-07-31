package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * 
 * @author evandro.tartari
 * 
 */
public class RedimencionaImagemIcon {
    public static ImageIcon redimencionaImagem(InputStream urlImagem,
            Integer size, Integer alt, Integer hints) {
        BufferedImage fundo = null;
        try {
            fundo = ImageIO.read(urlImagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Image imagem = fundo.getScaledInstance(size, alt, hints);
        ImageIcon jpg = new ImageIcon(imagem);
        return jpg;
    }
}
