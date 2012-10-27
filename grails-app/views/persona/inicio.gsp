<!DOCTYPE html>
<html lang="en">
<head>
<title>Home</title>

<meta charset="utf-8">
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
<script type="text/javascript" src="js/jquery-1.4.2.js" ></script>
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/cufon-replace.js"></script> 
<script type="text/javascript" src="js/Butter_500.font.js"></script>
<!--[if lt IE 9]>
	<script type="text/javascript" src="http://info.template-help.com/files/ie6_warning/ie6_script_other.js"></script>
	<script type="text/javascript" src="js/html5.js"></script>
<![endif]-->
</head>
<body id="page1">  

  
<div class="body1">
		<div class="main">
<!-- / header-->
<!-- content -->
			<section id="content">
				<div class="wrapper marg_left1">
					<article class="col1">
						<h2>BIENVENIDO</h2>
                                                <p><strong>Inicio de Sesi&oacute;n.</strong></p>
                                                <g:form action="inicioSesion" method="post">
                                                <table style="background: darkseagreen">
                                                  <tr>
                                                    <td>
                                                      <label for="cuenta" style="color: #ffffff">... Usuario: </label>
                                                    </td>
                                                    <td>
                                                      <input type="text" id="cuenta" name="cuenta" value='${persona?.correo}' />
                                                    </td>
                                                  </tr>
                                                  <tr>
                                                    <td>
                                                      <label for="clave" style="color: #ffffff">... Clave: </label>
                                                    </td>
                                                    <td>
                                                      <input type="password" id="clave" name="clave" value='${persona?.clave}'/>
                                                    </td>
                                                  </tr>
                                                </table>
                                                  
                                                  <div class="buttons">
                     <span class="formButton">
                        <input type="submit" value="Login">LOGIN</input>
</span>
               </div>
                                                </g:form>
                                               <!-- <input type="submit" value="Login"/> -->
					</article>
				</div>
				<div class="wrapper">
                                  <p></p>
                                  <h2><g:link controller="persona" action="inicioSesion">INICIAR</g:link></h2>
                                    <h2><a href="http://localhost:8080/GrailsApplication1/persona/create">CREAR USUARIO</a></h2>
                                  
                                  
				</div>
			</section>
<a href="http://localhost:8080/GrailsApplication1/persona/create" class="skip"><g:message code="default.link.skip.label" default="CREAR CUENTA"/></a>
		
		<div id="page-body" role="main">
			
			<div id="controller-list" role="navigation">
				<h2>Menu</h2>
				<ul>
					<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
						<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
					</g:each>
				</ul>
			</div>
		</div>
<!-- / content -->
		</div>
</div>
<div class="body3">
	<div class="main">
<!-- footer -->
		<footer>
			<p> Desarrolladores: Keyla Hernandez, Maria G Loreto, Angel Valderrama</p>                                        			
		</footer>
<!-- / footer -->
	</div>
</div>
<script type="text/javascript"> Cufon.now(); </script>
</body>
</html>