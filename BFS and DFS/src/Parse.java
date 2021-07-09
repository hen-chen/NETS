import java.io.*;
import java.util.*;

public class Parse {
    File file;
    Scanner s;
    
    public Parse(String filePath) {
        this.file = new File(filePath);
        try {
            this.s = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * getTable
     * @return a list of arrays of strings
     */
    public List<String[]> getTable() {
        List<String[]> table = new ArrayList<String[]>();
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] edge = line.split(" ");
            table.add(edge);
        }
        return table;
    }
}
