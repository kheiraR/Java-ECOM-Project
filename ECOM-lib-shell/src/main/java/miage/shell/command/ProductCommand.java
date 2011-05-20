package miage.shell.command;

import miage.shell.command.option.BaseOption;
import miage.shell.command.option.OptionName;

/**
 *
 * @author Lou
 */
public class ProductCommand extends AbstractActionCommand {
    
    public ProductCommand() {
        this.addOption(new BaseOption(OptionName.STORE, false));
        this.addOption(new BaseOption("name", false));
        this.addOption(new BaseOption("like", false));
        this.addOption(new BaseOption("price", false));
    }

    @Override
    public String getName() {
        return "product";
    }

    @Override
    public String getUsage() {
        return "product";
    }

    @Override
    public String getShortDescription() {
        return "Commande sur les produits";
    }

    @Override
    public void defaultAction() {
        this.getOut().println("Liste de tous les produits");
    }

    public void storeAction() {
        this.getOut().println("Liste de tous les produits du magasin : "+values.get(optionsByName.get(OptionName.STORE)));
    }

    public void nameAction() {
        this.getOut().println("Liste tous les produits dont le nom est : "+values.get(optionsByName.get(OptionName.NAME)));
    }

    public void likeAction() {
        this.getOut().println("Liste tous les produits dont le nom ressemble à : "+values.get(optionsByName.get(OptionName.LIKE)));
    }

    public void priceAction() {
        this.getOut().println("Liste tous les produits dans un intervalle de prix");
    }

}
