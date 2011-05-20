package miage.ecom.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lou
 */
@Entity
@Table(name = "account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByIdAccount", query = "SELECT a FROM Account a WHERE a.idAccount = :idAccount"),
    @NamedQuery(name = "Account.findByIban", query = "SELECT a FROM Account a WHERE a.iban = :iban"),
    @NamedQuery(name = "Account.findByBalance", query = "SELECT a FROM Account a WHERE a.balance = :balance")
})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_account", unique = true, nullable = false)
    private Integer idAccount;
    @Size(max = 34)
    @Column(name = "iban")
    private String iban;
    @Column(name = "balance")
    private Long balance;
    @JoinColumn(name = "id_status", referencedColumnName = "id_status")
    @ManyToOne(optional = false)
    private Status status;

    public Account() {
    }

    public Account(Integer idAccount) {
	this.idAccount = idAccount;
    }

    public Integer getIdAccount() {
	return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
	this.idAccount = idAccount;
    }

    public String getIban() {
	return iban;
    }

    public void setIban(String iban) {
	this.iban = iban;
    }

    public Long getBalance() {
	return balance;
    }

    public void setBalance(Long balance) {
	this.balance = balance;
    }

    public Status getStatus() {
	return status;
    }

    public void setStatus(Status idStatus) {
	this.status = idStatus;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (idAccount != null ? idAccount.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Account)) {
	    return false;
	}
	Account other = (Account) object;
	if ((this.idAccount == null && other.idAccount != null) || (this.idAccount != null && !this.idAccount.equals(other.idAccount))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "miage.ecom.entity.Account[ idAccount=" + idAccount + " ]";
    }
}
