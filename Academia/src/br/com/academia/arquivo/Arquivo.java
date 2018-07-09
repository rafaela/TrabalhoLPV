package br.com.academia.arquivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.FileChooser;

//import javax.swing.JFileChooser;
//import javax.swing.filechooser.FileNameExtensionFilter;

public class Arquivo {	
	
	/** 
	 * Exibe uma caixa de diálogo para o usuário indicar o nome do diretório e arquivo que será aberto. 
	 * 
	 * @param titulo <code>String</code> com o nome da barra de título da caixa de diálogo.
	 *        
	 * @return <code>List</code> com o nome dos arquivos a serem abertos. Se o usuário cancelar a 
	 * operação (clicar no botão "Cancelar") será retornado <code>null</code>.
	 *        
	 */
	
	public static List<File> dialogoAbrirArquivo( String titulo) {
		List<File> arquivos = new ArrayList<>();
		 FileChooser dialogoAbrir = new FileChooser();
		  dialogoAbrir.setTitle(titulo);
		  dialogoAbrir.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
		  dialogoAbrir.setInitialDirectory(new File("." + File.separator));
		  arquivos.addAll(dialogoAbrir.showOpenMultipleDialog(null));
		  if(!arquivos.isEmpty())
			  return arquivos;
		  return null;
	}
	
	/**
	 * Obtém os arquivos que foram selecionados pelo usuário
	 * @return array contendo dados dos arquivos.
	 */
	public static List<File> obtemArquivos(){
		//ArquivoTexto arquivoTexto = new ArquivoTexto();
		List<File> arquivos = dialogoAbrirArquivo("Escolha os arquivos");
		if(arquivos != null)
			return arquivos;
		
		return null;
	}
	
	/**
	 * Le os dados do arquivo de texto
	 * @param pathName nome do arquivo a ser lido
	 * @return <code>String</code> dados do texto do arquivo
	 */
	public static String abreELeDados(String pathName){
		ArquivoTexto arquivoTexto = new ArquivoTexto();
		String dados = null;
		
		try {
			arquivoTexto.abrir(pathName);
			dados = arquivoTexto.ler();
		} catch (FileNotFoundException e) {
			System.out.println("Não foi possivel abrir o arquivo " + pathName);
		}
		
		return dados;
	}
}
