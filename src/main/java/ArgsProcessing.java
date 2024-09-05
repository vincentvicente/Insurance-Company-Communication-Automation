import java.util.HashMap;
import java.util.Map;

/**
 * This class is for parsing command line arguments and validating them.
 *
 * @author Shaohua Guo
 */
public class ArgsProcessing {

  /**
   * Parses the command line arguments, then storing each argument and its value into a map
   *
   * @param args the command line arguments
   * @return the map containing the command line arguments and their values
   */
  public static Map<String, String> parseArgs(String[] args) {
    Map<String, String> options = new HashMap<>();
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "--email" -> options.put("email", "true");
        case "--email-template" -> options.put("email_template", args[++i]);
        case "--letter" -> options.put("letter", "true");
        case "--letter-template" -> options.put("letter_template", args[++i]);
        case "--output-dir" -> options.put("output_dir", args[++i]);
        case "--csv-file" -> options.put("csv_file", args[++i]);
        default -> throw new IllegalArgumentException("Unknown argument: " + args[i]);
      }
    }
    return options;
  }

  /**
   * Validates the given arguments and their values.
   *
   * @param options the options to validate
   */
  public static void validateArgs(Map<String, String> options) {
    if (!options.containsKey("csv_file")) {
      throw new IllegalArgumentException("Missing --csv-file");
    }
    if (!options.containsKey("output_dir")) {
      throw new IllegalArgumentException("Missing --output-dir");
    }
    if (options.containsKey("email") && !options.containsKey("email_template")) {
      throw new IllegalArgumentException("--email provided but no --email-template was given");
    }
    if (options.containsKey("letter") && !options.containsKey("letter_template")) {
      throw new IllegalArgumentException("--letter provided but no --letter-template was given");
    }
  }
}
