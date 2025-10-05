package gutfud.dacs.Server;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class MD5Hashing {
    public MD5Hashing()
    {}
    public  String getMD5Hash(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(data.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        return convertByteArrayToHex(digest);
    }
    private  String convertByteArrayToHex(byte[] array) {
        StringBuilder sb = new StringBuilder(array.length * 2);
        for (byte b : array) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }


}