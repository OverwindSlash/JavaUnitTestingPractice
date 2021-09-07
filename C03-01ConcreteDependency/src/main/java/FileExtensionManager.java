import java.util.HashSet;
import java.util.Locale;

public class FileExtensionManager {

    private HashSet<String> sites;

    public FileExtensionManager() {
        sites = readSupportedFileExtentions();
    }

    private HashSet<String> readSupportedFileExtentions() {
        sites = new HashSet<>();
        // 读取文件
        sites.add(".slf");
        sites.add(".log");

        return sites;
    }

    public boolean isValid(String filename) {
        for (String ext:sites) {
            if (filename.toLowerCase(Locale.ROOT).endsWith(ext))
            {
                return true;
            }
        }
        return false;
    }
}
