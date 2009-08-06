package forms;

import java.awt.Container;
import java.net.URL;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import util.Util;
import acao.FormEmotionsListener;

public class FormEmotions extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 2221141831768403270L;

    private List<String> listaEmotions;
    private FormEmotionsListener listener;
    private JButton btnEmotions;

    public FormEmotions(JButton btnEmotions, FormConversa formConversa) {
        listaEmotions = newArrayList();
        listener = new FormEmotionsListener(formConversa);
        this.btnEmotions = btnEmotions;
    }

    public void inicializar() {
        Integer x = 0;
        Integer y = 0;
        setContentPane(new Container());
        ClassLoader loader = this.getClass().getClassLoader();
        for (String botao : listaEmotions) {
            String action = "<"+botao.substring(botao.lastIndexOf("/") + 1, botao
                    .lastIndexOf("."))+">";
            JButton btn = new JButton();
            URL res = loader.getResource(botao);
            btn.setContentAreaFilled(false);
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setIcon(new ImageIcon(res));
            btn.setBounds(x, y, 20, 20);
            btn.addMouseListener(listener);
            btn.addActionListener(listener);
            btn.setActionCommand(action);
            if (x < 80) {
                x += 20;
            } else {
                x = 0;
                y += 20;
            }
            add(btn);
        }
        setSize(100, 82);
        setLocationRelativeTo(btnEmotions);
        setUndecorated(true);
        setVisible(true);

    }

    public List<String> newArrayList() {
        return Util.Emotions.getListEmotions();
    }

    public static void main(String[] args) {
        FormEmotions emotions = new FormEmotions(null, null);
        emotions.inicializar();
    }
}
