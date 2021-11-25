import java.io.Serializable;

public class Cliente implements Serializable{

	public String nome;
	public String CPF;
	private Pedido[] pedidos;
	private IFidelidade categoria;
	private int qtPedidos;

	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.CPF = cpf;
		this.pedidos = new Pedido[1_000];
		this.categoria = null;
		this.qtPedidos = 0;
	}

	public void addPedido(Pedido p) {
		this.pedidos[this.qtPedidos] = p;
		this.qtPedidos++;
		this.verificarCategoria();
	}

	private void verificarCategoria() {
		IFidelidade teste; // vou testar se ele pode subir de categoria.

		if (this.categoria == null) { // se não tenho fidelidade, posso passar para a primeira.
			teste = new Cliente10();
		} else { /// significa que já é Cliente10 ou 25
			teste = new Cliente25(); // se já estou na primeira, posso passar para segunda.
		}

		if (teste.desconto(this.pedidos) > 0) // se na categoria nova eu tenho desconto,
			this.categoria = teste; // mudo a minha categoria.
	}

	public double desconto() {
		if (this.categoria == null)
			return 0.0;
		else
			return this.categoria.desconto(this.pedidos);
	}

	@Override
	public String toString() {
		String aux = this.nome + "\n" + this.CPF;
		if (this.categoria != null)
			aux += "\n" + this.categoria.toString();
		return aux;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cpf) {
		CPF = cpf;
	}
}
