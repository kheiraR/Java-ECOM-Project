<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<app:layout pageTitle="ECOM - Login" pageId="login" columnNumber="one">
	
    <div id="content"> 

		<div id="existing-customer"> 
			<h2>Log in as existing customer</h2> 

			<form action="j_spring_security_check" method="post">
				<c:if test="${param.error == 'true'}">
					<div class="flash errors">
						<p>Incorrect login or password</p>
					</div>
				</c:if>
				<div id="password-credentials"> 
					<p> 
						<label for="login">Login</label>
						<br /> 
						<input class="title" id="login" name="j_username" size="30" type="text" /> 
					</p> 
					<p>
						<label for="password">Password</label>
						<br /> 
						<input class="title" id="password" name="j_password" size="30" type="password" /> 
					</p> 
				</div> 
				<p> 
					<input name="remember_me" type="hidden" value="0" /><input id="remember_me" name="remember_me" type="checkbox" value="1" /> 
					<label for="remember_me">Remember me</label> 
				</p> 

				<p><input class="button primary" name="commit" type="submit" value="Log In" /></p> 
			</form> 
			or <a href="signup">Create a new account</a> | <a href="users/password/new">Forgot Password?</a> 
		</div> 

    </div>
	
</app:layout>