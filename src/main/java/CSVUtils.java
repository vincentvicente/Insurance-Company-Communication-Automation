import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides utilities for reading CSV files and template files.
 * It includes methods for parsing CSV lines and reading the contents of a file.
 *
 * @author Qiyuan Zhu
 */
public class CSVUtils {

  /**
   * Reads a CSV file and returns a list of maps representing the data.
   * Each map corresponds to a row in the CSV file, with column headers as keys.
   *
   * @param filePath the path to the CSV file
   * @return a list of maps containing the CSV data
   * @throws IOException if an I/O error occurs while reading the file
   */
  public static List<Map<String, String>> readCSV(String filePath) throws IOException {
    List<Map<String, String>> data = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String headerLine = reader.readLine();
      if (headerLine == null) {
        throw new IOException("CSV file is empty");
      }
      String[] headers = parseCSVLine(headerLine);
      String line;
      while ((line = reader.readLine()) != null) {
        String[] values = parseCSVLine(line);
        Map<String, String> row = new HashMap<>();
        for (int i = 0; i < headers.length; i++) {
          row.put(headers[i], values[i]);
        }
        data.add(row);
      }
    }
    return data;
  }

  /**
   * Parses a single line of a CSV file into an array of values.
   * Handles values enclosed in quotes and trims whitespace.
   *
   * @param line the CSV line to parse
   * @return an array of values parsed from the line
   */
  private static String[] parseCSVLine(String line) {
    List<String> values = new ArrayList<>();
    Pattern pattern = Pattern.compile("(?<=^|,)(\"(?:[^\"]|\"\")*\"|[^,]*)");
    Matcher matcher = pattern.matcher(line);
    while (matcher.find()) {
      String value = matcher.group(1).trim();
      if (value.startsWith("\"") && value.endsWith("\"")) {
        value = value.substring(1, value.length() - 1).replace("\"\"", "\"");
      }
      values.add(value);
    }
    return values.toArray(new String[0]);
  }

  /**
   * Reads the entire contents of a file and returns it as a string.
   *
   * @param filePath the path to the file
   * @return the contents of the file as a string
   * @throws IOException if an I/O error occurs while reading the file
   */
  public static String readTemplate(String filePath) throws IOException {
    return new String(Files.readAllBytes(Paths.get(filePath)));
  }
}
