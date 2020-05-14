package mars.utils.file;

import lombok.Data;

@Data
public class CompressItem {

    private String fileName;
    private byte[] content;

    public CompressItem(String fileName, byte[] content) {
        this.fileName = fileName;
        this.content = content;
    }

}
