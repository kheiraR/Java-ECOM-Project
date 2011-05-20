<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<app:layout pageTitle="ECOM - Stores ${category.name}" columnNumber="two">

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
	<h2>Stores available</h2>
        <ul class="product-listing">
            <c:forEach var="s" items="${stores}">
                <li id="product_${s.idStore}">
		    <a href="products/store/${s.idStore}">
			<img alt="blabla" src="resources/images/store_icon.png" width="125px" /></a>
                    <a href="products/store/${s.idStore}" class="info">${s.name}</a>
                </li>
            </c:forEach>
        </ul>
    </div>

</app:layout>
