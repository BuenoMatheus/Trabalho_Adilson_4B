<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="dashboard.css"/>
    <title>Bem-vindo</title> 
</head>
<body>
   <header>
    <div class="welcome-message">
        Bem-vindo, <span id="nome-usuario"><%= request.getAttribute("nome") != null ? request.getAttribute("nome") : "Usuário" %></span>!
    </div>
    <div class="actions">
        <a href="listarClientes">Gerenciar Clientes</a>         
        <a href="#">? Carrinho</a>
        <a href="logout.jsp">Logout</a>
    </div>
</header>

    <main>
        <div class="watermark">Nada comprado ainda</div>
    </main>
</body>
</html>
