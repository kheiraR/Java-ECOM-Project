<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="app"%>
<app:layout>
	<jsp:attribute name="js">
		<app:headlink type="js" resource="js/uploadWindow.js"/>
		<app:headlink type="js" resource="js/app.js"/>
		<script type="text/javascript">
			(function() {
				Ext.onReady(function() {
					ecomAdmin('${pageContext.request.contextPath}/');
				})
			})();
		</script>
	</jsp:attribute>
	<jsp:body>
		
	</jsp:body>
</app:layout>