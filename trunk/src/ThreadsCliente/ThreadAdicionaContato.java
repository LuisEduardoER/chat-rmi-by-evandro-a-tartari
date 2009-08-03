package ThreadsCliente;

import javax.swing.DefaultListModel;

import contatos.Contatos;

public class ThreadAdicionaContato extends Thread{
    private Contatos contato;
    private DefaultListModel modelContatos;
    
    public ThreadAdicionaContato(DefaultListModel modelContatos, Contatos contato) {
        this.contato = contato;
        this.modelContatos = modelContatos;
    }
    
    
    public void run() {
        modelContatos.addElement(contato);
    }
}
