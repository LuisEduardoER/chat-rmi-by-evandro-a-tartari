package toaster;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

import util.Util;
import util.componente.JTextPaneI;

public class Toaster extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 2073978285458422975L;
    private ImageIcon icone;
    private Dimension dimensao;
    private ControladorToaster controladorToaster;
    private String nomePopUp;
    private JLabel icon = new JLabel("", JLabel.CENTER);
    private JLabel close = newJLabelIcon("", JLabel.RIGHT,
            "imagens/closeModificado.png", 155, 0, 15, 15);
    private JLabel imgCima = newJLabelIcon("", JLabel.RIGHT,
            "imagens/imgCima.png", 0, 0, 170, 30);
    private JLabel imgLadoR = newJLabelIcon("", JLabel.RIGHT,
            "imagens/imgLados.png", 0, 30, 170, 95);
    private JLabel imgLadoL = newJLabelIcon("", JLabel.RIGHT,
            "imagens/imgLados.png", 0, 30, 5, 95);
    private JLabel imgBaixo = newJLabelIcon("", JLabel.RIGHT,
            "imagens/imgBaixo.png", 0, 115, 170, 5);
    private JLabel tituloToaster = new JLabel("", JLabel.CENTER);
    private JTextPaneI txtReceptor = newJTextPaneI();

    public Toaster(String text, String name , ImageIcon icone, Integer posicaoX,
            Integer posicaoY, ControladorToaster controladorToaster) {
        this.dimensao = new Dimension(posicaoX, posicaoY);
        this.controladorToaster = controladorToaster;
        txtReceptor.setText(text);
        setIcone(icone);
        tituloToaster.setFont(new Font("", Font.BOLD, 12));
        tituloToaster.setText(name);
        tituloToaster.setForeground(Color.BLUE);
        tituloToaster.setBounds(14, 30, 140, 20);
    }

    public void init() {
        setContentPane(new Container());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBackground(Color.white);
        setSize(170, 120);
        icon.setVerticalAlignment(JLabel.CENTER);
        close.setVerticalAlignment(JLabel.TOP);
        add(getImageContato());
        add(close);
        add(icon);
        add(imgCima);
        add(txtReceptor);
        add(imgLadoL);
        add(imgLadoR);
        add(imgBaixo);
        add(tituloToaster);
        setLocation(((Double) dimensao.getWidth()).intValue(),
                ((Double) dimensao.getHeight()).intValue());
        setUndecorated(true);
    }

    public void render() {
        setVisible(true);
    }

    public void addListener() {
        close.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                dispose();
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                ClassLoader clazz = this.getClass().getClassLoader();
                URL res = clazz
                        .getResource("imagens/closeModificadoPressionado.png");
                close.setIcon(new ImageIcon(res));
            }

            public void mouseReleased(MouseEvent e) {
            }
        });

    }

    private JLabel newJLabelIcon(String text, int position, String urlImagem,
            int x, int y, int size, int alt) {
        JLabel lbl = new JLabel(text, position);
        ClassLoader clazz = this.getClass().getClassLoader();
        URL res = clazz.getResource(urlImagem);
        lbl.setIcon(new ImageIcon(res));
        lbl.setBounds(x, y, size, alt);
        return lbl;
    }

    private JTextPaneI newJTextPaneI() {
        JTextPaneI txtReceptor = new JTextPaneI();
        txtReceptor.setBounds(55, 50, 105, 65);
        txtReceptor.setEditable(false);
        return txtReceptor;
    }

    public JLabel getImageContato() {
        JLabel lbl = new JLabel();
        if(getIcone()==null){
            return new JLabel();
        }
        lbl.setIcon(Util.RedimencionaImagemIcon.redimencionaImagem(getIcone(),
                50, 50, 500));
        lbl.setBounds(7, 56, 50, 50);
        return lbl;
    }

    public void setIcone(ImageIcon icone) {
        this.icone = icone;
    }

    public ImageIcon getIcone() {
        return icone;
    }

    public void dispose() {
        controladorToaster.reordena(this);
        super.dispose();
    }

    public void setNomePopUp(String nomePopUp) {
        this.nomePopUp = nomePopUp;
    }

    public String getNomePopUp() {
        return nomePopUp;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((nomePopUp == null) ? 0 : nomePopUp.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Toaster other = (Toaster) obj;
        if (nomePopUp == null) {
            if (other.nomePopUp != null)
                return false;
        } else if (!nomePopUp.equals(other.nomePopUp))
            return false;
        return true;
    }

    public void start(int posicaoX, int posicaoY) {
        new ThreadApresentaToaster(this, posicaoX, posicaoY).start();
    }

    public void reordena(int posicaoX, int posicaoY) {
        new ThreadReordena(this, posicaoX, posicaoY).start();

    }

}
