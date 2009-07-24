package util;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Shape;

import javax.swing.text.DefaultHighlighter;
import javax.swing.text.JTextComponent;

public class Painter extends DefaultHighlighter.DefaultHighlightPainter {
    private Color cor;

    public Painter(Color cor) {
        super(cor);
        this.cor = cor;
    }

    public void paint(Graphics g, int offs0, int offs1, Shape bounds,
            JTextComponent c) {
        FontMetrics fontM = g.getFontMetrics();
//        g.setColor(Color.white);
//        g.fillRect(0, 0, fontM.stringWidth(c.getText()), fontM.getHeight());
        g.setColor(cor);
        g.drawString(c.getText(), 0, fontM.getAscent());
    }
    
    public void setColor(Color cor){
        this.cor = cor;
    }

}
