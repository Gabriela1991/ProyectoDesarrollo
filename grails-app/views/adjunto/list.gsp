<html>
  <head>
   
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
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
            <td style="color: transparent">dfjljkljkjljcghkjdfghjkdfghdfghfghjkjlkjlkj</td>
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
                <img src="../../images/adjuntar.png" width="55" height="55" />
              </td>
              <td style="color: transparent">fgghgh</td>
              <td><h2>.::Agregar Adjuntos::.</h2></td>
            </tr>
          </table>
        </div>
        
        <div class="articles">
          
          <g:form method="post"  enctype="multipart/form-data">
            <div class="dialog">
              <table>
                <tbody>
                  <tr class="prop">
                    <td valign="top" class="name">
                      <label for="fileUpload" style="color: cornflowerblue; font-size: medium">Archivo: </label>
                    </td>
                    <td><img src="../../images/espacios.png" width="20"></td>
                    <td valign="top" class="value ${hasErrors(bean:adjuntoInstance,field:'upload','errors')}">
                      <input type="file" id="fileUpload" name="fileUpload" />
                    </td>
                  </tr> 
                </tbody>
              </table>
            </div>
            <table>
              <tr>
                <td style="color: transparent">djjkhjhjkhkhkhcgfhkjhjkhkkm,lcvb</td>
                <td>
                  <span class="button">
                    </br>
                    <g:actionSubmit class="upload" value=".::Adjuntar::." action="upload" params="${[idnota:notaInstance?.id]}" />
                  </span>
                </td>
              </tr>
            </table>
            
          </g:form>
          
          <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
      </g:if>
      <div id="success"></div>
      <div class="list">
        <table>
          <thead>
            <tr>
              <td><g:sortableColumn property="files" title="Archivos"/></td>
              <td><g:sortableColumn property="path" title="Opciones" colspan="3"/></td>          
          </tr>
          </thead>
          <tbody>
          <g:each in="${adjuntoInstanceList}" status="i" var="adjuntoInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
              <td style="color: transparent">fghjk,hbhhgjhghcvbnm,</td>
              <td>${adjuntoInstance.decodeURL()}</td>
              <td style="color: transparent">fcvbnm,</td>
             <!-- <td> <input type="text" value="${adjuntoInstance.decodeURL()}"></input></td> -->
              <td><g:link action="download" id="${adjuntoInstance.replace('.','###')}"  > Ver </g:link>
                  <g:link action="delete" id="${adjuntoInstance.replace('.','###')}" onclick="return confirm('¿Esta Seguro?');"> Eliminar </g:link>
              </td>
              <td></td>
            </tr>
          </g:each>
          </tbody>
        </table>
      </div>
      <table>
        <tr>
          <td style="color: transparent">ffghghghjgjjhjkhhghgjhnjhjhjkhkh</td>
          <td>
            </br>
            <g:link action="show" controller="nota" id="${session.nota.id}"> <img src="../../images/bguardar.png"/> </g:link> 
          </td>
        </tr>
      </table>
      
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
  </div>

  </body>
</html>