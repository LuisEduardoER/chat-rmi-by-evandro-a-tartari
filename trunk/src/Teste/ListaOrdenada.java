package Teste;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListaOrdenada {
    private static FileReader fReader;
    private static BufferedReader reader;
    private static final String CAMINHO_ARQUIVO_IN = "C://workspace/chatRmi/src/Teste/arquivo.txt";
    private static List<Integer> listaB;
    private static List<List<Integer>> fList;

    public static List<Bolas> execute() throws Exception {
        fReader = new FileReader(CAMINHO_ARQUIVO_IN);
        reader = new BufferedReader(fReader);
        listaB = new ArrayList<Integer>();
        fList = new ArrayList<List<Integer>>();
        String line = "";
        while (reader.ready()) {
            line = reader.readLine();
            String[] lines = line.split(" ");
            for (int i = 0; i < lines.length; i++) {
                listaB.add(Integer.parseInt(lines[i]));
            }
            Collections.sort(listaB);
            fList.add(listaB);
            listaB = new ArrayList<Integer>();
        }
        close();
        return toResolveList();
    }

    private static List<Bolas> toResolveList() {
        List<Bolas> listaB = new ArrayList<Bolas>();
        int i = 1;
        for (List<Integer> listaInteger : fList) {
            String[] numeros = new String[15];
            int j = 0;
            for (Integer numB : listaInteger) {
                numeros[j] = numB.toString();
                j++;
            }
            listaB.add(new Bolas(i, numeros));
            i++;
        }
        return listaB;
    }

    private static void close() throws IOException {
        reader.close();
        fReader.close();
    }

}
