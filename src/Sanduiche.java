public class Sanduiche extends Comida{

    private static final double PRECO_BASE = 12.0; 
    private static final int MAX_ADICIONAIS = 7; 
    private boolean dobroDeCarne;
    private String descricao;

    public Sanduiche(boolean dobroDeCarne){
        super("Sanduiche ", PRECO_BASE);
        this.dobroDeCarne = dobroDeCarne;   
        if(this.dobroDeCarne) this.descricao = " e o dobro de carne!!! ";
        else this.descricao = ". ";
    }    

    @Override
    protected int maxAdicionais(){
        return MAX_ADICIONAIS;
    }

    @Override
    public double precoTotal() {

        this.precoFinal = this.precoBase;
    
        for (Ingrediente ingrediente : adicionais) {
            if(ingrediente!=null)
                this.precoFinal += ingrediente.getPreco();
        }
        
        if(this.dobroDeCarne) 
            this.precoFinal+=5.0;

        return this.precoFinal;
	}

    @Override
    
    public String toString(){
        return super.toString()+this.descricao+"- R$ "+this.precoTotal();

    }


}
