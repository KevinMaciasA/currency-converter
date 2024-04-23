# Java Currency Converter

This Java Currency Converter is a CLI tool that provides currency conversion functionality. Behind the scenes it use the well know ExchangeRate-API to do an easy convertion.

## Key Features

1. **ExchangeRate-API Integration:** The converter seamlessly interacts with the ExchangeRate-API, ensuring accurate and up-to-date exchange rates for all supported currencies.

2. **Flexible Conversion Options:** User can effortlessly convert between a range of currencies.

3. **Easy to use:** CLI experience is simple an intuitive.

4. **Customizable:** The converter's modular design allows for easy customization and adaptation to specific project requirements.

## Getting Started

Before everything, follow these steps to clone the repository.

1. Ensure you have Java Development Kit (JDK) installed on your system. You can download it from [here](https://www.oracle.com/java/technologies/javase-jdk16-downloads.html).

2. Clone the repository to your local machine:

   ```sh
   git clone https://github.com/your-username/java-currency-converter.git
   ```

3. Navigate to the project directory:

   ```sh
   cd currency-converter
   ```

## Configuration

Before using the Java Currency Converter, you'll need an API key from [ExchangeRate-API](https://www.exchangerate-api.com/) to start using the currency conversion. To add your key follow these steps:

1. Open the `.template.env` file located in the root directory of the project.
2. Add your ExchangeRate-API API key to the file.
3. Save the file.
4. Rename the file from `.template.env` to `.env`.

This `.env` file will be used to load your API key securely into the application. Ensure not to share your API key publicly.

## Running the Project

To run the Java project first compile it and then run it using these commands:

1. Compile the Java source files:

   ```sh
   javac *.java
   ```

2. Run the main program:

   ```sh
   java Main
   ```

## CLI

TODO: Add image

## Acknowledgments

Special thanks to ExchangeRate-API for providing the currency data necessary for this project.
