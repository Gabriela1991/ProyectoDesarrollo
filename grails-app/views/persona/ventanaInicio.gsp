<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
    <title>.::Perfil de Usuario::.</title>
    </head>
    <div class="body">
               
                   <div class="dialog">
                     <p></p>
                     <p style="color: forestgreen; font-size: xx-large">BIENVENIDO</p></br></br>
                     <g:form action="buscar" controller="nota">
                         <input type="text" name="campo" id="campo" value="Escriba su busqueda" onClick="this.value=''"/>                                      
                         <g:submitButton name="submit" value="Buscar"/>
                     </g:form>
                     <table  class="personaForm">
                       <tr>
                         <td style="background: darkseagreen">
                           <p><strong>.:: Gestion de Notas ::.</strong></p>
                         </td>
                       </tr>
                     </table>
                    <table  class="personaForm">
                      <tr>
                        <td style="background: chocolate">
                          <p><strong>Crear Libreta</strong></p>
                        </td>  
                        <td style="background: deepskyblue">
                          <p><strong>Crear Nota</strong></p>
                        </td>     
                      </tr>
                      <tr>
                        <td>
                          <p>Administre sus notas y recordatorios personales,</p>
                          <p>agrupandolos por un tema especifico mediante el</p>
                          <p>uso de libretas <g:link controller="libreta" action="create">.::Comenzar::.</g:link></p>
                        </td>
                        <td>
                          <p>Escriba sus notas y administre sus recordatorios</p>
                          <p>de la manera que m&aacute;s le agrade. As&oacute;cielos</p>
                          <p>a una libreta y adjunte los archivos de interes. 
                      <g:link controller="nota" action="create">.::Comenzar::.</g:link>
                        </td>
                      </tr>
                      <tr>
                        <td style="background: orchid">
                          <p><strong>Ver Libretas</strong></p>
                        </td>  
                        <td style="background: lightpink">
                          <p><strong>Ver Notas</strong></p>
                        </td>   
                      </tr>
                      <tr>
                        <td>
                          <p>Consulte, edite o elimine una libreta ya creada.</p>
                          <p><g:link controller="libreta" action="list">.::Comenzar::.</g:link></p>
                        </td>
                        <td>
                          <p>Consulte, edite o elimine una nota ya creada.</p>
                          <p><g:link controller="nota" action="list">.::Comenzar::.</g:link></p>
                        </td>
                      </tr>
                    </table> 
                     
                    </br></br>
                    
                    <table  class="personaForm">
                       <tr>
                         <td style="background: darkseagreen">
                           <p><strong>.:: Configuracion de Cuenta ::.</strong></p>
                         </td>
                       </tr>
                     </table>
                    
                    <table  class="personaForm">
                      <tr>
                        <td style="background: chocolate">
                          <p><strong>Ver Datos del Perfil</strong></p>
                        </td>  
                        <td style="background: deepskyblue">
                          <p><strong>Editar Perfil</strong></p>
                        </td>     
                      </tr>
                      <tr>
                        <td>
                     
                          <p>Consulte la informacion que ha sido almacenada</p>
                          <p>al momento de la creacion de su cuenta
                            <p><g:link controller="persona" action="show">.::Ver::.</g:link></p>
                           
                        </td>
                        <td>
                          <p>Edite su informacion de contacto con la idea de mantener</p>
                          <p>su perfil constantemente actualizado 
                            <p><g:link controller="persona" action="edit">.::Editar::.</g:link></p>
                          
                        </td>
                      </tr>
                    </table> 
                    
    </div>
      <g:form action="inicio">
                  <div class="buttons">
                         <span class="formButton">
                            <input type="submit" value="Cerrar Sesion"></input>                      
                         </span>
                   </div>
      </g:form>
                                 
            </div>
   </body>
</html>
