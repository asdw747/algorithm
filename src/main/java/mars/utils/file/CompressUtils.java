package mars.utils.file;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressUtils {
    private static final Logger log = LoggerFactory.getLogger(CompressUtils.class);
    private static final int buf_size = 8192;

    public CompressUtils() {
    }

    public static byte[] tarAndGzip(List<CompressItem> compressItemList) {
        byte[] tarContent = tar(compressItemList);
        BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(tarContent));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            GzipCompressorOutputStream gzipos = new GzipCompressorOutputStream(bos);
            byte[] buffer = new byte[8192];

            int len;
            while(-1 != (len = bis.read(buffer))) {
                gzipos.write(buffer, 0, len);
            }

            gzipos.close();
            bis.close();
            return bos.toByteArray();
        } catch (IOException var7) {
            log.error("in tarAndZip() catch an exception", var7);
            throw new RuntimeException("in tarAndZip() catch an exception");
        }
    }

    public static void tarAndGzip(List<CompressFile> compressFileList, OutputStream os) {
        try {
            GzipCompressorOutputStream gzipos = new GzipCompressorOutputStream(os);
            tar(compressFileList, gzipos);
            gzipos.close();
            os.close();
        } catch (IOException var3) {
            log.error("in tarAndZip() catch an exception", var3);
            throw new RuntimeException("in tarAndZip() catch an exception");
        }
    }

    public static byte[] zip(List<CompressItem> compressItemList) {
        if (CollectionUtils.isEmpty(compressItemList)) {
            throw new RuntimeException("compressItemList is empty");
        } else {
            ByteArrayOutputStream zipBos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(zipBos);

            try {
                Iterator var3 = compressItemList.iterator();

                while(var3.hasNext()) {
                    CompressItem compressItem = (CompressItem)var3.next();
                    zos.putNextEntry(new ZipEntry(compressItem.getFileName()));
                    zos.write(compressItem.getContent());
                    zos.closeEntry();
                }

                zos.close();
                return zipBos.toByteArray();
            } catch (IOException var5) {
                log.error("in zip() catch an exception", var5);
                throw new RuntimeException("in zip() catch an exception");
            }
        }
    }

    public static byte[] tar(List<CompressItem> compressItemList) {
        if (CollectionUtils.isEmpty(compressItemList)) {
            throw new RuntimeException("compressItemList is empty");
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            TarArchiveOutputStream tos = new TarArchiveOutputStream(bos);

            try {
                byte[] buffer = new byte[8192];
                Iterator var5 = compressItemList.iterator();

                while(var5.hasNext()) {
                    CompressItem compressItem = (CompressItem)var5.next();
                    TarArchiveEntry tarArchiveEntry = new TarArchiveEntry(compressItem.getFileName());
                    tarArchiveEntry.setSize((long)compressItem.getContent().length);
                    tos.putArchiveEntry(tarArchiveEntry);
                    BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(compressItem.getContent()));

                    int len;
                    while(-1 != (len = bis.read(buffer, 0, 8192))) {
                        tos.write(buffer, 0, len);
                    }

                    bis.close();
                    tos.closeArchiveEntry();
                }
            } catch (IOException var9) {
                log.error("in tar() catch an exception", var9);
                throw new RuntimeException("in tar() catch an exception");
            }

            return bos.toByteArray();
        }
    }

    public static void tar(List<CompressFile> compressFileList, OutputStream os) {
        TarArchiveOutputStream tos = new TarArchiveOutputStream(os);
        byte[] buffer = new byte[8192];
        FileInputStream fis = null;
        Iterator var6 = compressFileList.iterator();

        while(var6.hasNext()) {
            CompressFile compressFile = (CompressFile)var6.next();

            try {
                File file = new File(compressFile.getLocalPath());
                if (!file.exists()) {
                    throw new RuntimeException("文件不存在, filepath=" + compressFile.getLocalPath());
                }

                fis = new FileInputStream(file);
                TarArchiveEntry tarArchiveEntry = new TarArchiveEntry(compressFile.getFileName());
                tarArchiveEntry.setSize(file.length());
                tos.putArchiveEntry(tarArchiveEntry);

                int len;
                while(-1 != (len = fis.read(buffer))) {
                    tos.write(buffer, 0, len);
                }

                tos.closeArchiveEntry();
            } catch (IOException var13) {
                log.error("in tar() catch an exception", var13);
                throw new RuntimeException("in tar() catch an exception");
            } finally {
                IOUtils.closeQuietly(fis);
            }
        }

    }
}
