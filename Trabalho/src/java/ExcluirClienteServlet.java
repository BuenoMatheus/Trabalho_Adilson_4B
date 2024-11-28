import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ExcluirClienteServlet")
public class ExcluirClienteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        
        if (id != null) {
            String sql = "DELETE FROM cliente WHERE id = ?";
            try (Connection conn = new ClienteDAO().conectar();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, Integer.parseInt(id));
                int linhasAfetadas = ps.executeUpdate();

                if (linhasAfetadas > 0) {
                    response.sendRedirect("gerenciarClientes"); // Após exclusão, redireciona para a lista
                } else {
                    response.sendRedirect("gerenciarClientes.jsp?erro=true");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("gerenciarClientes.jsp?erro=true");
            }
        } else {
            response.sendRedirect("gerenciarClientes.jsp?erro=true");
        }
    }
}
