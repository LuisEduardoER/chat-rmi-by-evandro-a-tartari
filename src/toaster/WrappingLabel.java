package toaster;


import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Enumeration;
import java.util.Vector;

/**
 * VWrappingLabel is based on Symantec's class WrappingLabel; however, this
 * class can format the text vertically, too. It also wraps text at newlines
 * embedded in the label's text.
 *
 * @see symantec.awt.WrappingLabel
 * @author Paul F. Williams (mailto:paul@criterioninc.com) Criterion, Inc.
 *         (http://www.criterioninc.com)
 * @author Kyle Morris (mailto:morriskg@nexusfusion.com)
 *
 */

public class WrappingLabel extends Canvas {
    /**
     * 
     */
    private static final long serialVersionUID = -4349515078330379983L;
    protected String text;
    protected float m_nHAlign;
    protected float m_nVAlign;
    protected int baseline;
    protected FontMetrics fm;

    public WrappingLabel() {
        this(""); 
    }

    public WrappingLabel(String s) {
        this(s, Canvas.LEFT_ALIGNMENT, Canvas.CENTER_ALIGNMENT);
    }

    public WrappingLabel(String s, float nHorizontal, float nVertical) {
        super();
        setText(s);
        setHAlignStyle(nHorizontal);
        setVAlignStyle(nVertical);
    }

    public float getHAlignStyle() {
        return m_nHAlign;
    }

    public float getVAlignStyle() {
        return m_nVAlign;
    }

    public String getText() {
        return text;
    }

    public void setHAlignStyle(float a) {
        m_nHAlign = a;
        invalidate();
    }

    public void setVAlignStyle(float a) {
        m_nVAlign = a;
        invalidate();
    }

    public void setText(String s) {
        text = s;
        repaint();
    }


    public String paramString() {
        return ""; 
    }

    public Dimension getMinimumSize() {
        fm = getFontMetrics(getFont());
        return new Dimension(9999, breakIntoLines(text, 9999).size() * fm.getHeight());
    }

    public Dimension getPreferredSize() {
        return new Dimension(super.getPreferredSize().width, getMinimumSize().height);
    }

    @SuppressWarnings("unchecked")
    public void paint(Graphics g) {
        if (text != null) {
            Dimension d;
            int currentY = 0;
            Vector lines;

            fm = getFontMetrics(getFont());
            baseline = fm.getMaxAscent();

            d = getSize();

            lines = breakIntoLines(text, d.width);

            if (m_nVAlign == Canvas.CENTER_ALIGNMENT) {
                int center = (d.height / 2);
                currentY = center - ((lines.size() / 2) * fm.getHeight());
            }
            else if (m_nVAlign == Canvas.BOTTOM_ALIGNMENT) {
                currentY = d.height - (lines.size() * fm.getHeight());
            }

            Enumeration elements = lines.elements();
            while (elements.hasMoreElements()) {
                drawAlignedString(g, (String) (elements.nextElement()), 0, currentY, d.width);
                currentY += fm.getHeight();
            }

            fm = null;
        }
    }

    @SuppressWarnings("unchecked")
    protected Vector breakIntoLines(String s, int width) {
        int fromIndex = 0;
        int pos = 0;
        int bestpos;
        String largestString;
        Vector lines = new Vector();

        while (fromIndex != -1) {
            while (fromIndex < text.length() && text.charAt(fromIndex) == ' ') {
                ++fromIndex;
                if (fromIndex >= text.length())
                    break;
            }

            pos = fromIndex;
            bestpos = -1;
            largestString = null;

            while (pos >= fromIndex) {
                boolean bHardNewline = false;
                int newlinePos = text.indexOf('\n', pos);
                int spacePos = text.indexOf(' ', pos);

                if (newlinePos != -1 && // there is a newline and either
                                ((spacePos == -1) || // 1. there is no space,
                                                        // or
                                (spacePos != -1 && newlinePos < spacePos)))
                {
                    pos = newlinePos;
                    bHardNewline = true;
                } else {
                    pos = spacePos;
                    bHardNewline = false;
                }

                if (pos == -1) {
                    s = text.substring(fromIndex);
                } else {
                    s = text.substring(fromIndex, pos);
                }

                if (fm.stringWidth(s) < width) {
                    largestString = s;
                    bestpos = pos;

                    if (bHardNewline)
                        bestpos++;
                    if (pos == -1 || bHardNewline)
                        break;
                } else {
                    break;
                }

                ++pos;
            }

            if (largestString == null) {
                int totalWidth = 0;
                int oneCharWidth = 0;

                pos = fromIndex;

                while (pos < text.length()) {
                    oneCharWidth = fm.charWidth(text.charAt(pos));
                    if ((totalWidth + oneCharWidth) >= width)
                        break;
                    totalWidth += oneCharWidth;
                    ++pos;
                }

                lines.addElement(text.substring(fromIndex, pos));
                fromIndex = pos;
            } else {
                lines.addElement(largestString);
                fromIndex = bestpos;
            }
        }

        return lines;
    }

    protected void drawAlignedString(Graphics g, String s, int x, int y, int width) {
        int drawx;
        int drawy;

        drawx = x;
        drawy = y + baseline;

        if (m_nHAlign != Canvas.LEFT_ALIGNMENT) {
            int sw;

            sw = fm.stringWidth(s);

            if (m_nHAlign == Canvas.CENTER_ALIGNMENT) {
                drawx += (width - sw) / 2;
            } else if (m_nHAlign == Canvas.RIGHT_ALIGNMENT) {
                drawx = drawx + width - sw;
            }
        }

        g.drawString(s, drawx, drawy);
    }
}
