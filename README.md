# Insurance Company Communication Automation

## Overview
This project automates the process of sending personalized emails and letters to customers of an insurance company, following a data breach. The company stores customer information in CSV files, and templates are used for generating communications. The program processes these CSV files and replaces placeholders in the templates with the appropriate customer data.

## Table of Contents
- [Features](#features)
- [Classes](#classes)
- [Usage](#usage)
- [Installation](#installation)
- [Testing](#testing)

## Features
- **Command Line Arguments Parsing**: The program accepts various options via command line arguments to customize the generation of email and letter messages.
- **Template Parsing**: The program reads template files containing placeholders (e.g., `[[first_name]]`) and replaces them with actual customer data.
- **CSV Processing**: The program reads customer data from a CSV file and generates a personalized message for each customer.
- **File Output**: The generated emails and letters are saved to a specified output directory.

## Classes

### `Main`
- The entry point of the program.
- **Methods**:
    - `main(String[] args)`: Parses command line arguments and initiates the processing.

### `ArgsProcessing`
- Responsible for parsing and validating command line arguments.
- **Methods**:
    - `parseArgs(String[] args)`: Parses the command line arguments into a map of options and values.
    - `validateArgs(Map<String, String> args)`: Validates the provided arguments to ensure all required options are present.

### `CSVUtils`
- Handles reading and parsing of the CSV file and templates.
- **Methods**:
    - `readCSV(String filePath)`: Reads a CSV file and returns a list of maps representing each row of the file.
    - `parseCSVLine(String line)`: Parses a single line of the CSV file into an array of strings.
    - `readTemplate(String templatePath)`: Reads a template file and returns its content as a string.

### `Outputs`
- Handles the generation of messages and the writing of output files.
- **Methods**:
    - `generateMessage(String template, Map<String, String> customerData)`: Generates a personalized message by replacing placeholders in the template with customer data.
    - `generateMessages(List<Map<String, String>> customerData, String template, String outputDir, String outputFileExtension)`: Generates messages for all customers and writes them to the output directory.
    - `writeOutput(String outputDir, String fileName, String content)`: Writes the generated content to a file in the specified output directory.

## Usage
The program is run from the command line and accepts the following arguments:

- `--email`: Generate email messages. If this option is provided, then `--email-template` must also be provided.
- `--email-template <path/to/file>`: A filename for the email template.
- `--letter`: Generate letters. If this option is provided, then `--letter-template` must also be provided.
- `--letter-template <path/to/file>`: A filename for the letter template.
- `--output-dir <path/to/folder>`: The folder to store all generated files. This option is required.
- `--csv-file <path/to/file>`: The CSV file to process. This option is required.

### Example Command:
```bash
java -jar InsuranceAutomation.jar --email --email-template email-template.txt --output-dir emails --csv-file customers.csv
```

### Error Handling
If the program is run with an invalid combination of arguments (e.g., --email is provided without --email-template), the program will exit with an error message and display usage instructions.