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
                       
          <table  class="personaForm">
             <tr>
                <td>
                   <img src="../images/libreta (1).jpg" width="65" height="65" />
                </td>
                <td>
                   <h2>.::Crear Libreta::.</h2>
                </td>  
                <td>
                   <img src="../images/notebook1.png" width="60" height="60" />
                </td>
                <td>
                   <h2>.::Crear Nota::.</h2>
                </td>     
                   </tr>
          </table> 
          
          <table>
             <tr>
                <td>
                   <p><img src="../images/comillasA.jpg" width="10" height="10" />
                     Agrupe sus notas por un tema específico mediante el uso de libretas
                     <img src="../images/comillasC.jpg" width="10" height="10" /></p>
                   <p><g:link controller="libreta" action="create">.::Comenzar::.</g:link></p>
                </td>
                <td>
                   <p><img src="../images/comillasA.jpg" width="10" height="10" />
                     Administre sus recordatorios en una libreta y adjunte archivos de interes. 
                   <img src="../images/comillasC.jpg" width="10" height="10" /></p>
                   <p><g:link controller="nota" action="create">.::Comenzar::.</g:link></p>
                </td>
             </tr>
          </table>
      
        </div>
        
    </div>
    
<div id="left">

        <h3>Búsqueda de Notas: </h3>
        <g:form action="buscar" controller="nota">
           <input type="text" name="campo" id="campo" value="Escriba su busqueda" onClick="this.value=''"/>                                      
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
              <li><g:link controller="persona" action="show">.::Configurar Cuenta::.</g:link></li>
            </td>
          </tr>
        </table>        
      </div>
      
    <div style="clear: both;"> </div>
    
    <div id="footer">
          Desarroladores: Hernandez, Keyla || Loreto, Maria G || Valderrama, Angel
      </div>
      
   </body>
</html>
