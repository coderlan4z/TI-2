import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClasseXDAO {
    private Connection connection;
    
    public ClasseXDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void inserir(ClasseX x) throws SQLException {
        String query = "INSERT INTO tabela_x (nome, idade, email) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, x.getNome());
            statement.setInt(2, x.getIdade());
            statement.setString(3, x.getEmail());
            statement.executeUpdate();
        }
    }
    
    public void atualizar(ClasseX x) throws SQLException {
        String query = "UPDATE tabela_x SET nome = ?, idade = ?, email = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, x.getNome());
            statement.setInt(2, x.getIdade());
            statement.setString(3, x.getEmail());
            statement.setInt(4, x.getId());
            statement.executeUpdate();
        }
    }
    
    public void excluir(int id) throws SQLException {
        String query = "DELETE FROM tabela_x WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
    
    public List<ClasseX> listar() throws SQLException {
        List<ClasseX> lista = new ArrayList<>();
        String query = "SELECT * FROM tabela_x";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ClasseX x = new ClasseX();
                x.setId(resultSet.getInt("id"));
                x.setNome(resultSet.getString("nome"));
                x.setIdade(resultSet.getInt("idade"));
                x.setEmail(resultSet.getString("email"));
                lista.add(x);
            }
        }
        return lista;
    }
}
