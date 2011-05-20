<%@tag description="Génère un tag pour inclure une resource statique" pageEncoding="UTF-8"%>
<%@attribute name="resource" required="true"%>
<%@attribute name="type"%>
<% if (type != null && type.equals("js")) {%>
	<script type="text/javascript" language="JavaScript" src="<%=application.getContextPath()%>/resources/${resource}"></script>
<% } else {%>
	<link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/resources/${resource}"/>
<% } %>