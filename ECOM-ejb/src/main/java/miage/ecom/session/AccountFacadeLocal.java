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

import java.util.List;
import javax.ejb.Local;
import miage.ecom.entity.Account;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
@Local
public interface AccountFacadeLocal {

	void create(Account account);

	void edit(Account account);

	void remove(Account account);

	Account find(Object id);

	List<Account> findAll();

	List<Account> findRange(int[] range);

	int count();

	Account authenticate(String login, String password);

        Account findByIban(String iBan);

}
