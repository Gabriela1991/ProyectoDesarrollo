<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sample title</title>
  </head>
  <body>
    <div id="etiqueta${i}">
    <g:hiddenField name='etiquetas[${i}].id' value='${etiqueta.id}'/>
    <g:textField name='etiquetas[${i}]' value='${etiqueta.texto}'/>
    <input type="hidden" name='etiquetas[${i}]._deleted' id='etiquetas[${i}]._deleted' value='false'/>
    <span onClick="$('#etiquetas\\[${i}\\]\\._deleted').val('true'); $('#etiqueta${i}').hide()"><img src="${resource(dir:'images/skin', file:'icon_delete.png')}" /></span>
</div>
  </body>
</html>
