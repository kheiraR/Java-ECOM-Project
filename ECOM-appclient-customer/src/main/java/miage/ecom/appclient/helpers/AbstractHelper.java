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

package miage.ecom.appclient.helpers;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import miage.ecom.output.Output;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author MRABARIS
 */
public abstract class AbstractHelper<T> {

    public void print(PrintStream out, List<T> entitys, String output){
        if(output != null && output.equals(Output.XML)){
                printXML(out, entitys);

        }else{
            print(out, entitys);
        }
    }


    public void print(PrintStream out, T entity, String output){
        if(output != null && output.equals(Output.XML)){
                printXML(out, entity);

        }else{
            List<T> entitys = new ArrayList<T>();
            entitys.add(entity);
            print(out, entitys);
        }
    }

    public void printXML(PrintStream out, List<T> entitys){
        try{
            XMLOutputter xMLOutputter = new XMLOutputter();
            xMLOutputter.output(getXmlRepresentation(entitys), out);

            out.println();
        }catch(Exception e){
            e.printStackTrace(out);
        }
    }




    public void printXML(PrintStream out, T entity){
        try{
            XMLOutputter xMLOutputter = new XMLOutputter();
            xMLOutputter.output(getXmlRepresentation(entity), out);

            out.println();
        }catch(Exception e){
            e.printStackTrace(out);
        }
    }

    

    public abstract void print(PrintStream out, List<T> entitys);

    public Element getXmlRepresentation(List<T> entitys){
        Element element = new Element(getClassName() + "s");
        for(T entity : entitys){
            element.addContent(getXmlRepresentation(entity));
        }
        return element;
    }

    public abstract Element getXmlRepresentation(T entity);

    public abstract String getClassName();

}
