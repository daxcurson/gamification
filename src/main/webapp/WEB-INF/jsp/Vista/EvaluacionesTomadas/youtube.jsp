<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%
// En el video, texto_ordenar es la url
%>
<c:out value="${pregunta.texto_pregunta}"/><br/>
<iframe width="560" height="315" 
src="${pregunta.texto_ordenar}">
</iframe>