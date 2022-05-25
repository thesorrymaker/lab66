package Command;

import CSV.CSVWriter;
import Collection.*;
import java.sql.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * command manager
 */
public class CommandManager {
    public CommandManager() {
        commands.add(new Add());
        commands.add(new Addifmin());
        commands.add(new Average());
        commands.add(new Clear());
        commands.add(new ExecuteScript());
        commands.add(new Exit());
        commands.add(new Help());
        commands.add(new History());
        commands.add(new Info());
        commands.add(new Print());
        commands.add(new RemoveById());
        commands.add(new RemoveFuelType());
        commands.add(new RemoveGreater());
        commands.add(new Show());
        commands.add(new UpdateID());
    }

    private final LinkedHashSet<AbstractCommand> commands = new LinkedHashSet<>();
    private boolean findid = false;

    //set as response of Server
    private String out = "";

    public void setOut(String out, boolean ADD) {
        if (!ADD) {
            this.out = out;
        } else {
            this.out = this.out + out;
        }
    }

    public String getOut() {
        return out;
    }

    /**
     * get static LinkedHashSet<AbstrcteCommand> commands
     *
     * @return LinkedHashSet
     */
    public LinkedHashSet<AbstractCommand> getCommands() {
        return this.commands;
    }

    /**
     * use Iterator to read LinkedHashSet<AbstractCommand> commands,and print all helps
     */
    public void executeHelp() {
        setOut("All helps:\n", false);
        commands.stream().forEach(command -> {
            setOut(command.getName() + ":" + command.getHelp() + "\n", true);
        });
    }

    /**
     * user set and add a new object with the help of static method {@link Vehicle#vehiclecreate()}
     *
     * @param vehicle
     * @throws ValueTooBigException
     * @throws ValueTooSmallException
     * @throws NullException
     */
    public void executeAdd(Vehicle vehicle) throws ValueTooBigException, ValueTooSmallException, NullException {
        new CollectionsofVehicle().doInitialization();
        new CollectionsofVehicle().getVehicle().add(vehicle);
    }

    /**
     * If user set a person smaller than him, add the new person
     *
     * @param p
     */
    public void executeAddifmin(Vehicle p, CommandManager commandManager) {
        LinkedHashSet<Vehicle> judge = new LinkedHashSet<>();
        new CollectionsofVehicle().getVehicle().stream().filter(P -> p.compareTo(P) > 0).forEach(judge::add);
        if (judge.size() == 0) {
            new CollectionsofVehicle().getVehicle().add(p);
            commandManager.setOut("You add\n" + p.toString() + "to collection\n", false);
        } else {
            commandManager.setOut("Failed to add\n", false);
            Vehicle.balaceicode();
        }
    }

    /**
     * print the average of height
     */
    public void executeAverage() {
        Integer result;
        Iterator<Vehicle> iterator = new CollectionsofVehicle().getVehicle().iterator();
        if (iterator.hasNext()) {
            Integer Total = 0;
            while (iterator.hasNext()) {
            }
            result = Total / (new CollectionsofVehicle().getVehicle().size());
            setOut("the average of heights is " + result + "\n", false);
            System.out.print(out);
        } else {
            setOut("collections of people is still empty\n", false);
            throw new NullException("collections of people is still empty\n");
        }
    }

    /**
     * clear all elements in collections
     */
    public void executeClear() throws NullException {
        setOut("Now collection is cleared anyway\n", false);
        new CollectionsofVehicle().getVehicle().clear();
    }

    /**
     * exit the program
     */
    public void executeExit() {
        setOut("Exit", false);
    }

    /**
     * print the information of collection(type,amount of elements,when it is created)
     */
    public void executeInfo() {
        if (!CollectionsofVehicle.Initialization) {
            throw new NotInitializationException("collections was initialized");
        } else {
            System.out.print("the date of initialization is " + new CollectionsofVehicle().getInitializationTime() + "\n");
        }
        setOut("the amount of elements is " +  new CollectionsofVehicle().getVehicle().size() + "\n" + "the type of collection is " + new CollectionsofVehicle().getVehicle().getClass() + "\n", false);
        System.out.print(out);
    }

    /**
     * print all the elements
     * When collections is empty,throw NullException
     *
     * @throws NullException
     */
    public void executePrint() throws NullException {
        if (new CollectionsofVehicle().getVehicle().size() == 0) {
            setOut("Collection is still empty\n", false);
            System.out.print(out);
        } else {
            setOut("Informations of locations:\n", false);
            new CollectionsofVehicle().getVehicle().stream().forEach(P -> setOut(P.getId() + ":" + P.getLocation().toString(), true));
        }
    }

    /**
     * get the location of all people
     *
     * @return
     */
    private LinkedHashSet<Location> getLocations() {
        LinkedHashSet<Location> linkedHashSet = new LinkedHashSet<>();
        Iterator<Vehicle> iterator = new CollectionsofVehicle().getVehicle().iterator();
        while (iterator.hasNext()) {
            linkedHashSet.add(iterator.next().getLocation());
        }
        return linkedHashSet;
    }

    public void executeRemoveById(Integer id) {
        Vehicle p = findByid(id);
        if (findid == false) {
            throw new ParaInapproException("no such a person with this id\n");
        } else {
            findid = false;
            new CollectionsofVehicle().getVehicle().remove(p);
        }
    }

    /**
     * return person with specified id
     *
     * @param id
     * @return Person
     */
    public Vehicle findByid(Integer id) {
        Vehicle p = null;
        Vehicle m;
        Iterator<Vehicle> iterator = new CollectionsofVehicle().getVehicle().iterator();
        out:
        while (iterator.hasNext()) {
            if ((m = iterator.next()).getId().equals(id)) {
                p = m;
                findid = true;
                break out;
            }
        }
        if (!findid) {
            findid = false;
            throw new NullException("no such a person with this id\n");
        }
        findid = false;
        return p;
    }

    /**
     * remove person with specified eye color
     * {@link CommandManager#findbyEye(String)}
     *
     * @param eye
     */
    public void executeRemoveEyeColor(String eye) {
        for (Vehicle p : findbyEye(eye)) {
            new CollectionsofVehicle().getVehicle().remove(p);
        }
    }

    /**
     * find person with Specified eye color
     *
     * @param eye
     * @return
     */
    public LinkedHashSet<Vehicle> findbyEye(String eye) {
        LinkedHashSet<Vehicle> linkedHashSet = new LinkedHashSet<>();
        Vehicle A;
        FuelType eyeColor = FuelType.valueOf(eye.toUpperCase());
        Iterator<Vehicle> iterator = new CollectionsofVehicle().getVehicle().iterator();
        while (iterator.hasNext()) {
            if ((A = iterator.next()).getFuelType() == eyeColor) {
                linkedHashSet.add(A);
            }
        }
        return linkedHashSet;
    }

    /**
     * Remove all the people,whose if bigger than specified
     * {@link CommandManager#findByid(Integer)}
     *
     * @param in
     * @throws NullException
     */
    public void executeRemoveGreater(Integer in) throws NullException {
        LinkedHashSet<Vehicle> newone = new LinkedHashSet<>();
        Vehicle B = findByid(Integer.valueOf(in));
        if (B == null) {
            setOut("No element is available\n", false);
            throw new NullException("No element is available\n");
        } else {
            setOut("You remove:\n", false);
            new CollectionsofVehicle().getVehicle().stream().filter(P -> P.getId() <= in).forEach(newone::add);
            new CollectionsofVehicle().getVehicle().stream().filter(P -> P.getId() > in).forEach(P -> {
                setOut(P.toString(), true);
            });
            CollectionsofVehicle.setVehicle(newone);
        }
    }

    /**
     * write static LinkedHashSet in format csv
     * {@link CSVWriter#writetofile(LinkedHashSet, String)}
     *
     * @throws IOException
     */
    public void executeSave(String path) throws IOException,ClassNotFoundException,SQLException{
        LinkedHashSet<Vehicle> linkedHashSet = new CollectionsofVehicle().getVehicle();
        new CSVWriter().writetofile(linkedHashSet, path);
        Class.forName("org.postgresql.Driver");
        HashSet<Vehicle> people = new CollectionsofVehicle().getVehicle();
        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/studs","postgres","123456")){
            try(PreparedStatement ps = connection.prepareStatement("INSERT INTO people (name,haircolor,eyecolor,height,location,x,y,z,creationdate,birthday,username) values (?,?,?,?,?,?,?,?,?,?,?)")){
                for(Vehicle P:people){
                    ps.setObject(1,P.getName());
                    ps.setObject(2,P.getVehicleType().toString());
                    ps.setObject(3,P.getFuelType().toString());
                    ps.setObject(4,P.getLocation().getName());
                    ps.setObject(5,P.getLocation().getX());
                    ps.setObject(6,P.getLocation().getY());
                    ps.setObject(7,P.getLocation().getZ());
                    ps.setObject(8,P.getLocation().getCreationdate().toString());
                    ps.setObject(9,P.getBirthday().toString());
                    ps.setObject(10,"un");
                    int n = ps.executeUpdate();
                    System.out.print(n);
                }
            }
        }
    }

    /**
     * print all the elements
     */
    public void executeShow() {
        String Show = "";
        setOut("Informations of people:\n" + "id,name,haircolor,eyecolor,height,location,x,y,z,creationdate,birthday\n", false);
        if (new CollectionsofVehicle().getVehicle().size() == 0) {
            setOut("Collection of people is still empty\n", false);
            System.out.print("collection of people is still empty\n");
        } else {
            new CollectionsofVehicle().getVehicle().stream().forEach(P -> setOut(P.toString(), true));
        }
    }

    /**
     * reset element with specified id.
     * {@link CommandManager#findByid(Integer)}
     * @throws ParaInapproException
     */
    public void executeUpdateID(String in) throws ParaInapproException {
        Integer id = Integer.valueOf(in);
        Vehicle p;
        String before = "";
        String after = "";
        Iterator<Vehicle> iterator = new CollectionsofVehicle().getVehicle().iterator();
        out:
        while (iterator.hasNext()) {
            if (findByid(id) == null) {
                setOut("no such a person with this id\n", false);
                throw new ParaInapproException("no such a person with this id\n");
            }
            if ((p = iterator.next()).getId() == id) {
                new CollectionsofVehicle().getVehicle().remove(p);
                before = p.toString();
                Vehicle insert = Vehicle.vehiclecreate();
                insert.changeId(id);
                Vehicle.balaceicode();
                new CollectionsofVehicle().getVehicle().add(insert);
                after = insert.toString();
                break out;
            }
        }
        setOut("You replace the\n" + before + "to\n" + after, false);
    }

    public void executeUpdateID(String in, Vehicle P) throws ParaInapproException {
        Integer id = Integer.valueOf(in);
        Vehicle p;
        String before = "";
        String after = "";
        Iterator<Vehicle> iterator = new CollectionsofVehicle().getVehicle().iterator();
        out:
        while (iterator.hasNext()) {
            if (findByid(id) == null) {
                throw new ParaInapproException("no such a person with this id\n");
            }
            if ((p = iterator.next()).getId().equals(id)) {
                new CollectionsofVehicle().getVehicle().remove(p);
                before = p.toString();
                Vehicle insert = P;
                insert.changeId(id);
                new CollectionsofVehicle().getVehicle().add(insert);
                after = insert.toString();
                break out;
            }
        }
        setOut("You replace the\n" + before + "to\n" + after, false);
    }


    /**
     * print the last 7 commands
     */
    public void executeHistory() {
        String history = "";
        int size = new History().getHistory().size();
        Iterator<String> iterator = new History().getHistory().iterator();
        if (new History().getHistory().size() <= 7) {
            while (iterator.hasNext()) {
                history = history + iterator.next();
            }
        } else {
            while (size > 7) {
                iterator.next();
                size--;
            }
            while (iterator.hasNext()) {
                history = history + iterator.next();
            }
        }
        setOut("last 7 commands:\n" + history, false);
    }

    public void executeScript(String[] commarg) {
        setOut("you execute script " + commarg[1] + "\n", false);
    }
}