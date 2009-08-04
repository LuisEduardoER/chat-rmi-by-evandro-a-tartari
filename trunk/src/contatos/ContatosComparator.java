package contatos;

import java.util.Comparator;

public class ContatosComparator implements Comparator<Contatos>{

    public int compare(Contatos o1, Contatos o2) {
        return o1.getNome().compareTo(o2.getNome());
    }

}
