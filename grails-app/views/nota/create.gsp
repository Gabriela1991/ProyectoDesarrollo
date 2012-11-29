<%@ page import="grailsapplication1.Nota" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'nota.label', default: 'Nota')}" />
                <g:set var="entityName2" value="${message(code: 'etiqueta.label', default: 'Etiqueta')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
                <g:javascript library="jquery" plugin="jquery"/>
	</head>
	<body>
		<a href="#create-nota" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="../persona/ventanaInicio"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
                  <g:form action="buscar" controller="nota">
                         <input type="text" name="campo" id="campo" value="Escriba su busqueda" onClick="this.value=''"/>                                      
                         <g:submitButton name="submit" value="Buscar"/>
                     </g:form>
		</div>
                               
		<div id="create-nota" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${notaInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${notaInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" method="post">
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                                        
                                </fieldset>
			</g:form>                        
		</div>
	</body>
</html>
