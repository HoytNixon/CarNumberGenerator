import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Writer extends PrintWriter {
    private final String fileName;
    public Writer(String fileName) throws FileNotFoundException {
        super(fileName);
        this.fileName= fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
