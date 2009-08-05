package util;

import java.util.Calendar;

import cliente.EnviaArquivo;
import cliente.Mensagem;
import forms.FormConversa;

public class Util {

    public static String getDataHora() {
        Integer hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        Integer minuto = Calendar.getInstance().get(Calendar.MINUTE);
        String dataHora = "";
        if (hora < 10) {
            dataHora = "(0" + hora.toString() + ":";
        } else {
            dataHora = "(" + hora.toString() + ":";
        }
        if (minuto < 10) {
            dataHora += "0" + minuto.toString() + ")";
        } else {
            dataHora += minuto.toString() + ")";
        }
        return dataHora;
    }

    public static Mensagem newMensagem(FormConversa conversa, String text)
            throws Exception {
        return new Mensagem(conversa.getCliente().getContatos().getLogin(),
                conversa.getCliente().getContatos().getNome(), text, Util
                        .getDataHora(), conversa.getFontSize(), conversa
                        .getFontFamily(), conversa.getColor(), conversa
                        .getIsBold(), conversa.getIsItalic(), conversa
                        .getIsSublinhado(), conversa.getContato().getLogin());
    }

    public static Mensagem newMensagemArquivoRecebido(EnviaArquivo arquivo,
            FormConversa conversa) {
        return new Mensagem(arquivo.getContatoRecebe().getLogin(), arquivo
                .getContatoRecebe().getNome(),
                "Arquivo recebido com sucesso no caminho : C:\\MsMundica\\"
                        + arquivo.getNomeArquivo() + "\n", Util.getDataHora(),
                conversa.getFontSize(), conversa.getFontFamily(), conversa
                        .getColor(), conversa.getIsBold(), conversa
                        .getIsItalic(), conversa.getIsSublinhado(), arquivo
                        .getContatoRecebe().getNome());
    }

    public static Mensagem newMensagemRecebeAviso(EnviaArquivo arquivo,
            FormConversa conversa) {
        return new Mensagem(arquivo.getContatoEnvia().getLogin(), arquivo
                .getContatoEnvia().getNome(), arquivo.getRetorno(), Util
                .getDataHora(), conversa.getFontSize(), conversa
                .getFontFamily(), conversa.getColor(), conversa.getIsBold(),
                conversa.getIsItalic(), conversa.getIsSublinhado(), arquivo
                        .getContatoRecebe().getNome());
    }
}
