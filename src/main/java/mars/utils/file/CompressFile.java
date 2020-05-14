package mars.utils.file;

import lombok.Data;

@Data
public class CompressFile {
    private String fileName;
    private String localPath;

    public CompressFile(String fileName, String localPath) {
        this.fileName = fileName;
        this.localPath = localPath;
    }
}
