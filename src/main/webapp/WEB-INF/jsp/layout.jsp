<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Gamification</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script type="text/javascript" src="https://cf-4053.kxcdn.com/conversational-form/0.9.6/conversational-form.min.js"></script>
    
  </head>

  <body>
    <div class="content">
	    <!-- Fixed navbar -->
	    <nav class="navbar navbar-inverse navbar-fixed-top">
	    <tiles:insertAttribute name="menu"/>
	    </nav>

		<tiles:insertAttribute name="flash"/>

	    <div class="container theme-showcase" role="main">
	    <tiles:insertAttribute name="content"/>
	    </div>
    </div>
  </body>
</html>
