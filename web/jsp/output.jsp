<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>XML Parser</title>
    </head>
    <body>
        <jsp:useBean id="al" class="com.klimkov.webparser.unit.AirplaneList" scope="session" />
        <c:set var="items" value="${pageContext.request.getAttribute('planelist').getAirplaneList()}"  /> 
            <table border="1">
                <c:forEach var="plane" items="${items}">
                    <tr>
                        <td>ID: </td><td> <c:out value="${plane.id}" /></td>
                        <td>Capacity: </td><td> <c:out value="${plane.capacity}" /></td>
                        <td>Carrying: </td><td> <c:out value="${plane.carrying}" /></td>
                        <td>Fuel: </td><td> <c:out value="${plane.fuel}" /></td>
                        <td>Distance: </td><td> <c:out value="${plane.distance}" /></td>
                        <c:choose>
                            <c:when test="${plane.getClass().getSimpleName() == 'Airliner'}">
                                <td>Seats number: </td><td> <c:out value="${plane.seatsNumber}" /></td>
                            </c:when>
                            <c:when test="${plane.getClass().getSimpleName() == 'Airpost'}">
                                <td>Priority level: </td><td> <c:out value="${plane.priority}" /></td>
                            </c:when>
                        </c:choose>
                        
                    </tr>
                </c:forEach> 
            </table>
    </body>
</html>
