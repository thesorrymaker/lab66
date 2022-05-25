package Collection;

/**
 * the colors of Eyes
 *
 * @author liao yihong
 */
public enum FuelType {
    KEROSENE,
    ELECTRICITY,
    ALCOHOL,
    PLASMA,
    ANTIMATTER;

    /**
     * show all the element with String
     *
     * @return String
     */
    public static String List() {
        StringBuilder list = new StringBuilder();
        for (FuelType fuelType : FuelType.values()) {
            list.append(fuelType.name()).append("\n");
        }
        return list.toString();
    }
}