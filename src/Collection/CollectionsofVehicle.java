package Collection;

import java.time.LocalDate;
import java.util.LinkedHashSet;

/**
 * this class deal with the LinkedHashSet,which save all the vehicle that was set by user.
 */
public class CollectionsofVehicle {
    private static LocalDate InitializationTime;
    private static LinkedHashSet<Vehicle> vehicle;
    public static boolean Initialization = false;

    /**
     * When static linkedhashset vehicle wasn't initialized,throw NotInitializationException
     *
     * @return static linkedhashset vehicle
     * @throws NotInitializationException while Initialization == true
     */
    public LinkedHashSet<Vehicle> getVehicle() throws NotInitializationException {
        if (!Initialization) {
            throw new NotInitializationException("collections wasn't initialized\n");
        } else {
            return vehicle;
        }
    }

    /**
     * just do the initialization of people,at the same time,get the current time of initialization
     */
    public void doInitialization() {
        if (!Initialization) {
            vehicle = new LinkedHashSet<>();
            InitializationTime = LocalDate.now();
            Initialization = true;
        }
    }

    /**
     * return InitializationTime
     *
     * @return LocalDate
     */
    public LocalDate getInitializationTime() {
        return InitializationTime;
    }

    public static void setVehicle(LinkedHashSet<Vehicle> newone) {
        vehicle = newone;
    }

}
