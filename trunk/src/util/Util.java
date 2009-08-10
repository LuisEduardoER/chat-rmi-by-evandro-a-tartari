package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        return new Mensagem(Criptografia.decripto(conversa.getCliente()
                .getContatos().getLogin()), Criptografia.decripto(conversa
                .getCliente().getContatos().getNome()), text, Util
                .getDataHora(), conversa.getFontSize(), conversa
                .getFontFamily(), conversa.getColor(), conversa.getIsBold(),
                conversa.getIsItalic(), conversa.getIsSublinhado(),
                Criptografia.decripto(conversa.getContato().getLogin()));
    }

    public static Mensagem newMensagemArquivoRecebido(EnviaArquivo arquivo,
            FormConversa conversa) {
        return new Mensagem(Criptografia.decripto(arquivo.getContatoRecebe()
                .getLogin()), Criptografia.decripto(arquivo.getContatoRecebe()
                .getNome()),
                "Arquivo recebido com sucesso no caminho : C:\\MsMundica\\"
                        + arquivo.getNomeArquivo() + "\n", Util.getDataHora(),
                conversa.getFontSize(), conversa.getFontFamily(), conversa
                        .getColor(), conversa.getIsBold(), conversa
                        .getIsItalic(), conversa.getIsSublinhado(),
                Criptografia.decripto(arquivo.getContatoRecebe().getNome()));
    }

    public static Mensagem newMensagemRecebeAviso(EnviaArquivo arquivo,
            FormConversa conversa) {
        return new Mensagem(Criptografia.decripto(arquivo.getContatoEnvia()
                .getLogin()), Criptografia.decripto(arquivo.getContatoEnvia()
                .getNome()), arquivo.getRetorno(), Util.getDataHora(), conversa
                .getFontSize(), conversa.getFontFamily(), conversa.getColor(),
                conversa.getIsBold(), conversa.getIsItalic(), conversa
                        .getIsSublinhado(), Criptografia.decripto(arquivo
                        .getContatoRecebe().getNome()));
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

    public static class Emotions {
        private static List<String> listaEmotions;
        private static Map<String, String> mapaEmotions;

        public static List<String> getListEmotions() {
            if (listaEmotions == null) {
                listaEmotions = new ArrayList<String>();
                listaEmotions.add("imagens/spark/adore.png");
                listaEmotions.add("imagens/spark/amazed.png");
                listaEmotions.add("imagens/spark/beat_brick.png");
                listaEmotions.add("imagens/spark/big_smile.png");
                listaEmotions.add("imagens/spark/boss.png");
                listaEmotions.add("imagens/spark/canny.png");
                listaEmotions.add("imagens/spark/choler.png");
                listaEmotions.add("imagens/spark/confuse.png");
                listaEmotions.add("imagens/spark/embarrassed.png");
                listaEmotions.add("imagens/spark/hell_boy.png");
                listaEmotions.add("imagens/spark/look_down.png");
                listaEmotions.add("imagens/spark/matrix.png");
                listaEmotions.add("imagens/spark/sad.png");
                listaEmotions.add("imagens/spark/smile.png");
                listaEmotions.add("imagens/spark/spidy.png");
                listaEmotions.add("imagens/spark/sweet_kiss.png");
                listaEmotions.add("imagens/spark/thbbbpt.png");
                listaEmotions.add("imagens/spark/tire.png");
                listaEmotions.add("imagens/spark/too_sad.png");
                listaEmotions.add("imagens/spark/waaaht.png");
            }
            return listaEmotions;
        }

        public static Map<String, String> getEmotions() {
            if (mapaEmotions == null) {
                mapaEmotions = new HashMap<String, String>();
                for (String caminho : Emotions.getListEmotions()) {
                    String action = "<"
                            + caminho.substring(caminho.lastIndexOf("/") + 1,
                                    caminho.lastIndexOf(".")) + ">";
                    mapaEmotions.put(action, caminho);
                }
            }
            return mapaEmotions;

        }

    }

    public static class FormatedText {
        public static String findTags(String mensagem) {
            String[] msg = mensagem.split(" ");
            String msgFormatada = "";
            for (int i = 0; i < msg.length; i++) {
                msgFormatada += getMsg(msg[i]) + " ";
            }
            return msgFormatada;
        }

        private static String getMsg(String msg) {
            Set<String> key = Util.Emotions.getEmotions().keySet();
            for (String keyWord : key) {
                if (msg.contains(keyWord)) {
                    String replacement = keyWord.replace("<", " ");
                    replacement = replacement.replace(">", " ");
                    msg = msg.replace(keyWord, replacement);
                }
            }
            msg = msg.replace("<", "&lt;");
            msg = msg.replace(">", "&gt;");
            for (String keyWord : key) {
                String replacement = keyWord.replace("<", " ");
                replacement = replacement.replace(">", " ");
                if (msg.contains(replacement)) {
                    msg = msg.replace(replacement, keyWord);
                }

            }
            return msg;
        }

    }
}
