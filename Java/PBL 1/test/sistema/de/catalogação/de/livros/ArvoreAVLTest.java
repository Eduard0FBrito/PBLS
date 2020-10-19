/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.catalogação.de.livros;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kelly
 */
public class ArvoreAVLTest {
    
    private ArvoreAVL a;
    private Livro L1;
    private Livro L2;
    private Livro L3;
    private Livro L4;
    private Livro L5;
    
    public ArvoreAVLTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        a = new ArvoreAVL();
        L1 = new Livro(1,"Livro1", "Autor1", "Mes1", "Ano1", "Link1");
        L2 = new Livro(2,"Livro2", "Autor2", "Mes2", "Ano2", "Link2");
        L3 = new Livro(3,"Livro3", "Autor3", "Mes3", "Ano3", "Link3");
        L4 = new Livro(4,"Livro4", "Autor4", "Mes4", "Ano4", "Link4");
        L5 = new Livro(1,"Livro1", "Autor1", "Mes1", "Ano1", "Link1");
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    /**
     * Test of inserir method, of class ArvoreAVL.
     */
    @Test
    public void testInserir() {
        assertTrue(a.inserir(L1)); //Caso esteja tudo correto, o livro é inserido e retorna true
        assertEquals(1, a.len()); //O metodo len foi implementado para auxiliar nos testes, 
        // caso o livro tenha sido inserido corretamente, o tamanho será 1
        assertFalse(a.inserir(L5)); //O livro l5 tem o mesmo numero de ebook do que l1, 
        //logo o metodo inserir deve retornar false
    }

    /**
     * Test of deletar method, of class ArvoreAVL.
     */
    @Test
    public void testDeletar() {
        assertNull(a.deletar(1)); // A arvore ainda esta vazia, portanto deve retornar null
        a.inserir(L1);
        a.inserir(L2);
        assertNotNull(a.deletar(1)); // Ao tentar deletar um livro que se encontra na arvore, ela retorna ela mesma balanceada, sendo nao nulo
        assertTrue(a.deletar(30) == null); //Ao tentar deletar um livro que nao se encontra, a arvore tb retorna null
    }

    /**
     * Test of busca method, of class ArvoreAVL.
     */
    @Test
    public void testBusca_NoLivro_int() {
        a.inserir(L1);
        a.inserir(L2);
        a.inserir(L3);
        assertNull(a.busca(33)); //Ao tentar buscar um livro que nao se encontra na arvore, ela retorna null
        assertNotNull(a.busca(1)); //Ao tentar buscar um livro que se encontra, ela retorna o objeto procurado
        Livro L6 = a.busca(2);
        assertSame(L6,L2);
    }    
}
