/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.catalogação.de.livros;

/**
 *
 * @author Eduardo e Rodrigo
 */
public class NoLivro {
    
    protected int altura;       
    protected Livro chave;
    protected NoLivro esq, dir;

    public NoLivro ( Livro livro ) {
        this.chave = livro;
        this.altura = 0;
    }

    public NoLivro ( Livro livro, NoLivro esq, NoLivro dir ) {
        this.chave = livro;
        this.esq = esq;
        this.dir = dir;
        this.altura   = 0;
    }    
}
