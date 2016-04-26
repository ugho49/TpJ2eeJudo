<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template title="Page d'accueil">
	<jsp:attribute name="content">
		<h4>Accueil</h4>

        <br>

        <c:forEach items="${informations}" var="information">
            <h5><fmt:formatDate pattern="dd/mm/yyyy" value="${information.date}" /></h5>
            <h6>${information.message}</h6>
            <br>
        </c:forEach>

	</jsp:attribute>
</t:template>