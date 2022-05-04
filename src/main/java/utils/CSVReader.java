package utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    private static CSVReader instance;

    private CSVReader() {
    }

    public static CSVReader getInstance() {
        if (instance == null) {
            instance = new CSVReader();
        }
        return instance;
    }

    private static final String COMMA_DELIMITER = ",";

    public List<List<String>> readCSVFile(String path) {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return records;
    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

}
