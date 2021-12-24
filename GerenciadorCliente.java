import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class GerenciadorCliente{
    Scanner scanner;
    DaoCliente daoCliente;

    public GerenciadorCliente() {
        scanner = new Scanner(System.in);
        daoCliente = new DaoCliente();
    }

    public void menu(){
		int opcao = -1;
		
		while(opcao != 0){
			System.out.println("\n--------------------------------------------------------------");
			System.out.println("GERENCIAMENTO DE CLIENTES");
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
		Cliente cliente = new Cliente();
		
		System.out.println("\n--------------------------------------------------------------");
		System.out.println("[Cadastro de Clientes]");
		System.out.println("Nome: ");
		cliente.setNome(scanner.nextLine());
		System.out.println("Idade: ");
		cliente.setIdade(Integer.parseInt(scanner.nextLine()));
		System.out.println("CPF: ");
		cliente.setCPF(scanner.nextLine());
		System.out.println("Telefone: ");
		cliente.setTelefone(scanner.nextLine());
        System.out.println("Email: ");
		cliente.setEmail(scanner.nextLine());
		System.out.println("Numero do cartao exclusivo: ");
        cliente.setCartaoExclusivo(Integer.parseInt(scanner.nextLine()));

		boolean inserido = daoCliente.inserir(cliente);//passa o objeto para a camada model
		if(inserido){
			System.out.println("Inserido com sucesso.");
		}
	}

	private void listarTodos(){
		ArrayList<Cliente> resultado = daoCliente.buscarTodos();
		System.out.println("\n--------------------------------------------------------------");
		System.out.println("[Clientes cadastrados]");
		Iterator<Cliente> itCliente = resultado.iterator();
		while(itCliente.hasNext()){
			Cliente c = itCliente.next();
			System.out.println("Nome: " + c.getNome()
				+ ", Idade: " + c.getIdade()
				+ ", CPF: " + c.getCPF()
				+ ", Telefone: " + c.getTelefone()
				+ ", Codigo: " + c.getCodigo()
                +"Cartão exclusivo: "+ c.getCartaoExclusivo()
                +"Email: "+c.getEmail());
		}
	}
	
	public void excluir(){
		System.out.println("\n--------------------------------------------------------------");
		System.out.println("[Exclusao de Clientes]");
		System.out.println("Codigo: ");
		int codigo = Integer.parseInt(scanner.nextLine());
		int qtde = daoCliente.excluir(codigo);

		if(qtde > 0){
			System.out.println("Excluido com sucesso.");
		}else{
			System.out.println("Nao encontrado...");
		}
	}	
}