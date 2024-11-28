<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gerenciar Clientes</title>
     <style>
        body {
            text-align: center;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            
            flex-direction: column;
            height: 100vh;
        }
        header {
            background-color: #4CAF50;
            color: white;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
        }
        header .welcome-message {
            font-size: 18px;
        }
        header .actions {
            display: flex;
            gap: 10px;
        }
        header .actions a {
            color: white;
            text-decoration: none;
            font-size: 16px;
            background-color: #333;
            padding: 5px 10px;
            border-radius: 5px;
        }
        header .actions a:hover {
            background-color: #555;
        }
        
        h2 {
           text-align: center; 
            
        }
        
        main {
            flex-grow: 1;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 20px;
        }
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px auto;
            background-color: white;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 8px 12px;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        .action-button {
            background-color: #e74c3c;
            color: white;
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 5px;
        }
        .action-button:hover {
            background-color: #c0392b;
        }
        .back-button {
            display: block;
            margin: 20px auto;
            background-color: #3498db;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            text-align: center;
        }
        .back-button:hover {
            background-color: #2980b9;
        }
        
        a {
            text-align: center;
            width: 50%;
            padding: 10px;
            margin: 10px 0;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }
        
        a:hover {
            text-decoration: underline;
            background-color: #009e34;
        }
        
        footer {
            text-align: center;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
    <h2>Gerenciar Clientes</h2>
    
    <c:if test="${not empty clientes}">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Email</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="cliente" items="${clientes}">
                    <tr>
                        <td>${cliente.codigo}</td>
                        <td>${cliente.nome}</td>
                        <td>${cliente.email}</td>
                        <td>
                            <a href="editarCliente?id=${cliente.codigo}">Editar</a> |
                            <form action="listarClientes" method="POST" style="display:inline;">
                                <input type="hidden" name="id" value="${cliente.codigo}">
                                <button type="submit" class="action-button">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    
    <c:if test="${empty clientes}">
        <p>Nenhum cliente encontrado.</p>
    </c:if>
        <a href="dashboard.jsp" >retornar</a>
</body>
</html>
