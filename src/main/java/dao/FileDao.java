package dao;

import model.UserFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.CSVProvider;
import util.PropertyUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static util.CSVProvider.readFilesFromCSV;

public class FileDao {
    private static Logger logger = LogManager.getLogger(FileDao.class);
    private static String csvFilePath = PropertyUtil.getProperty("csvFilePath");

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
            CSVProvider.writeDataToCsvFile(fileList,csvFilePath);
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
            CSVProvider.writeDataToCsvFile(fileList,csvFilePath);
            return true;
        }
        return false;
    }
}
