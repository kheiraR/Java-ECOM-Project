<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<app:layout pageTitle="ECOM - Signup" pageId="signup" columnNumber="one">

    <div id="content"> 

	<div id="new-customer"> 
	    <h2>New customer</h2> 

	    <c:if test="${success != null}">
		<div class="success-message">
		    <p>${success}</p>
		</div>
	    </c:if>

	    <c:if test="${errors != null}">
		<div class="error-message">
		    <ul>
			<c:forEach items="${errors}" var="error">
			    <li>${error}</li>
			</c:forEach>
		    </ul>
		</div>
	    </c:if>

	    <form class="format" method="POST" action="signup">
		<table>
		    <tr>
			<td>Firstname *</td>
			<td><input type="text" name="firstname" value='${customer.get("firstname")}' /></td>
		    </tr>
		    <tr>
			<td>Lastname *</td>
			<td><input type="text" name="lastname" value='${customer.get("lastname")}' /></td>
		    </tr>
		    <tr>
			<td>Login *</td>
			<td><input type="text" name="login" value='${customer.get("login")}' /></td>
		    </tr>
		    <tr>
			<td>Password *</td>
			<td><input type="password" name="password" /></td>
		    </tr>
		    <tr>
			<td>Password Confirmation *</td>
			<td><input type="password" name="password_confirmation" /></td>
		    </tr>
		    <tr>
			<td>Address *</td>
			<td><input type="text" name="address" value='${customer.get("address")}' /></td>
		    </tr>
		    <tr>
			<td>IBAN *</td>
			<td><input type="text" name="iban" size="34" value='${customer.get("iban")}' /></td>
		    </tr>
		    <tr>
			<td>&nbsp;</td>
			<td><input class="small-button" type="submit" value="Signup" /></td>
		    </tr>

		</table>

	    </form>

	    or <a href="login">Log In as Existing Customer</a> 
	</div> 
    </div>

</app:layout>
