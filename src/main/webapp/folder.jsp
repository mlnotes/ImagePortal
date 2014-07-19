<%-- 
    Document   : folder
    Created on : 2014-7-19, 9:56:19
    Author     : Zhu Hanfeng <me@mlnotes.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Folder</title>
        <link rel="stylesheet" href="res/css/style.css" >
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        
        <h1>Hello World!</h1>
        <h3><s:property value="folder" /></h3>
        
        <s:if test="%{folder == 'scenery'}">
            <h3>This is Scenery</h3>
        </s:if>
        <s:elseif test="%{folder == 'portrait'}">
            <h3> This is Portait</h3>
        </s:elseif>
        <s:else>
            <h3> Not Supported</h3>
        </s:else>
        
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
