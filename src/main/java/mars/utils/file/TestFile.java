package mars.utils.file;

import com.google.common.collect.Lists;
import mars.utils.cryptor.DESedeUtil;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.tools.zip.ZipFile;

import java.io.*;
import java.util.Enumeration;
import java.util.List;

public class TestFile {

    public static void main(String [] args) {

//        tarGz();
//        unTarGz();
        FileUtil.unZipFiles("/home/mi/project_tmp/3423214565255544997.zip", "/home/mi/project_tmp/ok");

    }

    private static void tarGz() {
        try {
            long instructionId = 123456789L;

            List<CompressItem> compressFileList = Lists.newArrayList();

            File fileFromSign = new File("/home/mi/3412253366066085888.pdf");// 合同文件地址
            compressFileList.add(new CompressItem(instructionId + "_G.pdf", FileUtil.file2Bytes(fileFromSign.getAbsolutePath())));

            byte[] bytes = CompressUtils.tarAndGzip(compressFileList);
            FileUtil.buff2File(bytes,instructionId + ".tar.gz");

            File compressFile = new File(instructionId + ".tar.gz");
            FileInputStream inputStream = new FileInputStream(compressFile);

            FileOutputStream outputStream = new FileOutputStream(new File("/home/mi/桌面/temp/bbb.tar.gz"));
            int ch = 0;
            try {
                while((ch=inputStream.read()) != -1){
                    outputStream.write(ch);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally{
                //关闭输入流等（略）
                outputStream.close();
                inputStream.close();
            }


        } catch (Exception e) {
            System.currentTimeMillis();
        }
    }

    private static void unTarGz() {
        try {
            File file = new File("/home/mi/桌面/temp/bbb.tar.gz");
//            byte [] bytes = FileUtil.file2Bytes("/home/mi/桌面/temp/bbb.tar.gz");

            BufferedInputStream bis =
                    new BufferedInputStream(new FileInputStream(file));

            String fileName =
                    file.getName().substring(0, file.getName().lastIndexOf("."));

            String finalName = "/home/mi/桌面/temp/bbb.tar";

            BufferedOutputStream bos =
                    new BufferedOutputStream(new FileOutputStream(finalName));

            GzipCompressorInputStream gcis =
                    new GzipCompressorInputStream(bis);

            byte[] buffer = new byte[1024];
            int read = -1;
            while((read = gcis.read(buffer)) != -1){
                bos.write(buffer, 0, read);
            }
            gcis.close();
            bos.close();

            unCompressTar(finalName);

        } catch (Exception e) {

        }

    }

    private static void unCompressTar(String finalName) throws IOException {

        File file = new File(finalName);
        String parentPath = file.getParent();
        TarArchiveInputStream tais =
                new TarArchiveInputStream(new FileInputStream(file));

        TarArchiveEntry tarArchiveEntry = null;

        while((tarArchiveEntry = tais.getNextTarEntry()) != null){
            String name = tarArchiveEntry.getName();
            File tarFile = new File(parentPath, name);
            if(!tarFile.getParentFile().exists()){
                tarFile.getParentFile().mkdirs();
            }

            BufferedOutputStream bos =
                    new BufferedOutputStream(new FileOutputStream(tarFile));

            int read = -1;
            byte[] buffer = new byte[1024];
            while((read = tais.read(buffer)) != -1){
                bos.write(buffer, 0, read);
            }
            bos.close();
        }
        tais.close();
        file.delete();//删除tar文件
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
