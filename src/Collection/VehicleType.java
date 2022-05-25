package Collection;

/**
 * the color of hair
 *
 * @author liao yihong
 */
public enum VehicleType {
    CAR,
    BOAT,
    BICYCLE;


    /**
     * show all the elements in String
     *
     * @return
     */
    public static String List() {
        StringBuilder list = new StringBuilder();
        for (VehicleType vehicleType : VehicleType.values()) {
            list.append(vehicleType.name()).append("\n");
        }
        return list.toString();
    }
}
