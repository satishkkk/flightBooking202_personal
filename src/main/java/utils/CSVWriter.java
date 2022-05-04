package utils;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVWriter {

//    List<String[]> dataLines = new ArrayList<>();

    public void csvWriter(String CSV_FILE_NAME, List<String[]> dataLines) throws IOException {
        File csvOutputFile = new File(CSV_FILE_NAME);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    public void fileWriter(String fileName,List<String> output)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for(String line : output){
            writer.write(line + "\n");
        }
        writer.close();
    }
}
