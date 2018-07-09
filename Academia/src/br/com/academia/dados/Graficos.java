package br.com.academia.dados;

import static br.com.academia.dados.ManipulaDados.obtemAtividades;
import static br.com.academia.dados.ValidaDados.avancarDia;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.academia.modelo.AtividadeSimples;
import br.com.academia.modelo.Cliente;
/**
 * Obt�m os dados que ser�o utilizados na gera��o dos gr�ficos
 * @author Rafaela
 *
 */
public class Graficos {
	
	public static char SEPARADOR = '/';
	
	/**
	 * Obtem os dados das atividades praticadas do cliente 
	 * @param cliente cliente que ir� obter os dados
	 * @param dataInicial per�odo de datas das atividades praticadas
	 * @param datafinal per�odo de datas das atividades praticadas
	 * @return lista com todas atividades praticadas no per�odo
	 */
	public ArrayList<AtividadeSimples> obtemDadosGraficos(Cliente cliente, Calendar dataInicial, Calendar datafinal){
		ArrayList<AtividadeSimples> listaAtividadesCliente = new ArrayList<>();
		List<AtividadeSimples> atividadesCliente = new ArrayList<>();
		atividadesCliente = obtemAtividades(cliente);
		
		while(dataInicial.compareTo(datafinal) != 0){
			
			for(AtividadeSimples at : atividadesCliente){
				if(at.getData().compareTo(dataInicial) == 0)
					listaAtividadesCliente.add(at);
			}
			Calendar date = avancarDia(dataInicial);
			dataInicial = date;
		}
		return listaAtividadesCliente;
		
	}
}
