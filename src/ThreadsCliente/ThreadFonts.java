package ThreadsCliente;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import acao.FormConnectListener;

public class ThreadFonts extends Thread{
    private FormConnectListener listener;
    private Map<String, Font> mapaFontes;
    private ClassLoader clazz;
    public ThreadFonts(FormConnectListener listener) {
        this.listener = listener;
        mapaFontes = new HashMap<String, Font>();
        clazz = this.getClass().getClassLoader();
    }
    
    public void run() {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/ARIAL.TTF"));
            mapaFontes.put("Arial", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/ariblk.TTF"));
            mapaFontes.put("Arial Black", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/ARIALI.TTF"));
            mapaFontes.put("Arial Italico", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/ARIALBD.TTF"));
            mapaFontes.put("Arial Negrito", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/ARIALBI.TTF"));
            mapaFontes.put("Arial Negrito-Italico", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/BERNHC.TTF"));
            mapaFontes.put("Bernard MT", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/BRUSHSCI.TTF"));
            mapaFontes.put("Brush Script MT", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/comic.TTF"));
            mapaFontes.put("Comic Sans MS", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/comicbd.TTF"));
            mapaFontes.put("Comic Sans MS Negrito", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/COPRGTB.TTF"));
            mapaFontes.put("Copperplate Gothic Bold", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/COPRGTL.TTF"));
            mapaFontes.put("Copperplate Gothic Ligth", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/COURBD.TTF"));
            mapaFontes.put("Courier New Negrito", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/COURBI.TTF"));
            mapaFontes.put("Courier New Negrito-Italico", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/COURI.TTF"));
            mapaFontes.put("Courier New Italico", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/COUR.TTF"));
            mapaFontes.put("Courier New", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/FRSCRIPT.TTF"));
            mapaFontes.put("French Script MT", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/GLSNECB.TTF"));
            mapaFontes.put("Gill Sans MT", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/IMPRISHA.TTF"));
            mapaFontes.put("Imprint MT", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/ITCBLKAD.TTF"));
            mapaFontes.put("Blackadder ITC", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/LCALLIG.TTF"));
            mapaFontes.put("Lucida Calligraphy Italic", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/matrix-miltown.ttf"));
            mapaFontes.put("Matrix MT", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/MATURASC.TTF"));
            mapaFontes.put("Matura MT", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/MTCORSVA.TTF"));
            mapaFontes.put("Monotype Corsiva", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/NIAGENG.TTF"));
            mapaFontes.put("Niagara Engraved", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/NIAGSOL.TTF"));
            mapaFontes.put("Niagara Solid", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/PALSCRI.TTF"));
            mapaFontes.put("Palace Script MT", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/PARCHM.TTF"));
            mapaFontes.put("Parchment", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/RAGE.TTF"));
            mapaFontes.put("Rage Italic", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/SCRIPTBL.TTF"));
            mapaFontes.put("Script MT", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/scriptina.ttf"));
            mapaFontes.put("Scriptina Alternates", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/scriptina-.ttf"));
            mapaFontes.put("Scriptina", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/SHOWG.TTF"));
            mapaFontes.put("Showcard Gothic", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/TIMES.TTF"));
            mapaFontes.put("Time New Roman", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/TIMESBD.TTF"));
            mapaFontes.put("Time New Roman Negrito", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/TIMESBI.TTF"));
            mapaFontes.put("Time New Roman Negrito-Italico", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/TIMESI.TTF"));
            mapaFontes.put("Time New Roman Italico", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/verdana.TTF"));
            mapaFontes.put("Verdana", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/verdanab.TTF"));
            mapaFontes.put("Verdana Negrito", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/verdanai.TTF"));
            mapaFontes.put("Verdana Italico", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/verdanaz.TTF"));
            mapaFontes.put("Verdana Negrito-Italico", font);
            font = Font.createFont(Font.TRUETYPE_FONT, clazz.getResourceAsStream("fontes/VINERITC.TTF"));
            mapaFontes.put("Viner Hand ITC", font);
            listener.setMapFontes(mapaFontes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
