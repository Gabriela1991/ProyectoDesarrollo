
<%@ page import="grailsapplication1.Adjunto" %>
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
                    <td><h2>.::Ver Adjuntos::.</h2></td>
                  </tr>
                </table>
              </div>
              
              <div class="articles">
                <div id="show-adjunto" class="content scaffold-show" role="main">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list adjunto">
			
				<g:if test="${adjuntoInstance?.archivo}">
				<li class="fieldcontain">
					<span id="archivo-label" class="property-label"><g:message code="adjunto.archivo.label" default="Archivo" /></span>
					
						<span class="property-value" aria-labelledby="archivo-label"><g:fieldValue bean="${adjuntoInstance}" field="archivo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${adjuntoInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="adjunto.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${adjuntoInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${adjuntoInstance?.nota}">
				<li class="fieldcontain">
					<span id="nota-label" class="property-label"><g:message code="adjunto.nota.label" default="Nota" /></span>
					
						<span class="property-value" aria-labelledby="nota-label"><g:link controller="nota" action="show" id="${adjuntoInstance?.nota?.id}">${adjuntoInstance?.nota?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${adjuntoInstance?.id}" />
					<g:link class="edit" action="edit" id="${adjuntoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
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
                Desarroladores: Hernandez, Keyla || Loreto, Maria G || Valderrama, Angel
            </div>
          </div>
          
          
		
		
	</body>
</html>
