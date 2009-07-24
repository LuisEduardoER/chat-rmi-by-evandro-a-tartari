package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class RedimencionaImagemIcon {
    public static ImageIcon redimencionaImagem(String urlImagem) {
        BufferedImage fundo = null;
        try {
            fundo = ImageIO.read(new File(urlImagem));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Image imagem = fundo.getScaledInstance(100, 120, 2000);
        ImageIcon jpg = new ImageIcon(imagem);
        return jpg;
    }
}
