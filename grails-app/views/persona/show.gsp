
<%@ page import="grailsapplication1.Persona" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>.::Perfil Usuario :: Configurar Cuenta::.</title>
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
                      <img src="../images/perfil.png" width="35" height="35" />
                    </td>
                    <td style="color: transparent">fgghgh</td>
                    <td><h2>.::Configurar Cuenta::.</h2></td>
                  </tr>
                </table>
              </div>
              <div class="articles">
                <div id="show-persona" class="content scaffold-show" role="main">
		   
		   <ol class="property-list persona">
                <table>
                  <tr>
                    <td style="color: transparent">fgghghghghjkdfgh</td>
                    <td style="color: cornflowerblue; font-size: medium">Nombre:</td>
                    <td>
                      <g:if test="${personaInstance?.nombre}">
			  <li class="fieldcontain">
			     <span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${personaInstance}" field="nombre"/></span>
			  </li>
		       </g:if>
                    </td>
                  </tr>
                  <tr>
                    <td style="color: transparent">fgghghghghjkdfgh</td>
                    <td style="color: cornflowerblue; font-size: medium">Apellido:</td>
                    <td>
                      <g:if test="${personaInstance?.apellido}">
			  <li class="fieldcontain">
			      <span class="property-value" aria-labelledby="apellido-label"><g:fieldValue bean="${personaInstance}" field="apellido"/></span>
			  </li>
		       </g:if>
                    </td>
                  </tr>
                  <tr>
                    <td style="color: transparent">fgghghghghjkdfgh</td>
                    <td style="color: cornflowerblue; font-size: medium">Correo:</td>
                    <td>
                      <g:if test="${personaInstance?.correo}">
			  <li class="fieldcontain">
			      <span class="property-value" aria-labelledby="correo-label"><g:fieldValue bean="${personaInstance}" field="correo"/></span>
			  </li>
		       </g:if>
                    </td>
                  </tr>
                </table>
              </ol>
                  </br>
                  <table>
                    <tr>
                      <td style="color: transparent">fgghghghghjkdfgh</td>
                      <td>
                        <g:form>
                           <g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edite.label', default: '.::Modificar Perfil::.')}" onclick="return confirm('${message(code: 'default.button.edit.confirm.message', default: 'Esta seguro de Modificar su Perfil?')}');" />
		        <g:actionSubmit  action="desvincular" value="${message(code: 'default.button.edite.label', default: 'Desvincular dropbox')}" onclick="return confirm('${message(code: 'default.button.edit.confirm.message', default: 'Esta seguro de cerrar sesión den dropbox?')}');" />
                        </g:form>
                      </td>
                    </tr>
                  </table>
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