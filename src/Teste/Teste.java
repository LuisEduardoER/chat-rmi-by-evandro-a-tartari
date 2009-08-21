package Teste;


public class Teste {

    public static void main(String[] args) throws Exception {
        TesteL.execute();
        TesteListaOrdenada.execute();
        EstImparPar.execute(TesteListaImparPar
                .execute());
        EstImparPar.getEstatisticas();
        
    }
}
