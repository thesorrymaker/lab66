package Command;

import Collection.Vehicle;
import Lab.CommandPackage;

/**
 * command Addifmin
 */
public class Addifmin extends AbstractCommand {
    public Addifmin() {
        this.name = "Addifmin";
        this.help = "add a new element to the collection if its value is less than the smallest element in this collection";
    }

    /**
     * add if hashcode of new element smaller than others
     * {@link CommandManager#executeAddifmin(Vehicle, CommandManager)}
     *
     * @param commandManager CommandManage
     * @throws ParaInapproException by executeAddifmin
     */
    public void execute(CommandManager commandManager, CommandPackage commandPackage) throws ParaInapproException {
        commandManager.executeAddifmin(commandPackage.getVehicle(), commandManager);
    }
}
