<%-- 
    Document   : keyword
    Created on : 2014-7-19, 9:58:50
    Author     : Zhu Hanfeng <me@mlnotes.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Keyword</title>
        <link rel="stylesheet" href="res/css/style.css" >
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        <h1>Hello World!</h1>
        <h3><s:property value="keyword" /></h3>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
        
    </body>
</html>
