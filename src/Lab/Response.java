package Lab;

import Collection.Vehicle;

import java.io.Serializable;
import java.util.LinkedHashSet;

public class Response implements Serializable {

    Response(LinkedHashSet<Vehicle> vehicle, String managerout){
        this.vehicle = vehicle;
        this.managerout = managerout;
        this.id = null;
    }

    Response(Integer id,String managerout){
        this.vehicle = null;
        this.id = id;
        this.managerout = managerout;
    }

    private final LinkedHashSet<Vehicle> vehicle;

    private final String managerout;

    private final Integer id;

    public LinkedHashSet<Vehicle> getVehicle() {
        return vehicle;
    }

    public String getManagerout() {
        return managerout;
    }

    public Integer getId() {
        return id;
    }
}
