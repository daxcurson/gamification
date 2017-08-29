<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%
// La explicacion de texto tiene la pregunta y el texto adicional abajo.
%>
<p>
<c:out value="${pregunta.texto_pregunta}"/><br/>
</p>

<p>
<c:out value="${pregunta.texto_ordenar}"/><br/>
</p>
