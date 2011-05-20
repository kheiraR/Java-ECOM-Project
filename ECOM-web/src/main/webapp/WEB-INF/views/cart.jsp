<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>

<z:layout pageTitle="Your cart" pageId="cart" columnNumber="one">
    <div id="content">
	<h1>Shopping Cart</h1>
	<c:choose>

	    <c:when test="${products.size() == 0}">
		<p>Your cart is empty</p>
		<p><a href="" class="button continue">Continue shopping &rarr;</a></p>
	    </c:when>

	    <c:otherwise>
		<table class="index">
		    <tbody id='line-items'>
			<tr>
			    <th>Product</th>
			    <th class="price">Price</th>
			    <th class="qty">Quantity</th>
			    <th class="total"><span>Total</span></th>
			    <th class="total">&nbsp;</th>
			</tr>

		    <c:forEach var="p" items="${products}">
			<tr>
			    <td width="300">${p.product.name}</td>
			    <td class="price">${p.product.price}&nbsp;&euro;</td>
			    <td class="qty">${p.quantity}</td>
			    <td class="total"><span>${p.product.price * p.quantity} €</span></td>
			    <td><form action="cart/remove" method="POST"><input type="submit" class="small-button" value="Remove this item"><input type="hidden" name="idProduct" value="${p.product.idProduct}" /></form></a></td>
			</tr>
		    </c:forEach>

		    </tbody>
		</table>

		<div id="subtotal">
		    <h3>Total: ${cartTotalValue}&nbsp;&euro;</h3>
		    <hr />
		    <div class="links">
			<form action="cart/checkout" method="POST">
			    <a href="" style="float:left" class="small-button checkout primary">Continue Shopping</a>
			    <input style="float:left" type="submit" class="small-button checkout primary" value="Proceed to checkout" />
			</form>
			
		    </div>
		</div>
	    </c:otherwise>

	</c:choose>
    </div>
</z:layout>
