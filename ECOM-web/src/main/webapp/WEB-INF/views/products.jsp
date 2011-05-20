<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>

<z:layout pageTitle="A simple page" pageId="product_details" columnNumber="one">

<div id="content">
  <h1>${product.name}</h1>
    <div id="product-images">
        <div id="main-image">
		  <img alt="${product.description}" src="
			<c:choose>
			  <c:when test="${product.image == null}">http://placehold.it/200x250</c:when>
			  <c:otherwise>${product.image}</c:otherwise>
			</c:choose>" />

        </div>
    </div>

    <div id="product-description">
	  <p>${product.description}</p>
        <%-- <dl id="product-properties" class="table-display"> --%>
        <%--     <dt>Fabric</dt> --%>
        <%--     <dd>100% Vellum</dd> --%>
        <%--     <dt>Model</dt> --%>
        <%--     <dd>TL9002</dd> --%>
        <%--     <dt>Sleeve</dt> --%>
        <%--     <dd>short</dd> --%>
        <%--     <dt>Brand</dt> --%>
        <%--     <dd>Conditioned</dd> --%>
        <%--     <dt>fit</dt> --%>
        <%--     <dd>loose</dd> --%>
        <%--     <dt>Type</dt> --%>
        <%--     <dd>Ringer T</dd> --%>
        <%--     <dt>Manufacturer</dt> --%>
        <%--     <dd>Jerseys</dd> --%>
        <%--     <dt>Gender</dt> --%>
        <%--     <dd>Men's</dd> --%>
        <%-- </dl> --%>
    </div>

    <div id="cart-form">
	  <form accept-charset="UTF-8" action="orders/populate" method="post">
            <p class="prices">
                Prix<br />
				<span class="price selling">${product.price}&nbsp;&euro;</span>
            </p>
			<input class="title" id="variants_${product.idProduct}" name="variants[${product.idProduct}]" size="3" type="text" value="1" />
            &nbsp;
            <button type='submit' class='large primary'>
                <img alt="Add-to-cart" src="resources/images/add-to-cart.png" />Ajouter au panier
            </button>
        </form>
    </div>

    <div id="taxon-crumbs">
        <h3>Look for similar items</h3>
        <div>
            <ul id='similar_items_by_taxon'>
                <li><a href="t/categories/clothing/shirts/t-shirts/">T-Shirts</a></li>
                <li><a href="t/brands/ruby-on-rails/">Ruby on Rails</a></li>
            </ul>
        </div>
    </div>
</div>

</z:layout>
