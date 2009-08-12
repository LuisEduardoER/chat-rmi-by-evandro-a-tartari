package toaster;

import forms.FormListFriends;
import gerenteDeTelas.Gerente;

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
    private Gerente gerente; 
    private FormListFriends formList;

    public ControladorToaster(Gerente gerente, FormListFriends formList) {
        this.gerente = gerente;
        this.formList = formList;
        posicaoX = getToolkit().getScreenSize().width - 170;
        posicaoY = getToolkit().getScreenSize().height + 155;
        listaPopups = new ArrayList<Toaster>();
    }

    public void addToaster(String text, String name, ImageIcon icon) {
        if (listaPopups.size() > 0) {
            Toaster find = new Toaster();
            find.setNomePopUp(name);
            int posicao = listaPopups.indexOf(find);
            if (posicao != -1) {
                listaPopups.get(posicao).stopedThread();
                listaPopups.get(posicao).setNome(name);
                listaPopups.get(posicao).setText(text);
                listaPopups.get(posicao).render();
                listaPopups.get(posicao).start(posicaoX,
                        listaPopups.get(posicao).getLocation().y + 305);
            } else {
                toaster = new Toaster(text, name, icon, posicaoX, posicaoY,
                        this);
                toaster.init(gerente, formList);
                toaster.addListener();
                toaster.setNomePopUp(name);
                toaster.render();
                toaster.start(posicaoX, posicaoY);
                listaPopups.add(toaster);
                posicaoY -= 120;
            }
        } else {
            toaster = new Toaster(text, name, icon, posicaoX, posicaoY, this);
            toaster.init(gerente, formList);
            toaster.addListener();
            toaster.render();
            toaster.setNomePopUp(name);
            toaster.start(posicaoX, posicaoY);
            listaPopups.add(toaster);
            posicaoY -= 120;
        }
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

}
