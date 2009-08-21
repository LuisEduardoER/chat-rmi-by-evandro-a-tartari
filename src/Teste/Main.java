package Teste;

import java.util.List;


public class Main {

    public static void main(String[] args) throws Exception {
        Leitor.execute();
        List<Bolas> lista= ListaOrdenada.execute();
        EstImparPar.execute(ListaImparPar
                .execute());
        List<String> resultado = EstSoma.mediaSomaNumeros(lista);
        for (String res : resultado) {
            System.out.print(res);
        }
        System.out.println("Est Media " + EstSoma.getMediaSoma());
        System.out.print("Est Media " + EstSoma.getVarMedia(lista));
        
    }
}
