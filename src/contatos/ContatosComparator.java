package contatos;

import java.util.Comparator;

import util.Criptografia;
/**
 * 
 * @author evandro.tartari
 *
 */
public class ContatosComparator implements Comparator<Contatos>{

    public int compare(Contatos o1, Contatos o2) {
        return Criptografia.decripto(o1.getNome()).compareTo(Criptografia.decripto(o2.getNome()));
    }

}
