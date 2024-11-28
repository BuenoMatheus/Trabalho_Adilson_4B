import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listarClientes")
public class ListarClientesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cliente> clientes = new ArrayList<>();
        
        try (Connection conn = new ClienteDAO().conectar()) {
            String sql = "SELECT * FROM cliente";
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setCodigo(rs.getInt("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setEmail(rs.getString("email"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("gerenciarClientes.jsp").forward(request, response);
    }

    // Servlet para excluir o cliente
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            try (Connection conn = new ClienteDAO().conectar()) {
                String sql = "DELETE FROM cliente WHERE id = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, Integer.parseInt(id));
                    int linhasAfetadas = ps.executeUpdate();
                    
                    if (linhasAfetadas > 0) {
                        response.sendRedirect("listarClientes"); // Redireciona para a lista atualizada
                    } else {
                        response.sendRedirect("gerenciarClientes.jsp?erro=true");
                    }
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
