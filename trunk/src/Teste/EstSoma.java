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
            SomaMinMaxRegistrada.getInstancia().adicionaNumero(testeB.getSoma());
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
    
    public static List<Integer> getEstatisticaSoma(){
    	List<Integer> lista = new ArrayList<Integer>();
    	Integer faixa01 = SomaMinMaxRegistrada.getInstancia().getFaixa120_130();
    	Integer faixa02 = SomaMinMaxRegistrada.getInstancia().getFaixa131_140();
    	Integer faixa03 = SomaMinMaxRegistrada.getInstancia().getFaixa141_150();
    	Integer faixa04 = SomaMinMaxRegistrada.getInstancia().getFaixa151_160();
    	Integer faixa05 = SomaMinMaxRegistrada.getInstancia().getFaixa161_170();
    	Integer faixa06 = SomaMinMaxRegistrada.getInstancia().getFaixa171_180();
    	Integer faixa07 = SomaMinMaxRegistrada.getInstancia().getFaixa181_190();
    	Integer faixa08 = SomaMinMaxRegistrada.getInstancia().getFaixa191_200();
    	Integer faixa09 = SomaMinMaxRegistrada.getInstancia().getFaixa201_210();
    	Integer faixa10 = SomaMinMaxRegistrada.getInstancia().getFaixa211_220();
    	Integer faixa11 = SomaMinMaxRegistrada.getInstancia().getFaixa221_230();
    	Integer faixa12 = SomaMinMaxRegistrada.getInstancia().getFaixa231_240();
    	Integer faixa13 = SomaMinMaxRegistrada.getInstancia().getFaixa241_250();
    	Integer faixa14 = SomaMinMaxRegistrada.getInstancia().getFaixa251_260();
    	Integer faixa15 = SomaMinMaxRegistrada.getInstancia().getFaixa261_270();
    	if(faixa01!=null&faixa01>0){
    		lista.add(130);
    		lista.add(SomaMinMaxRegistrada.getInstancia().getFaixa120_130());
    	}else if(faixa02!=null&faixa02>0){
    		lista.add(140);
    		lista.add(SomaMinMaxRegistrada.getInstancia().getFaixa131_140());
    	}else if(faixa03!=null&faixa03>0){
    		lista.add(150);
    		lista.add(SomaMinMaxRegistrada.getInstancia().getFaixa141_150());
    	}else if(faixa04!=null&faixa04>0){
    		lista.add(160);
    		lista.add(SomaMinMaxRegistrada.getInstancia().getFaixa151_160());
    	}else if(faixa05!=null&faixa05>0){
    		lista.add(170);
    		lista.add(SomaMinMaxRegistrada.getInstancia().getFaixa161_170());
    	}else if(faixa06!=null&faixa06>0){
    		lista.add(180);
    		lista.add(SomaMinMaxRegistrada.getInstancia().getFaixa171_180());
    	}else if(faixa07!=null&faixa07>0){
    		lista.add(190);
    		lista.add(SomaMinMaxRegistrada.getInstancia().getFaixa181_190());
    	}else if(faixa08!=null&faixa08>0){
    		lista.add(200);
    		lista.add(SomaMinMaxRegistrada.getInstancia().getFaixa191_200());
    	}else if(faixa09!=null&faixa09>0){
    		lista.add(210);
    		lista.add(SomaMinMaxRegistrada.getInstancia().getFaixa201_210());
    	}else if(faixa10!=null&faixa10>0){
    		lista.add(220);
    		lista.add(SomaMinMaxRegistrada.getInstancia().getFaixa211_220());
    	}else if(faixa11!=null&faixa11>0){
    		lista.add(230);
    		lista.add(SomaMinMaxRegistrada.getInstancia().getFaixa221_230());
    	}else if(faixa12!=null&faixa12>0){
    		lista.add(240);
    		lista.add(SomaMinMaxRegistrada.getInstancia().getFaixa231_240());
    	}else if(faixa13!=null&faixa13>0){
    		lista.add(250);
    		lista.add(SomaMinMaxRegistrada.getInstancia().getFaixa241_250());
    	}else if(faixa14!=null&faixa14>0){
    		lista.add(260);
    		lista.add(SomaMinMaxRegistrada.getInstancia().getFaixa251_260());
    	}else if(faixa15!=null&faixa15>0){
    		lista.add(270);
    		lista.add(SomaMinMaxRegistrada.getInstancia().getFaixa261_270());
    	}
    	return lista;
    	
    }
}
