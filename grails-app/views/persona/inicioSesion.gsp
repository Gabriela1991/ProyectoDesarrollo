<!DOCTYPE html>
<html lang="en">
<head>
<title>Perfil Usuario</title>
<meta name="layout" content="main">
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
  
	<div class="body2"> 
		<div class="main">
                  <table>
                    <tr>
                      <td> <h2 style="color: forestgreen">BIENVENIDO</h2> </td>
                      <td>
                        
                        <h2> .::Modificar Perfil::.</h2> </td>
                      <td> 
                        <g:render template="inicio.gsp" />_inicio
                        <h2 style="color: brown">.::Cerrar Sesion::.</h2> </td>
                    </tr>
                  </table>
			
<!-- / header-->
<!-- content -->
			<section id="content">
				<div class="wrapper marg_left1">
				  <article class="col1">
					<div class="wrapper">
						  <p>
                                                  <h2 style="color: dodgerblue">Crear Libreta</h2>
						    <figure class="left marg_right1"><img src="images/libreta.jpg" alt="" width="123" height="128"></figure>
						  </p>
						  <p><img src="images/comillasA.jpg" width="29" height="24"><strong> Administre sus notas y recordatorios personales, agrupandolos por un tema especifico mediante el uso de libretas.</strong>						  <img src="images/comillasC.jpg" width="29" height="24"></p>
<article class="col1"><a href="http://localhost:8080/GrailsApplication1/libreta/create" class="button">Crear</a></article>
					  <p>&nbsp;</p>
                            
				      <p>&nbsp;</p>
				      <div class="wrapper">
				        <p>
                                          <h2 style="color: dodgerblue">Crear Nota</h2>
				          <figure class="left marg_right1"><img src="images/notas.jpg" alt="" width="134" height="128"></figure>
			            </p>
				        <p><span class="under"><strong><img src="images/comillasA.jpg" width="29" height="24">Escriba sus notas y administre sus recordatorios de la manera que m&aacute;s le agrade.</strong></span></p>
				        <p><span class="under"><strong> As&oacute;cielos a una libreta y adjunte los archivos de interes.</strong><img src="images/comillasC.jpg" width="29" height="24"></span></p>
				        <article class="col1"><a href="http://localhost:8080/GrailsApplication1/nota/create" class="button">Crear</a></article>
				        <p>&nbsp;</p>
			          </div>
				      <p>&nbsp;</p>
					</div>
				  </article>
				  <article class="col2 pad_left1">
					  <h2 style="color: dodgerblue">Ver Libretas</h2>
				    <figure class="marg_top1 pad_bot1">
			        <p><a href="http://localhost:8080/GrailsApplication1/libreta/list"><img src="images/libreta (1).jpg" alt="" width="115" height="93"></a></p>
					    <p><strong>Consulte, edite o elimine una libreta ya creada.</strong>
                                              <a href="http://localhost:8080/GrailsApplication1/libreta/list">.::Ver Libreta::.</a>
                                            </p>
				    </figure>
					  <p>&nbsp;</p>
						<h2 style="color: dodgerblue">Ver Notas</h2>
                    <figure class="marg_top1 pad_bot1">
                          <p><a href="http://localhost:8080/GrailsApplication1/nota/list"><img src="images/note.png" alt="" width="107" height="75"></a></p>
                          <p><strong>Consulte, edite o elimine una nota ya creada.</strong>
                          <a href="http://localhost:8080/GrailsApplication1/nota/list">.::Ver Nota::.</a>
                          </p>
                        </figure>
                        <p>&nbsp;</p>
</article>
			  </div>				
			</section>
<!-- / content -->
		</div>
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