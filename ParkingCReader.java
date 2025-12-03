package finalproject.org.data;

import java.util.List;
import finalproject.org.processing.ParkingViolation;

public interface ParkingCReader {
    public List<ParkingViolation> readFromCsv();
}