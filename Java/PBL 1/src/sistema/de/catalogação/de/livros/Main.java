/*******************************************************************************
Autores: Eduardo Fernandes e Rodrigo Santos 
Componente Curricular: MI de programação
Concluido em: 18/01/2020
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum 
trecho de código de outro colega ou de outro autor, tais como provindos de livros e 
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

package sistema.de.catalogação.de.livros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Eduardo e Rodrigo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        
        boolean continuar = true;
        String caminho;
        caminho = JOptionPane.showInputDialog("Digite o caminho do arquivo que deseja acessar");
        String nomeArquivo;
        nomeArquivo = JOptionPane.showInputDialog("Digite o nome do arquivo que deseja acessar");
        String arquivo;
        arquivo = caminho+"\\"+nomeArquivo+".txt";
        BufferedReader conteudo = null;
        String linha = "";
        ArrayList<String> autores = new ArrayList();
        
        try {
            String novaPasta = caminho+"\\HistoricoDeBuscas";
            File diretorio = new File(novaPasta);
            diretorio.mkdir();
        } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao criar o diretorio");
                System.out.println(ex);
        }
        try {
            conteudo = new BufferedReader(new FileReader(arquivo));
            conteudo.readLine();
            while ((linha = conteudo.readLine()) != null) {
                String[] livro = linha.split(";");
                
                int num = Integer.parseInt(livro[0]);
                String titulo = livro[1];
                String autor = livro[2];
                if(!autores.contains(autor)){
                    autores.add(autor);
                }
                String mes = livro [3];
                String ano = livro[4];
                String link = livro[5];
                
                Livro novo = new Livro(num, titulo, autor, mes, ano, link);
                LivrosDAO.add(novo);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: \n"+e.getMessage());
            System.out.println("Tente Novamente.");
            continuar = false;
        } catch (IOException e){
            System.out.println("IO Erro: \n+e.getMessage()");
            System.out.println("Tente Novamente.");
            continuar = false;
        } finally {
            if (conteudo != null){
                try {
                    conteudo.close();
                } catch (IOException e){
                    System.out.println("IO Erro: \n+e.getMessage()");
                    System.out.println("Tente Novamente.");
                    continuar = false;
                }
            }
        }
        
        while (continuar){
            int escolha;
            // Entrada de dados do usuario onde ele com qual classe deseja trabalhar
            escolha = Integer.parseInt(JOptionPane.showInputDialog("\n1 - Cadastrar Livro.\n2 - Listar: Autores/Quantidade.\n"
                    + "3 - Listar: Autor/Livros.\n4 - Listar: Livros.\n5 - Buscar: Livro\n6 - Buscar: Livro/Ano.\n7 - Excluir Livro."
                    + "\n8 - Sair do Programa\n\nQual sua escolha?"));
            
            if (escolha == 1){
                int num;
                do {
                    num = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero do Ebook desse livro:"));
                    Livro aux = LivrosDAO.buscar(num);
                    if (aux != null){
                        JOptionPane.showMessageDialog(null, "Nº de Ebook ja existe, tente outro");
                    }
                } while (LivrosDAO.buscar(num) != null);
                String titulo;
                do {
                    titulo = JOptionPane.showInputDialog("Digite o titulo do livro: ");
                    if ("".equals(titulo)){
                        JOptionPane.showMessageDialog(null, "O Livro precisa de um titulo.");
                    }
                } while ("".equals(titulo));
                String autor;
                do {
                    autor = JOptionPane.showInputDialog("Digite o autor deste livro: ");
                    if ("".equals(autor)){
                        JOptionPane.showMessageDialog(null, "O Livro precisa de um autor.");
                    }
                } while ("".equals(autor));
                Object [] meses = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
                String mes = String.valueOf(JOptionPane.showInputDialog(
                null, "Escolha:","Determine o mes de publicação",JOptionPane.QUESTION_MESSAGE, null, meses, meses[0]));
                String ano;
                do {
                    ano = JOptionPane.showInputDialog("Digite o ano de publicação deste livro.");
                    if ("".equals(ano)){
                        JOptionPane.showMessageDialog(null, "O Livro precisa de um ano.");
                    }
                } while ("".equals(ano));
                String link;
                do {
                    link = JOptionPane.showInputDialog("Digite o link para acesso desse livro: ");
                    if ("".equals(link)){
                        JOptionPane.showMessageDialog(null, "O Livro precisa de um link.");
                    }
                } while ("".equals(link));
                Livro livro = new Livro(num, titulo, autor, mes, ano, link);
                LivrosDAO.add(livro);
                if(!autores.contains(autor)){
                    autores.add(autor);
                }
                JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso.");
            } else if (escolha == 2){
                System.out.print("\n\n");
                for (int i = 0; i < autores.size(); i++) {
                    String autor = autores.get(i);
                    LivrosDAO.listarAutor(autor);
                }
            } else if (escolha == 3){
                String autor;
                autor = JOptionPane.showInputDialog("Digite o nome do autor: ");
                if(autores.contains(autor)){
                    LivrosDAO.buscaAutor(autor);
                    try {
                        String arqAutor = caminho+"\\HistoricoDeBuscas\\"+autor+".txt";
                        FileWriter arq = new FileWriter(arqAutor);
                        PrintWriter gravarArq = new PrintWriter(arq);
                        gravarArq.println(LivrosDAO.gerarCsvArtista(autor));
                        gravarArq.close();
                    } catch (IOException e){
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Autor não encontrado, tente novamente.");
                }
            } else if (escolha == 4){
                JOptionPane.showMessageDialog(null, "Aguarde um momento.");
                LivrosDAO.exibeLista();
            } else if (escolha == 5){
                int num;
                num = Integer.parseInt(JOptionPane.showInputDialog("Digite o Numero do Ebook do Livro Procurado: "));
                Livro aux = LivrosDAO.buscar(num);
                if (aux == null){
                    System.out.println("\n\nLivro não se encontra no nosso catalogo.");
                } else {
                    System.out.println("\n\nLivro: "+aux.getTitulo()+"\nO Link de Acesso para esse Ebook é:\n"+aux.getLink());
                }
            } else if (escolha == 6){
                String ano;
                ano = JOptionPane.showInputDialog("Digite o ano desejado: ");
                LivrosDAO.buscaAno(ano);
                 try {
                        String arqAno = caminho+"\\HistoricoDeBuscas\\"+ano+".txt";
                        FileWriter arq = new FileWriter(arqAno);
                        PrintWriter gravarArq = new PrintWriter(arq);
                        gravarArq.println(LivrosDAO.gerarCsvAno(ano));
                        gravarArq.close();
                    } catch (IOException e){
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
            } else if (escolha == 7){
                int num;
                num = Integer.parseInt(JOptionPane.showInputDialog("Digite o Numero do Ebook do Livro Procurado: "));
                Livro aux = LivrosDAO.buscar(num);
                if (aux == null){
                    System.out.println("Livro não se encontra no nosso catalogo.");
                } else {
                    LivrosDAO.remove(num);
                    if (LivrosDAO.quantLivros(aux.getAutor()) == 0){
                        autores.remove(aux.getAutor());
                    }
                    JOptionPane.showMessageDialog(null, "Livro Removido com Sucesso.");
                }
            } else if (escolha == 8){
                JOptionPane.showMessageDialog(null, "Vamos salvar nosso catagolo atual...");
                try {
                    FileWriter arq = new FileWriter(arquivo);
                    PrintWriter gravarArq = new PrintWriter(arq);
                    gravarArq.print(LivrosDAO.gerarCsvCompleto());
                    gravarArq.close();
                } catch (IOException e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                JOptionPane.showMessageDialog(null, "Finalizando...");
                continuar = false; // O looping para
            } else {
                System.out.println("Escolha invalida, tente novamente.");
            }
        }
    }
}
