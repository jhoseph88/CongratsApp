package Utils;

import java.io.InputStream;
import java.io.OutputStream;

public class java {
    public static void CopyStream(InputStream inputStream, OutputStream outputStream) {

        final int bufferSize = 1024;

        try {
            byte[] bytes = new byte[bufferSize];
            int count = 0;

            while (count != -1) {
                outputStream.write(bytes, 0, count);
                count = inputStream.read(bytes, 0, bufferSize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
