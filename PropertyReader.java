package finalproject.org.data;
import finalproject.org.processing.Property;
import java.io.IOException;
import java.util.List;

public interface PropertyReader {
    public List<Property> readFile();
}