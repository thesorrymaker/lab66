package Command;

import Collection.CollectionsofVehicle;
import Collection.NullException;
import Collection.Vehicle;
import Lab.CommandPackage;

import java.util.LinkedHashSet;

/**
 * command removebyid
 */
public class RemoveById extends AbstractCommand {
    public RemoveById() {
        this.name = "RemoveByid";
        this.help = "remove one element from the collection whose eyeColor field value is equivalent to the specified one";
    }

    /**
     * remove a element that contains specified id of collections.Only accept one int parameter
     * {@link CommandManager#executeRemoveById(Integer)}
     *
     * @throws ParaInapproException when para incorrect
     */
    public void execute(CommandManager manager, CommandPackage commandPackage) throws NullException {
        if (new CollectionsofVehicle().getVehicle().size() != 0) {
            LinkedHashSet<Vehicle> newone = new LinkedHashSet<>();
            new CollectionsofVehicle().getVehicle().stream().filter(Vehicle -> !Vehicle.getId().equals(commandPackage.getVehicle().getId())).forEach(newone::add);
            CollectionsofVehicle.setVehicle(newone);
            manager.setOut("You removed:\n" + commandPackage.getVehicle().toString(), false);
        } else {
            throw new NullException("Collection is empty\n");
        }
    }
}
