
<html>
  <head>
    <script src="js/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sample title</title>
  </head> 
  <body>   
    
 <script type="text/javascript">
   
    var childCount = ${notaInstance?.etiquetas.size()} + 0;
    
    function addChild2() {
   //  alert (document.getElementById('etiquetaSelection').value);
        var htmlId = "etiqueta" + childCount;      
        var deleteIcon = "${resource(dir:'images/skin', file:'icon_delete.png')}";    
        var texto2= document.getElementById('etiquetaSelection').value.toString(); 
        var templateHtml = "<div id='" + htmlId + "' name='" + htmlId + "'>\n";
        templateHtml += "<input type='text' id='etiquetas[" + childCount + "]' name='etiquetas[" + childCount + "]' value='"+texto2+"' />\n";
        templateHtml += "<span onClick='$(\"#" + htmlId + "\").remove();'><img src='" + deleteIcon + "' /></span>\n";
        templateHtml += "</div>\n";
        $("#childList").append(templateHtml);
        childCount++;  
     }
  </script>
  
  <g:select id="etiquetaSelection" name="etiqueta2" from="${etiquetasInstance}" onChange="addChild2()" optionValue="texto" optionKey="texto" noSelection="[Seleccione]" noSelection="${['null':'Seleccione una etiqueta...']}"/> 
 
  
 <div id="childList">
   <g:each var="etiqueta" in="${etiquetas}" status="i">
        <g:render template='etiqueta' model="['etiqueta':etiqueta,'i':i]"/>  <!-- //se supone que aki inserta?? 
    </g:each>
</div>

  </body>
</html>