package br.com.thyago.ui;

import br.com.thyago.dao.PacienteDAO;
import br.com.thyago.model.Paciente;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final PacienteDAO dao = new PacienteDAO();

    public void iniciar() {

        int opcao;

        do {

            System.out.println("\n=====================================");
            System.out.println("      CADASTRO DE PACIENTES");
            System.out.println("=====================================");
            System.out.println("1 - Cadastrar paciente");
            System.out.println("2 - Listar pacientes");
            System.out.println("3 - Buscar paciente");
            System.out.println("4 - Atualizar paciente");
            System.out.println("5 - Excluir paciente");
            System.out.println("0 - Sair");

            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    cadastrarPaciente();
                    break;

                case 2:
                    listarPacientes();
                    break;

                case 3:
                    buscarPaciente();
                    break;

                case 4:
                    atualizarPaciente();
                    break;

                case 5:
                    excluirPaciente();
                    break;

                case 0:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private void cadastrarPaciente() {

        Paciente paciente = new Paciente();

        System.out.print("Nome: ");
        paciente.setNome(scanner.nextLine());

        System.out.print("CPF: ");
        paciente.setCpf(scanner.nextLine());

        System.out.print("Data de nascimento (AAAA-MM-DD): ");
        paciente.setDataNascimento(LocalDate.parse(scanner.nextLine()));

        System.out.print("Telefone: ");
        paciente.setTelefone(scanner.nextLine());

        System.out.print("Email: ");
        paciente.setEmail(scanner.nextLine());

        System.out.print("Endereço: ");
        paciente.setEndereco(scanner.nextLine());

        dao.cadastrar(paciente);
    }

    private void listarPacientes() {

        List<Paciente> pacientes = dao.listar();

        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }

        for (Paciente paciente : pacientes) {

            System.out.println("\n----------------------------");
            System.out.println("ID: " + paciente.getId());
            System.out.println("Nome: " + paciente.getNome());
            System.out.println("CPF: " + paciente.getCpf());
            System.out.println("Nascimento: " + paciente.getDataNascimento());
            System.out.println("Telefone: " + paciente.getTelefone());
            System.out.println("Email: " + paciente.getEmail());
            System.out.println("Endereço: " + paciente.getEndereco());

        }
    }

    private void buscarPaciente() {

        System.out.print("Digite o ID do paciente: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Paciente paciente = dao.buscarPorId(id);

        if (paciente == null) {

            System.out.println("Paciente não encontrado.");
            return;

        }

        System.out.println("\nPaciente encontrado:");
        System.out.println("ID: " + paciente.getId());
        System.out.println("Nome: " + paciente.getNome());
        System.out.println("CPF: " + paciente.getCpf());
        System.out.println("Nascimento: " + paciente.getDataNascimento());
        System.out.println("Telefone: " + paciente.getTelefone());
        System.out.println("Email: " + paciente.getEmail());
        System.out.println("Endereço: " + paciente.getEndereco());

    }

    private void atualizarPaciente() {

        System.out.print("Digite o ID do paciente: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Paciente paciente = dao.buscarPorId(id);

        if (paciente == null) {

            System.out.println("Paciente não encontrado.");
            return;

        }

        System.out.print("Novo nome: ");
        paciente.setNome(scanner.nextLine());

        System.out.print("Novo CPF: ");
        paciente.setCpf(scanner.nextLine());

        System.out.print("Nova data de nascimento (AAAA-MM-DD): ");
        paciente.setDataNascimento(LocalDate.parse(scanner.nextLine()));

        System.out.print("Novo telefone: ");
        paciente.setTelefone(scanner.nextLine());

        System.out.print("Novo email: ");
        paciente.setEmail(scanner.nextLine());

        System.out.print("Novo endereço: ");
        paciente.setEndereco(scanner.nextLine());

        dao.atualizar(paciente);
    }

    private void excluirPaciente() {

        System.out.print("Digite o ID do paciente: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        dao.excluir(id);
    }
}