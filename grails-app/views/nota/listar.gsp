<html>
   <head>
   </head>
   <body>
        <ul>
        <g:each in="${notas}" var="persona">
           <li>${persona.texto} ${persona.titulo}</li>
        </g:each>
        </ul>
   </body>
</html>