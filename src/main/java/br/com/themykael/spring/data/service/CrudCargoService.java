package br.com.themykael.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.themykael.spring.data.orm.Cargo;
import br.com.themykael.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {
	
	private Boolean system = true;
	private final CargoRepository cargoRepository;
	
	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}
	
	public void iniciar(Scanner sc) {
		while (system) {
			System.out.println("Qual ação de cargo você deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar cargo");
			System.out.println("2 - Atualizar cargo");
			System.out.println("3 - Visualizar cargos");
			System.out.println("4 - Deletar cargo");
			
			int action = sc.nextInt();
			
			switch (action) {
				case 1:
					salvar(sc);
					break;
				case 2:
					atualizar(sc);
					break;
				case 3:
					visualizar();
					break;
				case 4:
					deletar(sc);
					break;
				default:
					system = false;
					break;
			}
		}
		
	}
	
	private void salvar(Scanner sc) {
		System.out.println("Descrição do cargo:");
		String descricao = sc.next();
		
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Salvo com sucesso");
	}
	
	private void atualizar(Scanner sc) {
		System.out.println("Informe o Id:");
		int id = sc.nextInt();
		System.out.println("Insira a nova descrição do cargo:");
		String descricao = sc.next();
		
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Atualizado com sucesso");
	}
	
	private void visualizar() {
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
	}
	
	private void deletar(Scanner sc) {
		System.out.println("Informe o Id:");
		int id = sc.nextInt();
		cargoRepository.deleteById(id);
		System.out.println("Deletado com sucesso");
	}
	
}
