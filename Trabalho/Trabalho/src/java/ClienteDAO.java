import java.sql.*;

public class ClienteDAO {
    public Connection conectar() throws SQLException {
        DriverManager.registerDriver (new com.mysql.jdbc.Driver());
        String url = "jdbc:mysql://localhost:3306/clientebd?useSSL=false&serverTimezone=UTC";
        String usuario = "root";
        String senha = "admin";
        return DriverManager.getConnection(url, usuario, senha);
    }

    public boolean registrarCliente(Cliente c) {
        String sql = "INSERT INTO cliente (nome, email, senha) VALUES (?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNome());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getSenha());

            int linhasAfetadas = ps.executeUpdate();
            return linhasAfetadas > 0; // Retorna true se o registro foi bem-sucedido
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean verificarLogin(String email, String senha) {
    String sql = "SELECT * FROM cliente WHERE email = ? AND senha = ?";
    try (Connection conn = conectar();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, email);
        ps.setString(2, senha);

        try (ResultSet rs = ps.executeQuery()) {
            return rs.next(); // Retorna true se encontrar um registro correspondente
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    public Cliente verificarLoginRetornarCliente(String email, String senha) {
    String sql = "SELECT * FROM cliente WHERE email = ? AND senha = ?";
    try (Connection conn = conectar();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, email);
        ps.setString(2, senha);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodigo(rs.getInt("id")); // Supondo que o campo de ID seja 'id'
                cliente.setNome(rs.getString("nome")); // Correto: nome do banco para nome do objeto
                cliente.setEmail(rs.getString("email")); // Correto: email do banco para email do objeto
                cliente.setSenha(rs.getString("senha")); // Opcional: se precisar armazenar a senha
                return cliente;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

    

}