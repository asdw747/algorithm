package mars.utils.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

public class TempDir {

    private static final Logger log = LoggerFactory.getLogger(TempDir.class);
    private HashSet<String> filenameSet = new HashSet();
    private File dir;

    public Collection<File> listFiles() {
        return this.listFiles((String)null, false);
    }

    public Collection<File> listFilesRecursive() {
        return this.listFiles((String)null, true);
    }

    public Collection<File> listFiles(String dirPath) {
        return this.listFiles(dirPath, false);
    }

    public Collection<File> listFiles(String dirPath, boolean recursive) {
        if (dirPath == null) {
            return FileUtils.listFiles(this.dir, (String[])null, recursive);
        } else if (dirPath.startsWith("/")) {
            if (!StringUtils.contains(dirPath, this.dir.getAbsolutePath())) {
                log.error("参数错误, dirPath不是tmpDir的子目录");
                throw new RuntimeException("参数错误, dirPath不是tmpDir的子目录");
            } else {
                return FileUtils.listFiles(new File(dirPath), (String[])null, recursive);
            }
        } else {
            return FileUtils.listFiles(new File(this.dir.getAbsolutePath() + File.separator + dirPath), (String[])null, recursive);
        }
    }

    public File saveFile(byte[] content) {
        String filename = this.randomFileName();
        File file = new File(this.dir.getAbsolutePath() + File.separator + filename);
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
            fos.write(content);
            fos.flush();
        } catch (IOException var9) {
            log.error("addFile() failed", var9);
            throw new RuntimeException("addFile() failed", var9);
        } finally {
            IOUtils.closeQuietly(fos);
        }

        return file;
    }

    public File newFile() {
        String filename = this.randomFileName();
        File file = new File(this.dir.getAbsolutePath() + File.separator + filename);

        try {
            file.createNewFile();
            return file;
        } catch (IOException var4) {
            log.error("emptyFile() failed", var4);
            throw new RuntimeException("emptyFile() failed", var4);
        }
    }

    public void clear() {
        try {
            FileUtils.deleteDirectory(this.dir);
            this.filenameSet = new HashSet();
        } catch (IOException var2) {
            log.error("clear dir failed", var2);
            throw new RuntimeException("clear dir failed", var2);
        }
    }

    protected TempDir(File dir) {
        this.dir = dir;
    }

    private synchronized String randomFileName() {
        String filename;
        for(filename = StringUtils.lowerCase(RandomStringUtils.randomAlphanumeric(8)); this.filenameSet.contains(filename); filename = StringUtils.lowerCase(RandomStringUtils.randomAlphanumeric(8))) {
        }

        this.filenameSet.add(filename);
        return filename;
    }

}
