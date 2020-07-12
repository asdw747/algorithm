package mars.utils.file;

import com.google.common.collect.Lists;
import mars.utils.MyStringUtil;
import mars.utils.cryptor.DESedeUtil;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class TestFile {

    private static final String ROOT_DATA_DIR = "/home/zhangys/project_tmp/";

    public static void main(String [] args) {

//        unTarGz();
//        FileUtil.unZipFiles("/home/mi/project_tmp/3423214565255544997.zip", "/home/mi/project_tmp/ok");

        tarGz();
        unTarGz();

    }

    private static void tarGz() {
        CompressFile compressFileA = new CompressFile("a.txt",ROOT_DATA_DIR + "resource/a.txt");
        CompressFile compressFileB = new CompressFile("b.txt",ROOT_DATA_DIR + "resource/b.txt");
        List<CompressFile> compressFiles = Arrays.asList(compressFileA, compressFileB);

        String fileName = MyStringUtil.getRandomString(5) + ".tar.gz";
        File outFile = new File(ROOT_DATA_DIR + fileName);
        try {
            CompressUtils.tarAndGzip(compressFiles,new FileOutputStream(outFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static void unTarGz() {
        try {
            String tarGzName = "4wj1f";
            File file = new File(ROOT_DATA_DIR + tarGzName + ".tar.gz");
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

            String finalName = ROOT_DATA_DIR + tarGzName + ".tar";
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(finalName));

            GzipCompressorInputStream gcis = new GzipCompressorInputStream(bis);

            byte[] buffer = new byte[1024];
            int read = -1;
            while((read = gcis.read(buffer)) != -1){
                bos.write(buffer, 0, read);
            }
            gcis.close();
            bos.close();

            unCompressTar(finalName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void unCompressTar(String finalName) throws IOException {

        File file = new File(finalName);
        String parentPath = file.getParent();
        TarArchiveInputStream tarArchiveInputStream = new TarArchiveInputStream(new FileInputStream(file));

        TarArchiveEntry tarArchiveEntry;
        while((tarArchiveEntry = tarArchiveInputStream.getNextTarEntry()) != null){
            String name = tarArchiveEntry.getName();
            File tarFile = new File(parentPath, name);
            if(!tarFile.getParentFile().exists()){
                tarFile.getParentFile().mkdirs();
            }

            BufferedOutputStream bos =
                    new BufferedOutputStream(new FileOutputStream(tarFile));

            int read = -1;
            byte[] buffer = new byte[1024];
            while((read = tarArchiveInputStream.read(buffer)) != -1){
                bos.write(buffer, 0, read);
            }
            bos.close();
        }
        tarArchiveInputStream.close();
//        file.delete();//删除tar文件
    }

    private static void zipAndCry() {
        try {
            long instructionId = 123456789L;

            List<CompressItem> compressFileList = Lists.newArrayList();

            File fileFromSign = new File("/home/mi/3412253366066085888.pdf");// 合同文件地址
            compressFileList.add(new CompressItem(instructionId + "_G.pdf", FileUtil.file2Bytes(fileFromSign.getAbsolutePath())));

            byte[] bytes = CompressUtils.zip(compressFileList);
            FileUtil.buff2File(bytes,instructionId + ".zip");

//            TempFileManager tempFileManager = BeanLoader.getBean(TempFileManager.class);
//            TempDir tempDir = tempFileManager.createRandomEmptyDir("uploadfile");
//            File encryptFile = tempDir.newFile();

            File encryptFile = new File("/home/mi/桌面/temp/aaa");

            File compressFile = new File(instructionId + ".zip");
            DESedeUtil.encrypt(new FileInputStream(compressFile), new FileOutputStream(encryptFile), "asdw");
        } catch (Exception e) {
            System.currentTimeMillis();
        }
    }



}
