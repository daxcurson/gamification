<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<fieldset>
<div class="form-group">
<form:label path="evaluacion.descripcion" id="LabelUserGroup">Descripci&oacute;n de la Evaluaci&oacute;n</form:label>
<form:input id="EvaluacionDescripcion" class="form-control" path="evaluacion.descripcion"/>
<form:errors path="evaluacion.descripcion"/>
</div>
</fieldset>
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
</c:forEach>