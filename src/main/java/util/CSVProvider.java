package util;

import com.opencsv.CSVWriter;
import model.Type;
import model.UserFile;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CSVProvider {

    private static String csvFilePath = "src/main/resources/files.csv";
    private static final String CSV_SEPARATOR = ",";

    public static List<UserFile> readFilesFromCSV(String fileName) {
        List<UserFile> files = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            while (!Objects.isNull(line)) {
                String[] attributes = line.split(CSV_SEPARATOR);
                UserFile userFile = createFile(attributes);
                files.add(userFile);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return files;
    }
/*
    private static UserFile createFile(String[] metadata) {
        String name = metadata[0];
        Type type = Type.valueOf(metadata[1].trim());
        int size = Integer.parseInt(metadata[2].trim());
        return new UserFile(name, type, size);
    }*/

    public static UserFile createFile(String[] metadata) {
        String name = metadata[0];
        Type type = Type.valueOf(metadata[1].trim());
        int size = Integer.parseInt(metadata[2].trim());
        return new UserFile(name, type, size);
    }


    /*    public static void writeDataToCsvFile(UserFile file) {
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFilePath),
                    "UTF-8"))) {
                List<UserFile> userFiles = readFilesFromCSV(csvFilePath);
                userFiles.add(file);
                for(UserFile userFile:userFiles){
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(file.getName().trim().length() == 0 ? "" : file.getName());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(file.getType().toString().trim().length() == 0 ? "" : file.getType().toString());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(file.getSize() < 0 ? "" : file.getSize());
                bw.write(oneLine.toString());
                bw.newLine();
                bw.flush();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    public static void writeDataToCsvFile(List<UserFile> userFiles) {
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n").withNullString("");
        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(csvFilePath)), "UTF-8"))) {
            CSVPrinter csvFilePrinter = new CSVPrinter(out, csvFileFormat);
            for (UserFile userFile : userFiles) {
                csvFilePrinter.printRecord(userFile.getName(), userFile.getType(), userFile.getSize());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

