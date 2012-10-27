<div class="fieldcontain ${hasErrors(bean: personaInstance, field: 'etiqueta', 'error')} required">
    <label for="estado">
        <g:message code="nota.etiqueta.label" default="Etiqueta"/>
        <span class="required-indicator">*</span>
    </label>
    <g:if test="${etiquetaList}">
        <g:select id="etiqueta" name="etiqueta.id" from="${etiquetaList}" optionKey="id" required=""
                  value="${notaInstance?.etiqueta?.id}" class="many-to-one"/>
    </g:if>
    <g:else>
        No existen etiquetas para esta nota
    </g:else>
</div>