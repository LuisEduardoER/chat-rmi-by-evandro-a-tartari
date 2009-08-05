package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import cliente.EnviaArquivo;
import cliente.Mensagem;
import forms.FormConversa;

public class Util {

    public static String getDataHora() {
        Integer hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        Integer minuto = Calendar.getInstance().get(Calendar.MINUTE);
        String dataHora = "";
        if (hora < 10) {
            dataHora = "(0" + hora.toString() + ":";
        } else {
            dataHora = "(" + hora.toString() + ":";
        }
        if (minuto < 10) {
            dataHora += "0" + minuto.toString() + ")";
        } else {
            dataHora += minuto.toString() + ")";
        }
        return dataHora;
    }

    public static Mensagem newMensagem(FormConversa conversa, String text)
            throws Exception {
        return new Mensagem(conversa.getCliente().getContatos().getLogin(),
                conversa.getCliente().getContatos().getNome(), text, Util
                        .getDataHora(), conversa.getFontSize(), conversa
                        .getFontFamily(), conversa.getColor(), conversa
                        .getIsBold(), conversa.getIsItalic(), conversa
                        .getIsSublinhado(), conversa.getContato().getLogin());
    }

    public static Mensagem newMensagemArquivoRecebido(EnviaArquivo arquivo,
            FormConversa conversa) {
        return new Mensagem(arquivo.getContatoRecebe().getLogin(), arquivo
                .getContatoRecebe().getNome(),
                "Arquivo recebido com sucesso no caminho : C:\\MsMundica\\"
                        + arquivo.getNomeArquivo() + "\n", Util.getDataHora(),
                conversa.getFontSize(), conversa.getFontFamily(), conversa
                        .getColor(), conversa.getIsBold(), conversa
                        .getIsItalic(), conversa.getIsSublinhado(), arquivo
                        .getContatoRecebe().getNome());
    }

    public static Mensagem newMensagemRecebeAviso(EnviaArquivo arquivo,
            FormConversa conversa) {
        return new Mensagem(arquivo.getContatoEnvia().getLogin(), arquivo
                .getContatoEnvia().getNome(), arquivo.getRetorno(), Util
                .getDataHora(), conversa.getFontSize(), conversa
                .getFontFamily(), conversa.getColor(), conversa.getIsBold(),
                conversa.getIsItalic(), conversa.getIsSublinhado(), arquivo
                        .getContatoRecebe().getNome());
    }

    public static class RedimencionaImagemIcon {
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

        public static ImageIcon redimencionaImagem(String urlImagem,
                Integer size, Integer alt, Integer hints) {
            BufferedImage fundo = null;
            try {
                fundo = ImageIO.read(new File(urlImagem));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Image imagem = fundo.getScaledInstance(size, alt, hints);
            ImageIcon jpg = new ImageIcon(imagem);
            return jpg;
        }

        public static ImageIcon redimencionaImagem(ImageIcon icon,
                Integer size, Integer alt, Integer hints) {

            try {
                Image image = icon.getImage();
                image = image.getScaledInstance(size, alt, hints);
                ImageIcon jpg = new ImageIcon(image);
                return jpg;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ImageIcon();

        }
    }

    public static class NumeroMaximoCaracteres extends PlainDocument {
        /**
         * 
         */
        private static final long serialVersionUID = -5345956100765302098L;
        private int iMaxLength;

        public NumeroMaximoCaracteres(int maxlen) {
            super();
            iMaxLength = maxlen;
        }

        public void insertString(int offset, String str, AttributeSet attr)
                throws BadLocationException {
            if (str == null)
                return;

            if (iMaxLength <= 0) {
                super.insertString(offset, str, attr);
                return;
            }

            int ilen = (getLength() + str.length());
            if (ilen <= iMaxLength)
                super.insertString(offset, str, attr);
        }
    }
}
