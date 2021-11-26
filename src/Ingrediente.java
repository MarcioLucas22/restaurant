import java.io.Serializable;

public enum Ingrediente implements Serializable{
    BACON("Bacon", 2.49),
    CHEDDAR("Cheddar", 2.19),
    PALMITO("Palmito", 3.99),
    CALABRESA("Calabresa", 1.99);

    private String desc;
    private double preco;

    private Ingrediente(String d, double p){
        this.desc = d;
        this.preco = p;
    }

    @Override
    public String toString(){
        return this.desc + " - R$ "+this.preco;
    }

    public double getPreco(){
        return this.preco;
    }
}
