<%@ page import="grailsapplication1.Persona" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>.::Crear Cuenta::.</title>
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
                  <td style="color: transparent">.:: Bienvenido ::.</td>
                  <td style="color: transparent"> ||nombre de usuario|| </td>
                  <td style="color: transparent">dfjljkljkjljcghkjkjlkjlkj</td>
                  <td><g:link controller="persona" action="inicio">.::Volver::.
                       <img src="../images/cerrarSesion.png" width="25" height="25" />
                      </g:link>
                  </td>
                </tr>
              </table>
              
              <div class="articles">
                <table>
                  <tr>
                    <td style="color: transparent">fgghjkdfgh</td>
                    <td>
                      <img src="../images/perfil.png" width="35" height="35" />
                    </td>
                    <td style="color: transparent">fgghgh</td>
                    <td><h2>.::Crear Cuenta::.</h2></td>
                  </tr>
                </table>
              </div>
              
              <div class="articles">
                <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
                </g:if>
                <g:hasErrors bean="${personaInstance}">
                <ul class="errors" role="alert">
                        <g:eachError bean="${personaInstance}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                        </g:eachError>
                </ul>
                </g:hasErrors>
                <g:form action="save" >
                  <table>
                    <tr>
                      <td style="color: transparent">fgghjkdfgh</td>
                      <td style="color: cornflowerblue; font-size: medium">
                        <g:render template="form"/>
                      </td>
                    </tr>
                  </table>
                  <table>
                    <tr>
                      <td style="color: transparent">dfghjkfghjdfghjkfghjghghjghjg</td>
                      <td>
                        <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: '.::Crear Cueenta::.')}" />
                      </td>
                    </tr>
                  </table>						
                </g:form>		
              </div>
            </div>
            
            <div style="clear: both;"> </div>

            <div id="footer">
                Desarrolladores: Hernandez, Keyla || Loreto, Maria G || Valderrama, Angel
            </div>
            
          </div>
		
		
	</body>
</html>