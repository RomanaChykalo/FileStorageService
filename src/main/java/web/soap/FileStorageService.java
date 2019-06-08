package web.soap;

import model.Type;
import model.UserFile;
import web.fault.SoapServiceException;

import javax.jws.WebService;
import java.util.List;
import java.util.Optional;

@WebService
public interface FileStorageService {
    public List<UserFile> getAllFiles() throws SoapServiceException;

    Optional<UserFile> getFile(String name) throws SoapServiceException;

    boolean addFile(UserFile file) throws SoapServiceException;

    boolean removeFile(String name) throws SoapServiceException;

}
