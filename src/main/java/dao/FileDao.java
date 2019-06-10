package dao;

import model.Type;
import model.UserFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.CSVProvider;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static model.Type.DOC;
import static util.CSVProvider.readFilesFromCSV;
import static util.CSVProvider.writeDataToCsvFile;

public class FileDao {
    private static String csvFilePath = "src/main/resources/files.csv";
    private static Logger logger = LogManager.getLogger(FileDao.class);

    public static List<UserFile> findAllFiles() {
        return readFilesFromCSV(csvFilePath);
    }

    public static Optional<UserFile> findByName(String name) {
        logger.info("Looking for file using file name for search");
        List<UserFile> fileList = readFilesFromCSV(csvFilePath);
        Optional<UserFile> searchedFile = fileList.stream().filter(f -> f.getName().contentEquals(name))
                .findAny();

        return searchedFile;
    }
    public static boolean addFile(UserFile file) {
        logger.info("Add file to general files list");
        List<UserFile> fileList = readFilesFromCSV(csvFilePath);
        boolean isAdded;
        List<UserFile> collect = fileList.stream().filter(f -> (f.getName().contentEquals(file.getName()))).collect(Collectors.toList());
        if (file.getSize() <= 1000 && collect.size() == 0) {
            fileList.add(file);
            CSVProvider.writeDataToCsvFile(fileList);
            isAdded = true;
        } else if (file.getSize() > 1000) {
            isAdded = false;
            logger.error(" Maximum file size is 1MB, you can't add larger file");
        } else {
            isAdded = false;
            logger.error(" This file already exists");
        }
        return isAdded;
    }

    public static boolean deleteFile(String name) {
        logger.info("try to delete file from file list");
        List<UserFile> fileList = readFilesFromCSV(csvFilePath);
        Optional<UserFile> fileToDelete = findByName(name);
        if (fileToDelete.isPresent()) {
            fileList.remove(fileToDelete.get());
            CSVProvider.writeDataToCsvFile(fileList);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(addFile(new UserFile("resume", Type.DOC, 34)));

    }
}
