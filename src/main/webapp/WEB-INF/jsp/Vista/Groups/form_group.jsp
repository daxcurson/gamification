<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fieldset>
<form:errors path=""/>
<div class="form-group">
<label for="GroupName">Nombre</label>
<form:input id="GroupName" class="form-control" path="group_name"/>
<form:errors path="group_name"/>
</div>
<div class="form-group">
<label for="VistaPrincipal">Vista Principal</label>
<form:input id="VistaPrincipal" class="form-control" path="vista_principal"/>
<form:errors path="vista_principal"/>
</div>
</fieldset>
