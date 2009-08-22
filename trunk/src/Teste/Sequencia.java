package Teste;

public class Sequencia {
	private String sequencia;
	private Integer contator;

	public void setSequencia(String sequencia) {
		this.sequencia = sequencia;
	}

	public String getSequencia() {
		return sequencia;
	}

	public void setContator(Integer contator) {
		this.contator = contator;
	}

	public Integer getContator() {
		return contator;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Sequencia) {
			Sequencia other = (Sequencia) obj;
			return other.getSequencia().equals(this.getSequencia());
		}
		return super.equals(obj);
	}

}
