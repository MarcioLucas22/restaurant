public abstract class Comida {

    protected String descricao;
	protected Ingrediente[] adicionais;
    protected int qtAdicionais;
	protected double precoBase;
    protected double precoFinal;
    
	protected Comida(String desc, double preco){
        this.setDescricao(desc);
        this.precoBase = preco;
        this.adicionais = new Ingrediente[this.maxAdicionais()];
    }

	protected void setDescricao(String qual) { //Inicia a string com pizza ou sanduiche
	    this.descricao = qual+"do Xulambs Delivery ";
	}

    @Override
    public String toString(){
         String aux =  this.descricao;
         for (Ingrediente ingrediente : adicionais) {
            if(ingrediente!=null){
                aux += "\n\t"+ingrediente.toString();
            }
         }
         return aux;
    }

    @Override
    public boolean equals(Object obj){
        return (this.toString().equals(obj.toString()));
    }

	public int getQtAdicionais() {
		return qtAdicionais;
	}

	public boolean addIngrediente(Ingrediente adicional) {
    
        int limite = this.maxAdicionais();

        if(qtAdicionais<limite){
            this.adicionais[qtAdicionais] = adicional;
            this.qtAdicionais++;
            return true;
        }
        else{
            return false;
        }
	}

    public boolean retIngrediente() {
    
        if(qtAdicionais>0){
            this.qtAdicionais--;
            return true;
        }
        else{
            return false;
        }
	}
	
	public abstract double precoTotal();
    protected abstract int maxAdicionais();        

    
	
}
