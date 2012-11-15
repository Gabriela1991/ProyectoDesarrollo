<%@ page import="grailsapplication1.Etiqueta" %>



<div class="fieldcontain ${hasErrors(bean: etiquetaInstance, field: 'nota', 'error')} required">
	<label for="nota">
		<g:message code="etiqueta.nota.label" default="Nota" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="nota" name="nota.id" from="${grailsapplication1.Nota.list()}" optionKey="id" required="" value="${etiquetaInstance?.nota?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: etiquetaInstance, field: 'texto', 'error')} ">
	<label for="texto">
		<g:message code="etiqueta.texto.label" default="Texto" />
		
	</label>
	<g:textField name="texto" value="${etiquetaInstance?.texto}"/>
</div>

