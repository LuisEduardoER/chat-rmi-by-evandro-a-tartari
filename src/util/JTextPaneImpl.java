package util;

import java.awt.Component;

import javax.swing.JTextPane;
import javax.swing.plaf.ComponentUI;

public class JTextPaneImpl extends JTextPane {
        /**
     * 
     */
        private static final long serialVersionUID = -3294172749626511633L;

        public boolean getScrollableTracksViewportWidth() {
            Component parent = getParent();
            ComponentUI ui = getUI();

            return parent != null ? (ui.getPreferredSize(this).width <= parent
                    .getSize().width) : true;
        }
    

}
