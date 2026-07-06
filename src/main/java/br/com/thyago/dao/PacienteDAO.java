package br.com.thyago.dao;

import br.com.thyago.database.Conexao;
import br.com.thyago.model.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public void cadastrar(Paciente paciente) {

        String sql = """
                INSERT INTO pacientes
                (nome, cpf, data_nascimento, telefone, email, endereco)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setDate(3, java.sql.Date.valueOf(paciente.getDataNascimento()));
            stmt.setString(4, paciente.getTelefone());
            stmt.setString(5, paciente.getEmail());
            stmt.setString(6, paciente.getEndereco());

            stmt.executeUpdate();

            System.out.println("Paciente cadastrado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Paciente> listar() {

        List<Paciente> pacientes = new ArrayList<>();

        String sql = "SELECT * FROM pacientes";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Paciente paciente = new Paciente();

                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setEmail(rs.getString("email"));
                paciente.setEndereco(rs.getString("endereco"));

                pacientes.add(paciente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pacientes;
    }

    public Paciente buscarPorId(int id) {

        String sql = "SELECT * FROM pacientes WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Paciente paciente = new Paciente();

                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setEmail(rs.getString("email"));
                paciente.setEndereco(rs.getString("endereco"));

                return paciente;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void atualizar(Paciente paciente) {

        String sql = """
                UPDATE pacientes
                SET nome = ?,
                    cpf = ?,
                    data_nascimento = ?,
                    telefone = ?,
                    email = ?,
                    endereco = ?
                WHERE id = ?
                """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setDate(3, java.sql.Date.valueOf(paciente.getDataNascimento()));
            stmt.setString(4, paciente.getTelefone());
            stmt.setString(5, paciente.getEmail());
            stmt.setString(6, paciente.getEndereco());
            stmt.setInt(7, paciente.getId());

            stmt.executeUpdate();

            System.out.println("Paciente atualizado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void excluir(int id) {

        String sql = "DELETE FROM pacientes WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Paciente excluído com sucesso!");
            } else {
                System.out.println("Paciente não encontrado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}