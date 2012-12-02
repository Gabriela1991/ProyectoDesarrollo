
<%@ page import="grailsapplication1.Nota" %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main">		
    <title>.::Perfil Usuario :: Ver Nota::.</title>
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
              <td><h2>.::Ver Nota::.</h2></td>
            </tr>
          </table>
        </div>

        <div class="articles">
          <div id="show-nota" class="content scaffold-show" role="main">

            <g:if test="${flash.message}">
              <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list nota">
              <table>
               
                <g:if test="${notaInstance?.etiquetas}">
                  <li class="fieldcontain">
                  <tr>
                    <td style="color: transparent">dfjljlkjlkjkljk</td>
                    <td style="color: cornflowerblue; fonlkjlkjt-size: medium">
                      <span id="etiquetas-label" class="property-label"><g:message code="nota.etiquetas.label" default="Etiquetas: " /></span>
                    </td>
                    <td style="color: black; font-size: medium">
                  <g:each in="${notaInstance.etiquetas}" var="e">
                    <span class="property-value" aria-labelledby="etiquetas-label"><g:link controller="etiqueta" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
                  </g:each>
                  </td>
                  </tr>
                  </li>
                </g:if>
                <g:if test="${notaInstance?.libreta}">
                  <li class="fieldcontain"> 
                  <tr>
                    <td style="color: transparent">dfjljlkjlkjkllkjlkjjk</td>
                    <td style="color: cornflowerblue; font-size: medium">
                      <span id="libreta-label" class="property-label"><g:message code="nota.libreta.label" default="Libreta: " /></span>
                    </td>
                    <td style="color: black; font-size: medium">
                      <span class="property-value" aria-labelledby="libreta-label"><g:link controller="libreta" action="show" id="${notaInstance?.libreta?.id}">${notaInstance?.libreta?.nombre.encodeAsHTML()}</g:link></span>
                    </td>
                  </tr>
                  </li>
                </g:if>
                <g:if test="${notaInstance?.texto}">
                  <li class="fieldcontain">
                  <tr>
                    <td style="color: transparent">dfjljkllkjlkjlkjlkjjk</td>
                    <td style="color: cornflowerblue; font-size: medium">
                      <span id="texto-label" class="property-label"><g:message code="nota.texto.label" default="Texto: " /></span>
                    </td>
                    <td style="color: black; font-size: medium">
                      <span class="property-value" aria-labelledby="texto-label"><g:fieldValue bean="${notaInstance}" field="texto"/></span>
                    </td>
                  </tr>
                  </li>
                </g:if>
                <g:if test="${notaInstance?.titulo}">
                  <li class="fieldcontain">
                  <tr>
                    <td style="color: transparent">dfjljkljlkjlkjlkjlkjk</td>
                    <td style="color: cornflowerblue; font-size: medium">
                      <span id="titulo-label" class="property-label"><g:message code="nota.titulo.label" default="Titulo: " /></span>
                    </td>
                    <td style="color: black; font-size: medium">
                      <span class="property-value" aria-labelledby="titulo-label"><g:fieldValue bean="${notaInstance}" field="titulo"/></span>
                    </td>
                  </tr>
                  </li>
                </g:if>
                <g:if test="${notaInstance?.titulo}">
                  <li class="fieldcontain">
                  <tr>
                    <td style="color: transparent">dfjljkljlklkjlkjjlkjk</td>
                    <td style="color: cornflowerblue; font-size: medium">
                      <span id="fecha-label" class="property-label"><g:message code="nota.fecha.label" default="Fecha de Creación: " /></span>
                    </td>
                    <td style="color: black; font-size: medium">
                      <span class="property-value" aria-labelledby="fecha-label"><g:fieldValue bean="${notaInstance}" field="fecha"/></span>
                    </td>
                  </tr>
                  </li>
                </g:if>
               
                  <g:link controller="adjunto" action="list" id="${notaInstance?.id}">${message(code: 'default.add.label', args: [message(code: 'adjunto.label', default: 'Adjunto')])}</g:link>
                   <g:link action="show" controller="nota" id="${notaInstance?.id}"> Guardar </g:link> 
              </table>
            </ol>
            </br>
            <g:form>
              <table>
                <tr>
                  <td style="color: transparent">hjklhjkl,klkjlkjlkjkljjhkjh</td>
                  <td>
                <g:hiddenField name="id" value="${notaInstance?.id}" />
               <!-- <g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edite.label', default: 'Modificar Nota')}" onclick="return confirm('${message(code: 'default.button.edit.confirm.message', default: 'Esta seguro de Modificar la nota?')}');" />
                                                <!-- <g:link class="edit" action="edit" id="${notaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link> -->				
                
                </td>
                <td>
        <!--        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
        -->       
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
          <g:submitButton name="submit" value=".::Buscar::."/>
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
