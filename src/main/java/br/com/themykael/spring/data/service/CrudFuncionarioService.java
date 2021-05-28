package br.com.themykael.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.themykael.spring.data.orm.Cargo;
import br.com.themykael.spring.data.orm.Funcionario;
import br.com.themykael.spring.data.orm.UnidadeTrabalho;
import br.com.themykael.spring.data.repository.CargoRepository;
import br.com.themykael.spring.data.repository.FuncionarioRepository;
import br.com.themykael.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService {

	private Boolean system = true;
	private final DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private final CargoRepository cargoRepository;
	private final FuncionarioRepository funcionarioRepository;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

	// Atributos apenas de utilidade da classe Service
	private String nome;
	private String cpf;
	private BigDecimal salario;
	private LocalDate dataContratacao;
	private Optional<Cargo> cargo;

	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository,
			UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.funcionarioRepository = funcionarioRepository;
		this.cargoRepository = cargoRepository;
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}

	public void iniciar(Scanner sc) {

		while (system) {
			System.out.println("Qual ação de funcionário você deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar funcionario");
			System.out.println("2 - Atualizar nome do funcionario");
			System.out.println("3 - Atualizar cpf do funcionario");
			System.out.println("4 - Atualizar salário do funcionario");
			System.out.println("5 - Atualizar cargo do funcionario");
			System.out.println("6 - Visualizar funcionario");
			System.out.println("7 - Deletar funcionario");

			int action = sc.nextInt();

			switch (action) {
			case 1:
				salvar(sc);
				break;
			case 2:
				atualizarNome(sc);
				break;
			case 3:
				atualizarCpf(sc);
				break;
			case 4:
				atualizarSalario(sc);
				break;
			case 5:
				atualizarCargo(sc);
				break;
			case 6:
				visualizar(sc);
				break;
			case 7:
				deletar(sc);
				break;
			default:
				system = false;
				break;
			}
		}

	}

	private void salvar(Scanner sc) {
		System.out.println("Nome do funcionario:");
		nome = sc.next();
		System.out.println("Cpf do funcionario:");
		cpf = sc.next();
		System.out.println("Data de contratação do funcionário:");
		String dataContratacao = sc.next();
		System.out.println("Salário do funcionario:");
		String salarioString = sc.next();
		salario = new BigDecimal(salarioString);
		System.out.println("ID do cargo do funcionário:");
		Integer cargoId = sc.nextInt();

		List<UnidadeTrabalho> unidades = unidade(sc);

		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatador));
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());
		funcionario.setUnidadeTrabalhos(unidades);

		funcionarioRepository.save(funcionario);
		System.out.println("Salvo com sucesso");
	}

	private List<UnidadeTrabalho> unidade(Scanner sc) {
		Boolean isTrue = true;
		List<UnidadeTrabalho> unidades = new ArrayList<>();

		while (isTrue) {
			System.out.println("Digite o ID da unidade. (Para sair digite 0)");
			Integer unidadeId = sc.nextInt();

			if (unidadeId != 0) {
				Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
				unidades.add(unidade.get());
			} else {
				isTrue = false;
			}
		}

		return unidades;
	}

	private void atualizarNome(Scanner sc) {
		System.out.println("Informe o Id:");
		int id = sc.nextInt();

		System.out.println("Novo nome do funcionario:");
		nome = sc.next();

		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(dataContratacao);
		funcionario.setCargo(cargo.get());

		funcionarioRepository.save(funcionario);
		System.out.println("Alterado com sucesso");
	}

	private void atualizarCpf(Scanner sc) {
		System.out.println("Informe o Id:");
		int id = sc.nextInt();

		System.out.println("Novo cpf do funcionario:");
		cpf = sc.next();

		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(dataContratacao);
		funcionario.setCargo(cargo.get());

		funcionarioRepository.save(funcionario);
		System.out.println("Alterado com sucesso");
	}

	private void atualizarSalario(Scanner sc) {
		System.out.println("Informe o Id:");
		int id = sc.nextInt();

		System.out.println("Novo salário do funcionario:");
		String salarioString = sc.next();
		salario = new BigDecimal(salarioString);

		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(dataContratacao);
		funcionario.setCargo(cargo.get());

		funcionarioRepository.save(funcionario);
		System.out.println("Alterado com sucesso");
	}
	
	private void atualizarCargo(Scanner sc) {
		System.out.println("Informe o Id:");
		int id = sc.nextInt();

		System.out.println("Novo ID de cargo do funcionario:");
		Integer cargoId = sc.nextInt();
		cargo = cargoRepository.findById(cargoId);
		
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(dataContratacao);
		funcionario.setCargo(cargo.get());

		funcionarioRepository.save(funcionario);
		System.out.println("Alterado com sucesso");
	}

	private void visualizar(Scanner sc) {
		System.out.println("Qual página você deseja visualizar?");
		Integer page = sc.nextInt();

		Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "nome"));
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
		
		System.out.println(funcionarios);
		System.out.println("Página atual: " + funcionarios.getNumber());
		System.out.println("Total de elementos na consulta: " + funcionarios.getTotalElements());
		funcionarios.forEach(funcionario -> System.out.println(funcionario));
	}

	private void deletar(Scanner sc) {
		System.out.println("Informe o Id:");
		int id = sc.nextInt();
		funcionarioRepository.deleteById(id);
		System.out.println("Deletado com sucesso");
	}

}
