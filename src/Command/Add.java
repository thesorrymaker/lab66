package Command;

import Collection.CollectionsofVehicle;
import Collection.CollectionsofVehicle;
import Collection.Vehicle;
import Lab.CommandPackage;

/**
 * Add command allows user set and add a new element to collection
 */
public class Add extends AbstractCommand {
    public Add() {
        this.name = "add";
        this.help = "add a new element to collection";
    }

    /**
     * when length of command line argument bigger than 1,throw ParaInapproException
     * {@link Command.CommandManager#executeAdd(Vehicle)}
     *
     * @param manager        CommandManager
     * @param commandPackage CommandPackage
     */
    public void execute(CommandManager manager, CommandPackage commandPackage) {
        int a = commandPackage.getVehicle().getId();
        int b = Vehicle.getidcode();
        Vehicle vehicle = commandPackage.getVehicle();
        if(a<=b){
            Integer newcode = Vehicle.getidcode()+1;
            vehicle.resetid(newcode);
        }
        new CollectionsofVehicle().getVehicle().add(vehicle);
        Vehicle.setIdcode(vehicle.getId());
        manager.setOut("You add vehicle:\n" + vehicle.toString() + "to collection\n", false);
    }
}
