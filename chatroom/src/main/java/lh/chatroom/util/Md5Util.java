package lh.chatroom.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * MD5加密器
 */
public final class Md5Util {

    private Md5Util() {
    }

    public static String encrypt32(Object source) {
        byte[] data;
        try {
            if (null == source) {
                data = "".getBytes();
            } else if (source instanceof File) {
                data = FileUtils.readFileToByteArray((File) source);
            } else if (source instanceof InputStream) {
                data = IOUtils.toByteArray((InputStream) source);
            } else if (source instanceof Reader) {
                data = IOUtils.toByteArray((Reader) source);
            } else if (source instanceof byte[]) {
                data = (byte[]) source;
            } else {
                data = source.toString().getBytes();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return DigestUtils.md5DigestAsHex(data);
    }

    public static String encrypt16(Object source) {
        String s = encrypt32(source);
        return s.substring(8, 24);
    }

    public static void main(String[] args) {
        Object s = "luohuan";
        System.out.println("32 bit: " + encrypt32(s));
        System.out.println("16 bit: " + encrypt16(s));
    }

}
