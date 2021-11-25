import java.io.Serializable;

public class Pedido implements Serializable {
    
    private static final Data HOJE;
    private static final int MAX_COMIDAS;
    protected static double valorTotalVendido;
    private static int ultId;
    
    private Comida[] comidas;
    private int qtComidas;
    private boolean fechado;
    private double precoTotal;
    private int idPedido;
    Data dataPedido;
    private Cliente cliente;
    public int cont = 0;

    static{
        MAX_COMIDAS = 10;
        ultId=0;
        valorTotalVendido=0.0;
        HOJE = new Data(6,9,2021);
    }
    
    public static String valorTotalVendido(){
        return "R$ "+String.format("%.2f", valorTotalVendido);
    }

    public Pedido(Cliente cliente){
        this.comidas = new Comida[MAX_COMIDAS];
        this.qtComidas = 0;
        this.fechado = false;
        this.precoTotal = 0.0;
        this.cliente = cliente;
    }

    public int quantComidas(){
        return this.qtComidas;    
    }

    /**
     * 
     * @return
     */
    public double valorTotal(){
        double valorAux=0.0;

        if(this.fechado) 
            valorAux = this.precoTotal;
        else{
            for(int i=0; i<this.quantComidas(); i++){
                valorAux += comidas[i].precoTotal();
            }
        }
        return valorAux;
    }

    public boolean fecharPedido(){
    	
        if(this.quantComidas() > 0){
            if(!this.fechado){
                this.precoTotal = this.valorTotal();
                valorTotalVendido+=this.precoTotal;
                this.idPedido = ++ultId;
                this.fechado = true;
                this.dataPedido = HOJE;
            }
        
    	}
        return this.fechado;
    }

    public boolean addComida(Comida nova){
        boolean adicionou = false;
        if(!this.fechado){
            if(this.quantComidas()<MAX_COMIDAS){
                comidas[this.quantComidas()] = nova;
                this.qtComidas++;
                adicionou = true;
            }
        }
        return adicionou;
    }

    @Override
    public String toString(){
        //String inicio = (this.fechado)?"Pedido concluído Nº "+this.idPedido:"Pedido em aberto. ";
        
        StringBuilder builder = new StringBuilder(/*inicio+*/"\n");

        for(int i=0; i<this.quantComidas();i++){
            builder.append(String.format("%02d", (i+1))+" - ");
            builder.append(comidas[i].toString()+"\n");
        }
        builder.append("\nCliente: " + this.cliente.getNome() + "\nCPF: " + this.cliente.getCPF() + "\n");
        builder.append("-----------------------------");
        builder.append("Total: R$"+this.valorTotal() + "\n");

        return builder.toString();
    }

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}

/**
 * Com a evolução do sistema, um pedido pode conter
até 10 comidas diferentes.
 O relatório de um pedido deve mostrar a descrição e
o valor de cada um dos produtos, detalhadamente, e
o valor total do pedido.
 */
