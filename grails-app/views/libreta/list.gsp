
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
                       <img src="../images/cerrarSesion.png" width="25" height="25" />
                      </g:link>
                  </td>
                </tr>
              </table>
              
              <div class="articles">
                <table>
                  <tr>
                    <td style="color: transparent">fgghghghghjkdfgh</td>
                    <td>
                      <img src="../images/libreta (1).jpg" width="55" height="55" />
                    </td>
                    <td style="color: transparent">fgghgh</td>
                    <td><h2>.::Ver Libretas::.</h2></td>
                  </tr>
                </table>
              </div>
              
              <div class="articles">
                <div id="list-libreta" class="content scaffold-list" role="main">
			<g:if test="${flash.message}">
			<p<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
                          <tr>
                            <td style="color: transparent">sdfghdfghjkldfghjkdfjncvhbjnmdfghjnkmfghjnkm,fghjmdfghjk</td>
                            <td>
                              <thead>
                                  <tr style="color: cornflowerblue; font-size: medium">
					<g:sortableColumn property="nombre" title="${message(code: 'libreta.nombre.label', default: 'Nombre')}" />
					<g:sortableColumn property="tema" title="${message(code: 'libreta.tema.label', default: 'Tema')}" />		
				  </tr>
				</thead>
                             </td>
                           </tr>
                        </table>
                        <table>
                          <tr>
                            <td>
				<tbody>
				<g:each in="${libretaInstanceList}" status="i" var="libretaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}"> 
                                                <td style="color: transparent">sdffghsdfghbjnmdfghjnm</td>
                                                <td><g:link action="show" controller="libreta" id="${libretaInstance?.id}">${fieldValue(bean: libretaInstance, field: "nombre")}</g:link></td>
                                                <td style="color: transparent">sdffghsdfghbjnmdfghjnm</td>
						<td>${fieldValue(bean: libretaInstance, field: "tema")}</td>
					</tr>
				</g:each>
				</tbody>
                            </td>
                          </tr>
			</table>
			<div class="pagination">
				<g:paginate total="${libretaInstanceTotal}" />
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
                    <img src="../images/home.png" width="25" height="25" />
                  </td>
                  <td>
                    <li><g:link controller="persona" action="ventanaInicio">.::HOME::.</g:link></li>
                  </td>
                </tr>
                <tr>
                  <td>
                    <img src="../images/book.png" width="25" height="25" />
                  </td>
                  <td>
                    <li><g:link controller="libreta" action="list">.::Libretas::.</g:link></li>
                  </td>
                </tr>
                <tr>
                  <td>
                    <img src="../images/perfil.png" width="25" height="25" />
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
