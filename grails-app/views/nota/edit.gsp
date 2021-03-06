<%@ page import="grailsapplication1.Nota" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>.::Perfil Usuario :: Editar Nota::.</title>           
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
                      <img src="../../images/notebook1.png" width="55" height="55" />
                    </td>
                    <td style="color: transparent">fgghgh</td>
                    <td><h2>.::Editar Nota::.</h2></td>
                  </tr>
                </table>
              </div>
              
              <div class="articles">
                <div id="edit-nota" class="content scaffold-edit" role="main">			
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${notaInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${notaInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${notaInstance?.id}" />
				<g:hiddenField name="version" value="${notaInstance?.version}" />
				<g:render template="form"/>
                                <table>
                                  <tr>
                                    <td style="color: transparent">hjjnkljslkkljkljk</td>
                                    <td><g:link controller="adjunto" action="list" id="${notaInstance?.id}"><img src="../../images/badministrar.png"></g:link></td>
                                    <td>
                                        <g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: '.::Guardar::.')}" />
                                    </td>
                                    <td>
                                        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: '.::Eliminar::.')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Seguro desea eliminar esta nota?')}');" />
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
