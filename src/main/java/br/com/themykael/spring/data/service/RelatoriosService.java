package br.com.themykael.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.themykael.spring.data.orm.Funcionario;
import br.com.themykael.spring.data.orm.FuncionarioProjecao;
import br.com.themykael.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private Boolean system = true;
	private final FuncionarioRepository funcionarioRepository;
	private final DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void iniciar(Scanner sc) {
		while (system) {
			System.out.println("Qual ação de relatório você deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Buscar funcionário pelo nome");
			System.out.println("2 - Buscar funcionário pelo nome, data de contratação e salário maior");
			System.out.println("3 - Buscar funcionário pela data de contratação");
			System.out.println("4 - Pesquisar funcionário pelo nome e salário");

			int action = sc.nextInt();

			switch (action) {
			case 1:
				buscaFuncionarioNome(sc);
				break;
			case 2:
				buscaFuncionarioPeloNomeSalarioMaiorData(sc);
				break;
			case 3:
				buscaFuncionarioDataContratacao(sc);
				break;
			case 4:
				pesquisaFuncionarioSalario();
				break;
			default:
				system = false;
				break;
			}
		}

	}

	private void buscaFuncionarioNome(Scanner sc) {
		System.out.println("Qual nome deseja pesquisar?");
		String nome = sc.next();
		List<Funcionario> lista = funcionarioRepository.findByNome(nome);
		lista.forEach(System.out::println);
	}

	private void buscaFuncionarioPeloNomeSalarioMaiorData(Scanner sc) {
		System.out.println("Qual nome deseja pesquisar?");
		String nome = sc.next();

		System.out.println("Qual data de contratação deseja pesquisar?");
		String data = sc.next();
		LocalDate localDate = LocalDate.parse(data, formatador);

		System.out.println("Qual salário deseja pesquisar?");
		String salarioString = sc.next();
		BigDecimal salario = new BigDecimal(salarioString);

		List<Funcionario> lista = funcionarioRepository.findByNomeSalarioMaiorEDataContratacao(nome, salario,
				localDate);
		lista.forEach(System.out::println);
	}
	
	private void buscaFuncionarioDataContratacao(Scanner sc) {
		System.out.println("Qual data de contratação deseja pesquisar?");
		String data = sc.next();
		LocalDate localDate = LocalDate.parse(data, formatador);
		
		List<Funcionario> lista = funcionarioRepository.findDataContratacaoMaior(localDate);
		lista.forEach(System.out::println);
	}
	
	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> lista = funcionarioRepository.findFuncionarioSalario();
		lista.forEach(funcionario -> System.out.println("Funcionario de id: " + funcionario.getId()
		+ " | Nome: " + funcionario.getNome() + " | Salário: " + funcionario.getSalario()));
	}

}
