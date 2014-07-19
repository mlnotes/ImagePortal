<%-- 
    Document   : recent
    Created on : 2014-7-19, 9:46:42
    Author     : Zhu Hanfeng <me@mlnotes.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:set var="page">recent</s:set>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recent</title>
        <link rel="stylesheet" href="res/css/style.css" >
        <script type="text/javascript" src="res/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="res/js/mlnotes.js"></script>
    </head>
    <body>
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
