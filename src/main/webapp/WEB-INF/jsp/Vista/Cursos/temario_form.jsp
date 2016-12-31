<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<fieldset>
<div class="form-group">
<label for="TemaNombre">Nombre</label>
<form:input id="TemaNombre" class="form-control" path="nombre"/>
<form:errors path="nombre"/>
</div>
<div class="form-group">
<label for="TemaNombre">Descripci&oacute;n</label>
<form:textarea cols="80" rows="10" path="descripcion" id="TemaDescripcion" class="form-control" />
<form:errors path="descripcion"/>
</div>
</fieldset>