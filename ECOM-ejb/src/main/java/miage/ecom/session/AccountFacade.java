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

package miage.ecom.session;

import java.util.LinkedList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import miage.ecom.entity.Account;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
@Stateless
public class AccountFacade extends AbstractFacade<Account> implements AccountFacadeLocal {

	@PersistenceContext(unitName = "EcomPersistenceUnit")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public AccountFacade() {
		super(Account.class);
	}

	@Override
	public Account authenticate(String login, String password) {

		TypedQuery<Account> findByCredentials = this.em.createNamedQuery("Account.findByCredentials", Account.class);
		findByCredentials.setParameter("login", login);
		findByCredentials.setParameter("password", password);
		
		LinkedList<Account> resultList = new LinkedList<Account>(findByCredentials.getResultList());

		if (!resultList.isEmpty()) {
			return resultList.getFirst();
		} else {
			return null;
		}
		
	}

        @Override
	public Account findByIban(String iBan) {

		TypedQuery<Account> findByCredentials = this.em.createNamedQuery("Account.findByIban", Account.class);
		findByCredentials.setParameter("iban", iBan);

		LinkedList<Account> resultList = new LinkedList<Account>(findByCredentials.getResultList());

		if (!resultList.isEmpty()) {
			return resultList.getFirst();
		} else {
			return null;
		}

	}

}
