/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package miage.shell.command;

import miage.shell.command.option.BaseOption;
import miage.shell.command.option.OptionName;

/**
 *
 * @author Maria Rabarison
 */
public class CartCommand extends AbstractActionCommand{

 

    @Override
    public String getName(){
        return "cart";
    }

    @Override
    public String getUsage(){
        return "cart [--add|--remove|--buy <value>]";
    }

    @Override
    public String getShortDescription(){
        return "commande pour interagir avec le caddie";
    }

    @Override
    public void defaultAction(){
       this.getOut().println("Liste tous les elements du caddie");
    }

    public CartCommand(){
        this.addOption(new BaseOption("add",false));
        this.addOption(new BaseOption("remove",false));
        this.addOption(new BaseOption("buy",false));
    }

    public void addAction(){
        this.getOut().println("ajoute dans le caddie le produit " + values.get(optionsByName.get(OptionName.ADD)));
        
    }
    
    public void removeAction(){
        this.getOut().println("retire du caddie le produit " + values.get(optionsByName.get(OptionName.REMOVE)));
    }

    public void buyAction(){
        this.getOut().println("achete le contenu du caddie avec le compte " + values.get(optionsByName.get(OptionName.BUY)));
    }
}
