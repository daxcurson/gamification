<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<script src="http://cdn.jsdelivr.net/sortable/latest/Sortable.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.fn.sortable.js"></script>

<script type="text/javascript">
$.fn.randomize = function(selector)
{
	var $elems = selector ? $(this).find(selector) : $(this).children();
	for (var i = $elems.length; i >= 0; i--) {
		$(this).append($elems[Math.random() * i | 0]);
	}

	return this;
}
$(document).ready(function()
{
	$('#pregunta-${pregunta.id}').sortable({
		animation: 150,
		handle:".glyphicon-move",
		group: "localStorage-example",
		store: {
			/**
			 * Get the order of elements. Called once during initialization.
			 * @param   {Sortable}  sortable
			 * @returns {Array}
			 */
			get: function (sortable) {
				var order = localStorage.getItem(sortable.options.group.name);
				return order ? order.split('|') : [];
			},

			/**
			 * Save the order of elements. Called onEnd (when the item is dropped).
			 * @param {Sortable}  sortable
			 */
			set: function (sortable) {
				var order = sortable.toArray();
				localStorage.setItem(sortable.options.group.name, order.join('|'));
			}
		}
	});
	$("#pregunta-${pregunta.id}").randomize();
});
</script>

<c:out value="${pregunta.texto_pregunta}"/>
<c:set var="fila" value="1"/>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<ul id="pregunta-${pregunta.id}" class="list-group">
<c:forTokens items="${pregunta.texto_ordenar}" delims="${newLineChar}" var="linea">
<li class="list-group-item" data-id="${fila}"><span class="glyphicon glyphicon-move" aria-hidden="true"></span><c:out value="${linea}"/></li>
<c:set var="fila" value="${fila+1}"/>
</c:forTokens>
</ul>