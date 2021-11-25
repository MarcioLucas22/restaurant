public class Cliente25 implements IFidelidade{

    Data hoje = new Data(24,9,2021); //fake, mock, teste

    @Override
    public double desconto(Pedido[] pedidos) {
        double desconto = 0.0;
        double valorPedidos=0.0;

        for (Pedido pedido : pedidos) {
            if(pedido!=null){
                Data aux = pedido.dataPedido.acrescentaDias(31);
                if(!hoje.maisRecente(aux)){
                    valorPedidos += pedido.valorTotal();
                }
            }
        }

        if(valorPedidos>=200.00)
            desconto = 0.25;
        
        return desconto;
    }    

    @Override
    public String toString(){
        return "Cliente com 10% de desconto";
    }
}
