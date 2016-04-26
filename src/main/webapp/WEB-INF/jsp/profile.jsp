<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template title="Page de login">
	<jsp:attribute name="content">
		<h4>Mon profil</h4>	
		
		<br>
		
		<c:choose>
		    <c:when test="${!user_connected}">
		        <p>Pas authentifié</p>
		    </c:when>    
		    <c:otherwise>
	            <form class="col s8 offset-s2" method="POST" action="${pageContext.request.contextPath}/action/update">
	                <div class="row">
	                    <div class="input-field col s6">
	                        <input id="nom" name="nom" type="text" class="validate" value="${user.nom}">
	                        <label class="active" for="nom">Nom</label>
	                    </div>
	                    <div class="input-field col s6">
	                        <input id="prenom" name="prenom" type="text" class="validate" value="${user.prenom}">
	                        <label class="active" for="prenom">Prénom</label>
	                    </div>
	                </div>
	
	                <div class="row">
	                    <div class="input-field col s12">
	                        <input id="dateOfBirth" name="dateOfBirth" type="date" class="datepicker"
                                   value="<fmt:formatDate pattern="dd/mm/yyyy" value="${user.birthday}" />">
	                        <label for="dateOfBirth">Date de naissance</label>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="input-field col s12">
	                        <select name="grade">
	                            <option value="" disabled>Grade</option>
	                            <c:forEach items="${grades}" var="grade"> 
									<option value="${grade.code}" 
										<c:if test="${user.grade.code == grade.code}">selected</c:if>
									>${grade.libelle}</option>
								</c:forEach>
	                        </select>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="input-field col s12">
	                        <input id="poids" name="poids" type="number" value="${user.poids}">
	                        <label class="active" for="poids">Poids</label>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="input-field col s12">
	                        <input id="login" name="login" type="text" value="${user.login}">
	                        <label class="active" for="login">Identifiant</label>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="input-field col s6">
	                        <input id="password" name="password" type="password">
	                        <label for="password">Mot de passe</label>
	                    </div>
	                    <div class="input-field col s6">
	                        <input id="password-confirm" name="password-confirm" type="password">
	                        <label for="password-confirm">Confirmation</label>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="input-field col s12 m8 l12">
	                        <button class="btn waves-effect waves-light green" type="submit" name="action">
	                            Mettre à jour
	                        </button>
	                    </div>
	                </div>
	            </form>
		    </c:otherwise>
		</c:choose>
	</jsp:attribute>
</t:template>