package Teste;

import java.util.ArrayList;
import java.util.List;

public class EstSoma {
    public static Double mediaSoma = 0.0;
    public static Double varMedia = 0.0;

    public static List<String> mediaSomaNumeros(List<TesteB> est) {
        StringBuilder sb;
        List<String> lista = new ArrayList<String>();
        for (TesteB testeB : est) {
            sb = new StringBuilder();
            if (mediaSoma == 0) {
                mediaSoma = testeB.getSoma().doubleValue();
            } else {
                mediaSoma = (mediaSoma + testeB.getSoma())/ 2;
            }
            sb.append("Concurso: ");
            sb.append(testeB.getId() + " ");
            sb.append("Soma: ");
            sb.append(testeB.getSoma()+"");
            lista.add(sb.toString()+"\r\n");
        }
        return lista;
    }
    
    public static Double getVarMedia(){
        return EstSoma.mediaSoma;
    }
}
