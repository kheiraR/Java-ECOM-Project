/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package miage.ecom.appclient.command;

import java.util.List;
import miage.ecom.appclient.EcomBeanRemote;
import miage.ecom.appclient.helpers.PurchaseHelper;
import miage.ecom.entity.Purchase;
import miage.shell.command.CartCommand;
import miage.shell.command.option.OptionName;

/**
 *
 * @author mrabaris
 */
public class CartShellCommand extends CartCommand{

        
        private EcomBeanRemote ecomCustomerRemote;

       

        public CartShellCommand(EcomBeanRemote ecomCustomerRemote){
            super();
            this.ecomCustomerRemote = ecomCustomerRemote;
        }

        @Override
        public void defaultAction(){
            super.defaultAction();
            List<Purchase> purchases = ecomCustomerRemote.getCartContents();
            PurchaseHelper.print(this.getOut(), purchases);
            this.getOut().println("Total Value : " + this.ecomCustomerRemote.getTotalValue());
        }


        @Override
        public void addAction(){
            super.addAction();
            if(dataArguments.size()< 2){
                this.getOut().println("cart --add|-a idProduct quantity");
            }else{
                try{
                    int idProduct = Integer.valueOf(dataArguments.getFirst());
                    int quantity = Integer.valueOf(dataArguments.getLast());
                    if(idProduct > 0 && quantity > 0){
                        this.ecomCustomerRemote.addProductToCart(idProduct, quantity);
                        this.getOut().println(this.ecomCustomerRemote.getCartMessages());
                    }else{
                        this.getOut().println("Les arguments doivent être des entiers positifs");
                    }

                }catch(NumberFormatException e){
                    this.getOut().println("Les arguments doivent être des entiers positifs");
                    e.printStackTrace(System.err);
                }catch(Exception e){
                    this.getOut().println(e.getMessage());
                    e.printStackTrace(System.err);
                }
            }
            
            
            
        }

        @Override
        public void removeAction(){
            super.removeAction();
            if(dataArguments.size()< 2){
                this.getOut().println("cart --remove|-r idProduct quantity");
            }else{
                try{
                    int idProduct = Integer.valueOf(dataArguments.getFirst());
                    int quantity = Integer.valueOf(dataArguments.getLast());
                    if(idProduct > 0 && quantity > 0){
                        this.ecomCustomerRemote.removeProductFromCart(idProduct, quantity);
                        this.getOut().println(this.ecomCustomerRemote.getCartMessages());
                    }else{
                        this.getOut().println("Les arguments doivent être des entiers positifs");
                    }

                }catch(NumberFormatException e){
                    this.getOut().println("Les arguments doivent être des entiers positifs");
                    e.printStackTrace(System.err);
                }catch(Exception e){
                    this.getOut().println(e.getMessage());
                    e.printStackTrace(System.err);
                }
            }
            
        }

        @Override
        public void buyAction(){
            super.buyAction();
            this.getOut().println("Buying cart");
            this.ecomCustomerRemote.buyCartWith(values.get(optionsByName.get(OptionName.BUY)));
            this.getOut().println(this.ecomCustomerRemote.getCartMessages());
        }

        
}
