package Teste;

public class EstPar {
	private static EstPar est;
	private Integer num03 = 0;
	private Integer num04 = 0;
	private Integer num05 = 0;
	private Integer num06 = 0;
	private Integer num07 = 0;
	private Integer num08 = 0;
	private Integer num09 = 0;
	private Integer num10 = 0;
	private Integer num11 = 0;
	private Integer num12 = 0;

	private EstPar() {

	}

	public Integer getNum03() {
		return num03;
	}

	public void setNum03(Integer num03) {
		this.num03 = num03;
	}

	public Integer getNum04() {
		return num04;
	}

	public void setNum04(Integer num04) {
		this.num04 = num04;
	}

	public Integer getNum05() {
		return num05;
	}

	public void setNum05(Integer num05) {
		this.num05 = num05;
	}

	public Integer getNum06() {
		return num06;
	}

	public void setNum06(Integer num06) {
		this.num06 = num06;
	}

	public Integer getNum07() {
		return num07;
	}

	public void setNum07(Integer num07) {
		this.num07 = num07;
	}

	public Integer getNum08() {
		return num08;
	}

	public void setNum08(Integer num08) {
		this.num08 = num08;
	}

	public Integer getNum09() {
		return num09;
	}

	public void setNum09(Integer num09) {
		this.num09 = num09;
	}

	public Integer getNum10() {
		return num10;
	}

	public void setNum10(Integer num10) {
		this.num10 = num10;
	}

	public Integer getNum11() {
		return num11;
	}

	public void setNum11(Integer num11) {
		this.num11 = num11;
	}

	public Integer getNum12() {
		return num12;
	}

	public void setNum12(Integer num12) {
		this.num12 = num12;
	}

	public void setNumero(Integer numero) {
		if (numero == 3) {
			num03++;
		} else if (numero == 4) {
			num04++;
		} else if (numero == 5) {
			num05++;
		} else if (numero == 6) {
			num06++;
		} else if (numero == 7) {
			num07++;
		} else if (numero == 8) {
			num08++;
		} else if (numero == 9) {
			num09++;
		} else if (numero == 10) {
			num10++;
		} else if (numero == 11) {
			num11++;
		} else if (numero == 12) {
			num12++;
		}

	}

	public static EstPar getInstancia() {
		if (est == null) {
			est = new EstPar();
		}
		return est;
	}

}
