package Teste;

import java.util.ArrayList;
import java.util.List;

public class EstSequencia {

	private static List<Sequencia> lista;
	private static EstSequencia seq;

	private EstSequencia() {

	}

	public static void geraListaSequencia(List<Bolas> ordenada) {
		lista = new ArrayList<Sequencia>();
		for (Bolas bolas : ordenada) {
			String seq = "";
			seq = sequencia(seq, bolas.getB01(), bolas.getB02());
			seq = sequencia(seq, bolas.getB02(), bolas.getB03());
			seq = sequencia(seq, bolas.getB03(), bolas.getB04());
			seq = sequencia(seq, bolas.getB04(), bolas.getB05());
			seq = sequencia(seq, bolas.getB05(), bolas.getB06());
			seq = sequencia(seq, bolas.getB06(), bolas.getB07());
			seq = sequencia(seq, bolas.getB07(), bolas.getB08());
			seq = sequencia(seq, bolas.getB08(), bolas.getB09());
			seq = sequencia(seq, bolas.getB09(), bolas.getB10());
			seq = sequencia(seq, bolas.getB10(), bolas.getB11());
			seq = sequencia(seq, bolas.getB11(), bolas.getB12());
			seq = sequencia(seq, bolas.getB12(), bolas.getB13());
			seq = sequencia(seq, bolas.getB13(), bolas.getB14());
			seq = sequencia(seq, bolas.getB14(), bolas.getB15());
			Sequencia find = new Sequencia();
			find.setSequencia(seq);
			int posicao = lista.indexOf(find);
			if (posicao != -1) {
				Sequencia sequencia = lista.get(posicao);
				sequencia.setContator(sequencia.getContator() + 1);
			} else {
				Sequencia sequencia = new Sequencia();
				sequencia.setContator(sequencia.getContator() + 1);
				sequencia.setSequencia(seq);
			}

		}
	}

	public static String sequencia(String sequencia, Double bola, Double bola1) {

		if (bola == bola1 - 1) {
			if (sequencia.equals("")) {
				return bola + "-" + bola1;
			} else {
				return "-" + bola1;
			}
		}
		return "";
	}

	public static EstSequencia getInstancia() {
		if (seq == null) {
			seq = new EstSequencia();

		}
		return seq;
	}

	public void setLista(List<Sequencia> lista) {
		EstSequencia.lista = lista;
	}

	public static List<Sequencia> getListaEstSequencia() {
		return lista;
	}
}
