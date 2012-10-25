
<%@ page import="grailsapplication1.Etiqueta" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'etiqueta.label', default: 'Etiqueta')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-etiqueta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-etiqueta" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="_deleted" title="${message(code: 'etiqueta._deleted.label', default: 'Deleted')}" />
					
						<th><g:message code="etiqueta.nota.label" default="Nota" /></th>
					
						<g:sortableColumn property="texto" title="${message(code: 'etiqueta.texto.label', default: 'Texto')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${etiquetaInstanceList}" status="i" var="etiquetaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${etiquetaInstance.id}">${fieldValue(bean: etiquetaInstance, field: "_deleted")}</g:link></td>
					
						<td>${fieldValue(bean: etiquetaInstance, field: "nota")}</td>
					
						<td>${fieldValue(bean: etiquetaInstance, field: "texto")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${etiquetaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
