package CSV;

import Collection.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.LinkedHashSet;

/**
 * read a csv file
 */
public class CSVReader {
    /**
     * read a csv file and write it to a linkedhashset
     *
     * @param vehiclelinkedhashset which will be written
     * @param path                File
     * @throws IOException by FileReader and readline()
     */
    public LinkedHashSet<Vehicle> readfile(LinkedHashSet<Vehicle> vehiclelinkedhashset, String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.readLine();
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            String[] information = s.split(",");
            Coordinates coordinates = new Coordinates(Integer.valueOf(information[6]), Integer.valueOf(information[7]));
            Location location = new Location(coordinates, information[5], Integer.valueOf(information[8]));
            Vehicle p = new Vehicle(location, VehicleType.valueOf(information[2].toUpperCase()), FuelType.valueOf(information[3].toUpperCase()), information[1]);
            Vehicle.balaceicode();
            p.changeId(Integer.valueOf(information[0]));
            p.setCreationDate(LocalDate.parse(information[9]));
            p.setBirthday(ZonedDateTime.parse(information[10]));
            vehiclelinkedhashset.add(p);
        }
        return vehiclelinkedhashset;
    }
}
