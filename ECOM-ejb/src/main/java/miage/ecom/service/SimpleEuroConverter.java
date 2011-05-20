/*
 *  The MIT License
 * 
 *  Copyright 2011 Michaël Schwartz <m.schwartz@epokmedia.fr>.
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

package miage.ecom.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Convertisseur prenant les symboles de base.
 * Le taux de conversion est en dur dans la classe
 * 
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
@Stateless
@Local(EuroConverter.class)
public class SimpleEuroConverter implements EuroConverter {

	
	@Override
	public double convertToEuro(double amount, String currencySymbol) {

        double taux = getExchangeRate(currencySymbol);
        double result = amount / taux;

        //2 décimales
        int tmp = (int) Math.round(result * 100);

        return tmp/100;
    }

	
	@Override
    public double convertFromEuro(double amount, String currencySymbol) {

        double taux = getExchangeRate(currencySymbol);
        double result = amount * taux;

        //2 décimales
        int tmp = (int) Math.round(result * 100);

        return tmp/100;
    }

	@Override
    public ArrayList<String> getCurrencySymbols() {

        ArrayList<String> currencySym = new ArrayList<String>();

        currencySym.add("EUR");
        currencySym.add("FRF");
        currencySym.add("DEM");
        currencySym.add("ATS");
        currencySym.add("BEF");
        currencySym.add("ESP");
        currencySym.add("ITL");
        currencySym.add("IEP");
        currencySym.add("NLG");
        currencySym.add("PTE");
        currencySym.add("FIM");
        currencySym.add("GRD");

        return currencySym;
    }

	
	@Override
    public String getCurrencyRealName(String currencySymbol) {

        Map<String,String> table = new HashMap<String, String>();

        table.put("EUR", "euro");
        table.put("FRF", "franc français");
        table.put("DEM", "mark allemand");
        table.put("ATS", "schilling autrichien");
        table.put("BEF", "franc belge/lux.");
        table.put("ESP", "peseta espagnole");
        table.put("ITL", "lire italienne");
        table.put("IEP", "livre irlandaise");
        table.put("NLG", "florin hollandais");
        table.put("PTE", "escudo portugais");
        table.put("FIM", "mark finlandais");
        table.put("GRD", "drachme grec");

        String currency = table.get(currencySymbol);

        return currency;
    }

	@Override
    public double getExchangeRate(String currencySymbol){

        Map<String,Double> table = new HashMap<String, Double>();

        table.put("EUR", new Double(1));
        table.put("FRF", new Double(6.55957));
        table.put("DEM", new Double(1.95583));
        table.put("ATS", new Double(13.7603));
        table.put("BEF", new Double(40.3399));
        table.put("ESP", new Double(166.386));
        table.put("ITL", new Double(1936.27));
        table.put("IEP", new Double(0.787564));
        table.put("NLG", new Double(2.20371));
        table.put("PTE", new Double(200.482));
        table.put("FIM", new Double(5.94573));
        table.put("GRD", new Double(340.75));

        double currency = table.get(currencySymbol);

        return currency;
    }

}
