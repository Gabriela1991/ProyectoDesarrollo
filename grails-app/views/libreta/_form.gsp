<%@ page import="grailsapplication1.Libreta" %>



<div class="fieldcontain ${hasErrors(bean: libretaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="libreta.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${libretaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: libretaInstance, field: 'persona', 'error')} required">
	<label for="persona">
		<g:message code="libreta.persona.label" default="Persona" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="persona" name="persona.id" from="${grailsapplication1.Persona.list()}" optionKey="id" required="" value="${libretaInstance?.persona?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: libretaInstance, field: 'tema', 'error')} ">
	<label for="tema">
		<g:message code="libreta.tema.label" default="Tema" />
		
	</label>
	<g:textField name="tema" value="${libretaInstance?.tema}"/>
</div>

