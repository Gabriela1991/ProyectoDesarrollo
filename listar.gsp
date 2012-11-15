<html>
   <head>
   </head>
   <body>
        <ul>
        <g:each in="${notas}" var="persona">
           <li>${persona.titulo} ${persona.texto}</li>
        </g:each>
        </ul>
   </body>
</html>