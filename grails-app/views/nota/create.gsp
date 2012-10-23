<%@ page import="grailsapplication1.Nota" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'nota.label', default: 'Nota')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-nota" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
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
			<g:form action="save" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
                        
                        
                                <!--            <div class="dialog"> -->
   <!-- <table>
        <tbody>
            <tr class="prop">
                <td valign="top" class="name"><label for="texto">Name:</label></td>
                <td valign="top" class="value ${hasErrors(bean:notaInstance,field:'texto','errors')}">
                    <input type="text" id="name" name="texto" value="${fieldValue(bean:notaInstance,field:'texto')}"/>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><label for="etiquetas">Books:</label></td>
                <td valign="top" class="value ${hasErrors(bean:notaInstance,field:'etiquetas','errors')}">
                    <g:render template="etiquetas" model="['notaInstance':notaInstance]" />
                </td>
            </tr>
        </tbody>
    </table> -->
<!--  </div> -->
                        
                        
		</div>
       
	</body>
</html>
