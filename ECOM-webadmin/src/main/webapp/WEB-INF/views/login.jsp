<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="app"%>
<app:layout title="">
	<jsp:attribute name="js">
		<app:headlink type="js" resource="js/login.js"/>
		<script type="text/javascript">
			
		</script>
	</jsp:attribute>
	<jsp:body>
		<div id="loginContainer">
			<h1>ECOM Admin Panel</h1>
			<div class="panel" id="loginPanel">
				
			</div>
		</div>
	</jsp:body>
</app:layout>