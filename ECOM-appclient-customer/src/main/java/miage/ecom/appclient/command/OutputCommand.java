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
import miage.ecom.output.Output;
import miage.shell.command.AbstractActionCommand;
import miage.shell.command.option.BaseOption;

/**
 *
 * @author MRABARIS
 */
public class OutputCommand extends AbstractActionCommand{

        private final EcomBeanRemote ecomCustomerRemote;

        public OutputCommand(EcomBeanRemote ecomCustomerRemote) {
            super();
            this.ecomCustomerRemote = ecomCustomerRemote;
            addOption(new BaseOption("XML", true));
            addOption(new BaseOption("HTML", true));
            addOption(new BaseOption("JSON", true));
            addOption(new BaseOption("TEXT", true));
            addOption(new BaseOption("WML", true));
        }


        @Override
	public String getName() {
		return "output";
	}

	@Override
	public String getUsage() {
		return "output --<format>";
	}

	@Override
	public String getShortDescription() {
		return "Format de l'output [XML,TEXT,HTML]";
	}

        @Override
	public void defaultAction(){
            String output = this.ecomCustomerRemote.getOutput();
            if(output != null){
                this.getOut().println("Current Output : " + output);
            }else{
                this.getOut().println("Unknown Current Output");
            }
            
        }

        public void XMLAction(){
            this.ecomCustomerRemote.setOutput(Output.XML);
        }

        public void HTMLAction(){
            this.ecomCustomerRemote.setOutput(Output.HTML);
        }

        public void TEXTAction(){
            this.ecomCustomerRemote.setOutput(Output.TEXT);
        }

        public void WMLAction(){
            this.ecomCustomerRemote.setOutput(Output.WML);
        }

        public void JSONAction(){
            this.ecomCustomerRemote.setOutput(Output.JSON);
        }
}
