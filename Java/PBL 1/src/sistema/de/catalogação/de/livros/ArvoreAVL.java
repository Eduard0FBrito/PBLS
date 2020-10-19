/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.catalogação.de.livros;

/**
 * Estrutura de dados utilizada no sistema para armazenar os livros.
 * @author Eduardo e Rodrigo
 */
public class ArvoreAVL {
    
    private NoLivro raiz = null;
    
    /**
     * Construtor da arvore AVL.
     */
    public ArvoreAVL() {
        raiz = null;
    }
    
    /**
     * Metodo pra esvaziar a arvore.
     */
    public void clear() {
        raiz = null;
    }
    
    /**
     * Metodo para verificar se a arvore esta vazia.
     * @return true caso esteja vazia, false caso contrario.
     */
    public boolean isEmpty() {
        return raiz == null;
    }
    
    /** 
     * Metodo que retorna a raiz da arvore.
     * @return A raiz da arvore.
     */
    public NoLivro getRaiz(){
        return raiz;
    }
    
    /**
     * Metodo ternario que retorna a altura do no.
     * @param no
     * @return Altura do no.
     */
    private static int height( NoLivro no ) {
        return no == null ? -1 : no.altura;
    }
    
    private static int max( int lhs, int rhs ) {
        return lhs > rhs ? lhs : rhs;
    }
    
    /**
     * Metodo que verifica o fator de balanceamento da arvore.
     * @param no
     * @return Fator de balanceamento.
     */
     private int getBal (NoLivro no) {
        return height( no.esq ) - height( no.dir );
    }
    
    /**
     * Metodo de inserção da arvore.
     * @param livro
     * @return
     */
    public boolean inserir (Livro livro) {
        raiz = inserir (livro, this.raiz);
        return true;
    }
    
    /**
     * Metodo recursivo de inserção.
     * @param livro
     * @param no
     * @return 
     */
    private NoLivro inserir (Livro livro, NoLivro no) {
        if( no == null ) //Caso a raiz seja nula, o no se torna a raiz
            no = new NoLivro(livro, null, null);
        else if( livro.getNum() < no.chave.getNum() ){ //Caso seja menor que a raiz, o metodo é chamado pela esquerda
            no.esq = inserir ( livro, no.esq );
        }
        else if( livro.getNum() > no.chave.getNum()) { //Caso seja maior, é chamado pela direita
            no.dir = inserir ( livro, no.dir );
        }
        no = balanciar (no); //Ao inserir um no numa arvore avl, ela deve ser balanciado caso necessite
        return no;
    }
    
    /**
     * Metodo que retorna o no de menor valor.
     * @param no
     * @return no de menor valor.
     */
    private NoLivro minValueNode(NoLivro no){
        NoLivro aux = no; 
        while (aux != null && aux.esq != null){
            aux = aux.esq;
        }
        return aux; 
    }
    
    /**
     * Metodo para deletar um no da arvore.
     * @param num
     * @return 
     */
    public NoLivro deletar(int num){
        return deletar(raiz, num);
    }
    
    /**
     * Metodo recursivo para deletar um no da arvore.
     * @param raiz
     * @param num
     * @return 
     */
    private NoLivro deletar (NoLivro raiz, int num) {
        if (raiz == null) // se a raiz for nula, nao tem o que deletar.
            return raiz;

        // Caso o no procurado seja menor que a raiz,
        // o metodo é chamado pela esquerda
        if ( num < raiz.chave.getNum() )
            raiz.esq = deletar (raiz.esq, num);

        // Caso o no procurado seja maior que a raiz,
        // o metodo é chamado pela direita
        else if( num > raiz.chave.getNum() )
            raiz.dir = deletar (raiz.dir, num);

        // Se for igual a raiz, então é deletado
        else {
            // Caso no não tenha filho ou apenas 1
            if( (raiz.esq == null) || (raiz.dir == null) ) {

                NoLivro temp;
                if (raiz.esq != null)
                        temp = raiz.esq;
                else
                    temp = raiz.dir;

                // Sem filhos
                if(temp == null) {
                    temp = raiz;
                    raiz = null;
                }
                else //Apenas um filho
                    raiz = temp;

                temp = null;
            }
            else {
                // No com 2 filhos
                NoLivro temp = minValueNode(raiz.dir); // Encontra-se o menor no da sub arvore da direita

                raiz.chave = temp.chave;

                raiz.dir = deletar(raiz.dir, temp.chave.getNum());
            }
        }

        if (raiz == null)
            return raiz;

        raiz.altura = Math.max(height(raiz.esq), height(raiz.dir)) + 1;
        
        return balanciar(raiz); //Ao deletar um no numa arvore avl, a mesma deve ser balanciada caso necessite
    }
    
    /**
     * Metodo de balanceamento
     * @param no
     * @return o no balanceado
     */
    public NoLivro balanciar (NoLivro no) {
        if ( getBal(no) == 2 ) { //Caso o balanceamento do no seja igual a 2, entao o balanceamento deve ser feito na direita
                if (getBal (no.esq)>0){ // se o no a esquerda tiver um balanceamento maior que 0, uma rotação simples basta
                    no = simplesDireita( no );
                } else { // caso contrario, uma rotação dupla é necessaria
                    no = duplaDireita( no );
                }
        }
        else if ( getBal(no) == -2 ) { //Se o balanceamento for igual a -2, entao o balanceamento deve ser feito na esquerda
                if ( getBal(no.dir)<0 ){ // se o o no a direita tiver um fator menor que 0, uma rotação simples basta
                    no = simplesEsquerda( no );
                } else { // caso contrario, uma rotação dupla é necessaria
                    no = duplaEsquerda( no );
                }
        }
        no.altura = max( height( no.esq ), height( no.dir ) ) + 1;
        return no;
    }
    
    /** Rotação simples a direita */
    private static NoLivro simplesDireita( NoLivro n2 ) {
        NoLivro n1 = n2.esq;
        n2.esq = n1.dir;
        n1.dir = n2;
        n2.altura = max( height( n2.esq ), height( n2.dir ) ) + 1;
        n1.altura = max( height( n1.esq ), n2.altura ) + 1;
        return n1;
    }
    
    /** Rotação simples à esquerda */
    private static NoLivro simplesEsquerda( NoLivro n1 ) {
        NoLivro n2 = n1.dir;
        n1.dir = n2.esq;
        n2.esq = n1;
        n1.altura = max( height( n1.esq ), height( n1.dir ) ) + 1;
        n2.altura = max( height( n2.dir ), n1.altura ) + 1;
        return n2;
    }
    
    /** Rotação dupla à direita */
    private static NoLivro duplaDireita( NoLivro n3 ) {
        n3.esq = simplesEsquerda( n3.esq );
        return simplesDireita( n3 );
   }
    
   /** Rotação dupla à esquerda */
    private static NoLivro duplaEsquerda( NoLivro n1 ) {
        n1.dir = simplesDireita( n1.dir );
        return simplesEsquerda( n1 );
    }
    
    /**
     * metodo de busca da arvore avl
     * @param num
     * @return o livro procurado
     */
    public Livro busca(int num) {
        return busca(raiz,num);
    }
    
   /**
    * metodo recursivo de busca
    * @param no
    * @param num
    * @return o livro procurado 
    */
    protected Livro busca(NoLivro no, int num) {
        while (no != null) {
            /* se valor procuradp == chave do nó retorna referência ao nó */ 
            if (num == no.chave.getNum()) {
                return no.chave;
            }
            /* se valor procurado < chave do nó, procurar na sub-árvore esquerda deste nó */
            else if (num < no.chave.getNum()) {
                no = no.esq;
            }
            /* se valor procurado > chave do nó, procurar na sub-árvore direita deste nó */
            else {
                no = no.dir;
            }
        }
        // caso chave não foi achada, retorna null
        return null;
    }
    
    /**
     * metodo pra impressão da arvore
     */
    public void inOrder() {
        inOrder(raiz);
    }
    
    /**
     * metodo recursivo para impressao da arvore
     * @param no 
     */
    protected void inOrder(NoLivro no) {
        if (no != null) { //imprime do menor para o maior
             inOrder(no.esq);
             System.out.print(no.chave);
             inOrder(no.dir);
        }
    }
    
    /**
     * metodo que imprime os livros de determinado autor
     * @param autor 
     */
    public void buscaAutor(String autor){
        System.out.println("\n\nO autor "+autor+" publicou os seguintes livros: \n");
        buscaAutor(raiz,autor);
    }
    
    /**
     * metodo recursivo 
     * @param no
     * @param autor 
     */
    protected void buscaAutor(NoLivro no, String autor){
        if (no != null) {
            buscaAutor(no.esq, autor);
            if (no.chave.getAutor().equals(autor)){
                System.out.println(no.chave+"\n");
            }
            buscaAutor(no.dir, autor);
        }
    }
    
    /**
     * metodo que imprime os livros de determinado ano
     * @param ano 
     */
    public void buscaAno(String ano){
        System.out.println("\n\nNo ano de "+ano+" foram publicados os seguintes livros: \n");
        buscaAno(raiz, ano);
    }
    
    /** 
     * metodo recursivo
     * @param no
     * @param ano 
     */
    protected void buscaAno(NoLivro no, String ano){
        if (no != null){
            buscaAno(no.esq, ano);
            if (no.chave.getAno().equals(ano)){
                System.out.println(no.chave+"\n");
            }
            buscaAno(no.dir, ano);
        }
    }
    
    /**
     * metodo que informa a quantidade de livros de cada autor presente no catalogo
     * @param autor 
     */
    public void listarAutores(String autor){
        int totalLivros = listarAutores(autor, raiz);
        System.out.println(autor+": "+totalLivros+" livro(s) publicado(s)");
    }
    
    public int quantLivros(String autor){
        return listarAutores(autor, raiz);
    }
    
    /**
     * metodo que retorna a quantidade de livros na arvore, metodo criado para auxiliar nos testes
     * @return num de livros
     */
    public int len(){
        return len(raiz);
    }
    
    protected int len(NoLivro no){
        int contador = 0;
        if (no != null){
            contador +=1;
            if (no.esq != null){
                contador += len(no.esq);
            }
            if (no.dir != null){
                contador += len(no.dir);
            }
        }
        return contador;
    }
    /**
     * metodo recursivo
     * @param autor
     * @param no
     * @return o numero de livros de determinado autor
     */
    protected int listarAutores(String autor, NoLivro no){
        int contador = 0;
        if (no != null) {
            if (no.chave.getAutor().equals(autor)){
                contador += 1;
            }
            if (no.esq != null) {
                contador += listarAutores(autor, no.esq);
            }
            if (no.dir != null) {
                contador += listarAutores(autor, no.dir);
            }
        }  
        return contador;
    }
    
    /**
     * metodo que gera uma string no formato csv para ser gravado em arquivo
     * @return string no formato csv contendo todas as informações do catalogo
     */
    public String gerarCSVcompleto(){
        String aux = "N_EBOOK;TITULO;AUTOR;MES;ANO;LINK\n";
        return aux + gerarCSVcompleto(raiz);
    }
    
    /** 
     * metodo recursivo que pecorre toda a arvore
     * @param no
     * @return 
     */
    protected String gerarCSVcompleto(NoLivro no){
        String str = "";
        if (no != null) {
            int num = no.chave.getNum();
            String titulo = no.chave.getTitulo();
            String autor = no.chave.getAutor();
            String mes = no.chave.getMes();
            String ano = no.chave.getAno();
            String link = no.chave.getLink();
            str += num+";"+titulo+";"+autor+";"+mes+";"+ano+";"+link+"\n";
            if (no.esq != null) {
                str += gerarCSVcompleto(no.esq);
            }
            if (no.dir != null) {
                str += gerarCSVcompleto(no.dir);
            }
        }
        return str;
    }
    
    /** 
     * metodo que gera uma string no formato csv para ser gravado num arquivo
     * @param autor
     * @return uma string contendo o nome de todos os livros publicados por determinado autor
     */
    public String gerarCSVartista(String autor){
        String aux = "Nosso catagolo conta com os seguintes livros do autor "+autor+":\n\n";
        String teste = "";
        String str = gerarCSVartista(raiz, autor);
        if (teste.equals(str)){
            return "Não existem livros desse autor em nosso catalogo";
        } else {
            return aux + str;
        }
    }
    
    /**
     * metodo recursivo que pecorre toda arvore
     * @param no
     * @param autor
     * @return 
     */
    protected String gerarCSVartista(NoLivro no, String autor){
        String str = "";
        if (no != null) {
            if (no.chave.getAutor().equals(autor)){
                str += no.chave.getTitulo()+"\n";
            }
            if (no.dir != null){
                str += gerarCSVartista(no.dir, autor);
            }
            if (no.esq != null){
                str += gerarCSVartista(no.esq, autor);
            }
        }
        return str;
    }
    
    /**
     * metodo que gera uma string no formato csv para que seja gravada num arquivo
     * @param ano
     * @return uma string contendo todos os livros publicados em determinado ano
     */
    public String gerarCSVano(String ano){
        String aux = "Nosso catagolo conta com os seguintes livros do ano de "+ano+":\n\n";
        String teste = "";
        String str = gerarCSVano(raiz, ano);
        if (teste.equals(str)){
            return "Não existem livros deste ano em nosso catalogo";
        } else {
            return aux + str;
        }
    }
    
    /**
     * metodo recursivo que pecorre toda arvore
     * @param no
     * @param ano
     * @return 
     */
    protected String gerarCSVano(NoLivro no, String ano){
        String str = "";
        if (no != null) {
            if (no.chave.getAno().equals(ano)){
                str += no.chave.getTitulo()+"\n";
            }
            if (no.dir != null){
                str += gerarCSVano(no.dir, ano);
            }
            if (no.esq != null){
                str += gerarCSVano(no.esq, ano);
            }
        }
        return str;
    }
       
}