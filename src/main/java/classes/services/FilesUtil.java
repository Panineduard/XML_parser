package classes.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilesUtil {
    private FilesUtil(){}
    public static List<File> listFilesForFolder(String folderPath) {
        List<File> files = new ArrayList<>();
        File folder = new File(folderPath);
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (!fileEntry.isDirectory()) {
                files.add(fileEntry);
            }
        }
        return files;
    }
}
