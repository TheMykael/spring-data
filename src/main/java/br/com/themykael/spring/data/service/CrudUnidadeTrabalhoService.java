package br.com.themykael.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.themykael.spring.data.orm.UnidadeTrabalho;
import br.com.themykael.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {
	
	private Boolean system = true;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	
	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}
	
	public void iniciar(Scanner sc) {
		while (system) {
			System.out.println("Qual ação de unidade de trabalho você deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar unidade de trabalho");
			System.out.println("2 - Atualizar unidade de trabalho");
			System.out.println("3 - Visualizar unidade de trabalho");
			System.out.println("4 - Deletar unidade de trabalho");
			
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
		System.out.println("Nome da unidade de trabalho:");
		String nome = sc.next();
		System.out.println("Endereço da unidade de trabalho:");
		String endereco = sc.next();
		
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setDescricao(nome);
		unidadeTrabalho.setEndereco(endereco);
		
		unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("Salvo com sucesso");
	}
	
	private void atualizar(Scanner sc) {
		System.out.println("Informe o Id:");
		Integer id = sc.nextInt();
		System.out.println("Insira o novo nome da unidade de trabalho:");
		String nome = sc.next();
		System.out.println("Insira o novo endereço da unidade de trabalho:");
		String endereco = sc.next();
		
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setId(id);
		unidadeTrabalho.setDescricao(nome);
		unidadeTrabalho.setEndereco(endereco);
		
		unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("Atualizado com sucesso");
	}
	
	private void visualizar() {
		Iterable<UnidadeTrabalho> unidadesTrabalho = unidadeTrabalhoRepository.findAll();
		unidadesTrabalho.forEach(unidadeTrabalho -> System.out.println(unidadeTrabalho));
	}
	
	private void deletar(Scanner sc) {
		System.out.println("Informe o Id:");
		int id = sc.nextInt();
		unidadeTrabalhoRepository.deleteById(id);
		System.out.println("Deletado com sucesso");
	}
	
}
