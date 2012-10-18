
<%@ page import="grailsapplication1.Persona" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'persona.label', default: 'Persona')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-persona" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-persona" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list persona">
			
				<g:if test="${personaInstance?.apellido}">
				<li class="fieldcontain">
					<span id="apellido-label" class="property-label"><g:message code="persona.apellido.label" default="Apellido" /></span>
					
						<span class="property-value" aria-labelledby="apellido-label"><g:fieldValue bean="${personaInstance}" field="apellido"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personaInstance?.clave}">
				<li class="fieldcontain">
					<span id="clave-label" class="property-label"><g:message code="persona.clave.label" default="Clave" /></span>
					
						<span class="property-value" aria-labelledby="clave-label"><g:fieldValue bean="${personaInstance}" field="clave"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personaInstance?.clavedropbox}">
				<li class="fieldcontain">
					<span id="clavedropbox-label" class="property-label"><g:message code="persona.clavedropbox.label" default="Clavedropbox" /></span>
					
						<span class="property-value" aria-labelledby="clavedropbox-label"><g:fieldValue bean="${personaInstance}" field="clavedropbox"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personaInstance?.correo}">
				<li class="fieldcontain">
					<span id="correo-label" class="property-label"><g:message code="persona.correo.label" default="Correo" /></span>
					
						<span class="property-value" aria-labelledby="correo-label"><g:fieldValue bean="${personaInstance}" field="correo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personaInstance?.cuenta}">
				<li class="fieldcontain">
					<span id="cuenta-label" class="property-label"><g:message code="persona.cuenta.label" default="Cuenta" /></span>
					
						<span class="property-value" aria-labelledby="cuenta-label"><g:fieldValue bean="${personaInstance}" field="cuenta"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personaInstance?.cuentadropbox}">
				<li class="fieldcontain">
					<span id="cuentadropbox-label" class="property-label"><g:message code="persona.cuentadropbox.label" default="Cuentadropbox" /></span>
					
						<span class="property-value" aria-labelledby="cuentadropbox-label"><g:fieldValue bean="${personaInstance}" field="cuentadropbox"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personaInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="persona.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${personaInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${personaInstance?.id}" />
					<g:link class="edit" action="edit" id="${personaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
