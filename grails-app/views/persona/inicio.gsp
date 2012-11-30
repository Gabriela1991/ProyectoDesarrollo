<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
    <title>.::Inicio de Sesión::.</title>
  </head>
  <body>
  
  <div id="wrap">
      <div id="header">
        <h1><a href="#">Block de Notas</a></h1>
        <h2>Administra tus recordatorios y Notas personales</h2>
      </div>
      
      <div id="right">     
        
        <div class="articles">
          <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
          </g:if>
          
          <g:form action="inicioSesion" method="post">
             <div class="dialog">
               
                <p>Ingrese sus datos para Iniciar Sesión:</p>
                
                <table  class="personaForm">
                   <tr class='prop'>
                     <td><img src="../../images/llave.png" width="45" height="45" /></td>
                      <td valign='top' style='text-align:left;' width='20%'>
                        <label style="color: cornflowerblue; font-size: medium" for='correo'>Correo:</label>
                        </br>
                        <label style="color: cornflowerblue; font-size: medium" for='clave'>Clave:</label>
                      </td>
                      <td valign='top' style='text-align:left;' width='80%'>
                        <input id="correo" type='text' name='correo' value='${persona?.correo}' />
                        </br>
                        <input id="password" type='password' name='clave' value='${persona?.clave}' />
                      </td>
                  </tr>                
               </table>  
                <table>
                  <tr>
                    <td style="color: transparent">sdfghjkl;d</td>
                    <td>
                      <div class="buttons">
                        <span class="formButton">
                           <input type="submit" value=".::Iniciar Sesión::."></input>      
                        </span>
                     </div>
                    </g:form>
                    </td>
                    <td>
                      <g:form action="create">
                        <div class="buttons">
                            <span class="formButton">     
                                <input type="submit" value=".::Crear Cuenta::."></input>
                            </span>
                        </div>
                      </g:form>
                    </td>
                  </tr>
                </table>
                </div>
                
            
        </div>
      </div>
        
      <div style="clear: both;"> </div>
    
      <div id="footer">
          Desarroladores: Hernandez, Keyla || Loreto, Maria G || Valderrama, Angel
      </div>
  </div>
    
    
    
    
    
    
    
<div class="body">
  
</body>
</html>
