package Teste;

import java.util.ArrayList;
import java.util.List;

public class EstImparPar {
    private static Double impar = 0.0;
    private static Double par = 0.0;
    private static List<String> resultado;

    public static List<String> execute(List<List<Integer>> est) {
        StringBuilder sb;
        for (int i = 0; i < est.size();) {
            sb = new StringBuilder();
            sb.append("Concurso: ");
            sb.append(est.get(i).get(0));
            sb.append("Pares: ");
            sb.append("Impares: ");
            Integer imp = est.get(i + 1).size();
            sb.append(impar);
            Integer pAr = est.get(i + 2).size();
            sb.append(par);
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
}
