<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<app:layout pageTitle="ECOM - Search" columnNumber="two">

    <div id="sidebar">
        <div id="taxonomies" class="sidebar-item">
            <ul class="navigation-list">
                <li><a href="" class="root">Shop by Category</a></li>
		<c:forEach items="${categories}" var="cat">
		    <li><a href="categories/${cat.idCategory}">${cat.name}</a></li>
		</c:forEach>
            </ul>
            <ul class="navigation-list">
                <li><a href="stores" class="root">Shop by store</a></li>
            </ul>
        </div>
    </div>

    <div id="content">
	<h2>Search results for "${keywords}"</h2>
        <ul class="product-listing">
            <c:forEach var="p" items="${products}">
                <li id="product_${p.idProduct}">
				<a href="products/${p.idProduct}"><img alt="blabla" src="
				  <c:choose>
				  <c:when test="${p.image == null}">http://placehold.it/350x250</c:when>
				  <c:otherwise>${p.image}</c:otherwise>
				  </c:choose>
				  " width="125px" /></a>
                    <a href="products/${p.idProduct}" class="info">${p.name}<span class='price selling'>${p.price}&nbsp;&euro;</span></a>
                </li>
            </c:forEach>
        </ul>
    </div>

</app:layout>
