package Command;

import Collection.CollectionsofVehicle;
import Collection.Vehicle;
import Lab.CommandPackage;

import java.util.LinkedHashSet;

/**
 * command RemoveVehicleType
 */
public class RemoveFuelType extends AbstractCommand {
    public RemoveFuelType() {
        this.name = "RemoveFuelType";
        this.help = "remove one element from the collection whose eyeColor field value is equivalent to the specified one";
    }

    /**
     * remove the element with specified eye color.Only accept one parameter
     * {@link CommandManager#executeRemoveFuelType(String)}
     *
     * @param manager CommandManger
     */
    public void execute(CommandManager manager, CommandPackage commandPackage) {
        String out = "You remove:\n";
        manager.setOut(out, false);
        LinkedHashSet<Vehicle> newone = new LinkedHashSet<>();
        Vehicle P = commandPackage.getLinkedHashSet().iterator().next();
        new CollectionsofVehicle().getVehicle().stream().filter(People -> People.getFuelType() == P.getFuelType()).forEach(P1 -> manager.setOut(P1.toString(), true));
        new CollectionsofVehicle().getVehicle().stream().filter(People -> People.getFuelType() != P.getFuelType()).forEach(newone::add);
        CollectionsofVehicle.setVehicle(newone);
    }
}
