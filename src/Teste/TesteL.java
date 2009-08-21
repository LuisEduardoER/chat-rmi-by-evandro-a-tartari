package Teste;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TesteL {
    private static FileReader fReader;
    private static BufferedReader reader;
    private static final String CAMINHO_ARQUIVO_IN = "C://workspace/MsMundica/src/Teste/arquivo.txt";
    private static List<TesteB> listaB;
    public static void execute() throws Exception {
        fReader = new FileReader(CAMINHO_ARQUIVO_IN);
        reader = new BufferedReader(fReader);
        listaB = new ArrayList<TesteB>();
        String line = "";
        int i = 0;
        while (reader.ready()) {
            line = reader.readLine();
            String[] lines = line.split(" ");
            listaB.add(new TesteB(i,lines));
            i++;
        }
        close();
    }

    private static void close() throws IOException {
        reader.close();
        fReader.close();
    }
}
