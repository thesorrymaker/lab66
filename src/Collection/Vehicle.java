package Collection;

import Tools.Tools;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Collections class Vehicle
 */
public class Vehicle implements Comparable<Vehicle>, Serializable {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    private java.time.ZonedDateTime birthday; //Поле может быть null
    private FuelType fuelType; //Поле не может быть null
    private VehicleType vehicleType; //Поле не может быть null
    private Location location; //Поле не может быть null
    public static int idcode = 0;

    /**
     * constructor
     *
     * @param location  Location
     * @param vehicleType color of Hair
     * @param fuelType  color of eyes
     * @throws NullException                               when set location or eyecolor as null
     * @throws ValueTooSmallException,ValueTooBigException when value of height or x,y,z not suitable
     */
    public Vehicle(Location location, VehicleType vehicleType, FuelType fuelType, String name) throws NullException, ValueTooSmallException, ValueTooBigException {
        setName(name);
        setLocation(location);
        setCoordinates(location.getCoordinates());
        setFuelType(fuelType);
        setVehicleTypeColor(vehicleType);
        this.id = setId();
        this.creationDate = location.getCreationdate();
        this.birthday = ZonedDateTime.now();
    }

    /**
     * set id
     *
     * @return void
     */
    public static Integer setId() {
        idcode = idcode + 1;
        return idcode;
    }

    public void resetid(Integer id){
        this.id = id;
    }

    public static void setIdcode(Integer code){
        idcode = code;
    }

    public static void balaceicode() {
        idcode = idcode - 1;
    }

    /**
     * set name
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * set coordinates.When coordinates=null,throws NullException
     *
     * @param coordinates Coordinates
     * @throws NullException when Coordinate set as null
     */
    public void setCoordinates(Coordinates coordinates) throws NullException {
        if (coordinates == null) {
            throw new NullException("coordinates can't be null");
        } else {
            this.coordinates = coordinates;
        }
    }

    /**
     * set the creationdate
     *
     * @param creationDate When location was created
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * set birthday
     *
     * @param birthday when Person was born
     */
    public void setBirthday(ZonedDateTime birthday) {
        this.birthday = birthday;
    }

    /**
     * set fuelType.When fuelType=null throws NullException
     *
     * @param fuelType FuelType
     * @throws NullException when set fuelType as null
     */
    public void setFuelType(FuelType fuelType) throws NullException {
        if (fuelType == null) {
            throw new NullException("fuelType can't be null\n");
        } else {
            this.fuelType = fuelType;
        }
    }

    /**
     * set location.When location==null throws NullException
     *
     * @param location Location
     * @throws NullException when set Location as null
     */
    public void setLocation(Location location) throws NullException {
        if (location == null) {
            throw new NullException("location can't be null\n");
        }
        this.location = location;
    }




    /**
     * set hair color.When vehicleType=null throws NullException
     *
     * @param vehicleType VehicleType
     * @throws NullException when haircolor set as null
     */
    public void setVehicleTypeColor(VehicleType vehicleType) throws NullException {
        if (vehicleType == null) {
            throw new NullException("vehicleType can't be null\n");
        } else {
            this.vehicleType = vehicleType;
        }
    }

    /**
     * return the name of vehicle
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * return the color of a vehicle
     *
     * @return String
     */
    public FuelType getFuelType() {
        return fuelType;
    }

    /**
     * return the id of a vehicle
     *
     * @return Integer
     */
    public Integer getId() {
        return id;
    }




    /**
     * return the location of a vehicle
     *
     * @return Location
     */
    public Location getLocation() {
        return location;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public ZonedDateTime getBirthday() {
        return birthday;
    }

    /**
     * set the id of a Vehicle
     *
     * @param id Integer
     */
    public void changeId(Integer id) {
        this.id = id;
    }

    public static Integer getidcode(){
        return idcode;
    }

    /**
     * here we set toString in order to write Vehicle to the file in format csv.
     *
     * @return information of a person
     */
    @Override
    public String toString() {
        return this.id + "," + this.name + "," + this.vehicleType + "," + this.fuelType + "," + this.location.getName() + "," + this.location.getX() + "," + this.coordinates.getY() + "," + this.location.getZ() + "," + this.creationDate + "," + this.birthday + "\n";
    }

    /**
     * user set a vehicle by himself
     *
     * @return Vehicle
     * @throws NullException,ValueTooBigException,ValueTooSmallException just like {@link Vehicle#Vehicle}
     */
    public static Vehicle vehiclecreate() throws NullException, ValueTooBigException, ValueTooSmallException, IllegalArgumentException {
        System.out.print("You have two chances to initialize every property\n");

        String sx;
        Integer x;
        try {
            System.out.print("Input x(<=79):\n");
            sx = Tools.input();
            x = Integer.valueOf(sx);
            if (x > 79)
                throw new ValueTooBigException("x should smaller than 79\n");
        } catch (ValueTooBigException v) {
            System.out.print(v.getMessage() + "try again:\n");
            sx = Tools.input();
            x = Integer.valueOf(sx);
            if (x > 79)
                throw new ValueTooBigException("x should smaller than 79\n");
        } catch (NumberFormatException N) {
            System.out.print("enter a number instead of a string\n" + "try again:\n");
            sx = Tools.input();
            x = Integer.valueOf(sx);
            if (x > 79)
                throw new ValueTooBigException("x should smaller than 79\n");
        }

        String sy;
        Integer y;
        try {
            System.out.print("Input y(y<=794):\n");
            sy = Tools.input();
            y = Integer.valueOf(sy);
            if (y > 794)
                throw new ValueTooBigException("y should smaller than 794\n");
        } catch (ValueTooBigException v) {
            System.out.print(v.getMessage() + "try again:\n");
            sy = Tools.input();
            y = Integer.valueOf(sy);
            if (y > 794)
                throw new ValueTooBigException("y should smaller than 794\n");
        } catch (NumberFormatException N) {
            System.out.print("please enter a number instead of string\n" + "try again:\n");
            sy = Tools.input();
            y = Integer.valueOf(sy);
            if (y > 794)
                throw new ValueTooBigException("y should smaller than 794");
        }

        String sz;
        Integer z;
        try {
            System.out.print("Input z(>0):\n");
            sz = Tools.input();
            z = Integer.valueOf(sz);
            if (z <= 0)
                throw new ValueTooSmallException("z should bigger than 0\n");
        } catch (ValueTooSmallException v) {
            System.out.print(v.getMessage() + "try again:\n");
            sz = Tools.input();
            z = Integer.valueOf(sz);
            if (z <= 0)
                throw new ValueTooSmallException("z should bigger than 0\n");
        } catch (NumberFormatException N) {
            System.out.print("please enter a number instead of string\n" + "try again:\n");
            sz = Tools.input();
            z = Integer.valueOf(sy);
            if (z > 794)
                throw new ValueTooBigException("y should smaller than 794");
        }

        System.out.print("name of location:\n");
        String nameoflocation = Tools.input();
        System.out.print("name of Vehicle:\n");
        String name = Tools.input();
        String HC;
        VehicleType vehicleType;
        try {
            System.out.print("set vehicleTyp[e from\n" + VehicleType.List() + ":");
            HC = Tools.input();
            HC = HC.toUpperCase();
            vehicleType = VehicleType.valueOf(HC);
        } catch (IllegalArgumentException i) {
            System.out.print("no Such color in list\n" + "try again:\n");
            HC = Tools.input();
            HC = HC.toUpperCase();
            vehicleType = VehicleType.valueOf(HC);
        }
        String EC;
        FuelType fuelType;
        try {
            System.out.print("set fuelType from\n" + FuelType.List() + ":");
            EC = Tools.input();
            EC = EC.toUpperCase();
            fuelType = FuelType.valueOf(EC);
        } catch (IllegalArgumentException i) {
            System.out.print("no such type in list\n" + "try again:\n");
            EC = Tools.input();
            EC = EC.toUpperCase();
            fuelType = FuelType.valueOf(EC);
        }

        Coordinates coordinates = new Coordinates(x, y);
        Location location = new Location(coordinates, nameoflocation, z);
        return new Vehicle(location, vehicleType, fuelType, name);
    }

    /**
     * implements from comparable.
     * Used to do comparation
     *
     * @param o another Vehicle
     * @return 0-the same as another,-1-smaller,1-bigger
     */
    @Override
    public int compareTo(Vehicle o) {
        if (o == null) {
            throw new NullPointerException("Null can't be compared");
        } else if (this.getId().equals(o.getId())) {
            return 0;
        } else if (this.getId() > o.getId()) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * @param o Vehicle
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id.equals(vehicle.id) &&
                name.equals(vehicle.name) &&
                coordinates.equals(vehicle.coordinates) &&
                creationDate.equals(vehicle.creationDate) &&
                Objects.equals(birthday, vehicle.birthday) &&
                fuelType == vehicle.fuelType &&
                vehicleType == vehicle.vehicleType &&
                location.equals(vehicle.location);
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, birthday, fuelType, vehicleType, location);
    }
}
