/*
 *  The MIT License
 * 
 *  Copyright 2011 mrabaris.
 * 
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 * 
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 * 
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package miage.ecom.admin.command;

import miage.ecom.appclient.EcomAdminRemote;
import miage.ecom.appclient.command.ProductShellCommand;
import miage.ecom.entity.Product;
import miage.shell.command.option.BaseOption;

/**
 *
 * @author mrabaris
 */
public class ProductAdminShellCommand extends ProductShellCommand{

    

    public ProductAdminShellCommand(EcomAdminRemote ecomBeanRemote) {
        super(ecomBeanRemote);
        this.addOption(new BaseOption("save", true));
    }

   
    public void saveAction() {
        try{
            Product product = new Product();
            String name= dataArguments.getFirst();
            product.setName(name);
            product.setDescription(name);
            product.setReference(name);
            product.setPrice(Long.parseLong(dataArguments.getLast()));
            this.getOut().println("Processing save on product");
            ((EcomAdminRemote) ecomBeanRemote).saveProduct(product);
            this.getOut().println("Product saved");
        }catch(Exception e){
            this.getOut().println("Can't save product");
            this.getOut().println("Usage : product --save <name> <price>");
            e.printStackTrace(this.getErr());
        }
       
    }

}
