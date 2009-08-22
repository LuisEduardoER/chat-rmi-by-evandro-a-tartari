package Teste;

import java.awt.Container;
import java.awt.TextArea;
import java.util.List;

import javax.swing.JFrame;

public class Main {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4059124652443893691L;
	private TextArea txtListaNaoOrdenada;
	private TextArea txtListaOrdenada;
	private TextArea txtListaImparPar;
	private TextArea txtResultadoEstSoma;
	private TextArea txtResultadoEstImparPar;
	private JFrame frameListaNaoOrdenada;
	private JFrame frameListaOrdenada;
	private JFrame frameListaImparPar;
	private JFrame frameResultadoEstSoma;
	private JFrame frameResultadoEstImparPar;

	public Main() throws Exception {
		newComponentes();
		propComponentes();
		setAndAddComponentes();
		render();
	}

	public void config(JFrame frame, Integer size, Integer alts) {
		frame.setContentPane(new Container());
		frame.setLocationRelativeTo(null);
		frame.setSize(size, alts);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setUndecorated(false);
	}

	public void newComponentes() {
		frameListaNaoOrdenada = new JFrame();
		config(frameListaNaoOrdenada,1000,300);
		frameListaOrdenada = new JFrame();
		config(frameListaOrdenada,1000,300);
		frameListaImparPar = new JFrame();
		config(frameListaImparPar,300,200);
		frameResultadoEstSoma = new JFrame();
		config(frameResultadoEstSoma,300,200);
		frameResultadoEstImparPar = new JFrame();
		config(frameResultadoEstImparPar,300,200);
		txtListaNaoOrdenada = new TextArea();
		txtListaOrdenada = new TextArea();
		txtListaImparPar = new TextArea();
		txtResultadoEstSoma = new TextArea();
		txtResultadoEstImparPar = new TextArea();
	}

	public void propComponentes() {
		txtListaNaoOrdenada.setBounds(0, 0, 1000, 300);
		txtListaOrdenada.setBounds(0, 0, 1000, 300);
		txtListaImparPar.setBounds(0, 0, 300, 200);
		txtResultadoEstSoma.setBounds(0, 0, 300, 200);
		txtResultadoEstImparPar.setBounds(0, 0, 300, 200);
	}

	public void setAndAddComponentes() throws Exception {
		List<Bolas> ordenada = ListaOrdenada.execute();
		List<Bolas> naoOrdenada = Leitor.execute();
		txtListaNaoOrdenada.setText(naoOrdenada.toString());
		frameListaNaoOrdenada.add(txtListaNaoOrdenada);
		txtListaOrdenada.setText(ordenada.toString());
		frameListaOrdenada.add(txtListaOrdenada);
		List<List<Integer>> imparPar = ListaImparPar.execute();
		for (List<Integer> list : imparPar) {
			txtListaImparPar.append(list.toString() + "\r\n");
		}
		frameListaImparPar.add(txtListaImparPar);
		List<String> resultado = EstSoma.mediaSomaNumeros(ordenada);
		for (String res : resultado) {
			txtResultadoEstSoma.append(res);
		}
		txtResultadoEstSoma.append("Total Media: " + EstSoma.getMediaSoma());
		txtResultadoEstSoma.append("Total Variacao Media: "
				+ EstSoma.getVarMedia(ordenada));
		frameResultadoEstSoma.add(txtResultadoEstSoma);
		List<String> resultados = EstImparPar.execute(imparPar);
		for (String res : resultados) {
			txtResultadoEstImparPar.append(res);
		}
		frameResultadoEstImparPar.add(txtResultadoEstImparPar);
	}

	public void render() {
		frameListaNaoOrdenada.setVisible(true);
		frameListaOrdenada.setVisible(true);
		frameListaImparPar.setVisible(true);
		frameResultadoEstSoma.setVisible(true);
		frameResultadoEstImparPar.setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		new Main();

	}
}
