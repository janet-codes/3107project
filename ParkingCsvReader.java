//Yevheniia yy3195
package finalproject.org.data;

import finalproject.org.processing.ParkingViolation;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;

//Implement CSV reading for parking violations
// timestamp, fine, description, vehicle, state, violation ID, zip code

public class ParkingCsvReader implements ParkingCReader {

    /**
    @param filename path to the CSV file (src/main/resources/parking.csv)
     @return list of ParkingViolations objects
     */

    protected String filename;

    public ParkingCsvReader(String filename) {
        this.filename = filename;
    }

    @Override
    public List<ParkingViolation> readFromCsv() {
        List<ParkingViolation> violations = new ArrayList<>();

        if (filename == null || filename.trim().isEmpty()) {
            // You can either return empty list or throw IllegalArgumentException
            return violations;
        }

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8)) {

            String line;
            while ((line = reader.readLine()) != null) {
                //skip blank lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",", -1);
                if (parts.length != 7) {
                    continue;
                }

                String timestamp = parts[0].trim();
                String fineStr = parts[1].trim();
                String description = parts[2].trim();
                String vehicleID = parts[3].trim();
                String state = parts[4].trim();
                String violationID = parts[5].trim();
                String zipRaw = parts[6].trim();

                double fine;
                try {
                    fine = Double.parseDouble(fineStr);
                } catch (NumberFormatException e) {
                    continue;
                }

                String zipCode = null;
                if (!zipRaw.isEmpty()) {
                    zipCode = (zipRaw.length() >= 5) ? zipRaw.substring(0, 5) : zipRaw;
                }

                ParkingViolation violation = new ParkingViolation(
                        timestamp,
                        fine,
                        description,
                        vehicleID,
                        state,
                        violationID,
                        zipCode
                );

                violations.add(violation);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return violations;
    }
}
