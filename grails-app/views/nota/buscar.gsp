
<%@ page import="grailsapplication1.Nota" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>.::Perfil Usuario :: Buscar Nota::.</title>           
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
                <table>
                  <tr>
                    <td style="color: transparent">fgghghghghjkdfgh</td>
                    <td>
                      <img src="../images/notebook1.png" width="55" height="55" />
                    </td>
                    <td style="color: transparent">fgghgh</td>
                    <td><h2>.::Buscar Nota::.</h2></td>
                  </tr>
                </table>
              </div>
              
              <div class="articles">
                  <div id="list-nota" class="content scaffold-list" role="main">
                      <g:if test="${flash.message}">
                      <div class="message" role="status">${flash.message}</div>
                      </g:if>
                        <table>
                          <thead>
                            <tr>
                              <g:sortableColumn property="titulo" title="${message(code: 'nota.titulo.label', default: 'Titulo')}" />
                              <g:sortableColumn property="texto" title="${message(code: 'nota.texto.label', default: 'Texto')}" />
                              <g:sortableColumn property="fecha" title="${message(code: 'nota.fecha.label', default: 'Fecha de Creación')}" />

                                            </tr>
                                    </thead>


                                    <tbody>
                                      <g:each in="${libretaInstance}" status="k" var="libreta">
                                                     <tr class="${(k % 2) == 0 ? 'even' : 'odd'}">
                                                            <g:each in="${libreta}" status="i" var="nota">

                                                                  <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                                                                        <td><g:link action="show" id="${nota.id}">${nota.titulo?.encodeAsHTML()}</g:link></td> 			

                                                                        <td>${fieldValue(bean: nota, field: "texto")}</td>

                                                                        <td>${fieldValue(bean: nota, field: "fecha")}</td>

                                                                  </tr>
                                                            </g:each>
                                                     </tr>
                                      </g:each>



                                    </tbody>
                            </table>
                            <div class="pagination">
                                    <g:paginate total="10" />
                            </div>
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
                  <li><g:link controller="persona" action="show">.::Crear Nota::.</g:link></li>
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
