import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * This class handles the generation of email and letter messages based on the provided CSV data and templates.
 *
 * @author xuyit
 */
public class Outputs {

  /**
   * For each row in the CSV file, we'll generate a personalized email and/or letter.
   *
   * csvData: a list of maps, where each map represents a row from the CSV file.
   * emailTemplate: a template file for generating email content.
   * letterTemplate: a template file for generating letter content.
   */
  public static void generateMessages(List<Map<String, String>> csvData, String emailTemplate, String letterTemplate, String outputDir) throws IOException {
    for (Map<String, String> row : csvData) {
      if (emailTemplate != null) {
        String emailContent = generateMessage(emailTemplate, row);
        // Use key of map to get first_name and last_name from the row and create a new file for the email.
        writeOutput(outputDir, row.get("first_name") + "_" + row.get("last_name") + "_email.txt", emailContent);
      }
      if (letterTemplate != null) {
        String letterContent = generateMessage(letterTemplate, row);
        writeOutput(outputDir, row.get("first_name") + "_" + row.get("last_name") + "_letter.txt", letterContent);
      }
    }
  }

  /**
   * Generate a personalized message from a given template.
   * @param template: the template file to read.
   * @param data: the data to be used in the template.
   * @return
   */
  public static String generateMessage(String template, Map<String, String> data) {
    String message = template;
    for (Map.Entry<String, String> entry : data.entrySet()) {
      // use replacement to replace placeholders in the template with the corresponding data values.
      message = message.replace("[[" + entry.getKey() + "]]", entry.getValue());

    }
    return message;
  }

  /**
   * Write the generated message to a file.
   * @param outputDir: the output directory where the generated files will be stored.
   * @param fileName: the name of the file to be created.
   * @param content: the content to be written to the file.
   * @throws IOException
   */
  public static void writeOutput(String outputDir, String fileName, String content) throws IOException {
    Files.write(Paths.get(outputDir, fileName), content.getBytes());
  }

  /**
   * Print usage instructions when the command line arguments are missing or incorrect.
   */
  public static void printUsage() {
    System.out.println("Usage:");
    System.out.println("--email Generate email messages. If this option is provided, then --email-template must also be provided.");
    System.out.println("--email-template <path/to/file> A filename for the email template.");
    System.out.println("--letter Generate letters. If this option is provided, then --letter-template must also be provided.");
    System.out.println("--letter-template <path/to/file> A filename for the letter template.");
    System.out.println("--output-dir <path/to/folder> The folder to store all generated files. This option is required.");
    System.out.println("--csv-file <path/to/file> The CSV file to process. This option is required.");
    System.out.println("Examples:");
    System.out.println("--email --email-template email-template.txt --output-dir emails --csv-file customer.csv");
    System.out.println("--letter --letter-template letter-template.txt --output-dir letters --csv-file customer.csv");
  }
}