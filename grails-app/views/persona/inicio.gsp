<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main" />
<title>Inicio de Sesion</title>
</head>
<div class="body">
           <g:form action="ventanaInicio" method="post">
               <div class="dialog">
    <p>Ingrese sus datos para Iniciar Sesión aquí:</p>
                <table  class="personaForm">
                  <tr class='prop'>
                      <td valign='top' style='text-align:left;' width='20%'>
                          <label for='correo'>Correo:</label>
</td>
<td valign='top' style='text-align:left;' width='80%'>
                          <input id="correo" type='text' name='correo' value='${persona?.correo}' />                      </td>
                  </tr>
<tr class='prop'>
                      <td valign='top' style='text-align:left;' width='20%'>
                          <label for='clave'>Clave:</label>
</td>
<td valign='top' style='text-align:left;' width='80%'>
                          <input id="password" type='password' name='clave'
                                 value='${persona?.clave}' />
                      </td>
                  </tr>
                       
               </table>   
</div>
<div class="buttons">
                     <span class="formButton">
                        <input type="submit" value="Iniciar"></input>
                        
</span>
               </div>
            </g:form>
        <g:form action="create">
          <div class="buttons">
        <span class="formButton">     
                                <input type="submit" value="Crear Cuenta"></input>
        </span>
             </div>
        </g:form>
        </div>
</body>
</html>
