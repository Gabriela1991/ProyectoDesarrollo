
<%@ page import="grailsapplication1.Etiqueta" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">		
		<title>.::Perfil de Usuario :: Nota::.</title>
	</head>
	<body>
          <div id="wrap">
            <div id="header">
              <h1><a href="#">Block de Notas</a></h1>
              <h2>Administra tus recordatorios y Notas personales</h2>
            </div>
            
            <div id="right">
              <table>
                <tr>
                  <td style="color: cornflowerblue; font-size: medium">Bienvenido </td>
                  <td style="color: yellowgreen; font-size: medium">${session.persona.nombre}</td>
                  <td style="color: transparent">dfjljkljkjljcghkjkjlkjlkj</td>
                  <td><g:link controller="persona" action="inicio">.::Cerrar Sesion::.
                       <img src="../../images/cerrarSesion.png" width="25" height="25" />
                      </g:link>
                  </td>
                </tr>
              </table>
              
              <div class="articles">
                <table>
                  <tr>
                    <td style="color: transparent">fgghghghghjkdfgh</td>
                    <td>
                      <img src="../../images/libreta (1).jpg" width="55" height="55" />
                    </td>
                    <td style="color: transparent">fgghgh</td>
                    <td><h2>.::Ver Etiqueta::.</h2></td>
                  </tr>
                </table>
              </div>
              <div class="articles">
                <div id="list-etiqueta" class="content scaffold-list" role="main">		
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="etiqueta.nota.label" default="Nota" /></th>
					
						<g:sortableColumn property="texto" title="${message(code: 'etiqueta.texto.label', default: 'Texto')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${etiquetaInstanceList}" status="i" var="etiquetaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${etiquetaInstance.id}">${fieldValue(bean: etiquetaInstance, field: "nota")}</g:link></td>
					
						<td>${fieldValue(bean: etiquetaInstance, field: "texto")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${etiquetaInstanceTotal}" />
			</div>
		</div>
              </div>
            </div>
            
            <div id="left">
              <h3>Búsqueda de Notas: </h3>
              <g:form action="buscar" controller="nota">
                 <input type="text" name="campo" id="campo" value="Escriba su búsqueda" onClick="this.value=''"/>                                      
                 <g:submitButton name="submit" value="Ok"/>
              </g:form>

              <h3>Opciones: </h3>
              <table>
                <tr>
                  <td>
                    <img src="../../images/home.png" width="25" height="25" />
                  </td>
                  <td>
                    <li><g:link controller="persona" action="ventanaInicio">.::HOME::.</g:link></li>
                  </td>
                </tr>
                <tr>
                  <td>
                    <img src="../../images/book.png" width="25" height="25" />
                  </td>
                  <td>
                    <li><g:link controller="libreta" action="list">.::Libretas::.</g:link></li>
                  </td>
                </tr>
                <tr>
                  <td>
                    <img src="../../images/perfil.png" width="25" height="25" />
                  </td>
                  <td>
                    <li><g:link controller="persona" action="show">.::Configurar Cuenta::.</g:link></li>
                  </td>
                </tr>
              </table>    
            </div>
            
            <div style="clear: both;"> </div>

            <div id="footer">
                Desarroladores: Hernandez, Keyla || Loreto, Maria G || Valderrama, Angel
            </div>
          </div>
		
	</body>
</html>
