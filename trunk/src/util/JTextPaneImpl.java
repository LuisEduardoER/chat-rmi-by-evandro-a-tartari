package util;

import java.awt.Component;

import javax.swing.JTextPane;
import javax.swing.plaf.ComponentUI;

public class JTextPaneImpl extends JTextPane {
        /**
     * 
     */
        private static final long serialVersionUID = -3294172749626511633L;
        private StringBuilder sb;
        public JTextPaneImpl() {
            super();
            sb = new StringBuilder();
        }
        public boolean getScrollableTracksViewportWidth() {
            Component parent = getParent();
            ComponentUI ui = getUI();

            return parent != null ? (ui.getPreferredSize(this).width <= parent
                    .getSize().width) : true;
        }
        
        public void append(String text){
            sb.append(text+"\n");
            setText(sb.toString());
        }
    

}
