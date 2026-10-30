
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Product Inventory</title>
</head>
<body>

    <h2>Product Inventory Data Entry</h2>

    <form action="InventoryServlet" method="post">

        Item Code:
        <input type="number" name="item_code" required>
        <br><br>

        Item Name:
        <input type="text" name="item_name" required>
        <br><br>

        Quantity:
        <input type="number" name="quantity" required>
        <br><br>

        Price:
        <input type="number" step="0.01" name="price" required>
        <br><br>

        <input type="submit" value="Add Item">

    </form>

    <hr>

    <h2>Available Inventory Items</h2>

    <table border="1" cellpadding="10">
        <tr>
            <th>Item Code</th>
            <th>Item Name</th>
            <th>Quantity</th>
            <th>Price</th>
        </tr>

        <c:forEach var="item" items="${items}">
            <tr>
                <td>${item[0]}</td>
                <td>${item[1]}</td>
                <td>${item[2]}</td>
                <td>${item[3]}</td>
            </tr>
        </c:forEach>

    </table>

</body>
</html>
