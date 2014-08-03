<%-- 
    Document   : about
    Created on : 2014-7-19, 9:27:52
    Author     : Zhu Hanfeng <me@mlnotes.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/WEB-INF/tlds/router.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About</title>
        <link rel="stylesheet" href="res/css/style.css" >
        <script type="text/javascript" src="res/js/lib/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="res/js/mlnotes.js"></script>
    </head>
    <body>
        <s:set name="page">about</s:set>
        <%@include file="/WEB-INF/jspf/header.jspf" %> 
        <div class="content">
            <div class="main">
                <div class="main-left"></div>
                <%@include file="/WEB-INF/jspf/menu.jspf" %>
                <div class="clear"></div>
            </div>    
        </div>
        
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
