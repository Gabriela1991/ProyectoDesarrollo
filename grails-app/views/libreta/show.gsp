
<%@ page import="grailsapplication1.Libreta" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>.::Perfil Usuario :: Ver Libretas::.</title>
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
                    <td><h2>.::Ver Libreta::.</h2></td>
                  </tr>
                </table>
              </div>
              
              <div class="articles">
                	<div id="show-libreta" class="content scaffold-show" role="main">
	
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list libreta">
                          <table>
                            <tr>
                              <td style="color: transparent">dfghjkghjkhjkdfghjkghjdfghj</td>
                              <td style="color: cornflowerblue; font-size: medium">
                                <g:if test="${libretaInstance?.nombre}">
				    <li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="libreta.nombre.label" default="Nombre:" /></span>
                              </td>
                              <td style="color: black; font-size: medium">
                                        <span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${libretaInstance}" field="nombre"/></span>
				    </li>
				 </g:if>
                              </td>
                            </tr>
                            <tr>
                              <td style="color: transparent">dfghjkghjkhjkdfghjkghjdfghj</td>
                              <td style="color: cornflowerblue; font-size: medium">
                                <g:if test="${libretaInstance?.tema}">
				    <li class="fieldcontain">
					<span id="tema-label" class="property-label"><g:message code="libreta.tema.label" default="Tema: " /></span>
                              </td>
                              <td style="color: black; font-size: medium">
                                        <span class="property-value" aria-labelledby="tema-label"><g:fieldValue bean="${libretaInstance}" field="tema"/></span>
				    </li>
				 </g:if>
                              </td>
                            </tr>
                          </table>
			</ol>
			<g:form>
                          </br>
                          <g:hiddenField name="id" value="${libretaInstance?.id}" />
                                <table>
                                  <tr>
                                    <td style="color: transparent">dfghjkghjkhj</td>
                                    <td>
                                      <g:link controller="nota" action="create1" id="${libretaInstance?.id}"><img src="../../images/bagregar.png" width="95" height="26" /></g:link>
                                    </td>
                                    <td>
                                        <g:link controller="nota" action="list" id="${libretaInstance?.id}"><img src="../../images/bMostrar.png" width="95" height="26" /></g:link>
                                    </td>
                                    <td>
                                        <g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" />
                                    </td>
                                    <td>
                                        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                                    </td>
                                  </tr>
                                </table>
			</g:form>
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
                Desarrolladores: Hernandez, Keyla || Loreto, Maria G || Valderrama, Angel
            </div>

            </div>
                        
	</body>
</html>
