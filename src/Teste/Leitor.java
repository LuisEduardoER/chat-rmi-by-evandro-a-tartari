package Teste;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Leitor {
    private static FileReader fReader;
    private static BufferedReader reader;
    private static final String CAMINHO_ARQUIVO_IN = "C://workspace/chatRmi/src/Teste/arquivo.txt";
    private static List<Bolas> listaB;
    public static List<Bolas> execute() throws Exception {
        fReader = new FileReader(CAMINHO_ARQUIVO_IN);
        reader = new BufferedReader(fReader);
        listaB = new ArrayList<Bolas>();
        String line = "";
        int i = 1;
        while (reader.ready()) {
            line = reader.readLine();
            String[] lines = line.split(" ");
            listaB.add(new Bolas(i,lines));
            i++;
        }
        close();
        return listaB;
    }

    private static void close() throws IOException {
        reader.close();
        fReader.close();
    }
}
