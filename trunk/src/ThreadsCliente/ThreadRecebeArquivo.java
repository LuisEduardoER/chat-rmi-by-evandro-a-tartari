package ThreadsCliente;

import interfaces.IMensageiroCliente;

import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import util.RedimencionaImagemIcon;
import cliente.EnviaArquivo;
import forms.FormConversa;

public class ThreadRecebeArquivo extends Thread {
    private EnviaArquivo arquivo;
    private FormConversa conversa;
    private IMensageiroCliente cliente;
    public ThreadRecebeArquivo(EnviaArquivo arquivo, JFrame conversa, IMensageiroCliente cliente) {
        this.arquivo = arquivo;
        this.conversa = (FormConversa) conversa;
        this.cliente = cliente;
    }
    
    public void run() {
        ClassLoader clazz = this.getClass().getClassLoader();
        String mensagemTela = arquivo.getContatoEnvia().getNome() + " enviou um arquivo: "
                + arquivo.getNomeArquivo();
        int retorno = JOptionPane.showOptionDialog(conversa, mensagemTela,
                        "Notificação de recebimentp de arquivo",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        RedimencionaImagemIcon.redimencionaImagem(clazz
                                .getResourceAsStream("imagens/file.png"), 35,
                                35, 1000), null, null);
        if (retorno == 0) {
            try {
                File file = new File("C:/MsMunica/");
                if (!file.exists()) {
                    file.mkdir();
                }
                String caminho = "C:/MsMunica/" + arquivo.getNomeArquivo();
                FileOutputStream fos = new FileOutputStream(caminho);
                byte[] buffer = arquivo.getFile();
                fos.write(buffer);
                fos.flush();
                fos.close();
                arquivo.setRetorno("Arquivo recebido com sucesso por "
                        + arquivo.getContatoRecebe().getNome());
                cliente.enviaAvisoEnvioCompleto(arquivo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (retorno == 1) {
            try {
                arquivo.setRetorno("Arquivo não enviado para "
                        + arquivo.getContatoRecebe().getNome());
                cliente.enviaAvisoEnvioCompleto(arquivo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
