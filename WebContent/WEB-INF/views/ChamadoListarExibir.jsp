<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Listar Chamados</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    
</head>

<body>
    <!-- Barra superior com os menus de navegação -->
    <c:import url="Menu.jsp" />
    <!-- Container Principal -->
    <div id="main" class="container">
        <br>               
		<table class="table table-bordered">
			<tr class="active">
				<td style="display:none">ID</td>
				<td>FILA</td>
				<td>DESCRICAO</td>
				<td>STATUS</td>
				<td>DATA DE ABERTURA</td>
				<td>DATA DE FECHAMENTO</td>
				
			</tr>
	
			<c:forEach var="chamados" items="${chamados}">
				<tr>
					<td style="display:none">${chamados.id}</td>
					<td class="">${chamados.fila.nome}</td>
					<td class="">${chamados.descricao}</td>
					<td class="success">${chamados.status}</td>
					<td>${chamados.DT_ABERTURA}</td>
					<td>${chamados.DT_FECHAMENTO}</td>					
					<!-- <td class="warning">${voluntario.senha}</td> -->
				</tr>
			</c:forEach>
		</table>                         
                         	
        
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>