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
<c:forEach items="${evaluacion_tomada.evaluacion.preguntas}" var="pregunta">
<%@include file="/WEB-INF/jsp/Vista/EvaluacionesTomadas/code_magnet.jsp" %>
</c:forEach>