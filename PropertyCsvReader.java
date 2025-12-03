    //Janet
    package finalproject.org.data;
    package finalproject.org.processing.Property;

    // only care about - market_value, total_livable_area, zip_code
    // for zip-code - only first 5 digits
    // see main.resources.properties.csv
    //program should use the first row of the input file to determine
            //which fields these correspond to in each subsequent row.
    import finalproject.org.processing.Property;
    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;
    import java.util.HashMap;
    import java.util.Map;
    import java.util.ArrayList;
    import java.util.List;

    public class PropertyCsvReader implements PropertyReader {

        protected String filename;

        public PropertyCsvReader(String filename) {
            this.filename = filename;
        }

        @Override
        public List<Property> readFile(){

            List<Property> properties = new ArrayList<>();

            try {

                BufferedReader br = new BufferedReader(new FileReader(filename));

                String headers = br.readLine();
                if (headers == null) return null; // checks if theres a null value in the header

                String[] headernames = headers.split(","); // splits header values

                Map<String, Integer> headerIndex = new HashMap<>();
                for (int i = 0; i < headernames.length; i++) {
                    headerIndex.put(headernames[i].trim(), i);
                }

                int marketIndex = headerIndex.get("market_value");
                int areaIndex = headerIndex.get("total_livable_area");
                int zipcodeIndex = headerIndex.get("zip_code");

                String line;
                while ((line = br.readLine()) != null) {
                    if(line.trim().isEmpty()) continue;

                    String[] values = line.split(",");
                    String marketValueString = values[marketIndex].trim();
                    String areaString = values[areaIndex].trim();
                    String zipcodeFull = values[zipcodeIndex].trim();

                    if (marketValueString.isEmpty() || areaString.isEmpty()) continue;

                    double marketValue;
                    double livableArea;

                    try {
                        marketValue = Double.parseDouble(marketValueString);
                        livableArea = Double.parseDouble(areaString);
                    } catch (NumberFormatException e) { // if value is non-numeric, skip row
                        continue;
                    }

                    if (marketValue <= 0 || livableArea <= 0) continue;

                    String zipcode = zipcodeFull.length() >= 5 ? zipcodeFull.substring(0, 5) : zipcodeFull;

                    Property property = new Property(zipcode, marketValue, livableArea);
                    properties.add(property);

                }

            } catch (IOException e) {
                throw e;
            }

            return properties;
        }
    }

