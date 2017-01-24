<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<fieldset>
<div class="form-group">
<form:label path="descripcion" id="LabelUserGroup">Descripci&oacute;n de la Evaluaci&oacute;n</form:label>
<form:input id="EvaluacionDescripcion" class="form-control" path="descripcion"/>
<form:errors path="descripcion"/>
</div>
<div class="form-group">
<form:label path="curso_oferta" id="LabelEvaluacionCursoOferta">Curso Ofrecido</form:label>
<form:select path="curso_oferta" id="EvaluacionCursoOferta" class="form-control">
<form:options items="${cursos_ofertas}" itemValue="id" itemLabel="curso.codigo_curso" />
</form:select>
<form:errors path="curso_oferta"/>
</div>
</fieldset>