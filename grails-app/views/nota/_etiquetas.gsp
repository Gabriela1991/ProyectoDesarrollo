
<html>
  <head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
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
        templateHtml += "<input type='text' id='EtiquetasList[" + childCount + "].title' name='EtiquetasList[" + childCount + "].title' />\n";
        templateHtml += "<span onClick='$(\"#" + htmlId + "\").remove();'><img src='" + deleteIcon + "' /></span>\n";
        templateHtml += "</div>\n";
        $("#childList").append(templateHtml);
        childCount++;
    }
</script>
<input type="button" value="Añadir etiqueta" onclick="addChild();" />
 <div id="childList">
    <g:each var="etiqueta" in="${notaInstance.etiquetas}" status="i">
        <g:render template='etiqueta' model="['etiqueta':etiqueta,'i':i]"/>
    </g:each>
</div> 
  </body>
</html>