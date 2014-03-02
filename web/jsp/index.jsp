<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>XML Parser</title>
</head>

<body>
    <FORM action="serv" method="POST">
        <select name="parser">
            <option value="1">SAX</option>
            <option value="2">StAX</option>
            <option value="3">DOM</option>>
        </select>
        <INPUT type="submit" value="GO"> 
    </FORM> 
</body>
</html>