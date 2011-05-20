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

package miage.ecom.auth;

import java.io.Serializable;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class AuthResult implements Serializable {

	private static final long serialVersionUID = 7692988502803106333L;

	public static final AuthResult ERROR = new AuthResult("Erreur d'authentification",
														  AuthResultCode.UNKNOWN,
														  null);

	private final String message;
	private final AuthResultCode authResultCode;
	private final AuthIdentity identity;
	
	public AuthResult(String message, AuthResultCode authResultCode, AuthIdentity identity) {
		this.message = message;
		this.authResultCode = authResultCode;
		this.identity = identity;
	}

	public AuthResultCode getAuthResultCode() {
		return authResultCode;
	}

	public AuthIdentity getIdentity() {
		return identity;
	}

	public String getMessage() {
		return message;
	}

	public boolean hasIdentity() {
		return getIdentity() != null;
	}

	public boolean isValid() {
		return authResultCode.equals(AuthResultCode.SUCCESS);
	}
}
