<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%
// La verdad es, no necesito que la descripcion de la evaluacion 
// sea un input.
%>
<p>
${evaluacion.descripcion}
</p>
<c:forEach items="${evaluacion_tomada.evaluacion.preguntas}" var="pregunta" varStatus="preguntaNro">
<div id="pregunta-${pregunta.id}" class="pregunta_examen">
</div>
</c:forEach>