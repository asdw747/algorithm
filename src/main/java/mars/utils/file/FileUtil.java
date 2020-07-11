package mars.utils.file;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.tools.zip.ZipFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Enumeration;
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
        Base64.Decoder decoder = Base64.getDecoder();
        File file = tempDir.newFile();
        try {
            OutputStream outStream = new FileOutputStream(file);
            outStream.write(decoder.decode(image));
        } catch (IOException e) {
            LOGGER.error("转换图片出错！, {}", ExceptionUtils.getStackTrace(e));
            return null;
        }
        return file;
    }

    public static boolean unZipFiles(String zipFileName, String extPlace) {
        System.setProperty("sun.zip.encoding",
                System.getProperty("sun.jnu.encoding"));
        try {
            (new File(extPlace)).mkdirs();
            File f = new File(zipFileName);
            ZipFile zipFile = new ZipFile(zipFileName, "GBK"); // 处理中文文件名乱码的问题
            if ((!f.exists()) && (f.length() <= 0)) {
                throw new Exception("要解压的文件不存在!");
            }
            String strPath, gbkPath, strtemp;
            File tempFile = new File(extPlace);
            strPath = tempFile.getAbsolutePath();
            Enumeration<?> e = zipFile.getEntries();
            while (e.hasMoreElements()) {
                org.apache.tools.zip.ZipEntry zipEnt = (org.apache.tools.zip.ZipEntry) e.nextElement();
                gbkPath = zipEnt.getName();
                if (zipEnt.isDirectory()) {
                    strtemp = strPath + File.separator + gbkPath;
                    File dir = new File(strtemp);
                    dir.mkdirs();
                    continue;
                } else { // 读写文件
                    InputStream is = zipFile.getInputStream(zipEnt);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    gbkPath = zipEnt.getName();
                    strtemp = strPath + File.separator + gbkPath;// 建目录
                    String strsubdir = gbkPath;
                    for (int i = 0; i < strsubdir.length(); i++) {
                        if (strsubdir.substring(i, i + 1).equalsIgnoreCase("/")) {
                            String temp = strPath + File.separator
                                    + strsubdir.substring(0, i);
                            File subdir = new File(temp);
                            if (!subdir.exists())
                                subdir.mkdir();
                        }
                    }
                    FileOutputStream fos = new FileOutputStream(strtemp);
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    int c;
                    while ((c = bis.read()) != -1) {
                        bos.write((byte) c);
                    }
                    bos.close();
                    fos.close();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
