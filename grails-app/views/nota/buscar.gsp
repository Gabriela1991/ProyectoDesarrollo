
<%@ page import="grailsapplication1.Nota" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'nota.label', default: 'Nota')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-nota" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
                          <li><a class="home" href="../../persona/ventanaInicio"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="Crear nueva nota" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-nota" class="content scaffold-list" role="main">
			<h1><g:message code="Lista de Notas" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					                             
                                                <g:sortableColumn property="titulo" title="${message(code: 'nota.titulo.label', default: 'Titulo')}" />
                                                
						<g:sortableColumn property="texto" title="${message(code: 'nota.texto.label', default: 'Texto')}" />
                                                
                                                <g:sortableColumn property="fecha" title="${message(code: 'nota.fecha.label', default: 'Fecha de Creación')}" />
					
					</tr>
				</thead>
				<tbody>
                                  <g:each in="${libreta}" status="k" var="nota">
                                                 <tr class="${(k % 2) == 0 ? 'even' : 'odd'}">
                                                    
						<td><g:link action="show" id="${nota.id}">${nota.titulo?.encodeAsHTML()}</g:link></td> 			
                                  
                                                <td>${fieldValue(bean: nota, field: "texto")}</td>
					
                                                <td>${fieldValue(bean: nota, field: "fecha")}</td>
					
                                                 </tr>
                                  </g:each>

             

				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${notaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
