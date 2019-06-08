package web.soap;

import bo.FileBO;
import model.UserFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import web.fault.SoapServiceException;

import javax.jws.WebService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static web.fault.ServiceFaultManager.NO_FILES_IN_STORAGE;
import static web.fault.ServiceFaultManager.NO_FILE_WITH_NAME;
import static web.fault.ServiceFaultManager.SUCH_FILE_ALREADY_EXIST;

@WebService(endpointInterface = "web.soap.FileStorageService")
public class FileStorageServiceImpl implements FileStorageService {
    private Logger logger = LogManager.getLogger(FileStorageServiceImpl.class);

    @Override
    public List<UserFile> getAllFiles() throws SoapServiceException {
        logger.info("Get all files in storage.");
        FileBO fileBO = new FileBO();
        List<UserFile> allFiles = fileBO.getAllFiles();
        if (allFiles.size() == 0) {
            throw new SoapServiceException(NO_FILES_IN_STORAGE);
        }
        return allFiles;
    }

    @Override
    public Optional<UserFile> getFile(String name) throws SoapServiceException {
        logger.info("Get file via name and type");
        FileBO fileBO = new FileBO();
        Optional<UserFile> searchedFile = fileBO.getFileByName(name);
        if (Objects.isNull(searchedFile)) {
            logger.warn(NO_FILE_WITH_NAME);
            throw new SoapServiceException(NO_FILE_WITH_NAME);
        }
        logger.info("getFile result:" + searchedFile);
        return searchedFile;
    }

    @Override
    public boolean addFile(UserFile file) throws SoapServiceException {
        logger.info("Try to add file using addFile method ");
        FileBO fileBO = new FileBO();
        boolean isAdded = fileBO.addFile(file);
        if (!isAdded) {
            logger.warn(SUCH_FILE_ALREADY_EXIST);
            throw new SoapServiceException(SUCH_FILE_ALREADY_EXIST);
        }
        logger.info(file + " is added: " + isAdded);
        return true;
    }

    @Override
    public boolean removeFile(String name) throws SoapServiceException {
        logger.info("Try delete file using removeFile method");
        FileBO fileBO = new FileBO();
        boolean isDeleted = fileBO.removeFile(name);
        if (!isDeleted) {
            logger.warn(NO_FILE_WITH_NAME);
            throw new SoapServiceException(NO_FILE_WITH_NAME);
        }
        return false;
    }
}
