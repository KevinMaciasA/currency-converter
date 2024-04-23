import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;

public class Recorder {
  private String fileName;
  private File file;
  private String[] headers = { "From", "To", "Amount", "Result" };
  public static int maxRows = 10;

  Recorder(String fileName) throws IOException {
    this.fileName = fileName;
    var file = new File(fileName);
    if (!file.exists())
      file.createNewFile();
    this.file = file;
  }

  public String fileName() {
    return fileName;
  }

  public void write(List<String[]> data) {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));

      writeRow(writer, headers);
      for (var row : data) {
        writeRow(writer, row);
      }

      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void writeRow(BufferedWriter writer, String[] rowData) throws IOException {
    for (int i = 0; i < rowData.length; i++) {
      writer.write(rowData[i]);
      if (i < rowData.length - 1) {
        writer.write(",");
      } else {
        writer.write("\n");
      }
    }
  }

  public List<String> readData() {
    try {
      var buffer = new BufferedReader(new FileReader(file));

      var lines = new ArrayList<String>();
      boolean ended = buffer.readLine() == null; // skip header and check end
      while (!ended) {
        var line = buffer.readLine();

        if (line == null)
          ended = true;
        else
          lines.add(line);
      }

      buffer.close();
      return lines;

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
