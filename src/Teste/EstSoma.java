package Teste;

import java.util.ArrayList;
import java.util.List;

public class EstSoma {
    private static Double mediaSoma = 0.0;
    private static Double varMedia = 0.0;
    private static String sequenciaVariacao = "";

    public static List<String> mediaSomaNumeros(List<Bolas> est) {
        StringBuilder sb;
        List<String> lista = new ArrayList<String>();
        for (Bolas testeB : est) {
            sb = new StringBuilder();
            if (mediaSoma == 0) {
                mediaSoma = testeB.getSoma().doubleValue();
            } else {
                mediaSoma = (mediaSoma + testeB.getSoma()) / 2;
            }
            sb.append("Concurso: ");
            sb.append(testeB.getId() + " ");
            sb.append("Soma: ");
            sb.append(testeB.getSoma() + "");
            lista.add(sb.toString() + "\r\n");
        }
        return lista;
    }

    public static Double getMediaSoma() {
        return EstSoma.mediaSoma;
    }

    public static Double getVarMedia(List<Bolas> est) {
        for (int i = 0; i < est.size() - 1; i++) {
            Bolas a = est.get(i);
            Bolas b = est.get(i + 1);
            Integer variacao = a.getSoma() - b.getSoma();
            if (variacao < 0) {
                variacao = variacao * -1;
                sequenciaVariacao += "- ";
            } else
                sequenciaVariacao += "+ ";
            if (varMedia == 0) {
                varMedia = variacao.doubleValue();
            } else {
                varMedia = (varMedia + variacao) / 2;
            }
        }
        return EstSoma.varMedia;
    }
    
    public static String getSequenciaVariacao(){
        return EstSoma.sequenciaVariacao;
    }
}
