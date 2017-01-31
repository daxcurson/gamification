<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<script src="${pageContext.request.contextPath}/js/Sortable.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.fn.sortable.js"></script>

<script type="text/javascript">
<%@include file="/WEB-INF/jsp/Vista/EvaluacionesTomadas/code_magnet.js" %>
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