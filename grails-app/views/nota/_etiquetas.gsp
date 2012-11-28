
<html>
  <head>
    <script src="js/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sample title</title>
  </head> 
  <body>
<script type="text/javascript">
    var childCount = ${notaInstance?.etiquetas.size()} + 0;
   // document.writeln (${notaInstance?.etiquetas.size()});
    function addChild() {
        var htmlId = "etiqueta" + childCount;      
        var deleteIcon = "${resource(dir:'images/skin', file:'icon_delete.png')}";      
        var templateHtml = "<div id='" + htmlId + "' name='" + htmlId + "'>\n";
        templateHtml += "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
        templateHtml += "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
        templateHtml += "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
        templateHtml += "<input type='text' id='etiquetas[" + childCount + "]' name='etiquetas[" + childCount + "]' />\n";
        templateHtml += "<span onClick='$(\"#" + htmlId + "\").remove();'><img src='" + deleteIcon + "' /></span>\n";
        templateHtml += "</div>\n";
        $("#childList").append(templateHtml);
     /*   var posicion= childCount;
      * 
      */
        childCount++;      
  }
</script>

<br>
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
&nbsp&nbsp&nbsp&nbsp&nbsp

<input type="button" value="Añadir etiqueta" onclick="addChild();" />

 <div id="childList">
    <g:each var="etiqueta" in="${notaInstance.etiquetas}" status="i">
        <g:render template='etiqueta' model="['etiqueta':etiqueta,'i':i]"/>  <!-- //se supone que aki inserta?? -->
    </g:each>
</div>

  </body>
</html>