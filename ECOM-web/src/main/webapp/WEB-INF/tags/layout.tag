<%@ tag body-content="scriptless" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="pageTitle" required="true" type="java.lang.String" %>
<%@ attribute name="pageId" required="false" type="java.lang.String" %>
<%@ attribute name="columnNumber" required="true" type="java.lang.String" %>
<%@ tag import="miage.ecom.entity.Customer" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${pageTitle}</title>
  <base href="<%= request.getContextPath()%>/" />
        <link href="<%= request.getContextPath()%>/resources/css/screen.css" media="screen" rel="stylesheet" type="text/css" />
    </head>
    <body class="${columnNumber}-col" id="${pageId}">
        <div id="header" class="container_bg">
            <div class="container">
                <div id="login-bar">
                    <ul id="nav-bar">
                        <li>
                            <form accept-charset="UTF-8" action="search" method="GET">
<!--                                <select id="taxon" name="taxon"><option value="">All departments</option>
                                    <option value="1000">Categories</option>
                                    <option value="20000">Brands</option></select>-->
                                <input id="keywords" name="keywords" type="text" />
                                <input type="submit" value="Search" />
                            </form>
            </li>

            <sec:authorize access="not isAuthenticated()">
              <li><a href="<%= request.getContextPath()%>/login">Log In</a></li>
            </sec:authorize>
      
            <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
              <li><sec:authentication property="principal.username"/> (<a href="<%= request.getContextPath()%>/logout">Log Out</a>)</li>
            </sec:authorize>
      
                        <li><a href="<%= request.getContextPath()%>">Home</a></li>
                        <li class="cart-indicator">
                    <c:choose>
                        <c:when test="${nbProducts > 0}">
                      <a href="<%= request.getContextPath()%>/cart" class="full">Cart: (${nbProducts}) ${cartTotalValue}&nbsp;&euro;</a>
                        </c:when>
                        <c:otherwise>
                      <a href="<%= request.getContextPath()%>/cart">Cart: (Empty)</a>
                        </c:otherwise>
                    </c:choose>
                    </li>
                    </ul>
                </div>
                <a id="logo" href="<%= request.getContextPath()%>"><img alt="iCom" src="<%= request.getContextPath()%>/resources/images/logo.png" /></a>
            </div>
        </div>

        <div id="wrapper" class="container">
            <jsp:doBody/>
        </div>

        <div id="footer">
            <div id="contact-us">
                <h3>Contact Us</h3>
                <p>
                    Maria Rabarison<br />
                    Lou Ferrand<br />
                    Michaël Schwartz<br />
                    Grégoire Yakan<br />
                    <a href="#">email@example.com</a>
                </p>
            </div>
            <div id="social">
                <h3>Social</h3>
                <p>
                    <a href="http://facebook.com/" id="facebook"><span>Facebook</span></a>
                    <a href="http://feeds2.feedburner.com/spreehq" id="rss"><span>RSS</span></a>
                    <a href="http://twitter.com/" id="twitter"><span>Twitter</span></a>
                </p>
            </div>
            <div id="menu">
                <h3>Menu</h3>
                <p>
                    <a href="<%= request.getContextPath()%>/about">About us</a><br />
                    <a href="<%= request.getContextPath()%>/privacy">Privacy policy</a>
                </p>
            </div>
            <div id="copyright" class="clear">© Copyright 2011 Dagofly. All rights reserved</div>
        </div>
    </body>
</html>
