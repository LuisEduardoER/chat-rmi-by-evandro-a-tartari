package Teste;

import java.util.ArrayList;
import java.util.List;

public class EstAparicoes {
	private static EstAparicoes est;
	private Integer numero01;
	private Integer numero02;
	private Integer numero03;
	private Integer numero04;
	private Integer numero05;
	private Integer numero06;
	private Integer numero07;
	private Integer numero08;
	private Integer numero09;
	private Integer numero10;
	private Integer numero11;
	private Integer numero12;
	private Integer numero13;
	private Integer numero14;
	private Integer numero15;
	private Integer numero16;
	private Integer numero17;
	private Integer numero18;
	private Integer numero19;
	private Integer numero20;
	private Integer numero21;
	private Integer numero22;
	private Integer numero23;
	private Integer numero24;
	private Integer numero25;

	private EstAparicoes() {

	}

	public List<EstAparicoes> geraEstatisticas(List<Bolas> ordenada) {
		List<EstAparicoes> lista = new ArrayList<EstAparicoes>();
		for (Bolas bolas : ordenada) {
			adiciona(bolas.getB01());
			adiciona(bolas.getB02());
			adiciona(bolas.getB03());
			adiciona(bolas.getB04());
			adiciona(bolas.getB05());
			adiciona(bolas.getB06());
			adiciona(bolas.getB07());
			adiciona(bolas.getB08());
			adiciona(bolas.getB09());
			adiciona(bolas.getB10());
			adiciona(bolas.getB11());
			adiciona(bolas.getB12());
			adiciona(bolas.getB13());
			adiciona(bolas.getB14());
			adiciona(bolas.getB15());
		}
		lista.add(this);
		return lista;
	}

	private void adiciona(Double bola) {
		if (bola == 1) {
			numero01++;
		} else if (bola == 2) {
			numero02++;
		} else if (bola == 3) {
			numero03++;
		} else if (bola == 4) {
			numero04++;
		} else if (bola == 5) {
			numero05++;
		} else if (bola == 6) {
			numero06++;
		} else if (bola == 7) {
			numero07++;
		} else if (bola == 8) {
			numero08++;
		} else if (bola == 9) {
			numero09++;
		} else if (bola == 10) {
			numero10++;
		} else if (bola == 11) {
			numero11++;
		} else if (bola == 12) {
			numero12++;
		} else if (bola == 13) {
			numero13++;
		} else if (bola == 14) {
			numero14++;
		} else if (bola == 15) {
			numero15++;
		} else if (bola == 16) {
			numero16++;
		} else if (bola == 17) {
			numero17++;
		} else if (bola == 18) {
			numero18++;
		} else if (bola == 19) {
			numero19++;
		} else if (bola == 20) {
			numero20++;
		} else if (bola == 21) {
			numero21++;
		} else if (bola == 22) {
			numero22++;
		} else if (bola == 23) {
			numero23++;
		} else if (bola == 24) {
			numero24++;
		} else if (bola == 25) {
			numero25++;
		}

	}

	public static EstAparicoes getEst() {
		if (est == null) {
			est = new EstAparicoes();

		}
		return est;
	}

	public Integer getNumero01() {
		return numero01;
	}

	public void setNumero01(Integer numero01) {
		this.numero01 = numero01;
	}

	public Integer getNumero02() {
		return numero02;
	}

	public void setNumero02(Integer numero02) {
		this.numero02 = numero02;
	}

	public Integer getNumero03() {
		return numero03;
	}

	public void setNumero03(Integer numero03) {
		this.numero03 = numero03;
	}

	public Integer getNumero04() {
		return numero04;
	}

	public void setNumero04(Integer numero04) {
		this.numero04 = numero04;
	}

	public Integer getNumero05() {
		return numero05;
	}

	public void setNumero05(Integer numero05) {
		this.numero05 = numero05;
	}

	public Integer getNumero06() {
		return numero06;
	}

	public void setNumero06(Integer numero06) {
		this.numero06 = numero06;
	}

	public Integer getNumero07() {
		return numero07;
	}

	public void setNumero07(Integer numero07) {
		this.numero07 = numero07;
	}

	public Integer getNumero08() {
		return numero08;
	}

	public void setNumero08(Integer numero08) {
		this.numero08 = numero08;
	}

	public Integer getNumero09() {
		return numero09;
	}

	public void setNumero09(Integer numero09) {
		this.numero09 = numero09;
	}

	public Integer getNumero10() {
		return numero10;
	}

	public void setNumero10(Integer numero10) {
		this.numero10 = numero10;
	}

	public Integer getNumero11() {
		return numero11;
	}

	public void setNumero11(Integer numero11) {
		this.numero11 = numero11;
	}

	public Integer getNumero12() {
		return numero12;
	}

	public void setNumero12(Integer numero12) {
		this.numero12 = numero12;
	}

	public Integer getNumero13() {
		return numero13;
	}

	public void setNumero13(Integer numero13) {
		this.numero13 = numero13;
	}

	public Integer getNumero14() {
		return numero14;
	}

	public void setNumero14(Integer numero14) {
		this.numero14 = numero14;
	}

	public Integer getNumero15() {
		return numero15;
	}

	public void setNumero15(Integer numero15) {
		this.numero15 = numero15;
	}

	public Integer getNumero16() {
		return numero16;
	}

	public void setNumero16(Integer numero16) {
		this.numero16 = numero16;
	}

	public Integer getNumero17() {
		return numero17;
	}

	public void setNumero17(Integer numero17) {
		this.numero17 = numero17;
	}

	public Integer getNumero18() {
		return numero18;
	}

	public void setNumero18(Integer numero18) {
		this.numero18 = numero18;
	}

	public Integer getNumero19() {
		return numero19;
	}

	public void setNumero19(Integer numero19) {
		this.numero19 = numero19;
	}

	public Integer getNumero20() {
		return numero20;
	}

	public void setNumero20(Integer numero20) {
		this.numero20 = numero20;
	}

	public Integer getNumero21() {
		return numero21;
	}

	public void setNumero21(Integer numero21) {
		this.numero21 = numero21;
	}

	public Integer getNumero22() {
		return numero22;
	}

	public void setNumero22(Integer numero22) {
		this.numero22 = numero22;
	}

	public Integer getNumero23() {
		return numero23;
	}

	public void setNumero23(Integer numero23) {
		this.numero23 = numero23;
	}

	public Integer getNumero24() {
		return numero24;
	}

	public void setNumero24(Integer numero24) {
		this.numero24 = numero24;
	}

	public Integer getNumero25() {
		return numero25;
	}

	public void setNumero25(Integer numero25) {
		this.numero25 = numero25;
	}

}
