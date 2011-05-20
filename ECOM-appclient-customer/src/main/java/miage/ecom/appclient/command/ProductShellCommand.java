/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.ecom.appclient.command;


import java.util.List;
import miage.ecom.appclient.EcomBeanRemote;
import miage.ecom.entity.Product;
import miage.shell.command.ProductCommand;
import miage.ecom.appclient.helpers.ProductHelper;
import miage.shell.command.option.OptionName;

/**
 *
 * @author Lou
 */
public class ProductShellCommand extends ProductCommand {

    protected EcomBeanRemote ecomBeanRemote;
    private ProductHelper productHelper;

    public ProductShellCommand(EcomBeanRemote ecomCustomerRemote) {
        super();
        this.ecomBeanRemote = ecomCustomerRemote;
        this.productHelper = new ProductHelper();
    }

    @Override
    public void defaultAction() {
        super.defaultAction();
        List<Product> products = ecomBeanRemote.getProductsList();
        print(products);
    }

    @Override
    public void likeAction() {
        super.likeAction();
        List<Product> products = this.ecomBeanRemote.getProductsByNameLike(values.get(optionsByName.get(OptionName.LIKE)));
        print(products);
    }

    @Override
    public void nameAction() {
        super.nameAction();
        List<Product> products = this.ecomBeanRemote.getProductsByName(values.get(optionsByName.get(OptionName.NAME)));
        print(products);
    }

    @Override
    public void priceAction() {
        super.priceAction();
        List<Product> products;
        if(dataArguments.size() == 1){
            products = this.ecomBeanRemote.getProductsByRangePrice(null, Long.valueOf(dataArguments.getFirst()) );
        }else{
            products = this.ecomBeanRemote.getProductsByRangePrice(Long.valueOf(dataArguments.getFirst()), Long.valueOf(dataArguments.getLast()) );
        }
        print(products);
    }

    @Override
    public void storeAction() {
        super.storeAction();
        List<Product> products = this.ecomBeanRemote.getProductsByStore(Integer.valueOf(values.get(optionsByName.get(OptionName.STORE))));
        print(products);
    }

    

    private void print(List<Product> products){
        productHelper.print(this.getOut(), products, this.ecomBeanRemote.getOutput());
    }
}
