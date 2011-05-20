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

package miage.ecom.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author MRABARIS
 */
@Entity
@Table(name = "purchase")
@IdClass(PurchasePK.class)
@NamedQueries({
    @NamedQuery(name = "Purchase.findAll", query = "SELECT p FROM Purchase p"),
    @NamedQuery(name = "Purchase.findByIdOrder", query = "SELECT p FROM Purchase p WHERE p.customerOrder = :id_order"),
    @NamedQuery(name = "Purchase.findByIdProduct", query = "SELECT p FROM Purchase p WHERE p.product = :id_product"),
    @NamedQuery(name = "Purchase.findByQuantity", query = "SELECT p FROM Purchase p WHERE p.quantity = :quantity")
})
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @JoinColumn(name = "id_product", referencedColumnName = "id_product", insertable=false , updatable=false)
    @ManyToOne
    private Product product;

    @Id
    @JoinColumn(name = "id_order", referencedColumnName = "id_order" , insertable=false , updatable=false)
    @ManyToOne
    private CustomerOrder customerOrder;

    @Column(name = "quantity")
    private Integer quantity;

    public Purchase() {
    }

    

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerOrder != null ? customerOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Purchase)) {
            return false;
        }
        Purchase other = (Purchase) object;
        if ((this.getCustomerOrder() == null && other.getCustomerOrder()!= null) || (this.getCustomerOrder() != null && !this.customerOrder.equals(other.getCustomerOrder()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.myecom.entity.Purchase[purchasePK=" + product.getIdProduct() + "]";
    }

    public boolean isPurchasable(){
        return product != null && quantity != null ;
    }
}
