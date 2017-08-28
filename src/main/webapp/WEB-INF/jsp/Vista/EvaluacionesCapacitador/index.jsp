<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${pageContext.request.contextPath}/css/bootstrap-table.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/bootstrap-table.js"></script>
<script type="text/javascript">
$(document).ready(function()
{
	cargar_tabla();
});
function cargar_tabla()
{
	var data = [
	<c:forEach items="${evaluaciones_corregir}" var="evaluacion" varStatus="lista">
	{
		'nombre_estudiante': '<c:out value="${evaluacion.inscripcion.estudiante.nombre}"/>',
		'codigo_curso': '<c:out value="${evaluacion.curso_oferta.curso.codigo_curso}"/>',
		'correcciones': [
		<c:forEach items="${evaluacion.correcciones}" var="correccion" varStatus="correcciones_lista">
		{
			'fecha_correccion': '<c:out value="${correccion.fecha}"/>',
			'capacitador': '<c:out value="${correccion.capacitador.nombre}"/>',
			'acciones': '<a href="${pageContext.request.contextPath}/evaluaciones_capacitador/editar_correccion/${correccion.id}">Editar correcci&oacute;n</a>'
		}
		<c:if test="${fn:length(evaluacion.correcciones) >correcciones_lista.index}">
		,
		</c:if>
		</c:forEach>
		],
		'acciones': '<a href="${pageContext.request.contextPath}/evaluaciones_capacitador/nueva_correccion/${evaluacion.id}">Nueva correcci&oacute;n</a>'
	}
	<c:if test="${fn:length(evaluaciones_corregir) >lista.index}">
	,
	</c:if>
	</c:forEach>
	]


	var $table = $('#tabla_examenes');
	$(function() {

		$table.bootstrapTable({
			columns: [{
				field: 'nombre_estudiante',
				title: 'Nombre Estudiante'
			}, {
				field: 'codigo_curso',
				title: 'C&oacute;digo Curso'
			}, {
				field: 'fecha',
				title: 'Fecha'
			}, {
				field: 'acciones',
				title: 'Acciones'
			}],
			data: data,
			detailView: true,
			onExpandRow: function(index, row, $detail) {
				console.log(row)
				$detail.html('<table></table>').find('table').bootstrapTable({
					columns: [{
						field: 'fecha_correccion',
						title: 'Fecha de Correcci&oacute;n'
					}, {
						field: 'capacitador',
						title: 'Capacitador'
					}, {
						field: 'acciones',
						title: 'Acciones'
					}],
					data: row.correcciones
				});
			}
		});
	});
}
</script>

<h2>Ex&aacute;menes para corregir</h2>

<table id="tabla_examenes">
</table>