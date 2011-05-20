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

import miage.ecom.appclient.EcomCustomerRemote;
import miage.ecom.appclient.helpers.AccountHelper;
import miage.ecom.auth.AuthResult;
import miage.ecom.entity.Account;
import miage.shell.command.AccountCommand;

/**
 *
 * @author MRABARIS
 */
public class AccountShellCommand extends AccountCommand{

        private final EcomCustomerRemote ecomCustomerRemote;
        private AccountHelper accountHelper;

        public AccountShellCommand(EcomCustomerRemote ecomCustomerRemote){
            super();
            this.ecomCustomerRemote = ecomCustomerRemote;
            this.accountHelper = new AccountHelper();
        }

        @Override
        public void defaultAction() {
            super.defaultAction();
            AuthResult authResult = this.ecomCustomerRemote.getAuthResult();
            if(authResult == null || !authResult.isValid()){
                this.getOut().println("Please log in");
            }else{
                print(this.ecomCustomerRemote.getAuthResult().getIdentity().getValue().getIdAccount());
            }
        }


        private void print(Account account){
           accountHelper.print(this.getOut(), account, this.ecomCustomerRemote.getOutput());
        }
}
