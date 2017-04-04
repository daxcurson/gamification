<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%
// Aqui unimos definicion con concepto. Vamos a poner la palabra, dos enters, definicion.
// Palabra, dos enters, definicion. 
%>

<c:out value="${pregunta.texto_pregunta}"/><br/>
<% 
pageContext.setAttribute("newLineChar", "\n"); 
// Obtengo el texto_ordenar, lo trabajo y despues devuelvo los 2 objetos, con
// las definiciones y los conceptos.
gamification.model.Pregunta p=(gamification.model.Pregunta)pageContext.getAttribute("pregunta");
String texto=p.getTexto_ordenar();
java.util.StringTokenizer tokenizer=new java.util.StringTokenizer(texto,"\r\n\r\n");
int i=0;
java.util.List<String> definiciones=new java.util.LinkedList();
java.util.List<String> textos=new java.util.LinkedList();
while(tokenizer.hasMoreElements())
{
	if(i%2==0)
		definiciones.add(tokenizer.nextToken());
	else
		textos.add(tokenizer.nextToken());
	i++;
}
pageContext.setAttribute("definiciones",definiciones);
pageContext.setAttribute("textos",textos);
%>
<table>
<c:forEach items="${textos}" var="texto">
<tr><td>
<form:select path="respuestas[${preguntaNro.index}].valor_respuesta">
<form:options items="${definiciones}"/>
</form:select>
</td>
<td>
<c:out value="${texto}"/>
</td>
</tr>
</c:forEach>
</table>
<form:input path="respuestas[${preguntaNro.index}].pregunta" type="hidden" value="${pregunta.id}"/>