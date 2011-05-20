/*
 *  The MIT License
 * 
 *  Copyright 2011 MRABARIS.
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

package miage.ecom.appclient.command;

import miage.ecom.appclient.EcomBeanRemote;
import miage.ecom.appclient.EcomCustomerRemote;
import miage.shell.command.AbstractActionCommand;
import miage.shell.command.option.BaseOption;

/**
 *
 * @author Maria Rabarison
 */
public class TransactionCommand extends AbstractActionCommand{


        private EcomBeanRemote ecomCustomerRemote;

        @Override
	public String getName() {
		return "transaction";
	}

	@Override
	public String getUsage() {
		return "transaction --<action>";
	}

	@Override
	public String getShortDescription() {
		return "Gestion manuelle des transactions";
	}


        public TransactionCommand(EcomBeanRemote ecomCustomerRemote) {
            super();
            this.ecomCustomerRemote = ecomCustomerRemote;
            this.addOption(new BaseOption("begin", true));
            this.addOption(new BaseOption("commit", true));
            this.addOption(new BaseOption("rollback", true));
        }


        public void beginAction(){
            try{
                this.ecomCustomerRemote.beginTransaction();
            }catch(Exception e){
                this.getErr().println("Can not BEGIN transaction");
            }
        }


        public void commitAction(){
            try{
                this.ecomCustomerRemote.commitTransaction();
            }catch(Exception e){
                this.getErr().println("Can not COMMIT transaction");
            }
        }


        public void rollbackAction(){
            try{
                this.ecomCustomerRemote.rollbackTransaction();
            }catch(Exception e){
                this.getErr().println("Can not ROLLBACK transaction");
            }
        }



    @Override
    public void defaultAction(){
        this.getOut().println("Usage : " + this.getUsage());
        this.getOut().println("You need to specify an action");
    }

}
