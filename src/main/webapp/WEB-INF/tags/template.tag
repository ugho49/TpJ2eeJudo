<%@tag description="template de chaque page" pageEncoding="UTF-8" %>
<%@tag import="utils.Flash"%>
<%@tag import="java.util.ArrayList"%>
<%@tag import="java.util.HashMap"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="title" required="false" type="java.lang.String" %>
<%@attribute name="content" required="true" fragment="true" %>

<% 
    HashMap<String, ArrayList<String>> flashes = Flash.getMessages(session);
%>

<c:set var="flashes" value="<%= flashes %>" scope="page" />

<!doctype html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/static/img/logo.png">
    
	<title>
		<c:choose>
		    <c:when test="${empty title}">
		        TP Site Judo
		    </c:when>
		    <c:otherwise>
		        ${title}
		    </c:otherwise>
		</c:choose>
	</title>
	
	<!-- Google Font -->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      
	<!-- Stylesheets -->
	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">
	<link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
</head>
<body>

	<jsp:include page="../jspf/header.jspf"/>
	
	<section id="user-info" class="row">
	    <div class="col s12">
	        
	        <c:choose>
			    <c:when test="${user_connected}">
			        <p>${user.prenom} ${user.nom}</p>
			    </c:when>    
			    <c:otherwise>
			        <p>Pas authentifié</p>
			    </c:otherwise>
			</c:choose>
			
	    </div>
	</section>
	
	<div class="row">
	    <aside class="col s12 m3 l2">
	        <h3>Menu</h3>
            <!-- Menu -->
            <ul>
                <li><a href="${pageContext.request.contextPath}/action/">Accueil</a></li>
			    <c:if test="${user_connected}">
			    	<li><a href="${pageContext.request.contextPath}/action/profile">Mon profil</a></li>
			    </c:if>
				<li><a href="${pageContext.request.contextPath}/action/competitions">Compétitions</a></li>
				<li><a href="${pageContext.request.contextPath}/action/informations">Coordonnées</a></li>
            </ul>
	
	        <div class="row">
	        	<c:choose>
				    <c:when test="${!user_connected}">
					    <form class="col s8 offset-s2" method="POST" action="${pageContext.request.contextPath}/action/login">
			                <div class="row">
			                    <div class="input-field col s12">
			                        <input id="login" name="login" type="text" class="validate">
			                        <label for="login">Identifiant</label>
			                    </div>
			                </div>
			                <div class="row">
			                    <div class="input-field col s12">
			                        <input id="password" name="password" type="password" class="validate">
			                        <label for="password">Mot de passe</label>
			                    </div>
			                </div>
			                <div class="row">
			                    <div class="input-field col s12 m8 l12">
			                        <button class="btn waves-effect waves-light" type="submit" name="action">
			                            Connexion
			                        </button>
			                    </div>
			                </div>
			            </form>
				    </c:when>    
				    <c:otherwise>
				        <form class="col s8 offset-s2" method="POST" action="${pageContext.request.contextPath}/action/logout">
			                <div class="row">
			                    <div class="input-field col s12 m8 l12">
			                        <button class="btn waves-effect waves-light red" type="submit" name="action">
			                            Deconnexion
			                        </button>
			                    </div>
			                </div>
			            </form>
				    </c:otherwise>
				</c:choose>
	        </div>
	    </aside>
	    
	    <section id="container" class="col s12 m9 l10">
            <article>
            	<!-- Flash Messages -->
            	<c:forEach items="${pageScope.flashes}" var="flash"> 
            		<div class="delete">
						<c:forEach items="${flash.value}" var="message">
							<div class="chip col s12 z-depth-1 ${flash.key}">${message}<i class="material-icons">close</i></div>
						</c:forEach>
					</div>
				</c:forEach>
            	<!-- Main Content -->
                <jsp:invoke fragment="content"/>
            </article>
	    </section>
	</div>
	
	<jsp:include page="../jspf/footer.jspf"/>
	
	<!-- Scripts -->
	<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/main.js"></script>
</body>
</html>