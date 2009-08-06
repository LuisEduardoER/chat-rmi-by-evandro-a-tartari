package forms;

import java.awt.Container;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import acao.FormEmotionsListener;

public class FormEmotions extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 2221141831768403270L;

    private List<String> listaEmotions;
    private Map<String, String> mapaEmotions;
    private FormEmotionsListener listener;

    public FormEmotions() {
        listaEmotions = newArrayList();
        mapaEmotions = newHashMap();
        listener = new FormEmotionsListener(mapaEmotions);
    }

    public void inicializar() {
        Integer x = 0;
        Integer y = 0;
        setContentPane(new Container());
        ClassLoader loader = this.getClass().getClassLoader();
        for (String botao : listaEmotions) {
            String action = botao.substring(botao.lastIndexOf("/") + 1, botao
                    .lastIndexOf("."));
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
        setUndecorated(true);
        setVisible(true);

    }

    public List<String> newArrayList() {
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

    private Map<String, String> newHashMap() {
        if (mapaEmotions == null) {
            mapaEmotions = new HashMap<String, String>();
            for (String caminho : listaEmotions) {
                String action = caminho.substring(caminho.lastIndexOf("/") + 1,
                        caminho.lastIndexOf("."));
                mapaEmotions.put(action, caminho);
            }
        }
        return mapaEmotions;
    }

    public static void main(String[] args) {
        FormEmotions emotions = new FormEmotions();
        emotions.inicializar();
    }
}
