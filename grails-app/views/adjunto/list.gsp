<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Adjuntos</title>
    </head>
    <body>
        <div class="nav">
   			  <span class="menuButton"><a class="home" href="/">Home</a></span>
     </div>
        <div class="body">

			<h1>Archivo a adjuntar</h1><br>

			 <g:form method="post"  enctype="multipart/form-data">
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="fileUpload">Archivo</label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean:adjuntoInstance,field:'upload','errors')}">
	                                    <input type="file" id="fileUpload" name="fileUpload" />
	                                </td>
	                            </tr> 
	                        </tbody>
	                    </table>
	                </div>
	                <div class="buttons">
	                    <span class="button"><g:actionSubmit class="upload" value="Adjuntar" action="upload" /></span>
	                </div>
	            </g:form>

            <h1>Adjuntos</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
			<div id="success"></div>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                            <g:sortableColumn property="files" title="file"/>
                            <g:sortableColumn property="path" title="path" colspan="3"/>
                       </tr>
                    </thead>
                    <tbody>
                    <g:each in="${adjuntoInstanceList}" status="i" var="adjuntoInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>${adjuntoInstance.decodeURL()}</td>
                            <td> <input type="text" value="/images/${adjuntoInstance.decodeURL()}"></input></td>
                            <td><a href="${createLinkTo( dir:'images' , file: adjuntoInstance.decodeURL(), absolute:true )}" target="_new">Ver</a></td>
                            <td><g:link action="delete" id="${adjuntoInstance.replace('.','###')}" onclick="return confirm('¿Esta Seguro?');"> Eliminar </g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
