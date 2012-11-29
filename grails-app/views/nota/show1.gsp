
<%@ page import="grailsapplication1.Nota" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'nota.label', default: 'Nota')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-nota" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="../../persona/ventanaInicio"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
                  <g:form action="buscar" controller="nota">
                         <input type="text" name="campo" id="campo" value="Escriba su busqueda" onClick="this.value=''"/>                                      
                         <g:submitButton name="submit" value="Buscar"/>
                     </g:form>
		</div>
		<div id="show-nota" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list nota">
			
				<g:link controller="adjunto" action="list" >${message(code: 'default.add.label', args: [message(code: 'adjunto.label', default: 'Adjunto')])}</g:link>

			
				<g:if test="${notaInstance?.etiquetas}">
				<li class="fieldcontain">
					<span id="etiquetas-label" class="property-label"><g:message code="nota.etiquetas.label" default="Etiquetas" /></span>
					
						<g:each in="${notaInstance.etiquetas}" var="e">
						<span class="property-value" aria-labelledby="etiquetas-label"><g:link controller="etiqueta" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${notaInstance?.libreta}">
				<li class="fieldcontain">
					<span id="libreta-label" class="property-label"><g:message code="nota.libreta.label" default="Libreta" /></span>
					
						<span class="property-value" aria-labelledby="libreta-label"><g:link controller="libreta" action="show" id="${notaInstance?.libreta?.id}">${notaInstance?.libreta?.nombre.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${notaInstance?.texto}">
				<li class="fieldcontain">
					<span id="texto-label" class="property-label"><g:message code="nota.texto.label" default="Texto" /></span>
					
						<span class="property-value" aria-labelledby="texto-label"><g:fieldValue bean="${notaInstance}" field="texto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${notaInstance?.titulo}">
				<li class="fieldcontain">
					<span id="titulo-label" class="property-label"><g:message code="nota.titulo.label" default="Titulo" /></span>
					
						<span class="property-value" aria-labelledby="titulo-label"><g:fieldValue bean="${notaInstance}" field="titulo"/></span>
					
				</li>
				</g:if>
                          
                                <g:if test="${notaInstance?.titulo}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="nota.fecha.label" default="Fecha de Creación" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:fieldValue bean="${notaInstance}" field="fecha"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${notaInstance?.id}" />
					<g:link class="edit" action="edit" id="${notaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
