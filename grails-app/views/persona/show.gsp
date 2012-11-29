
<%@ page import="grailsapplication1.Persona" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'persona.label', default: 'Persona')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
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
                  <td>.:: Bienvenido ::.</td>
                  <td> ||nombre de usuario|| </td>
                  <td style="color: transparent">dfjljkljkjljcghkjkjlkjlkj</td>
                  <td><g:link controller="persona" action="inicio">.::Cerrar Sesion::.
                       <img src="../images/cerrarSesion.png" width="25" height="25" />
                      </g:link>
                  </td>
                </tr>
              </table>
              <div class="articles">
                <div id="show-persona" class="content scaffold-show" role="main">
		   
		   <ol class="property-list persona">
                     <table style="alignment-adjust: central">
                  <tr>
                    <td>Nombre de Usuario: </td>
                    <td>
                      <g:if test="${personaInstance?.nombre}">
			  <li class="fieldcontain">
			     <span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${personaInstance}" field="nombre"/></span>
			  </li>
		       </g:if>
                    </td>
                  </tr>
                </table>
                
                
		       
		       <g:if test="${personaInstance?.apellido}">
			  <li class="fieldcontain">
			      <span id="apellido-label" class="property-label"><g:message code="persona.apellido.label" default="Apellido" /></span>
			      <span class="property-value" aria-labelledby="apellido-label"><g:fieldValue bean="${personaInstance}" field="apellido"/></span>
			  </li>
		       </g:if>
		       <g:if test="${personaInstance?.correo}">
			  <li class="fieldcontain">
			      <span id="correo-label" class="property-label"><g:message code="persona.correo.label" default="Correo" /></span>
			      <span class="property-value" aria-labelledby="correo-label"><g:fieldValue bean="${personaInstance}" field="correo"/></span>
			  </li>
		       </g:if>
		   </ol>
		   <g:form>
                         <g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edite.label', default: 'Edit')}" onclick="return confirm('${message(code: 'default.button.edit.confirm.message', default: 'Esta seguro de editar??')}');" />
		   </g:form>
		</div>
              </div>
            </div>
            
          <div id="left">

            <h3>Búsqueda de Notas: </h3>
            <g:form action="buscar" controller="nota">
               <input type="text" name="campo" id="campo" value="Escriba su busqueda" onClick="this.value=''"/>                                      
               <g:submitButton name="submit" value="Buscar"/>
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
          Desarroladores: Hernandez, Keyla || Loreto, Maria G || Valderrama, Angel
      </div>
        
</div>

	</body>
</html>
