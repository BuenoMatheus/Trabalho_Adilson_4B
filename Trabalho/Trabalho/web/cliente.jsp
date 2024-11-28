<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Central do Assinante | Entrar na conta</title>
<head>
    <title>Gerenciar Hóspedes</title>
</head>
<body>
    <h2>Lista de Hóspedes</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Email</th>
            <th>Ações</th>
        </tr>
        <c:forEach var="cliente" items="${clientes}">
            <tr>
                <td>${cliente.id}</td>
                <td>${cliente.nome}</td>
                <td>${cliente.email}</td>
                <td>
                    <a href="editarCliente?id=${cliente.id}">Editar</a> |
                    <a href="excluirCliente?id=${cliente.id}">Excluir</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
