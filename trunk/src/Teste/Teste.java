package Teste;

import java.util.List;


public class Teste {

    public static void main(String[] args) throws Exception {
        TesteL.execute();
        TesteListaOrdenada.execute();
        List<String> resultado = EstImparPar.execute(TesteListaImparPar.execute());
        List<Double> lista = EstImparPar.getEstatisticas();
        for (String res : resultado) {
            System.out.print(res);
        }
        System.out.print("I: ");
        System.out.print(lista.get(0)+" ");
        System.out.print("P: ");
        System.out.println(lista.get(1)+" ");
        System.out.print("t");
    }
}
