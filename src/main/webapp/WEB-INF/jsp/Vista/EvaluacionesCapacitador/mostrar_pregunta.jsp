<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>Pregunta</h1>

<c:out value="${respuesta.valor_respuesta}"/>

<form:form id="correccionPregunta" commandName="correccion_pregunta" method="post">
<form:input path="comentarios" type="text"/>
<form:errors path="comentarios"/>
<form:select path="nota_pregunta">
<form:options items="${notas}" itemValue="nota_valor" itemLabel="nota_nombre"/>
</form:select>
<form:errors path="nota_pregunta"/>
<input type="submit" name="_eventId_review" value="Corregir" />
</form:form>