
<%@ page import="grailsapplication1.Libreta" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'libreta.label', default: 'Libreta')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-libreta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="http://localhost:8080/GrailsApplication1/persona/ventanaInicio"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-libreta" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'libreta.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="tema" title="${message(code: 'libreta.tema.label', default: 'Tema')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${libretaInstanceList}" status="i" var="libretaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${libretaInstance.id}">${fieldValue(bean: libretaInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: libretaInstance, field: "tema")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${libretaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
