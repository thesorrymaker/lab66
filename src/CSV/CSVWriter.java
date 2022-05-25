package CSV;

import Collection.Vehicle;

import java.io.*;
import java.util.LinkedHashSet;

/**
 * write in format csv
 */
public class CSVWriter {

    /**
     * @param linkedHashSet write file to
     * @param path          File
     * @throws IOException by File and readline()
     */
    public void writetofile(LinkedHashSet<Vehicle> linkedHashSet, String path) throws IOException {
        File file = new File(path);
        String firstline;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String addfirstline = "id,name,vehicleType,fuelType,location,x,y,z,creationdate,birthday\n";
        BufferedOutputStream BOS = new BufferedOutputStream(new FileOutputStream(file));
        firstline = bufferedReader.readLine();
        if (firstline == null || !(firstline.equals("id,name,vehicleType,fuelType,location,x,y,z,creationdate,birthday"))) {
            BOS.write(addfirstline.getBytes());
            BOS.flush();
        }
        if(linkedHashSet!=null) {
            for (Vehicle vehicle : linkedHashSet) {
                BOS.write(vehicle.toString().getBytes());
            }
        }
        bufferedReader.close();
        BOS.close();
        //return linkedHashSet;
    }
}