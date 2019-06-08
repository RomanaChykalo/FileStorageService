package web.rest;

import bo.FileBO;
import model.Type;
import model.UserFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static web.fault.ServiceFaultManager.*;

public class FileStorageServiceImpl implements FileStorageService {
    private static Logger logger = LogManager.getLogger(FileStorageServiceImpl.class);

    @Override
    public Response getAllFiles() {
        logger.info("Get all files from storage");
        FileBO fileBO = new FileBO();
        return Response.ok().entity(fileBO.getAllFiles()).build();
    }

    @Override
    public Response findByName(String name) {
        logger.info("Get file from storage method");
        FileBO fileBO = new FileBO();
        Optional<UserFile> searchedFile = fileBO.getFileByName(name);
        Response response;
        if (Objects.isNull(searchedFile)) {
            logger.warn(NO_FILE_WITH_NAME);
            response = Response.status(Response.Status.NOT_FOUND).build();
        } else {
            response = Response.ok().entity(searchedFile).build();
            logger.info("File is found: " + searchedFile);
        }
        return response;
    }
    @Override
    public Response addFile(UserFile file) {
        logger.info("Add file to storage");
        Response response;
        FileBO fileBO = new FileBO();
        if (fileBO.addFile(file)) {
            response = Response.ok().build();
        } else {
            logger.warn(SUCH_FILE_ALREADY_EXIST + " or " + CANT_LOAD_LARGE_FILE);
            response = Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return response;
    }

    @Override
    public Response removeFile(String name) {
        logger.info("Remove file from storage");
        Response response;
        FileBO fileBO = new FileBO();
        boolean isRemoved = fileBO.removeFile(name);
        if(!isRemoved){
            response = Response.status(Response.Status.NOT_FOUND).build();
            logger.warn(NO_FILE_WITH_NAME);
        } else {
            response=Response.ok().build();
        }
        return response;
    }

    @Override
    public Response getOneTypeFilesList(Type type) {
        logger.info("get one type files list");
        Response response;
        FileBO fileBO = new FileBO();
        List<UserFile> oneTypeList = fileBO.getFilesByType(type);
        if (oneTypeList.size()==0) {
            response = Response.status(Response.Status.NOT_FOUND).build();
            logger.warn(NO_FILE_WITH_TYPE);
        } else {
            response = Response.ok().entity(oneTypeList).build();
        }

        return response;
    }

}
