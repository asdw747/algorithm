package mars.utils.file;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.UUID;

public class TempFileManager {

    private Logger logger = LoggerFactory.getLogger(TempFileManager.class);
    private String dirPrefix;

    public TempFileManager(String dirPrefix) {
        this.dirPrefix = dirPrefix;
    }

    public TempDir createRandomEmptyDirIn(String path) {
        if (this.dirPrefix == null) {
            throw new RuntimeException("dirPrefix未正确设置");
        } else {
            path = StringUtils.trimToEmpty(path);
            path = StringUtils.stripEnd(path, File.separator);
            String absoluteDirPath;
            if (StringUtils.startsWith(path, File.separator)) {
                if (!StringUtils.startsWith(path, this.dirPrefix)) {
                    throw new RuntimeException("临时文件夹路径错误(不是[dirPrefix]指定的文件夹或其子文件夹)");
                }

                absoluteDirPath = path + File.separator + StringUtils.remove(UUID.randomUUID().toString(), "-");
            } else {
                absoluteDirPath = this.dirPrefix + File.separator + path + File.separator + StringUtils.remove(UUID.randomUUID().toString(), "-");
            }

            File dir = new File(absoluteDirPath);
            TempDir instance = new TempDir(dir);
            instance.clear();
            dir.mkdirs();
            return instance;
        }
    }

    /** @deprecated */
    @Deprecated
    public TempDir createRandomEmptyDir(String path) {
        if (this.dirPrefix == null) {
            throw new RuntimeException("dirPrefix未正确设置");
        } else {
            path = StringUtils.trimToEmpty(path);
            path = StringUtils.stripEnd(path, File.separator);
            String absoluteDirPath;
            if (StringUtils.startsWith(path, File.separator)) {
                if (!StringUtils.startsWith(path, this.dirPrefix)) {
                    throw new RuntimeException("临时文件夹路径错误(不是[dirPrefix]指定的文件夹或其子文件夹)");
                }

                absoluteDirPath = path + File.separator + StringUtils.remove(UUID.randomUUID().toString(), "-");
            } else {
                absoluteDirPath = this.dirPrefix + File.separator + path + File.separator + StringUtils.remove(UUID.randomUUID().toString(), "-");
            }

            File dir = new File(absoluteDirPath);
            TempDir instance = new TempDir(dir);
            instance.clear();
            dir.mkdirs();
            return instance;
        }
    }

    public void setDirPrefix(String dirPrefix) {
        if (StringUtils.isBlank(dirPrefix)) {
            throw new RuntimeException("dirPrefix路径前缀不能为空");
        } else {
            String osName = System.getProperty("os.name");
            if (!StringUtils.containsIgnoreCase(osName, "windows") && !dirPrefix.startsWith("/tmp/") && !StringUtils.equals("/tmp", dirPrefix)) {
                this.logger.warn("系统[{}], 临时文件没有创建在/tmp/目录下, 请注意及时清理[{}]目录", osName, dirPrefix);
            }

            this.dirPrefix = StringUtils.stripEnd(dirPrefix, File.separator);
        }
    }

}
