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

package miage.ecom.entity;

import java.io.Serializable;

/**
 *
 * @author Maria Rabarison
 */

public class PurchasePK implements Serializable {

	private static final long serialVersionUID = -2481901006391956802L;
    
    private int customerOrder;
    
    private int product;

    public PurchasePK() {
    }

    public PurchasePK(int idOrder, int idProduct) {
        this.customerOrder = idOrder;
        this.product = idProduct;
    }

    public int getIdOrder() {
        return customerOrder;
    }

    public void setIdOrder(int idOrder) {
        this.customerOrder = idOrder;
    }

    public int getIdProduct() {
        return product;
    }

    public void setIdProduct(int idProduct) {
        this.product = idProduct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += customerOrder;
        hash += product;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchasePK)) {
            return false;
        }
        PurchasePK other = (PurchasePK) object;
        if (this.customerOrder != other.customerOrder) {
            return false;
        }
        if (this.product != other.product) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.myecom.entity.PurchasePK[idOrder=" + customerOrder + ", idProduct=" + product + "]";
    }

}
