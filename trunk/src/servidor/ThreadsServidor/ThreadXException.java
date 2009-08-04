package servidor.ThreadsServidor;

import interfaces.IMensageiroCliente;

import java.util.Map;

import contatos.Contatos;

public class ThreadXException extends Thread {
    private Map<String, IMensageiroCliente> clientes;
    private Contatos contato;

    public ThreadXException(Map<String, IMensageiroCliente> clientes,
            Contatos contato) {
        this.clientes = clientes;
        this.contato = contato;
    }

    public void run() {
        try {
            clientes.get(contato.getLogin()).servidorFechando();
        } catch (Exception e) {
        }
    }
}
