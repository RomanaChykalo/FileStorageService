package bo;

import dao.FileDao;
import model.Type;
import model.UserFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileBO {
    public List<UserFile> getAllFiles() {
        return FileDao.findAllFiles();
    }

    public Optional<UserFile> getFileByName(String name) {
        return FileDao.findByName(name);
    }

    public List<UserFile> getFilesByType(Type type) {
        List<UserFile> userFileList = getAllFiles();
        List<UserFile> oneTypeFileList = userFileList.stream().filter(f -> f.getType().equals(type)).collect(Collectors.toList());
        return oneTypeFileList;
    }

    public boolean addFile(UserFile file) {
        return FileDao.addFile(file);
    }

    public boolean removeFile(String name) {
        return FileDao.deleteFile(name);
    }
}
