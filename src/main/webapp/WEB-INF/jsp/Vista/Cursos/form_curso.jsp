<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<fieldset>
<div class="form-group">
<label for="CursoCodigoCurso">C&oacute;digo de Curso</label>
<form:input id="CursoCodigoCurso" class="form-control" path="codigo_curso"/>
<form:errors path="codigo_curso"/>
</div>
<div class="form-group">
<label for="CursoNombre">Nombre</label>
<form:input id="CursoNombre" class="form-control" path="nombre"/>
<form:errors path="nombre"/>
</div>
<div class="form-group">
<label for="CursoCapacitador">capacitador</label>
<form:select path="capacitador" id="CursoCapacitador" class="form-control">
<form:options items="${capacitadores}" itemValue="id" itemLabel="nombre" />
</form:select>
<form:errors path="capacitador"/>
</div>
<div class="form-group">
<label for="CursoPuntos">Puntos que otorga</label>
<form:input id="CursoPuntos" class="form-control" path="puntos"/>
<form:errors path="puntos"/>
</div>
</fieldset>