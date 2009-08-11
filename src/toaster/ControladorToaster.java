package toaster;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class ControladorToaster extends JDialog {
    /**
     * 
     */
    private static final long serialVersionUID = 1021918571685456461L;
    private Toaster toaster;
    private List<Toaster> listaPopups;
    private final Integer posicaoX;
    private Integer posicaoY;

    public ControladorToaster() {
        posicaoX = getToolkit().getScreenSize().width - 170;
        posicaoY = getToolkit().getScreenSize().height + 155;
        listaPopups = new ArrayList<Toaster>();
    }

    public void addToaster(String text, ImageIcon icon) {
        toaster = new Toaster(text, icon, posicaoX, posicaoY, this);
        toaster.init();
        toaster.addListener();
        toaster.render();
        toaster.setNomePopUp("1" + posicaoY);
        toaster.start(posicaoX, posicaoY);
        listaPopups.add(toaster);
        posicaoY -= 120;
    }

    public void reordena(Toaster toaster) {
        listaPopups.remove(toaster);
        int newPosicaoY = getToolkit().getScreenSize().height - 150;
        if (listaPopups.size() > 0) {
            for (Toaster popup : listaPopups) {
                popup.reordena(posicaoX, newPosicaoY);
                newPosicaoY -= 120;
            }
        } else {
            posicaoY += 120;
        }
    }

    public static void main(String[] args) {
        new ControladorToaster();
    }
}
