<%@ page import="grailsapplication1.Persona" %>



<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="persona.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="15" required="" value="${personaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'apellido', 'error')} required">
	<label for="apellido">
		<g:message code="persona.apellido.label" default="Apellido" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="apellido" maxlength="15" required="" value="${personaInstance?.apellido}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'correo', 'error')} required">
	<label for="correo">
		<g:message code="persona.correo.label" default="Correo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="correo" maxlength="45" required="" value="${personaInstance?.correo}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'clave', 'error')} required">
	<label for="clave">
		<g:message code="persona.clave.label" default="Clave" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="clave" maxlength="15" required="" value="${personaInstance?.clave}"/>
</div>



