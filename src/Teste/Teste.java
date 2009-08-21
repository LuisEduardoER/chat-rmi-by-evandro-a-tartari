package Teste;

import java.util.List;


public class Teste {

    public static void main(String[] args) throws Exception {
        TesteL.execute();
        List<TesteB> lista= TesteListaOrdenada.execute();
        EstImparPar.execute(TesteListaImparPar
                .execute());
        List<String> resultado = EstSoma.mediaSomaNumeros(lista);
        for (String res : resultado) {
            System.out.print(res);
        }
        System.out.print("Est Media " + EstSoma.getMediaSoma());
        
    }
}
