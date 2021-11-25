
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class App {

//	public static void dadosClientes(ArrayList<Pedido> pedidos) {
//		pedidos.stream().forEach(pedido -> System.out.println(pedido + "\n"));
//	}
//	
//	public static void valorTotalPedidos(ArrayList<Pedido> pedidos) {
//		Stream<Pedido> dados = pedidos.stream();
//		double aux = 0;
//		
//		dados.forEach(pedido -> System.out.println("Valor Total Pedidos: " + pedido.valorTotal()));
//	}
	
	public static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static int menu(Scanner teclado) {
		limparTela();
		System.out.println("XULAMBS DELIVERY");
		System.out.println("==========================");
		System.out.println("1 - Novo pedido");
		System.out.println("2 - Incluir comida em pedido");
		System.out.println("3 - Detalhes do pedido");
		System.out.println("4 - Fechar pedido");
		System.out.println("5 - Cadastrar cliente");
		System.out.println("6 - Contabilidade");
		System.out.println("0 - Sair");

		int opcao = teclado.nextInt();
		teclado.nextLine();
		return opcao;
	}

	static void pausa(Scanner teclado) {
		System.out.println("Enter para continuar.");
		teclado.nextLine();
	}

	static Comida criarComida(Scanner teclado) {
			System.out.print("Incluir no pedido(1-Pizza 2-Sanduíche): ");
			int tipo = Integer.parseInt(teclado.nextLine());
			Comida nova;
			switch (tipo) {
			case 1:
				nova = new Pizza(false);
				break;
			case 2:
				nova = new Sanduiche(false);
				break;
			default:
				nova = null;
				break;
			}
			if (nova != null) {

				System.out.print("Quantos adicionais: ");
				int quantos = Integer.parseInt(teclado.nextLine());
				for (int ad = 0; ad < quantos; ad++) {
					System.out.print("1 - Bacon || 2 - Palmito || 3 - Cheddar ");
					int opcAd = Integer.parseInt(teclado.nextLine());
					Ingrediente adicional = null;
					switch (opcAd) {
					case 1:
						adicional = Ingrediente.BACON;
						break;
					case 2:
						adicional = Ingrediente.PALMITO;
						break;
					case 3:
						adicional = Ingrediente.CHEDDAR;
						break;
					}

					nova.addIngrediente(adicional);

				}

				System.out.println();
			}
			return nova;
		
	}

	static void criarNovo(Pedido p, Cliente c) {
		p = new Pedido(c);
		System.out.print("Novo pedido criado. ");
	}

	public static void main(String[] args) throws Exception {
		
		Scanner teclado = new Scanner(System.in);
		Pedido pedido = null;
		Cliente cliente = new Cliente("Ada Lovelace", "123456-78");
		Map<String, Cliente> clientes = new HashMap<String, Cliente>();
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		int opcao = -1;

		do {
			opcao = menu(teclado);
			limparTela();
			switch (opcao) {
			case 1:
				System.out.println("Digite seu CPF: ");
				String cpf = teclado.nextLine();

				if (clientes.containsKey(cpf)) {
					System.out.println("Cliente encontrado.\n");
					pedido = new Pedido(clientes.get(cpf));
					pedidos.add(pedido);
					System.out.print("Novo pedido criado. ");
					pausa(teclado);
					break;
				} else {
					System.out.println("Cliente n�o encontrado. � necess�rio realizar um cadastro.");
					pausa(teclado);
					break;
				}
				

			case 2:
				if (pedido != null) {
					Comida aux = criarComida(teclado);
					if (aux != null) {
						if (pedido.addComida(aux))
							System.out.println("Adicionado: " + aux);
						else
							System.out.println("Não foi possível adicionar.");
					} else
						System.out.print("Inválido. Favor tentar novamente. ");
				} else
					System.out.print("Pedido ainda não foi aberto. ");
				pausa(teclado);
				break;
			case 3:
				for (int i = 0; i < pedidos.size(); i++) {
					if (pedido != null) {
						//dadosClientes(pedidos);
						System.out.println(pedidos);
						//pausa(teclado);
						break;
					} else
						System.out.print("Pedido ainda não foi aberto. ");
					pausa(teclado);
				}
				
				break;
			case 4:
				double aux = 0;
				double desconto = 0;
				if (pedido != null) {

					if (pedido.fecharPedido()) {

						System.out.println(pedidos);

						for (int i = 0; i < pedidos.size(); i++) {
							aux += pedidos.get(i).valorTotal();
						}
						for (int j = 0; j < clientes.size(); j++) {
							desconto = cliente.desconto() * aux;
						}

						double total = aux - desconto;

						System.out.println("Desconto da cliente: R$ " + desconto);
						System.out.println("A pagar: R$ " + total);
						//valorTotalPedidos(pedidos);
											

					} else
						System.out.println("Pedido não foi fechado. Inclua comidas ou crie novo pedido.");

				} else
					System.out.print("Pedido ainda não foi aberto. ");
				pausa(teclado);
				break;
			case 5:
				System.out.println("Digite o nome do cliente:");
				String nomeCliente = teclado.nextLine();
				System.out.println("Digite o CPF:");
				String cpfCliente = teclado.nextLine();
				Cliente cliente2 = new Cliente(nomeCliente, cpfCliente);
				clientes.put(cpfCliente, cliente2);
				break;
			case 6: double aux2 = 0;
				for(int i = 0; i < pedidos.size(); i++) {
					pedidos.get(i);
					aux2 += Pedido.valorTotalVendido;
				}
				System.out.println("Total vendido: " + aux2);
				pausa(teclado);
				break;
			}

		} while (opcao != 0);

		System.out.println("FIM");
		teclado.close();

		try {
			fos = new FileOutputStream("arquivo.dat");
			oos = new ObjectOutputStream(fos);
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo n�o encontrado.");
		} catch (IOException e) {
			System.out.println("Erro ao criar arquivo.");
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					System.out.println("Erro ao fechar arquivo.");
				}
			}
		}
	}
}
