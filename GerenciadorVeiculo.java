import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class GerenciadorVeiculo{
	Scanner scanner;
	DaoVeiculo daoVeiculo;
	
	public GerenciadorVeiculo(){
		scanner = new Scanner(System.in);
		daoVeiculo = new DaoVeiculo();
	}
	
	public void menu(){
		int opcao = -1;
		
		while(opcao != 0){
			System.out.println("\n--------------------------------------------------------------");
			System.out.println("GERENCIAMENTO DE VEICULOS");
			System.out.println("[1] Cadastrar");
			System.out.println("[2] Consultar");
			System.out.println("[3] Alterar");
			System.out.println("[4] Excluir");
			System.out.println("[5] Listar todos");
			System.out.println("[0] Voltar ao menu anterior");
			System.out.println("--------------------------------------------------------------\n");
			try{
				opcao = Integer.parseInt(scanner.nextLine());	
			} catch (Exception e) {
				System.out.println("Erro... informe um numero inteiro");
			}			

			if(opcao == 1){
				cadastrar();
			}else if(opcao == 2){
				//consultar();			
			}else if(opcao == 3){
				//alterar();
			}else if(opcao == 4){
				excluir();
			}else if(opcao == 5){
				listarTodos();
			}else if(opcao != 0){
				System.out.println("Opcao invalida!");
			}
		}
	}
	
	public void cadastrar(){
		Veiculo veiculo = new Veiculo();
		
		System.out.println("\n--------------------------------------------------------------");
		System.out.println("[Cadastro de Veiculos]");
		System.out.println("Marca: ");
		veiculo.setMarca(scanner.nextLine());
		System.out.println("Modelo: ");
		veiculo.setModelo(scanner.nextLine());
		System.out.println("Chassi: ");
		veiculo.setChassi(scanner.nextLine());
		System.out.println("Ano: ");
		veiculo.setAno(Integer.parseInt(scanner.nextLine()));
		
		boolean inserido = daoVeiculo.inserir(veiculo);//passa o objeto para a camada model
		if(inserido){
			System.out.println("Inserido com sucesso.");
		}
	}

	private void listarTodos(){
		ArrayList<Veiculo> resultado = daoVeiculo.buscarTodos();
		System.out.println("\n--------------------------------------------------------------");
		System.out.println("[Veiculos cadastrados]");
		Iterator<Veiculo> itVeic = resultado.iterator();
		while(itVeic.hasNext()){
			Veiculo v = itVeic.next();
			System.out.println("Modelo: " + v.getModelo()
				+ ", Marca: " + v.getMarca()
				+ ", Chassi: " + v.getChassi()
				+ ", Ano: " + v.getAno()
				+ ", Codigo: " + v.getCodigo());
		}
	}
	
	public void excluir(){
		System.out.println("\n--------------------------------------------------------------");
		System.out.println("[Exclusao de Veiculos]");
		System.out.println("Codigo: ");
		int codigo = Integer.parseInt(scanner.nextLine());
		int qtde = daoVeiculo.excluir(codigo);

		if(qtde > 0){
			System.out.println("Excluido com sucesso.");
		}else{
			System.out.println("Nao encontrado...");
		}
	}	
}



