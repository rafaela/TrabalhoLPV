package br.com.academia.dados;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Classe para validar se os dados fornecidos est�o corretos
 * @author Rafaela Paiva
 *
 */
public class ValidaDados {
	
	public static String SEPARADOR = "/";
	
	/**
	 * Valida uma data para o tipo <code>Calendar</code>
	 * @param date data a ser validada
	 * @return data validada, caso n�o seja possivel, retorna null
	 */
	public static Calendar isDataValida(String date) {
		Calendar cal = Calendar.getInstance();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			cal.setTime(sdf.parse(date));
			
			return cal;
		} catch (ParseException e) {
			System.out.println("Erro na data");
		} 
		return null;
	}
	
	/**
	 * Verifica se o e-mail informado est� no formato correto
	 * @param email dado a ser analisado.
	 * @return <code>true</code> caso esteja  correto, <code>false</code>
	 * caso n�o esteja
	 */
	public static boolean validaEmail(String email){
		if(email.matches(".+@.+") == true)
			return true;
		return false;
	}
	
	/**
	 * Transforma a dura��o da atividade em minutos
	 * @param duracao dura��o a ser transformada
	 * @return dura��o em minutos
	 */
	public static float calculaDuracao(String duracao){
		float duracaoMinutos = 0;
		int hora = Integer.parseInt(duracao.substring(0, 2));;
		int minuto = Integer.parseInt(duracao.substring(3, 5));
		float segundo = Float.parseFloat("0." + duracao.substring(6, 8));
		duracaoMinutos = (hora * 60) + minuto + segundo;
		return duracaoMinutos;
		
	}
	
	/**
	 * Calcula a pr�xima data v�lida a partir da data passada
	 * @param date data inicial
	 * @return prox�ma data v�lida
	 */
	public static Calendar avancarDia(Calendar date) {
		int dia = date.get(Calendar.DAY_OF_MONTH) + 1;
		int mes = date.get(Calendar.MONTH) + 1;
		int ano = date.get(Calendar.YEAR);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//Informa ao formato que ele dever ser conivente com erros e que se possivel os corrija
		sdf.setLenient(true);
		try {
			String data = sdf.format(sdf.parse(String.format("%d%s%d%s%d", 
					dia, SEPARADOR, mes, SEPARADOR, ano)));
			return isDataValida(data);
			
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
