public class Pizza extends Comida{
  
    private static final double PRECO_BASE = 30.0; 
    private static final int MAX_ADICIONAIS = 10; 
    private boolean bordaRecheada;

    public Pizza(boolean bordaRecheada){
        super("Pizza ", PRECO_BASE);
        this.bordaRecheada = bordaRecheada;
    }   
    
    @Override
    protected int maxAdicionais(){
        return MAX_ADICIONAIS;
    }
  
    @Override
    public double precoTotal() {
        double multiplicador = 2.0;

        this.precoFinal = this.precoBase;
        for (Ingrediente ingrediente : adicionais) {
            if(ingrediente!=null)
                this.precoFinal += ingrediente.getPreco()*multiplicador;
        }
                
        if(this.bordaRecheada)
            this.precoFinal += 7.50;

        return this.precoFinal;
	}

    @Override
    public String toString(){
        
        String desc = super.toString();
        if(this.bordaRecheada)
            desc +=" e uma super borda de nutella!!! ";
        else
            desc +=". ";
        
        desc+= "-  R$"+this.precoTotal();
        
        return desc;
    }


}  

