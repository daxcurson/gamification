<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>Mis inscripciones a cursos</h1>

<p>
<a href="${pageContext.request.contextPath}/inscripciones/add">Inscribirse en curso</a>
</p>

<table class="table">
<tr><th></th><th>C&oacute;digo del Curso</th><th>Nombre del Curso</th><th>Fechas en oferta</th><th>Acciones</th></tr>
<tr class="clickable" data-toggle="collapse" id="row1" data-target=".row1">
<td><i class="glyphicon glyphicon-plus"></i></td>
<td>Codigocurso</td><td>nombrecurso</td><td>fechasoferta</td><td><a href="${pageContext.request.contextPath}/evaluaciones_tomadas/add">Tomar Evaluaci&oacute;n</a></td>
</tr>
<tr class="collapse row1">
<td colspan="5">
<table class="table table-bordered">
<tr><td>Evaluacion: Intento1</td><td>Fecha</td><td>Resultado</td></tr>
</table>
</td>
</tr>
</table>