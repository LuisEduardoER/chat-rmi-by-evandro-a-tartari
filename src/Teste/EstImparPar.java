package Teste;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class EstImparPar {
	private static Double impar = 0.0;
	private static Double par = 0.0;
	private static List<String> resultado;

	public static List<String> execute(List<List<Integer>> est) {
		resultado = new ArrayList<String>();
		StringBuilder sb;
		for (int i = 0; i < est.size();) {
			sb = new StringBuilder();
			sb.append("Concurso: ");
			sb.append(est.get(i).get(0) + " ");
			sb.append("Impares: ");
			Integer imp = est.get(i + 1).size();
			EstImp.getInstancia().setNumero(imp);
			sb.append(imp + " ");
			sb.append("Pares: ");
			Integer pAr = est.get(i + 2).size();
			EstPar.getInstancia().setNumero(pAr);
			sb.append(pAr + " ");
			i += 3;
			if (impar == 0 && par == 0) {
				impar = imp.doubleValue();
				par = pAr.doubleValue();
			} else {
				impar = (impar + imp.doubleValue()) / 2;
				par = (par + pAr.doubleValue()) / 2;
			}
			resultado.add(sb.toString() + "\r\n");
		}

		return resultado;
	}

	public static List<Double> getEstatisticas() {
		List<Double> lista = new ArrayList<Double>();
		lista.add(impar);
		lista.add(par);
		return lista;
	}

	public static List<Integer> getListaEstatisticasImpar() {
		List<Integer> listaInteger = new ArrayList<Integer>();
		Integer num03 = EstImp.getInstancia().getNum03();
		Integer num04 = EstImp.getInstancia().getNum04();
		Integer num05 = EstImp.getInstancia().getNum05();
		Integer num06 = EstImp.getInstancia().getNum06();
		Integer num07 = EstImp.getInstancia().getNum07();
		Integer num08 = EstImp.getInstancia().getNum08();
		Integer num09 = EstImp.getInstancia().getNum09();
		Integer num10 = EstImp.getInstancia().getNum10();
		Integer num11 = EstImp.getInstancia().getNum11();
		Integer num12 = EstImp.getInstancia().getNum12();
		if (num03 != 0 && num03 != null) {
			listaInteger.add(3);
			listaInteger.add(num03 / 454);
		} else if (num04 != 0 && num04 != null) {
			listaInteger.add(4);
			listaInteger.add(num04 / 454);
		} else if (num05 != 0 && num05 != null) {
			listaInteger.add(5);
			listaInteger.add(num05 / 454);
		} else if (num06 != 0 && num06 != null) {
			listaInteger.add(6);
			listaInteger.add(num06 / 454);
		} else if (num07 != 0 && num07 != null) {
			listaInteger.add(7);
			listaInteger.add(num07 / 454);
		} else if (num08 != 0 && num08 != null) {
			listaInteger.add(8);
			listaInteger.add(num08 / 454);
		} else if (num09 != 0 && num09 != null) {
			listaInteger.add(9);
			listaInteger.add(num09 / 454);
		} else if (num10 != 0 && num10 != null) {
			listaInteger.add(10);
			listaInteger.add(num10 / 454);
		} else if (num11 != 0 && num11 != null) {
			listaInteger.add(11);
			listaInteger.add(num11 / 454);
		} else if (num12 != 0 && num12 != null) {
			listaInteger.add(12);
			listaInteger.add(num12 / 454);
		}

		return listaInteger;
	}

	public static List<Integer> getListaEstatisticasPar() {
		List<Integer> listaInteger = new ArrayList<Integer>();
		Integer num03 = EstPar.getInstancia().getNum03();
		Integer num04 = EstPar.getInstancia().getNum04();
		Integer num05 = EstPar.getInstancia().getNum05();
		Integer num06 = EstPar.getInstancia().getNum06();
		Integer num07 = EstPar.getInstancia().getNum07();
		Integer num08 = EstPar.getInstancia().getNum08();
		Integer num09 = EstPar.getInstancia().getNum09();
		Integer num10 = EstPar.getInstancia().getNum10();
		Integer num11 = EstPar.getInstancia().getNum11();
		Integer num12 = EstPar.getInstancia().getNum12();
		if (num03 != 0 && num03 != null) {
			listaInteger.add(3);
			listaInteger.add(num03 / 454);
		} else if (num04 != 0 && num04 != null) {
			listaInteger.add(4);
			listaInteger.add(num04 / 454);
		} else if (num05 != 0 && num05 != null) {
			listaInteger.add(5);
			listaInteger.add(num05 / 454);
		} else if (num06 != 0 && num06 != null) {
			listaInteger.add(6);
			listaInteger.add(num06 / 454);
		} else if (num07 != 0 && num07 != null) {
			listaInteger.add(7);
			listaInteger.add(num07 / 454);
		} else if (num08 != 0 && num08 != null) {
			listaInteger.add(8);
			listaInteger.add(num08 / 454);
		} else if (num09 != 0 && num09 != null) {
			listaInteger.add(9);
			listaInteger.add(num09 / 454);
		} else if (num10 != 0 && num10 != null) {
			listaInteger.add(10);
			listaInteger.add(num10 / 454);
		} else if (num11 != 0 && num11 != null) {
			listaInteger.add(11);
			listaInteger.add(num11 / 454);
		} else if (num12 != 0 && num12 != null) {
			listaInteger.add(12);
			listaInteger.add(num12 / 454);
		}

		return listaInteger;

	}
}
