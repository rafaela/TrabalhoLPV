package br.com.academia.dados;

import static br.com.academia.arquivo.Arquivo.abreELeDados;
import static br.com.academia.arquivo.Arquivo.obtemArquivos;
import static br.com.academia.dados.ValidaDados.avancarDia;
import static br.com.academia.dados.ValidaDados.isDataValida;
import static br.com.academia.dados.ValidaDados.validaEmail;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.academia.dao.AtividadeCompletaDAO;
import br.com.academia.dao.AtividadeSimplesDAO;
import br.com.academia.dao.ClienteDAO;
import br.com.academia.dao.RitmoDAO;
import br.com.academia.modelo.AtividadeCompleta;
import br.com.academia.modelo.AtividadeSimples;
import br.com.academia.modelo.Cliente;
import br.com.academia.modelo.Ritmo;

/**
 * Manipula os dados do cliente, obtendo as informações, salvando no banco de dados
 * e recuperando os dados salvos
 * @author Rafaela
 *
 */
public class ManipulaDados {
	public static AtividadeSimplesDAO simplesDao = new AtividadeSimplesDAO();
	public static AtividadeCompletaDAO completaDao = new AtividadeCompletaDAO();
	public static RitmoDAO ritmoDAO = new RitmoDAO();
	public static ClienteDAO clienteDAO = new ClienteDAO();

	/**
	 * Obtem os dados referentes ao cliente
	 * @param conteudo <code>String</code> onde irá extrair os dados
	 * @return dados do cliente, caso não seja possível, retorna <code>null</code>
	 */
	public static Cliente obtemDadosCliente(String conteudo){
		Cliente cliente = new Cliente();
		for(String linha: conteudo.split("\n")){
			if(linha.contains(": ")){
				String atributo = linha.split(": ")[0];
				String valor = linha.split(": ")[1];
				if(atributo.equals("Nome"))
					cliente.setNome(valor);			
				else if(atributo.equals("Sexo"))
					cliente.setSexo(valor);
				else if(atributo.equals("Altura"))
					cliente.setAltura(Float.parseFloat(valor.split(" ")[0].replace(",", ".")));
				else if(atributo.equals("Peso"))
					cliente.setPeso(Float.parseFloat(valor.split(" ")[0].replace(",", ".")));
				else if(atributo.equals("Data de Nascimento"))
					cliente.setDataNascimento(isDataValida(valor));
				else if(atributo.equals("E-mail"))
					if(validaEmail(valor))
						cliente.setEmail(valor);
					else
						System.out.println("Deu Erro");
				else if(atributo.equals("CPF"))
					cliente.setCpf(valor);
				else if(atributo.equals("WhatsApp"))
					cliente.setWhatsapp(valor);	
					
			}
		}
		
		return cliente;
	}
	
	/**
	 * Obtem os dados referentes a Atividade Simples
	 * @param conteudo <code>String</code> onde irá extrair os dados
	 * @return dados da atividade, caso não seja possível, retorna <code>null</code>
	 */
	public static AtividadeSimples obtemDadosAtividadeS(String conteudo){
		AtividadeSimples atividadeSimples = new AtividadeSimples();
		for(String linha: conteudo.split("\n")){
			if(linha.contains(": ")){
				String atributo = linha.split(": ")[0];
				String valor = linha.split(": ")[1];
				if(atributo.equals("Exercício"))
					atividadeSimples.setNome(valor);
				if(atributo.equals("Data"))
					atividadeSimples.setData(isDataValida(valor));
				if(atributo.equals("Tempo"))
					atividadeSimples.setTempo(valor);
				if(atributo.equals("Duração"))
					atividadeSimples.setDuracao(valor);
				if(atributo.equals("Distância"))
					atividadeSimples.setDistacia(Float.parseFloat(valor.split(" ")[0].replace(",", ".")));
				if(atributo.equals("Calorias perdidas"))
					atividadeSimples.setCalorias(Float.parseFloat(valor.split(" ")[0].replace(",", ".")));
				if(atributo.equals("Passos"))
					atividadeSimples.setPassos(Integer.parseInt(valor.replace(".", "")));;
			}
		}
		
		return atividadeSimples;
	}
	
	/**
	 * Obtem os dados referentes ao ritmo da Atividade completa desenvolvida 
	 * @param conteudo <code>String</code> onde irá extrair os dados
	 * @return dados da atividade, caso não seja possível, retorna <code>null</code>
	 */
	public static ArrayList<Ritmo> obtemDadosRitmo(String conteudo){
		ArrayList<Ritmo> ritmos = new ArrayList<>();
		
		for(String linha: conteudo.split("\n")){
			if(linha.contains(": ")){
				if(linha.contains("Km: ") && linha.contains("'")){
					Ritmo ritmo = new Ritmo();
					String km = linha.split("Km: ")[0];
					ritmo.setNumKm(Float.parseFloat(km.replace(",", ".")));
					
					String minuto = linha.substring(linha.indexOf(": "), linha.indexOf("'"));
					ritmo.setMinuto(Integer.parseInt(minuto.replace(": ", "")));
					
					String segundo = linha.split("'")[1];
					ritmo.setSegundo(Integer.parseInt(segundo.replace("\"", "")));
					
					ritmos.add(ritmo);
				}
			}
		}
		return ritmos;
	}
	
	/**
	 * Obtem os dados referentes a Atividade Completa
	 * @param conteudo <code>String</code> onde irá extrair os dados
	 * @return dados da atividade, caso não seja possível, retorna <code>null</code>
	 */
	public static AtividadeCompleta obtemDadosAtividadeC(String conteudo){
		AtividadeCompleta atividadeC = new AtividadeCompleta();
		AtividadeSimples atividade = obtemDadosAtividadeS(conteudo);
		ArrayList<Ritmo> ritmos = obtemDadosRitmo(conteudo);
		for(String linha: conteudo.split("\n")){
			if(linha.contains(": ")){
				String atributo = linha.split(": ")[0];
				String valor = linha.split(": ")[1];
				if(atributo.equals("Velocidade média"))
					atividadeC.setVelocidadeMedia(Float.parseFloat(valor.split(" ")[0].replace(",", ".")));
				if(atributo.equals("Velocidade máxima"))
					atividadeC.setVelocidadeMaxima(Float.parseFloat(valor.split(" ")[0].replace(",", ".")));
				if(atributo.equals("Ritmo médio"))
					atividadeC.setRitmoMedio(valor);
				if(atributo.equals("Ritmo máximo"))
					atividadeC.setRitmoMaximo(valor);				
				if(atributo.equals("Menor elevação"))
					atividadeC.setMenorElevacao(Float.parseFloat(valor.split(" ")[0].replace(".", "")));
				if(atributo.equals("Maior elevação"))
					atividadeC.setMaiorElevacao(Float.parseFloat(valor.split(" ")[0].replace(".", "")));			}
		}
		atividadeC.setNome(atividade.getNome());
		atividadeC.setData(atividade.getData());
		atividadeC.setTempo(atividade.getTempo());
		atividadeC.setDuracao(atividade.getDuracao());
		atividadeC.setDistacia(atividade.getDistancia());
		atividadeC.setCalorias(atividade.getCalorias());
		atividadeC.setPassos(atividade.getPassos());
		atividadeC.setRitmo(ritmos);
		
		return atividadeC;
	}
	/**
	 * Lê os dados do cliente e das atividades desenvolvidas e insere os dados no banco
	 */
	public static void leEInsereDados(){
		List<File> arquivos = obtemArquivos();
		for(File arquivo : arquivos){
			String dados = abreELeDados(arquivo.getAbsolutePath());
			if(dados != null){
				if(dados.contains("Exercício") == false)
					continue;
				Cliente cliente = new Cliente();
				cliente = obtemDadosCliente(dados);
				
				Cliente pesquisaCliente = null;
				pesquisaCliente = clienteDAO.pesquisarClientePorEmail(cliente.getEmail());
				
				//Verifica se o cliente já foi cadastrado
				if(pesquisaCliente.getEmail() == null){
					clienteDAO.insereCliente(cliente);
				}
				
				AtividadeCompleta atividadeCompleta = new AtividadeCompleta();

				if(dados.contains("Ritmo")){
					RitmoDAO ritmoDAO = new RitmoDAO();
					ArrayList<Ritmo> ritmos = new ArrayList<>();
					
					atividadeCompleta = obtemDadosAtividadeC(dados);
					ritmos = obtemDadosRitmo(dados);
					
					
					ArrayList<AtividadeCompleta> arrayCompleta = new ArrayList<>();
					//Obtém as atividades já cadastradas do cliente
					arrayCompleta = completaDao.pesquisarAtividadeCliente(cliente.getEmail());
					if(!arrayCompleta.isEmpty()){
						for(AtividadeCompleta atCompleta : arrayCompleta){
							if(atCompleta.getData().compareTo(atividadeCompleta.getData()) != 0)
								if(atCompleta.getTempo().equals(atividadeCompleta.getTempo()) == false)
									completaDao.insereAtividadeCompleta(atCompleta, cliente.getEmail());
									atCompleta = completaDao.pesquisarID(cliente.getEmail());
									if(!ritmos.isEmpty())
										for(Ritmo rit: ritmos)
											ritmoDAO.insereRitmo(rit, atCompleta.getId_atividade());										
						}//for
					}
					else{
						completaDao.insereAtividadeCompleta(atividadeCompleta, cliente.getEmail());
						atividadeCompleta = completaDao.pesquisarID(cliente.getEmail());
						if(!ritmos.isEmpty())
							for(Ritmo rit: ritmos)
								ritmoDAO.insereRitmo(rit, atividadeCompleta.getId_atividade());
								
					}
				}//if(dados.contains("Ritmo")
				else{
					AtividadeSimples atividade = new AtividadeSimples();
					atividade = obtemDadosAtividadeS(dados);
					ArrayList<AtividadeSimples> arraySimples = new ArrayList<>();
					arraySimples = simplesDao.pesquisaAtividadeCliente(cliente.getEmail());
					
					if(!arraySimples.isEmpty()){
						for(AtividadeSimples atSimples : arraySimples){
							if(atSimples.getData().compareTo(atividade.getData()) != 0)
								if(atSimples.getTempo().equals(atividade.getTempo()) == false)
									simplesDao.insereAtividadeSimples(atividade, cliente.getEmail());
						}
					}	
					else
						simplesDao.insereAtividadeSimples(atividade, cliente.getEmail());
				}//else
			}
		}
	}//leEInsereDados()
	
	/**
	 * Obtém todas as atividades praticadas por um cliente
	 * @param cliente cliente que irá obter os dados
	 * @return lista de atividades do cliente
	 */
	public static List<AtividadeSimples> obtemAtividades(Cliente cliente){
		List<AtividadeSimples> listaAtividades = new ArrayList<AtividadeSimples>();
		listaAtividades.addAll(simplesDao.pesquisaAtividadeCliente(cliente.getEmail()));
		listaAtividades.addAll(completaDao.pesquisarAtividadeCliente(cliente.getEmail()));
		
		return listaAtividades;
	}
	
	/**
	 * Obtém todas as atividades praticadas
	 * @param cliente cliente que irá obter os dados
	 * @return lista de atividades do cliente
	 */
	public static List<AtividadeSimples> obtemTodasAtividades(String nome){
		List<AtividadeSimples> listaAtividades = new ArrayList<AtividadeSimples>();
		listaAtividades.addAll(simplesDao.listaAtividadesPesquisa(nome));
		listaAtividades.addAll(completaDao.listaAtividadesPesquisa(nome));
		
		return listaAtividades;
	}
	
	/**
	 * Obtém todas as atividades praticadas
	 * @param cliente cliente que irá obter os dados
	 * @return lista de atividades do cliente
	 */
	public static List<AtividadeSimples> obtemTodasAtividades(){
		List<AtividadeSimples> listaAtividades = new ArrayList<AtividadeSimples>();
		listaAtividades.addAll(simplesDao.listaAtividades());
		listaAtividades.addAll(completaDao.listaAtividades());
		
		return listaAtividades;
	}
	
	/**
	 * Obtem os dados das atividades praticadas
	 * @param cliente cliente que irá obter os dados
	 * @param dataInicial período de datas das atividades praticadas
	 * @param datafinal período de datas das atividades praticadas
	 * @return lista com todas atividades praticadas no período
	 */
	public static ArrayList<AtividadeSimples> obtemAtividadesPeriodo(String nome, Calendar dataInicial, Calendar datafinal){
		ArrayList<AtividadeSimples> listaAtividades = new ArrayList<>();
		List<AtividadeSimples> atividades = new ArrayList<>();
		atividades = obtemTodasAtividades(nome);
		
		while(dataInicial.compareTo(datafinal) != 0){
			Calendar date = avancarDia(dataInicial);
			for(AtividadeSimples at : atividades){
				if(at.getData().compareTo(date) == 0)
					listaAtividades.add(at);
			}	
			dataInicial = date;
		}
		return listaAtividades;
		
	}
	
	/**
	 * Obtem os dados das atividades praticadas do cliente 
	 * @param cliente cliente que irá obter os dados
	 * @param dataInicial período de datas das atividades praticadas
	 * @param datafinal período de datas das atividades praticadas
	 * @return lista com todas atividades praticadas no período
	 */
	public static ArrayList<AtividadeSimples> obtemDadosAtividades(Cliente cliente, Calendar dataInicial, Calendar datafinal){
		ArrayList<AtividadeSimples> listaAtividadesCliente = new ArrayList<>();
		List<AtividadeSimples> atividadesCliente = new ArrayList<>();
		atividadesCliente = obtemAtividades(cliente);
		
		while(dataInicial.compareTo(datafinal) != 0){
			Calendar date = avancarDia(dataInicial);
			for(AtividadeSimples at : atividadesCliente){
				if(at.getData().compareTo(date) == 0)
					listaAtividadesCliente.add(at);
			}	
			dataInicial = date;
		}
		return listaAtividadesCliente;
		
	}

}
