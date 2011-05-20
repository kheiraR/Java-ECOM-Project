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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import miage.utils.Image;
import miage.utils.ImageType;
import org.apache.commons.codec.binary.Base64;
import us.monoid.web.Resty;
import us.monoid.web.mime.MultipartContent;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class ImgurImageManager {

	private static final String DEVELOPER_KEY = "1b5eaf6b47f18348c6262a035e20bd21";
	
	private static final String API_ENDPOINT = "http://api.imgur.com/2/";

	
	/**
	 * Prend un stream d'image, upload l'image et renvoi l'url résultant.
	 * Retourne null si l'image n'a pas été envoyée
	 *
	 * @param fileInput
	 * @return
	 * @throws Exception
	 */
	public URL uploadImage(InputStream is) throws Exception {
		
		MultipartContent form = Resty.form(
				Resty.data("key", DEVELOPER_KEY),
				Resty.data("image", getBase64String(is)),
				Resty.data("type", "base64")
		);

		Resty r = new Resty();
		
		String imageURL = r.xml(API_ENDPOINT + "upload.xml",form).get("//original", String.class);


		return new URL(imageURL);
	}

	/**
	 * Similaire à upload image mais resize l'image en un carré avant.
	 * Préserve le ratio de l'image.
	 *
	 * @param file
	 * @param size la taille d'un coté de l'image
	 * @return
	 * @throws Exception
	 */
	public URL uploadThumb(InputStream is, int size) throws Exception {

		Image resizedToSquare = new Image(is, ImageType.UNKNOWN).getResizedToSquare(size, 0);

		return this.uploadImage(resizedToSquare.getImageInputStream());
	}

    
    private String getBase64String(InputStream is) throws IOException {
		return Base64.encodeBase64String(inputStreamToByteArray(is));
	}


	private byte[] inputStreamToByteArray(InputStream inStream) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] buffer = new byte[8192];
		int bytesRead;
		while ((bytesRead = inStream.read(buffer)) > 0) {
			baos.write(buffer, 0, bytesRead);
		}
		
		return baos.toByteArray();
	}
}
