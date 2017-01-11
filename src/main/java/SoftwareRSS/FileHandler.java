package SoftwareRSS;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Aurélien on 11/01/2017.
 */
public class FileHandler {
    static public List readLines(String filePath)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            List<String> result = new LinkedList<>();
            String ret;
            while ((ret = reader.readLine()) != null)
            {
                result.add(ret);
            }
            reader.close();
            return (result.size() > 0 ? result : null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return (null);
    }
    static public boolean clearAndWriteLines(String filePath, String... lines)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));
            for (String line : lines)
            {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
            return (true);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return (false);
    }
    static public void removeFile(String filePath)
    {
        File file = new File(filePath);
        if (file.exists())
            file.delete();
    }
}
