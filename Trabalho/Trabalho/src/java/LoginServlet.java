import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.verificarLoginRetornarCliente(email, senha);

            if (cliente != null) {
                request.setAttribute("nome", cliente.getNome());
                request.getRequestDispatcher("dashboard.jsp").forward(request, response); // Redireciona para a tela de bem-vindo
            } else {
                response.sendRedirect("index.jsp?erro=true");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?erro=true");
        }
    }
}

