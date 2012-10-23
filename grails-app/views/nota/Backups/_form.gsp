<%@ page import="grailsapplication1.Nota" %>



<div class="fieldcontain ${hasErrors(bean: notaInstance, field: 'adjuntos', 'error')} ">
	<label for="adjuntos">
		<g:message code="nota.adjuntos.label" default="Adjuntos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${notaInstance?.adjuntos?}" var="a">
    <li><g:link controller="adjunto" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="adjunto" action="create" params="['nota.id': notaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'adjunto.label', default: 'Adjunto')])}</g:link>
</li>
</ul>

</div>

 <div class="fieldcontain ${hasErrors(bean: notaInstance, field: 'etiquetas', 'error')} ">
	<label for="etiquetas">
		<g:message code="nota.etiquetas.label" default="Etiquetas" />
		
	</label>
	
 <script language="javascript" type="text/javascript">  
var i = 2;
var sourceTextNode = document.getElementsByName("imagedescription2")[0];
function createTextBox(){
    var newNode = sourceTextNode.cloneNode(false);
    newNode.setAttribute("name", ++i);
    var parent = sourceTextNode.parentNode;
    if(parent.lastchild == sourceTextNode) {
        parent.appendChild(newNode);
    } else {
        parent.insertBefore(newNode, sourceTextNode.nextSibling);
    }
}

function btnClicked(){
    createTextBox();
}   
   
 </script>  
   
 <g:submitButton name="Agregar Etiqueta" value="Submit" onclick="btnClicked" />
   
<!-- <ul class="one-to-many">
<g:each in="${notaInstance?.etiquetas?}" var="e">
    <li><g:link controller="etiqueta" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each> 
<li class="add">
<g:link controller="etiqueta" action="create" params="['nota.id': notaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'etiqueta.label', default: 'Etiqueta')])}</g:link>
</li>
</ul>  -->

</div>  

<div class="fieldcontain ${hasErrors(bean: notaInstance, field: 'libreta', 'error')} required">
	<label for="libreta">
		<g:message code="nota.libreta.label" default="Libreta" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="libreta" name="libreta.id" from="${grailsapplication1.Libreta.list()}" optionKey="id" required="" value="${notaInstance?.libreta?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: notaInstance, field: 'texto', 'error')} ">
	<label for="texto">
		<g:message code="nota.texto.label" default="Texto" />
		
	</label>
	<g:textField name="texto" value="${notaInstance?.texto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: notaInstance, field: 'titulo', 'error')} ">
	<label for="titulo">
		<g:message code="nota.titulo.label" default="Titulo" />
		
	</label>
	<g:textField name="titulo" value="${notaInstance?.titulo}"/>
</div>

