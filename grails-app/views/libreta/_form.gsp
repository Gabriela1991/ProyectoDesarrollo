<%@ page import="grailsapplication1.Libreta" %>



<div class="fieldcontain ${hasErrors(bean: libretaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="libreta.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${libretaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: libretaInstance, field: 'tema', 'error')} ">
	<label for="tema">
		<g:message code="libreta.tema.label" default="Tema" />
		
	</label>
	<g:textField name="tema" value="${libretaInstance?.tema}"/>
</div>

