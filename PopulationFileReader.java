//Makenna
package finalproject.org.data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PopulationFileReader implements PopulationReader {

    protected String filename;

    public PopulationFileReader(String filename) {
        this.filename = filename;
    }
    @Override
    public Map<String, Integer> popReader() {
        Map<String, Integer> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] split = line.split("\\s+");
                if (split.length != 2) {
                    continue;
                }
                String zip = split[0];
                try {
                    int population = Integer.parseInt(split[1]);
                    if (population > 0) {
                        map.put(zip, population);
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
