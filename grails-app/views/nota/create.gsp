<%@ page import="grailsapplication1.Nota" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>.::Perfil Usuario :: Crear Nota::.</title>
                <g:javascript library="jquery" plugin="jquery"/>
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
                      <img src="../images/notebook1.png" width="55" height="55" />
                    </td>
                    <td style="color: transparent">fgghgh</td>
                    <td><h2>.::Crear Nota::.</h2></td>
                  </tr>
                </table>
              </div>
              
              <div class="articles">
                <div id="create-nota" class="content scaffold-create" role="main">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
                        
                  <g:if test="${flash.error}">
			<div class="error" role="status">${flash.error}</div>
			</g:if>
                        
                  
                        <g:hasErrors bean="${notaInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${notaInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" method="post">
				
					<g:render template="form"/>
                                        </br>
				
			<table>
                          <tr>
                            <td style="color: transparent">dfghjkfghjdfghjkfghjghghjghjg</td>
                            <td>
                              <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: '.::Configurar Cuenta::.')}" />
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
