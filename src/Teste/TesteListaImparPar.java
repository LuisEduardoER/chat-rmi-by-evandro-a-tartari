package Teste;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TesteListaImparPar {
    private static FileReader fReader;
    private static BufferedReader reader;
    private static final String CAMINHO_ARQUIVO_IN = "C://workspace/MsMundica/src/Teste/arquivo.txt";
    private static List<Integer> listaPar;
    private static List<Integer> listaImpar;
    private static List<List<Integer>> listaResultados;

    public static List<List<Integer>> execute() throws Exception {
        fReader = new FileReader(CAMINHO_ARQUIVO_IN);
        reader = new BufferedReader(fReader);
        listaResultados = new ArrayList<List<Integer>>();
        listaPar = new ArrayList<Integer>();
        listaImpar = new ArrayList<Integer>();
        String line = "";
        int i = 1;
        while (reader.ready()) {
            line = reader.readLine();
            String[] lines = line.split(" ");
            for (int j = 0; j < lines.length; j++) {
                int numero = Integer.parseInt(lines[j]);
                if (numero % 2 == 0) {
                    listaPar.add(numero);
                } else {
                    listaImpar.add(numero);
                }
            }
            List<Integer> numeroConc = new ArrayList<Integer>();
            numeroConc.add(i);
            listaResultados.add(numeroConc);
            listaResultados.add(listaImpar);
            listaResultados.add(listaPar);
            listaImpar = new ArrayList<Integer>();
            listaPar = new ArrayList<Integer>();
            i++;
        }
        close();
        return listaResultados;
    }

    private static void close() throws IOException {
        reader.close();
        fReader.close();
    }
}
