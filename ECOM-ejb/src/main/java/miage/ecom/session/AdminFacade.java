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
import miage.ecom.entity.Admin;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
@Stateless
public class AdminFacade extends AbstractFacade<Admin> implements AdminFacadeLocal {

	@PersistenceContext(unitName = "EcomPersistenceUnit")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public AdminFacade() {
		super(Admin.class);
	}

	@Override
	public Admin authenticate(String login, String password) {

		TypedQuery<Admin> findByCredentials = this.em.createNamedQuery(
				"Admin.findByCredentials", Admin.class);
		findByCredentials.setParameter("login", login);
		findByCredentials.setParameter("password", password);

		LinkedList<Admin> resultList = new LinkedList<Admin>(
				findByCredentials.getResultList());

		if (!resultList.isEmpty()) {
			return resultList.getFirst();
		} else {
			return null;
		}
	}

	@Override
	public Admin findByLogin(String login) {
		
		TypedQuery<Admin> findByCredentials = this.em.createNamedQuery("Admin.findByLogin", Admin.class);
		findByCredentials.setParameter("login", login);

		LinkedList<Admin> resultList = new LinkedList<Admin>(findByCredentials.getResultList());

		if (!resultList.isEmpty()) {
			return resultList.getFirst();
		} else {
			return null;
		}
		
	}
}
