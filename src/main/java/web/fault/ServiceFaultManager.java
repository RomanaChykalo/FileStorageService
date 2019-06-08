package web.fault;

public class ServiceFaultManager {
    private ServiceFaultManager(){}
    public static final String NO_FILE_WITH_NAME = " There is no file with  such name";
    public static final String SUCH_FILE_ALREADY_EXIST = "Such file already exist ";
    public static final String CANT_LOAD_LARGE_FILE = "File is too large for loading, max size is 1000 kb ";
    public static final String NO_FILES_IN_STORAGE = "There is no file in storage: list is empty";
    public static final String NO_FILE_WITH_TYPE = "There is no file in storage with such type";
}
