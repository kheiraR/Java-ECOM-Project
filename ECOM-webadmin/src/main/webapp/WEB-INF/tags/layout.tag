<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Admin Layout Page Tag" pageEncoding="UTF-8"%>
<%@taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="js" fragment="true" %>
<%@attribute name="css" fragment="true" %>
<%@attribute name="title" required="false"%>
<!DOCTYPE html>
<html>
    <head>
        <title><c:if test="${!empty title}">${title} – </c:if>ECOM Admin Panel</title>
		<%--
			On place les fichiers CSS dans le head
			 (c.f : http://developer.yahoo.com/performance/rules.html#css_top)
		--%>
		<app:headlink resource="css/reset.css"></app:headlink>
		<app:headlink resource="css/content.css"></app:headlink>
		<app:headlink resource="css/ext-all.css"></app:headlink>
		
		<%-- les css restant inclus par la vue--%>
		<jsp:invoke fragment="css"/>
    </head>
    <body>
		<div id="loadingOverlay" class="overlay"><div class="txt">Chargement...</div></div>
		<div class="page liquid">
			<div id="hd">
				<jsp:include page="/WEB-INF/layout/header.jsp"/>
			</div>
			<div id="bd">
				<div class="main">
					<jsp:doBody/>
				</div>
			</div>
			<div id="ft">
				<jsp:include page="/WEB-INF/layout/footer.jsp"/>
			</div
		</div>
		<%--
			On place le javascript en fin de page afin de ne pas bloquer le chargement
			(c.f http://developer.yahoo.com/performance/rules.html#js_bottom)
		--%>
		<app:headlink type="js" resource="js/extjs/ext-all.js"/>
		<script type="text/javascript">
			Ext.onReady(function() {
				document.getElementById('loadingOverlay').style.display = 'none';
			})
		</script>
		<%-- les scripts js spécifiques inclus par la vue--%>
		<jsp:invoke fragment="js"/>
	</body>
</html>
    