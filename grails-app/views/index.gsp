
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Flower</title>
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
<meta http-equiv="Content-Language" content="English" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="style.css" media="screen" />
</head>
<body>

<div id="wrap">

<div id="header">
<h1><a href="#">Website Title</a></h1>
<h2>Subheader, website description H2</h2>
</div>

<div id="right">

<h2><a href="#">License and terms of use</a></h2>
<div class="articles">
Flower is a CSS template that is free and fully standards compliant. <a href="http://www.free-css-templates.com/">Free CSS templates</a> designed this template.
This template is allowed for all uses, including commercial use, as it is released under the <strong>Creative Commons Attributions 2.5</strong> license. The only stipulation to the use of this free template is that the links appearing in the footer remain intact. Beyond that, simply enjoy and have fun with it!	 
<br /><br />
<img src="images/pic.jpg" alt="Example pic" style="border: 3px solid #ccc;" />
<br /><br />
Donec nulla. Aenean eu augue ac nisl tincidunt rutrum. Proin erat justo, pharetra eget, posuere at, malesuada 
et, nulla. Donec pretium nibh sed est faucibus suscipit. Nunc nisi. Nullam vehicula. In ipsum lorem, bibendum sed, 
consectetuer et, gravida id, erat. Ut imperdiet, leo vel condimentum faucibus, risus justo feugiat purus, vitae 
congue nulla diam non urna.
</div>
<h2><a href="#">Title with a link - Example of heading 2</a></h2>
<div class="articles">
Donec nulla. Aenean eu augue ac nisl tincidunt rutrum. Proin erat justo, pharetra eget, posuere at, malesuada 
et, nulla. Donec pretium nibh sed est faucibus suscipit. Nunc nisi. Nullam vehicula. In ipsum lorem, bibendum sed, 
consectetuer et, gravida id, erat. Ut imperdiet, leo vel condimentum faucibus, risus justo feugiat purus, vitae 
congue nulla diam non urna.
</div>
</div>

<div id="left"> 

<h3>Categories :</h3>
<ul>
<li><a href="#">World Politics</a></li> 
<li><a href="#">Europe Sport</a></li> 
<li><a href="#">Networking</a></li> 
<li><a href="#">Nature - Africa</a></li>
<li><a href="#">SuperCool</a></li> 
<li><a href="#">Last Category</a></li>
</ul>

<h3>Archives</h3>
<ul>
<li><a href="#">January 2007</a></li> 
<li><a href="#">February 2007</a></li> 
<li><a href="#">March 2007</a></li> 
<li><a href="#">April 2007</a></li>
<li><a href="#">May 2007</a></li> 
<li><a href="#">June 2007</a></li> 
<li><a href="#">July 2007</a></li> 
<li><a href="#">August 2007</a></li> 
<li><a href="#">September 2007</a></li>
<li><a href="#">October 2007</a></li>
<li><a href="#">November 2007</a></li>
<li><a href="#">December 2007</a></li>
</ul>

</div>
<div style="clear: both;"> </div>

<div class="body1">
                <div class="main">
<!-- / header-->
<!-- content -->
                        <section id="content">
                                <div class="wrapper marg_left1">
                                        <article class="col1">
                                                <h2><g:link controller="persona" action="inicio">INICIAR APLICACION</g:link></h2>

                                               <!-- <input type="submit" value="Login"/> -->
                                        </article>
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

<div id="footer">
Designed by <a href="http://www.free-css-templates.com/">Free CSS Templates</a>, Thanks to <a href="http://www.openwebdesign.org/">Web Design Firm</a>
</div>
</div>


</body>
</html>

