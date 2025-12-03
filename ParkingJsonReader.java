/*Makenna */
package finalproject.org.data;
import finalproject.org.processing.ParkingViolation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.io.FileReader;
import java.util.*;
import java.io.*;

//Implement JSON reader
public class ParkingJsonReader implements ParkingJReader {

    protected String filename;

    public ParkingJsonReader(String filename) {
        this.filename = filename;
    }

    @Override
    public List<ParkingViolation> readJSON() {
        List<ParkingViolation> list = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = null;
            try {
                reader = new FileReader(filename);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            JSONArray parkingViolation = null;
            try {
                parkingViolation = (JSONArray) parser.parse(reader);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            for (Object o : parkingViolation) {
                JSONObject v = (JSONObject) o;
                String violationID = String.valueOf(v.get("ticket_number"));
                String vehicleID = (String) v.get("plate_id");
                String timestamp = (String) v.get("date");
                String zipCode = (String) v.get("zip_code");
                double fine = ((Number) v.get("fine")).doubleValue();
                String state = (String) v.get("state");
                String description = (String) v.get("violation");

                ParkingViolation pv = new ParkingViolation(
                        timestamp,
                        fine,
                        description,
                        vehicleID,
                        state,
                        violationID,
                        zipCode
                );
                list.add(pv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
