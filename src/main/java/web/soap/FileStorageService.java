package web.soap;

import model.Type;
import model.UserFile;
import web.fault.SoapServiceException;

import javax.jws.WebService;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Optional;

@WebService
public interface FileStorageService {
    List<UserFile> getAllFiles() throws SoapServiceException;

    UserFile getFile(String name) throws SoapServiceException;

    boolean addFile(UserFile file) throws SoapServiceException;

    boolean removeFile(String name) throws SoapServiceException;

    List<UserFile> getOneTypeFileList(Type type) throws SoapServiceException;
}
