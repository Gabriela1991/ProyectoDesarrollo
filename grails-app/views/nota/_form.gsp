<%@ page import="grailsapplication1.Nota" %>

<div class="fieldcontain ${hasErrors(bean: notaInstance, field: 'libreta', 'error')} required">
  <label for="libreta">
    <g:message code="nota.libreta.label" default="Libreta" />
    <span class="required-indicator">*</span>
  </label>
  <g:select id="libreta" name="libreta.id" from="${libretasInstance}" optionKey="id" required="" value="${notaInstance?.libreta?.id}" class="many-to-one"/> 

 <!-- <g:render template="etiquetasCombo" model="['notaInstance':notaInstance]" />  -->

 
</div>

<div class="fieldcontain ${hasErrors(bean: notaInstance, field: 'titulo', 'error')} ">
	<label for="titulo">
		<g:message code="nota.titulo.label" default="Titulo" />
		
	</label>
	<g:textField name="titulo" value="${notaInstance?.titulo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: notaInstance, field: 'texto', 'error')} ">
	<label for="texto">
		<g:message code="nota.texto.label" default="Texto" />
		
	</label>
	<g:textArea name="texto" cols="40" rows="5" maxlength="1000" value="${notaInstance?.texto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: notaInstance, field: 'etiquetas', 'error')} ">

  <div class="dialog"> 

    <tbody>
      <tr class="prop">
        <td valign="top" class="name"><label for="textoetiqueta">Etiquetas:</label></td>
        <td valign="top" class="value ${hasErrors(bean:notaInstance,field:'texto','errors')}">
        <g:render template="etiquetasCombo" model="['notaInstance':notaInstance]" />  
        </td>
        <td valign="top" class="value ${hasErrors(bean:notaInstance,field:'etiquetas','errors')}">
    <g:render template="etiquetas" model="['notaInstance':notaInstance]" />
    </td>
    </tr>

    </tbody>

  </div> 


</div> 



<!--<div class="fieldcontain ${hasErrors(bean: notaInstance, field: 'adjuntos', 'error')} ">
	<label for="adjuntos">
		<g:message code="nota.adjuntos.label" default="Adjuntos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${notaInstance?.adjuntos?}" var="a">
    <li><g:link controller="adjunto" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="adjunto" action="list" >${message(code: 'default.add.label', args: [message(code: 'adjunto.label', default: 'Adjunto')])}</g:link>
</li>


</ul>

</div>

-->