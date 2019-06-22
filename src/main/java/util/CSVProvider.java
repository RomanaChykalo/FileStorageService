package util;

import model.Type;
import model.UserFile;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;

import javax.jws.soap.SOAPBinding;
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
    public static String[] HEADERS = {"Name", "Type", "Size"};
    private static InputStreamReader newReader(final InputStream inputStream) {
        return new InputStreamReader(new BOMInputStream(inputStream), StandardCharsets.UTF_8);
    }

    public static List<UserFile> readFilesFromCSV(File file) {
        List<UserFile> files = new ArrayList<>();
        Path pathToFile = Paths.get(csvFilePath);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line=br.readLine();
            while (!Objects.isNull(line)) {
                String[] attributes = line.split(CSV_SEPARATOR);
                UserFile userFile = createFile(attributes);
                files.add(userFile);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return files;}
     /*   List<UserFile> userFileList = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/files.csv"));
            // Reading first line..
            String line = reader.readLine();
            while (!Objects.isNull(line)) {
                String[] userData = line.split(",");
                // Execpt 'names' there are total 4 students, A,B,C,D.
                String name = userData[0];
                Type type = Type.valueOf(userData[1].trim());
                int size = Integer.parseInt(userData[2].trim());
                userFileList.add(new UserFile(name, type, size));
                line = reader.readLine();
            }
            reader.close();
            return userFileList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;*/

          /*  List<UserFile> list = new ArrayList<>();
            InputStream in = null;
            try {
                in = new FileInputStream(new File(csvFilePath));
                Iterable<CSVRecord> parser = CSVFormat.DEFAULT.withHeader(HEADERS).withSkipHeaderRecord(true).parse(newReader(in));

                for (CSVRecord record : parser) {
                    list.add(new UserFile(record.get("Name"), Type.valueOf(record.get("Type")), Integer.parseInt(record.get("Size"))));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return list;
        }
*/

        public static UserFile createFile (String[]metadata){
            String name = metadata[0];
            Type type = Type.valueOf(metadata[1].trim());
            int size = Integer.parseInt(metadata[2].trim());
            return new UserFile(name, type, size);
        }
        public static void writeDataToCsvFile (List < UserFile > userFiles) {
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

