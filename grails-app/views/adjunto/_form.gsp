<%@ page import="grailsapplication1.Adjunto" %>



<div class="fieldcontain ${hasErrors(bean: adjuntoInstance, field: 'archivo', 'error')} ">
	<label for="archivo">
		<g:message code="adjunto.archivo.label" default="Archivo" />
		
	</label>
	<g:textField name="archivo" value="${adjuntoInstance?.archivo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: adjuntoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="adjunto.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${adjuntoInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: adjuntoInstance, field: 'nota', 'error')} required">
	<label for="nota">
		<g:message code="adjunto.nota.label" default="Nota" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="nota" name="nota.id" from="${grailsapplication1.Nota.list()}" optionKey="id" required="" value="${adjuntoInstance?.nota?.id}" class="many-to-one"/>
</div>

