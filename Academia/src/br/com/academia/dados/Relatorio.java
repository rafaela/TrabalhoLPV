package br.com.academia.dados;

import static br.com.academia.dados.ManipulaDados.obtemAtividades;
import static br.com.academia.dados.ValidaDados.calculaDuracao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.academia.modelo.AtividadeCompleta;
import br.com.academia.modelo.AtividadeSimples;
import br.com.academia.modelo.Cliente;

/**
 * Classe que obtem os dados para gerar o relatório
 * @author Rafaela Paiva
 *
 */

public class Relatorio {
	
	/**
	 * Obtém a maior duração da atividade desenvolvida pelo cliente
	 * @param cliente cliente que irá obter os dados
	 * @return <code>String</code> contendo a maior duração e a data que ela ocorreu
	 */
	public String obtemMaiorDuracao(Cliente cliente){
		List<AtividadeSimples> listaAtividades = new ArrayList<>();
		listaAtividades = obtemAtividades(cliente);
		float duracao = 0;
		String maiorDuracao = "", dataDuracao = "";
		
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		
		for (AtividadeSimples atividadeSimples : listaAtividades) {
			if(calculaDuracao(atividadeSimples.getDuracao())  > duracao){
				duracao = calculaDuracao(atividadeSimples.getDuracao());
				maiorDuracao = atividadeSimples.getDuracao();
				dataDuracao = formatoData.format(atividadeSimples.getData().getTime());
			}
		}
		String dados = "Maior Duração: " + maiorDuracao + "\nData: " + dataDuracao;
		return dados;
	}
	
	/**
	 * Obtém a maior distância da atividade desenvolvida pelo cliente
	 * @param cliente cliente que irá obter os dados
	 * @return <code>String</code> contendo a maior distância e a data que ela ocorreu
	 */
	public String obtemMaiorDistancia(Cliente cliente){
		List<AtividadeSimples> listaAtividades = new ArrayList<>();
		listaAtividades = obtemAtividades(cliente);
		
		float maiorDistancia = 0;
		String dataDistancia = "";
		
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		
		for (AtividadeSimples atividadeSimples : listaAtividades) {
			if(atividadeSimples.getDistancia() > maiorDistancia){
				maiorDistancia = atividadeSimples.getDistancia();
				dataDistancia = formatoData.format(atividadeSimples.getData().getTime());
			}
		}
		String dados = "Maior Distancia: " + maiorDistancia + "\nData: " + dataDistancia;
		return dados;
	}
	
	/**
	 * Obtém a maior quantidade de calorias perdidas na atividade desenvolvida pelo cliente
	 * @param cliente cliente que irá obter os dados
	 * @return <code>String</code> contendo as calorias e a data que ela ocorreu
	 */
	public String obtemMaiorCalorias(Cliente cliente){
		List<AtividadeSimples> listaAtividades = new ArrayList<>();
		listaAtividades = obtemAtividades(cliente);
		
		float maiorCalorias = 0;
		String dataCalorias = "";
		
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		
		for (AtividadeSimples atividadeSimples : listaAtividades) {
			if(atividadeSimples.getCalorias() > maiorCalorias){
				maiorCalorias = atividadeSimples.getCalorias();
				dataCalorias = formatoData.format(atividadeSimples.getData().getTime());
			}
		}
		String dados = "Maior número de calorias: " + maiorCalorias + "\nData: " + dataCalorias;
		return dados;
	}
	
	/**
	 * Obtém a maior quantidade de passos dados da atividade desenvolvida pelo cliente
	 * @param cliente cliente que irá obter os dados
	 * @return <code>String</code> contendo a número de passos e a data que ela ocorreu
	 */
	public String obtemMaiorPassos(Cliente cliente){
		List<AtividadeSimples> listaAtividades = new ArrayList<>();
		listaAtividades = obtemAtividades(cliente);
		
		int maiorNumeroPassos = 0;
		String dataPassos = "";
		
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		
		for (AtividadeSimples atividadeSimples : listaAtividades) {
			if(atividadeSimples.getPassos() > maiorNumeroPassos){
				maiorNumeroPassos = atividadeSimples.getPassos();
				dataPassos = formatoData.format(atividadeSimples.getData().getTime());
			}
		}
		String dados = "Maior número de passos dados: " + maiorNumeroPassos + 
				"\nData: " + dataPassos;
		return dados;
	}
	
	/**
	 * Obtém a maior velocidade da atividade desenvolvida pelo cliente
	 * @param cliente cliente que tera o relatorio gerado
	 * @return <code>String</code> contendo a maior velocidade e a data que ela ocorreu
	 */
	public String obtemMaiorVelocidade(Cliente cliente){
		List<AtividadeSimples> listaAtividades = new ArrayList<>();
		listaAtividades = obtemAtividades(cliente);;
		
		float maiorVelocidade = 0;
		String dataVelocidade = "";
		
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		
		for (AtividadeSimples atividadeSimples : listaAtividades) {
			if(atividadeSimples instanceof AtividadeCompleta)
				if(((AtividadeCompleta)atividadeSimples).getVelocidadeMaxima() > maiorVelocidade){
					maiorVelocidade = ((AtividadeCompleta)atividadeSimples).getVelocidadeMaxima();
					dataVelocidade = formatoData.format(atividadeSimples.getData().getTime());
				}
		}
		String dados = "Maior Velocidade: " + maiorVelocidade + "\nData: " + dataVelocidade;
		return dados;
	}
	
	
	
}
