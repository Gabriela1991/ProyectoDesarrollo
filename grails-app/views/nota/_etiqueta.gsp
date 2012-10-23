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
    <g:hiddenField name='EtiquetaList[${i}].id' value='${etiqueta.id}'/>
    <g:textField name='EtiquetasList[${i}].title' value='${etiqueta.title}'/>
    <input type="hidden" name='EtiquetasList[${i}]._deleted' id='EtiquetasList[${i}]._deleted' value='false'/>
    <span onClick="$('#EtiquetasList\\[${i}\\]\\._deleted').val('true'); $('#etiqueta${i}').hide()"><img src="${resource(dir:'images/skin', file:'icon_delete.png')}" /></span>
</div>
  </body>
</html>
