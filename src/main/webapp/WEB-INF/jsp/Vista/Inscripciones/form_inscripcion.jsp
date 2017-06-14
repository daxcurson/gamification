<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="${pageContext.request.contextPath}/js/moment.js"></script>
<script type="text/javascript">
<%@include file="/WEB-INF/jsp/Vista/Inscripciones/buscar_ofertas.js" %>
</script>

<fieldset>
<div class="form-group">
<label for="Curso">Curso a inscribir</label>
<select id="Curso" class="form-control" name="curso" onchange="javascript:buscar_ofertas()">
<c:forEach items="${cursos}" var="curso">
<option value="<c:out value="${curso.id}"/>"><c:out value="${curso.nombre}"/></option>
</c:forEach>
</select>
</div>
<div class="form-group">
<label for="CursoOferta">Ofertas del curso</label>
<form:select id="CursoOferta" class="form-control" path="curso_oferta">
</form:select>
<form:errors path="curso_oferta"/>
</div>
<div class="form-group">
<label for="InscripcionFecha">Fecha de Inscripci&oacute;n</label>
<form:input id="InscripcionFecha" class="form-control" path="fecha_inscripcion"/>
<form:errors path="fecha_inscripcion"/>
</div>
</fieldset>