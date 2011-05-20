/*
 *  The MIT License
 * 
 *  Copyright 2011 Maria Rabarison.
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

package miage.ecom.appclient.helpers;

import java.io.PrintStream;
import java.util.List;
import miage.ecom.entity.Store;
import org.jdom.Element;

/**
 *
 * @author MRABARIS
 */
public class StoreHelper {

    public static void print(PrintStream out, List<Store> stores){
        out.printf("%-20s   %6s%n", "ID", "Name");
        out.printf("--------------------------------------------------------%n");

        for (Store s : stores) {
            String id = s.getIdStore() + "";
            String name = s.getName() + "";
            System.out.printf("%-20s   %6s%n", id, name);
        }
    }

    public Element getXmlRepresentation(Store store){
        Element element = new Element("");
        return element;

    }
}
