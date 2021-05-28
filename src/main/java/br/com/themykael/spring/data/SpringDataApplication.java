package br.com.themykael.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.themykael.spring.data.service.CrudUnidadeTrabalhoService;
import br.com.themykael.spring.data.service.RelatorioFuncionarioDinamico;
import br.com.themykael.spring.data.service.CrudCargoService;
import br.com.themykael.spring.data.service.CrudFuncionarioService;
import br.com.themykael.spring.data.service.RelatoriosService;

@EnableJpaRepositories
@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final RelatoriosService relatoriosService;
	private final CrudUnidadeTrabalhoService unidadeTrabalhoService;
	private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;
	private Boolean system = true;

	public SpringDataApplication(CrudCargoService cargoService,
			CrudFuncionarioService funcionarioService,
			RelatoriosService relatoriosService,
			CrudUnidadeTrabalhoService unidadeTrabalhoService,
			RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.relatoriosService = relatoriosService;
		this.unidadeTrabalhoService = unidadeTrabalhoService;
		this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);

		while (system) {
			System.out.println("Qual função você deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionário");
			System.out.println("3 - Unidade");
			System.out.println("4 - Relatórios");
			System.out.println("5 - Relatório dinâmico");

			int action = sc.nextInt();

			switch (action) {
			case 0:
				System.out.println("Finalizando programa...");
				system = false;
				break;
			case 1:
				cargoService.iniciar(sc);
				break;
			case 2:
				funcionarioService.iniciar(sc);
				break;
			case 3:
				unidadeTrabalhoService.iniciar(sc);
				break;
			case 4:
				relatoriosService.iniciar(sc);
				break;
			case 5:
				relatorioFuncionarioDinamico.iniciar(sc);
				break;
			default:
				System.err.println("Comando inválido");
				system = false;
				break;
			}
		}

		System.out.println("Programa finalizado");
		System.exit(0);
	}

}
