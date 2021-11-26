import java.io.Serializable;

interface IFidelidade implements Serializable{
    double desconto(Pedido[] meusPedidos); 
}

