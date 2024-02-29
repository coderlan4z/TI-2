import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/my_db", "postgres", "mypassword")) {
            ClasseXDAO dao = new ClasseXDAO(connection);
            Scanner scanner = new Scanner(System.in);
            int opcao;
            
            do {
                System.out.println("\n=== MENU ===");
                System.out.println("1. Listar");
                System.out.println("2. Inserir");
                System.out.println("3. Atualizar");
                System.out.println("4. Excluir");
                System.out.println("5. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                
                switch (opcao) {
                    case 1:
                        List<ClasseX> lista = dao.listar();
                        for (ClasseX x : lista) {
                            System.out.println(x.getId() + " | " + x.getNome() + " | " + x.getIdade() + " | " + x.getEmail());
                        }
                        break;
                    case 2:
                                                System.out.print("Nome: ");
                        String nome = scanner.next();
                        System.out.print("Idade: ");
                        int idade = scanner.nextInt();
                        System.out.print("Email: ");
                        String email = scanner.next();
                        dao.inserir(new ClasseX(0, nome, idade, email));
                        System.out.println("Registro inserido com sucesso!");
                        break;
                    case 3:
                        System.out.print("ID do registro a ser atualizado: ");
                        int idAtualizar = scanner.nextInt();
                        System.out.print("Novo nome: ");
                        nome = scanner.next();
                        System.out.print("Nova idade: ");
                        idade = scanner.nextInt();
                        System.out.print("Novo email: ");
                        email = scanner.next();
                        dao.atualizar(new ClasseX(idAtualizar, nome, idade, email));
                        System.out.println("Registro atualizado com sucesso!");
                        break;
                    case 4:
                        System.out.print("ID do registro a ser excluído: ");
                        int idExcluir = scanner.nextInt();
                        dao.excluir(idExcluir);
                        System.out.println("Registro excluído com sucesso!");
                        break;
                    case 5:
                        System.out.println("Encerrando...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } while (opcao != 5);
            
        } catch (SQLException e) {
            System.err.println("Erro de conexão com o banco de dados: " + e.getMessage());
        }
    }
}
