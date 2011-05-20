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

package miage.ecom.web.admin.service;

import java.net.URL;
import java.net.URLConnection;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Schwartz Michaël
 */
public class ImgurImageManagerTest {

    public ImgurImageManagerTest() {
    }

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	/**
	 * Test of uploadImage method, of class ImgurImageManager.
	 */
	@Test
	public void testUploadImage() throws Exception {
		Assume.assumeTrue(false);

		System.out.println("uploadImage");

		URL url = new URL("http://i.imgur.com/y7prW.jpg");
		URLConnection openConnection = url.openConnection();

		ImgurImageManager instance = new ImgurImageManager();


		URL result = instance.uploadImage(openConnection.getInputStream());

		System.out.println(result.toString());

		assertTrue(result != null);
	}

	/**
	 * Test of uploadThumb method, of class ImgurImageManager.
	 */
	@Test
	public void testUploadThumb() throws Exception {
		Assume.assumeTrue(false);
		
		URL url = new URL("http://i.imgur.com/y7prW.jpg");
		URLConnection openConnection = url.openConnection();

		ImgurImageManager instance = new ImgurImageManager();


		URL result = instance.uploadThumb(openConnection.getInputStream(), 100);

		System.out.println(result.toString());

		assertTrue(result != null);
	}

}