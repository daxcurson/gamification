<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<fieldset>
<div class="form-group">
<label for="CursoOfertaCurso">C&oacute;digo de Curso Ofrecido</label>
<form:select path="curso" id="CursoOfertaCurso" class="form-control">
<form:options items="${cursos_lista}" itemValue="id" itemLabel="codigo_curso" />
</form:select>
<form:errors path="curso"/>
</div>
<div class="form-group">
<label for="CursoOfertaFechaComienzo">Fecha de comienzo de la oferta</label>
<form:input id="CursoOfertaFechaComienzo" class="form-control" path="fecha_comienzo"/>
<form:errors path="fecha_comienzo"/>
</div>
<div class="form-group">
<label for="CursoOfertaFechaFin">Fecha de fin de la oferta</label>
<form:input id="CursoOfertaFechaFin" class="form-control" path="fecha_fin"/>
<form:errors path="fecha_fin"/>
</div>
</fieldset>