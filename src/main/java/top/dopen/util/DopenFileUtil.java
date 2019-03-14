package top.dopen.util;

import java.io.*;

public class DopenFileUtil {

    public static void writeFIleWithoutInput(String path, InputStream is) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        byte[] buffer = new byte[102400];

        int len;
        while((len = is.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        is.close();
        fos.flush();
        fos.close();
    }

}
