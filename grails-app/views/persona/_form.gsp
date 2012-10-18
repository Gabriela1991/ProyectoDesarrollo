<%@ page import="grailsapplication1.Persona" %>


<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="persona.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${personaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'apellido', 'error')} ">
	<label for="apellido">
		<g:message code="persona.apellido.label" default="Apellido" />
		
	</label>
	<g:textField name="apellido" value="${personaInstance?.apellido}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'clave', 'error')} ">
	<label for="clave">
		<g:message code="persona.clave.label" default="Clave" />
		
	</label>
	<g:passwordField name="clave" value="${personaInstance?.clave}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'clavedropbox', 'error')} ">
	<label for="clavedropbox">
		<g:message code="persona.clavedropbox.label" default="Clave de dropbox" />
		
	</label>
	<g:passwordField name="clavedropbox" value="${personaInstance?.clavedropbox}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'correo', 'error')} ">
	<label for="correo">
		<g:message code="persona.correo.label" default="Correo" />
		
	</label>
	<g:textField name="correo" value="${personaInstance?.correo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'cuenta', 'error')} ">
	<label for="cuenta">
		<g:message code="persona.cuenta.label" default="Cuenta" />
		
	</label>
	<g:textField name="cuenta" value="${personaInstance?.cuenta}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'cuentadropbox', 'error')} ">
	<label for="cuentadropbox">
		<g:message code="persona.cuentadropbox.label" default="Cuenta de dropbox" />
		
	</label>
	<g:textField name="cuentadropbox" value="${personaInstance?.cuentadropbox}"/>
</div>

<br></br>

<div class="rightcol" align="center">
      <g:submitButton class="button" name="submitButton" value="Crear cuenta o iniciar sesion no se cual es xD" />
</div>

