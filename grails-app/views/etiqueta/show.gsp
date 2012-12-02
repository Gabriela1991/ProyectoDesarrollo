
<%@ page import="grailsapplication1.Etiqueta" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">		

	</head>
	<body>
		
		<div id="show-etiqueta" class="content scaffold-show" role="main">		
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list etiqueta">
			
				<g:if test="${etiquetaInstance?.nota}">
				<li class="fieldcontain">
					<span id="nota-label" class="property-label"><g:message code="etiqueta.nota.label" default="Nota" /></span>
					
						<span class="property-value" aria-labelledby="nota-label"><g:link controller="nota" action="show" id="${etiquetaInstance?.nota?.id}">${etiquetaInstance?.nota?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${etiquetaInstance?.texto}">
				<li class="fieldcontain">
					<span id="texto-label" class="property-label"><g:message code="etiqueta.texto.label" default="Texto" /></span>
					
						<span class="property-value" aria-labelledby="texto-label"><g:fieldValue bean="${etiquetaInstance}" field="texto"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${etiquetaInstance?.id}" />
					<g:link class="edit" action="edit" id="${etiquetaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
