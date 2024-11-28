import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/RegistroClienteServlet")
public class RegistroClienteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Captura os parâmetros do formulário
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        // Cria um novo objeto Cliente
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setSenha(senha);

        // Interage com o DAO para salvar no banco
        ClienteDAO clienteDAO = new ClienteDAO();
        boolean sucesso = clienteDAO.registrarCliente(cliente);

        // Redireciona com base no resultado
        if (sucesso) {
            response.sendRedirect("registroSucesso.jsp");
        } else {
            response.sendRedirect("registroErro.jsp");
        }
    }
}