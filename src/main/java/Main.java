import java.util.List;
import java.util.Map;

/**
 * This class is as for the entrance to start the program.
 * Step1: Parse the arguments and validate the arguments.
 * Step2: Read the given CSV file and store the data into a list of map.
 * Step3: Read the email template and letter template files as strings.
 * Step4: Generate the output string based on CSV file and templates.
 * Step5: Write the output string to the targeted output directory.
 *
 * @author Shaohua Guo
 */
public class Main {

  /**
   * The entry point of the program.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // Parse the arguments
    Map<String, String> options = ArgsProcessing.parseArgs(args);

    try {
      // Validate the arguments
      ArgsProcessing.validateArgs(options);

      // Read the CSV file and store the data into a list of map
      List<Map<String, String>> csvData = CSVUtils.readCSV(options.get("csv_file"));

      // Read the email template and letter template files as strings
      String emailTemplate = CSVUtils.readTemplate(options.get("email_template"));
      String letterTemplate = CSVUtils.readTemplate(options.get("letter_template"));

      // Generate the output string based on CSV file and templates, then write them into the output file
      Outputs.generateMessages(csvData, emailTemplate, letterTemplate, options.get("output_dir"));

      System.out.println("Program finished successfully.");

    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      // Print the usage instructions if there's an error.
      Outputs.printUsage();
    }
  }
}
