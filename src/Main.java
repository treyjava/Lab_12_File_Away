import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.JFileChooser;

public class Main
{

    public static void main(String[] args)
    {

        JFileChooser chooser = new JFileChooser();
        File selectedFile = null;
        int charCount = 0;
        int wordCount = 0;
        int lineCount = 0;

        try
        {

            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                String currentLine = reader.readLine();

                while (currentLine != null)
                {
                    lineCount++;
                    String[] words = currentLine.split(" ");

                    wordCount = wordCount + words.length;

                    for (String word : words)
                    {
                        charCount = charCount + word.length();
                    }

                    currentLine = reader.readLine();
                }

            }
            else
            {
                System.out.println("No file was selected, rerun the program");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File was not found");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // outputs

        System.out.println("File is: " + selectedFile.getName());

        System.out.println("There are " + lineCount + " lines in this file");
        System.out.println("There are " + wordCount + " words in this file");
        System.out.println("There are " + charCount + " characters in this file");

    }

}