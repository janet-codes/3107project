package finalproject.org.processing;

import finalproject.org.data.ParkingJReader;
import finalproject.org.data.ParkingCReader;
import finalproject.org.data.PopulationReader;
import finalproject.org.data.PropertyReader;

import java.util.List;


public class Processor {

    protected ParkingCReader parkingcsv;
    protected ParkingJReader parkingjson;
    protected PopulationReader populationreader;
    protected PropertyReader propertyreader;

    protected final List<Property> properties;

    public Processor(ParkingCReader parkingcsv,
                     ParkingJReader parkingjson,
                     PopulationReader populationreader,
                     PropertyReader propertyreader) {

        this.parkingcsv = parkingcsv;
        this.parkingjson = parkingjson;
        this.populationreader = populationreader;
        this.propertyreader = propertyreader;

        // load once from the data tier
        this.properties = propertyreader.readFile();

    }

    //main menu option 2

    public Map<String, Integer> FinesPerCapita() {
        if (this.properties.size() == 0) return null;



    }

    //main menu option 3

    public int getAverageResidentialValue(int zip) {
        if (properties == null || properties.isEmpty()) {
            return 0;
        }

        // convert to the same format as Property.zipCode
        // (5 digits; this also preserves leading zeros if needed)
        String zipString = String.format("%05d", zip);

        double total = 0.0;
        int count = 0;

        for (Property p : properties) {
            if (zipString.equals(p.getZipCode())) {
                // PropertyCsvReader has already filtered out bad market values
                total += p.getMarketValue();
                count++;
            }
        }

        if (count == 0) {
            return 0;  // spec: show 0 when no residences in that ZIP
        }

        double avg = total / count;
        return (int) Math.round(avg);  // rounded to an integer
    }

}

//main menu option 4

    public int getAverageLivableArea(int zip) {


    }


// main menu option 5

// main menu option 6


// main menu option 7


}