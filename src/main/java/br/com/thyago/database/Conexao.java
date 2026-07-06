package br.com.thyago.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/postgres";

    private static final String USER = "postgres";

    private static final String PASSWORD = "sua_senha";

    public static Connection conectar() {

        try {

            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {

            throw new RuntimeException("Erro ao conectar ao banco de dados.", e);

        }

    }


}
