/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.catalogação.de.livros;

/**
 * Classe que representa um livro no sistema.
 * @author Eduardo e Rodrigo
 */
public class Livro {
    
    private int num;
    private String titulo;
    private String autor;
    private String mes;
    private String ano;
    private String link;
    
    /**
     * construtor da classe livro
     * @param num
     * @param titulo
     * @param autor
     * @param mes
     * @param ano
     * @param link 
     */
    public Livro(int num, String titulo, String autor, String mes, String ano, String link) {
        this.num = num;
        this.titulo = titulo;
        this.autor = autor;
        this.mes = mes;
        this.ano = ano;
        this.link = link;
    }

    /**
     * Metodo que retorna o numero de ebook do livro, a arvore avl é organizada por ele
     * @return numero do ebook
     */
    public int getNum() {
        return num;
    }
    
    /**
     * metodo para alterar o numero do ebook de um livro.
     * @param num 
     */
    public void setNum(int num) {
        this.num = num;
    }

    /** 
     * metodo que retorna o titulo do livro
     * @return titulo do livro
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * metodo para alterar o titulo de um livro
     * @param titulo 
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * metodo que retorna o nome do autor de um livro.
     * @return nome do autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * metodo para alterar o nome do autor de um livro
     * @param autor 
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * metodo que retorna o mes de publicação de um livro
     * @return mes de publicação
     */
    public String getMes() {
        return mes;
    }

    /**
     * metodo para alterar o mes de publicação de um livro
     * @param mes 
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     * metodo que retorna o ano de publicação de um livro.
     * @return ano de publicação 
     */
    public String getAno() {
        return ano;
    }

    /** 
     * metodo para alterar o ano de publicação de um livro
     * @param ano 
     */
    public void setAno(String ano) {
        this.ano = ano;
    }

    /**
     * metodo que retorna o link de acesso de um livro
     * @return link de acesso
     */
    public String getLink() {
        return link;
    }

    /**
     * metodo para alterar o link de acesso de um livro
     * @param link 
     */
    public void setLink(String link) {
        this.link = link;
    }
    
    /**
     * metodo de impressão de um livro
     * @return as informações do livro
     */
    @Override
    public String toString(){
        String completo = "--------------------------------\nTitulo do Livro: "+this.titulo+"\nNº do Ebook: "+this.num+"\nAutor: "+this.autor+"\nData de Publicação: "
                +this.mes.toUpperCase()+"/"+this.ano+"\nLink: "+this.link+"\n--------------------------------";
        return completo;
    }
}
