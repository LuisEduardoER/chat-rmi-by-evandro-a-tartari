package Teste;

public class SomaMinMaxRegistrada {
	private static SomaMinMaxRegistrada somaMinMaxReg;
	private Integer faixa120_130;
	private Integer faixa131_140;
	private Integer faixa141_150;
	private Integer faixa151_160;
	private Integer faixa161_170;
	private Integer faixa171_180;
	private Integer faixa181_190;
	private Integer faixa191_200;
	private Integer faixa201_210;
	private Integer faixa211_220;
	private Integer faixa221_230;
	private Integer faixa231_240;
	private Integer faixa241_250;
	private Integer faixa251_260;
	private Integer faixa261_270;
	private Integer minimo;
	private Integer maximo;

	private SomaMinMaxRegistrada() {

	}

	public Integer getFaixa120_130() {
		return faixa120_130;
	}

	public void setFaixa120_130(Integer faixa120_130) {
		this.faixa120_130 = faixa120_130;
	}

	public Integer getFaixa131_140() {
		return faixa131_140;
	}

	public void setFaixa131_140(Integer faixa131_140) {
		this.faixa131_140 = faixa131_140;
	}

	public Integer getFaixa141_150() {
		return faixa141_150;
	}

	public void setFaixa141_150(Integer faixa141_150) {
		this.faixa141_150 = faixa141_150;
	}

	public Integer getFaixa151_160() {
		return faixa151_160;
	}

	public void setFaixa151_160(Integer faixa151_160) {
		this.faixa151_160 = faixa151_160;
	}

	public Integer getFaixa161_170() {
		return faixa161_170;
	}

	public void setFaixa161_170(Integer faixa161_170) {
		this.faixa161_170 = faixa161_170;
	}

	public Integer getFaixa171_180() {
		return faixa171_180;
	}

	public void setFaixa171_180(Integer faixa171_180) {
		this.faixa171_180 = faixa171_180;
	}

	public Integer getFaixa181_190() {
		return faixa181_190;
	}

	public void setFaixa181_190(Integer faixa181_190) {
		this.faixa181_190 = faixa181_190;
	}

	public Integer getFaixa191_200() {
		return faixa191_200;
	}

	public void setFaixa191_200(Integer faixa191_200) {
		this.faixa191_200 = faixa191_200;
	}

	public Integer getFaixa201_210() {
		return faixa201_210;
	}

	public void setFaixa201_210(Integer faixa201_210) {
		this.faixa201_210 = faixa201_210;
	}

	public Integer getFaixa211_220() {
		return faixa211_220;
	}

	public void setFaixa211_220(Integer faixa211_220) {
		this.faixa211_220 = faixa211_220;
	}

	public Integer getFaixa221_230() {
		return faixa221_230;
	}

	public void setFaixa221_230(Integer faixa221_230) {
		this.faixa221_230 = faixa221_230;
	}

	public Integer getFaixa231_240() {
		return faixa231_240;
	}

	public void setFaixa231_240(Integer faixa231_240) {
		this.faixa231_240 = faixa231_240;
	}

	public Integer getFaixa241_250() {
		return faixa241_250;
	}

	public void setFaixa241_250(Integer faixa241_250) {
		this.faixa241_250 = faixa241_250;
	}

	public Integer getFaixa251_260() {
		return faixa251_260;
	}

	public void setFaixa251_260(Integer faixa251_260) {
		this.faixa251_260 = faixa251_260;
	}

	public Integer getFaixa261_270() {
		return faixa261_270;
	}

	public void setFaixa261_270(Integer faixa261_270) {
		this.faixa261_270 = faixa261_270;
	}

	public void adicionaNumero(Integer numero) {
		if(maximo==null & minimo==null){
			maximo = numero; 
			minimo = numero;
		}else{
			if(numero > maximo){
				maximo = numero;
			}else if(numero < minimo){
				minimo = numero;
			}
		}

		if (numero < 130) {
			faixa120_130++;
		} else if (numero > 130 && numero <= 140) {
			faixa131_140++;
		} else if (numero > 140 && numero <= 150) {
			faixa141_150++;
		} else if (numero > 150 && numero <= 160) {
			faixa151_160++;
		} else if (numero > 160 && numero <= 170) {
			faixa161_170++;
		} else if (numero > 170 && numero <= 180) {
			faixa171_180++;
		} else if (numero > 180 && numero <= 190) {
			faixa181_190++;
		} else if (numero > 190 && numero <= 200) {
			faixa191_200++;
		} else if (numero > 200 && numero <= 210) {
			faixa201_210++;
		} else if (numero > 210 && numero <= 220) {
			faixa211_220++;
		} else if (numero > 220 && numero <= 230) {
			faixa221_230++;
		} else if (numero > 230 && numero <= 240) {
			faixa231_240++;
		} else if (numero > 240 && numero <= 250) {
			faixa241_250++;
		} else if (numero > 250 && numero <= 260) {
			faixa251_260++;
		} else if (numero > 260 && numero <= 270) {
			faixa261_270++;
		}
	}

	public static SomaMinMaxRegistrada getInstancia() {
		if (somaMinMaxReg == null) {
			somaMinMaxReg = new SomaMinMaxRegistrada();
		}
		return somaMinMaxReg;
	}

	public void setMinimo(Integer minimo) {
		this.minimo = minimo;
	}

	public Integer getMinimo() {
		return minimo;
	}

	public void setMaximo(Integer maximo) {
		this.maximo = maximo;
	}

	public Integer getMaximo() {
		return maximo;
	}

}
