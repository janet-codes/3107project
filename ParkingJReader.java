package finalproject.org.data;

import finalproject.org.processing.ParkingViolation;
import java.util.List;

public interface ParkingJReader {
    public List<ParkingViolation> readJSON();
}