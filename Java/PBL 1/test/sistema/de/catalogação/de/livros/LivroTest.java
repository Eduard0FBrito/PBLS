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
public class LivroTest {
    
    private Livro l;
    
    public LivroTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        l = new Livro(1, "Teste Livro", "ER", "JAN", "2019", "mepassaRAFAEL.com");
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getNum method, of class Livro.
     */
    @Test
    public void testGetNum() {
        assertEquals(1, l.getNum()); //Como o livro foi criado com o numero 1, inicialmente deve retornar 1
        l.setNum(10);
        assertTrue(l.getNum() == 10); //Ao utilizar o set, o numero de ebook do livro passa a ser 10
        assertFalse(l.getNum() != 10); //Caso nao seja alterado o valor permanece o mesmo
    }

    /**
     * Test of setNum method, of class Livro.
     */
    @Test
    public void testSetNum() {
        assertTrue(l.getNum() == 1); //Inicialmente o valor é 1
        l.setNum(11); // Alterando o valor para 11
        assertEquals(11,l.getNum()); //Conferindo se o valor realmente foi alterado
        assertFalse(l.getNum() != 11); //Caso nao seja alterado o valor permanece o mesmo
    }

    /**
     * Test of getTitulo method, of class Livro.
     */
    @Test
    public void testGetTitulo() {
        assertEquals("Teste Livro", l.getTitulo()); //Verificando se esta retornando o valor que foi instaciado
        l.setTitulo("ME PASSA NAMORAL"); //Alterando titulo
        assertTrue("ME PASSA NAMORAL".equals(l.getTitulo())); //Verificando se o valor realmente foi alterado
        assertFalse(!"ME PASSA NAMORAL".equals(l.getTitulo())); //Caso nao seja alterado o valor permanece o mesmo
    }

    /**
     * Test of setTitulo method, of class Livro.
     */
    @Test
    public void testSetTitulo() {
        assertTrue("Teste Livro".equals(l.getTitulo())); //Inicialmente o valor é "Teste Livro"
        l.setTitulo("Me passa ae"); // Alterando o valor para "Me passa ae"
        assertEquals("Me passa ae", l.getTitulo()); //Conferindo se o valor realmente foi alterado
        assertFalse(!"Me passa ae".equals(l.getTitulo())); //Caso nao seja alterado o valor permanece o mesmo
    }

    // Os outros atriubutos seguem a mesma logica :)
    
}
