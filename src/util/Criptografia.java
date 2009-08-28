package util;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class Criptografia {

    private static final Integer SOMA = 2;
    private static final Integer SUBTRAI = 6;
    private static final Integer MULTIPLICA = 2;
    private static final Integer DIVIDE = 2;

    public static String cripto(String text) {
        String result = "";
        byte[] hexBytes = HexBin.encode(text.getBytes()).trim().getBytes();
        byte[] hexResult = new byte[hexBytes.length];
        int j = hexBytes.length - 1;
        for (int i = 0; i < hexBytes.length; i++) {
            hexResult[i] = (byte) (((((hexBytes[j] + new Long(SOMA).byteValue()) * new Long(
                    MULTIPLICA).byteValue()) - new Long(SUBTRAI).byteValue()) / new Long(
                    DIVIDE).byteValue()) - new Long(SUBTRAI).byteValue());

            j--;
        }
        result = Fibonnaci.getInstancia().codificaString(new String(hexResult));
        return new String(result);
    }

    public static String decripto(String text) {
        String result = "";
        byte[] hexBytes = Fibonnaci.getInstancia().decodificaString(
                new String(text)).getBytes();
        int j = hexBytes.length - 1;
        byte[] hexResult = new byte[hexBytes.length];
        hexResult = new byte[hexBytes.length];
        for (int i = 0; i < hexBytes.length; i++) {
            hexResult[i] = (byte) (((((hexBytes[j] + new Long(SUBTRAI)
                    .byteValue()) * new Long(DIVIDE).byteValue()) + new Long(
                    SUBTRAI).byteValue()) / new Long(MULTIPLICA).byteValue()) - new Long(
                    SOMA).byteValue());
            j--;
        }
        result = new String(HexBin.decode(new String(hexResult)));
        return result;
    }

    private static class Fibonnaci {

        public static Fibonnaci getInstancia() {
            if (instancia == null)
                instancia = new Fibonnaci();
            return instancia;
        }

        private Fibonnaci() {
        }

        public String codificaString(String str) {
            return codifica(str, '+');
        }

        public String decodificaString(String str) {
            return codifica(str, '-');
        }

        private String codifica(String str, char operador) {
            int fibonnaci = 1;
            int primeiro = 0;
            int segundo = 1;
            String result = "";
            char caracteres[] = str.toCharArray();
            char ac[];
            int j = (ac = caracteres).length;
            for (int i = 0; i < j; i++) {
                char c = ac[i];
                switch (operador) {
                case 43: // '+'
                    result = (new StringBuilder(String.valueOf(result)))
                            .append((char) (c + fibonnaci)).toString();
                    break;

                case 45: // '-'
                    result = (new StringBuilder(String.valueOf(result)))
                            .append((char) (c - fibonnaci)).toString();
                    break;
                }
                fibonnaci = primeiro + segundo;
                primeiro = segundo;
                segundo = fibonnaci;
                if (fibonnaci > 21) {
                    fibonnaci = 1;
                    primeiro = 0;
                    segundo = 1;
                }
            }

            return result;
        }

        private static Fibonnaci instancia;
    }

}
