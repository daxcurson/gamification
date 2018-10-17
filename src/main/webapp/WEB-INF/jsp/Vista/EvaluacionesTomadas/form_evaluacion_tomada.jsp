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
<c:if test="${pregunta.tipo_pregunta.codigo=='CMAGNET'}">
<%@include file="/WEB-INF/jsp/Vista/EvaluacionesTomadas/code_magnet.jsp" %>
</c:if>
<c:if test="${pregunta.tipo_pregunta.codigo=='MCHOICE'}">
<%@include file="/WEB-INF/jsp/Vista/EvaluacionesTomadas/multiple_choice.jsp" %>
</c:if>
<c:if test="${pregunta.tipo_pregunta.codigo=='COMPLETAR'}">
<%@include file="/WEB-INF/jsp/Vista/EvaluacionesTomadas/completar_parrafo.jsp" %>
</c:if>
<c:if test="${pregunta.tipo_pregunta.codigo=='UNIRDEF'}">
<%@include file="/WEB-INF/jsp/Vista/EvaluacionesTomadas/unir_definicion.jsp" %>
</c:if>
<c:if test="${pregunta.tipo_pregunta.codigo=='YOUTUBE'}">
<%@include file="/WEB-INF/jsp/Vista/EvaluacionesTomadas/youtube.jsp" %>
</c:if>
<c:if test="${pregunta.tipo_pregunta.codigo=='BLOCKLY'}">
<%@include file="/WEB-INF/jsp/Vista/EvaluacionesTomadas/blockly.jsp" %>
</c:if>
<c:if test="${pregunta.tipo_pregunta.codigo=='TEXTO'}">
<%@include file="/WEB-INF/jsp/Vista/EvaluacionesTomadas/texto.jsp" %>
</c:if>
</c:forEach>