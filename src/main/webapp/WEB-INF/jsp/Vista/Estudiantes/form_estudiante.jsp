<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fieldset>
<div class="form-group">
<label for="PersonaNombre">Nombre</label>
<form:input id="PersonaNombre" class="form-control" path="nombre"/>
<form:errors path="nombre"/>
</div>
<div class="form-group">
<label for="PersonaUsuarioSistema">Es usuario del sistema?</label>
<form:checkbox path="usuario_sistema" id="PersonaUsuarioSistema" class="form-control" />
</div>
<div class="form-group">
<label for="UserUsername">Login</label>
<form:input id="UserUsername" class="form-control" path="user.username"/>
<form:errors path="user.username"/>
</div>
<div class="form-group">
<form:label path="user.group" id="LabelUserGroup">Grupo</form:label>
<form:select path="user.group" id="UserGroup" class="form-control">
<form:options items="${groups}" itemValue="id" itemLabel="groupName" />
</form:select>
</div>
<form:errors path=""/>
<div class="form-group">
<form:label path="user.password">Clave</form:label>
<form:input type="password" id="UserPassword" class="form-control" path="user.password"/>
</div>
<div class="form-group">
<form:label path="user.confirm_password">Confirmar Clave</form:label>
<form:input type="password" id="UserConfirmPassword" class="form-control" path="user.confirm_password"/>
</div>
</fieldset>