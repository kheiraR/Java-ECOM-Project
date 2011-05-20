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

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.arquillian.api.Deployment;
import javax.ejb.EJB;
import miage.ecom.test.EcomBaseTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 *
 * @author Michaël Schwartz <m.schwartz@epokmedia.fr>
 */
@RunWith(Arquillian.class)
public class SimpleEuroConverterTest extends EcomBaseTest {

	@EJB
	public EuroConverter euroConverter;

	@Deployment
	public static JavaArchive createTestArchive() {
		return EcomBaseTest.getTestArchive(SimpleEuroConverterTest.class);
	}

	@Test
	public void testInjectBean() {
		assertNotNull(euroConverter);
	}

	@Test
	public void testGetCurrencyRealName() {
		assertEquals(euroConverter.getCurrencyRealName("EUR"), "euro");
	}
}
