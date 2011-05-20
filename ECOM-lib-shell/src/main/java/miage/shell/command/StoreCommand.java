package miage.shell.command;

/**
 *
 * @author Lou
 */
public class StoreCommand extends AbstractActionCommand {

    public StoreCommand() {
    }

    @Override
    public String getName() {
        return "store";
    }

    @Override
    public String getShortDescription() {
        return "Commande sur les magasins";
    }

    @Override
    public String getUsage() {
        return "store";
    }

    @Override
    public void defaultAction(){
        this.getOut().print("Liste tous les magasins");
    }

}
