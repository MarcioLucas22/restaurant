/** 
 * MIT License
 *
 * Copyright(c) 2021 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.io.Serializable;

public class Data implements Serializable{
        
    //constante: dias de cada mês (para verificação de validade)
    private static final int[] DIASDOMES = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    
    //atributos 
    private int dia, mes, ano;
    
	private void init(int dia, int mes, int ano){
		this.dia = dia;
        this.mes = mes;
        this.ano = ano;
	}
    
	/**
     * Construtor para iniciar com valor fixo
     */
    public Data(){
		this.init(1,1,2000);
    }

	/**
     * Construtor para iniciar com valor personalizado
     */
    public Data(int dia, int mes, int ano){
        this.init(dia, mes, ano);
        if (!this.dataValida())     //se a data não é válida... (método da própria classe)
            init(1,1,2000);
    }
    
            
    
    /**
    * Método para verificação de ano bissexto
    * Para regras do ano bissexto:
    * http://educacao.uol.com.br/disciplinas/matematica/ano-bissexto-eles-se-repetem-a-cada-4-anos.htm
    * http://www.sogeografia.com.br/Curiosidades/?pg=4
    */
    private boolean anoBissexto(){
    
        if(this.ano%100==0) 
              return ((this.ano%400)==0);         //ano divisível por 400 --> bissexto
        else 
              return((this.ano%4)==0);              
    }

    /**
    * Verifica se uma data é válida
    */
    private boolean dataValida(){
        boolean resposta = true;       
		if(this.dia < 1)
				resposta = false;
		else{
			if (this.mes < 1 || this.mes > 12)                           
				   resposta = false;
		   else { 
		      if (this.anoBissexto()) 
					DIASDOMES[2] = 29;
			  if (this.dia > DIASDOMES[this.mes])    //verificamos com o vetor estático de dias de cada mês            
					resposta = false;
					   
			  DIASDOMES[2] = 28;
					
			}
		}
        return resposta;    //retorna a resposta obtida
    }

    /**
    * Acrescenta dias à data, retornando nova data sem modificar a atual;
    */
    public Data acrescentaDias(int quant){
        Data aux = new Data(this.dia, this.mes, this.ano);

        aux.dia += quant;      //acrescenta a quantidade ao dia atual e, em seguida, devemos ajustar mês e ano

        if (aux.anoBissexto()) 
			DIASDOMES[2] = 29; //se o ano é bissexto, altera fevereiro para 29 dias

        while (aux.dia > DIASDOMES[aux.mes]){     //enquanto os dias ultrapassam o limite de dias do mês atual... ajustar

            aux.dia = aux.dia - DIASDOMES[aux.mes]; // desconta a quantidade de dias do mês       
            aux.mes++; //avança o mês
            
            if (aux.mes > 12){     //se passar de 12 meses...
                aux.mes = aux.mes - 12;       //desconta-se 1 ano
                aux.ano++;                     //avança o ano.
                if (aux.anoBissexto()) 
					DIASDOMES[2] = 29; //verifica se o novo ano é bissexto para ajustar os dias de fevereiro.
                else 
					DIASDOMES[2] = 28;
            }
        }
        return aux;
    }

	/**
	* Verifica, entre esta data e a outra, qual é mais recente 
	* (mais avançada no calendário)
	*/
    public boolean maisRecente(Data outra){
       
        if(this.ano > outra.ano) 
              return true;
        else if(this.ano < outra.ano)
              return false;
        
        if(this.mes > outra.mes)
              return true;
        else if(this.mes < outra.mes)
              return false;
              
        if(this.dia > outra.dia)
              return true;
        else 
              return false;
    }		
    
    /**
    * Retorna a data formatada como DD/MM/AAAA
    */
    
    public String dataFormatada(){
        
        return (String.format("%02d",this.dia)+ "/" + String.format("%02d",this.mes)+ "/" + String.format("%4d",this.ano));
    }    
}

