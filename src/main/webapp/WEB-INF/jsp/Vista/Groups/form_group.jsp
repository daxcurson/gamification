<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fieldset>
<form:errors path=""/>
<div class="form-group">
<label for="GroupName">Nombre</label>
<form:input id="GroupName" class="form-control" path="groupName"/>
<form:errors path="groupName"/>
</div>
</fieldset>
