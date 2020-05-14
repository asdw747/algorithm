package mars.utils.file;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
    public static List<Long> readLongValueFromFile(String fileName) throws IOException {
        File file = ResourceUtils.getFile("classpath:"+fileName);
        FileReader reader = new FileReader(file);
        LOGGER.info("start to get file with fileName: {}", fileName);
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<Long> result = new ArrayList<Long>();
        String s;
        while( (s = bufferedReader.readLine()) !=null) {
            long id = Long.parseLong(s.trim());
            result.add(id);
        }
        LOGGER.info("start to get file with fileName: {}, long value size: {}", fileName, result.size());
        reader.close();
        return result;
    }

    public static byte[] file2Bytes(String imgSrc) throws Exception {
        FileInputStream fin = new FileInputStream(new File(imgSrc));
        //可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
        byte[] bytes = new byte[fin.available()];
        //将文件内容写入字节数组，提供测试的case
        fin.read(bytes);
        fin.close();
        return bytes;
    }

    public static void buff2File(byte[] b,String tagSrc) throws Exception {

        FileOutputStream fout = new FileOutputStream(tagSrc);
        //将字节写入文件
        fout.write(b);
        fout.close();
    }

    public static File getImage(String image, TempDir tempDir) {
        BASE64Decoder decoder = new BASE64Decoder();
        File file = tempDir.newFile();
        try {
            OutputStream outStream = new FileOutputStream(file);
            outStream.write(decoder.decodeBuffer(image));
        } catch (IOException e) {
            LOGGER.error("转换图片出错！, {}", ExceptionUtils.getStackTrace(e));
            return null;
        }
        return file;
    }

}
