package util;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class Criptografia {
    private static final Integer SOMA = 2;
    private static final Integer SUBTRAI = 6;
    private static final Integer MULTIPLICA = 2;
    private static final Integer DIVIDE = 2;

    public static String cripto(String text) {
        byte[] hexBytes = HexBin.encode(text.getBytes()).getBytes();
        int j = hexBytes.length - 1;
        byte[] hexResult = new byte[hexBytes.length];
        for (int i = 0; i < hexBytes.length; i++) {
            hexResult[i] = (byte) (((((hexBytes[j] + new Long(SOMA).byteValue()) * new Long(
                    MULTIPLICA).byteValue()) - new Long(SUBTRAI).byteValue()) / new Long(
                    DIVIDE).byteValue()) - new Long(SUBTRAI).byteValue());
            j--;
        }
        return new String(hexResult);
    }

    public static String decripto(String text) {
        byte[] hexBytes = text.getBytes();
        int j = hexBytes.length - 1;
        byte[] hexResult = new byte[hexBytes.length];
        for (int i = 0; i < hexBytes.length; i++) {
            hexResult[i] = (byte) (((((hexBytes[j] 
                    + new Long(SUBTRAI).byteValue()) 
                    * new Long(DIVIDE).byteValue()) 
                    + new Long(SUBTRAI).byteValue())
                    / new Long(MULTIPLICA).byteValue()) 
                    - new Long(SOMA).byteValue());
            j--;
        }
        return new String(HexBin.decode(new String(hexResult)));
    }
}
