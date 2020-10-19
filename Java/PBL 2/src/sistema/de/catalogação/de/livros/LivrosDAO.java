/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.catalogação.de.livros;

import java.util.ArrayList;

/**
 *
 * @author Eduardo e Rodrigo
 */
public class LivrosDAO {
    
    private static ArvoreAVL arvore = new ArvoreAVL();
    
    
    public static boolean add(Livro livro){
        return arvore.inserir(livro);
    }
    
    public static NoLivro remove(int num){
        return arvore.deletar(num);
    }
    
    public static Livro buscar(int num){
        return arvore.busca(num);
    }
    
    public static void exibeLista(){
        arvore.inOrder();
    }
    
    public static String gerarCsvCompleto(){
        return arvore.gerarCSVcompleto();
    }
    
    public static String gerarCsvArtista(String autor){
        return arvore.gerarCSVartista(autor);
    }
    
    public static String gerarCsvAno(String ano){
        return arvore.gerarCSVano(ano);
    }
    
    public static void buscaAutor(String autor){
        arvore.buscaAutor(autor);
    }
    
    public static void buscaAno(String ano){
        arvore.buscaAno(ano);
    }
    
    public static void listarAutor(String autor){
        arvore.listarAutores(autor);
    }
    
    public static int quantLivros(String autor){
        return arvore.quantLivros(autor);
    }
}
