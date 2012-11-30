<html>
  <head>
   
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
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
            <td> || ${session.persona.nombre} || </td>
            <td style="color: transparent">dfjljkljkjljcghkjkjlkjlkj</td>
            <td>.::Cerrar Sesion::. <img src="../images/cerrarSesion.png" width="25" height="25" /></td>
          </tr>
        </table>
        <h2>Adjuntos</h2>
        
        <div class="articles">
          
          <g:form method="post"  enctype="multipart/form-data">
            <div class="dialog">
              <table>
                <tbody>
                  <tr class="prop">
                    <td valign="top" class="name">
                      <label for="fileUpload">Archivo</label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean:adjuntoInstance,field:'upload','errors')}">
                      <input type="file" id="fileUpload" name="fileUpload" />
                    </td>
                  </tr> 
                </tbody>
              </table>
            </div>
            <span class="button">
              <g:actionSubmit class="upload" value="Adjuntar" action="upload" params="${notaInstance?.id}" />
             
            </span>
          </g:form>
          
          <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
      </g:if>
      <div id="success"></div>
      <div class="list">
        <table>
          <thead>
            <tr>
          <g:sortableColumn property="files" title="Archivos"/>
          <g:sortableColumn property="path" title="Opciones" colspan="3"/>
          </tr>
          </thead>
          <tbody>
          <g:each in="${adjuntoInstanceList}" status="i" var="adjuntoInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
              <td>${adjuntoInstance.decodeURL()}</td>
             <!-- <td> <input type="text" value="${adjuntoInstance.decodeURL()}"></input></td> -->
              <td><g:link action="download" id="${adjuntoInstance.replace('.','###')}"  > Ver </g:link></td>
              
              <td><g:link action="delete" id="${adjuntoInstance.replace('.','###')}" onclick="return confirm('¿Esta Seguro?');"> Eliminar </g:link></td>
            </tr>
          </g:each>
          </tbody>
        </table>
      </div>
          
        </div>
        
      </div>
    
      <div id="left">
        <h3>Opciones: </h3>
        <table>
          <tr>
            <td>
              <img src="../images/home.png" width="25" height="25" />
            </td>
            <td>
              <li><a href="#">.::HOME::.</a></li>
            </td>
          </tr>
          <tr>
            <td>
              <img src="../images/book.png" width="25" height="25" />
            </td>
            <td>
              <li><a href="#">.::Libretas::.</a></li>
            </td>
          </tr>
          <tr>
            <td>
              <img src="../images/notitas.png" width="25" height="25" />
            </td>
            <td>
              <li><a href="#">.::Notas::.</a></li>
            </td>
          </tr>
        </table>        
      </div>
      <div style="clear: both;"> </div>
  </div>

  </body>
</html>
