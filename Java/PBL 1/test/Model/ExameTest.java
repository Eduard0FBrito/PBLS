/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eduardo
 */
public class ExameTest {
    
    private Exame e1;
    private Exame e2;
    private Exame e3;
    
    public ExameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        e1 = new Exame(1);
        e2 = new Exame(2);
        e3 = new Exame(3);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTipo method, of class Exame.
     */
    @Test
    public void testGetTipo() {
        assertEquals(1, e1.getTipo()); // Verificando se a função esta recuperando o tipo corretamente
        assertTrue(e2.getTipo() == 2); // Verificando se a função esta recuperando o tipo corretamente
        e2.setTipo(10); // Alterando o tipo 
        assertFalse(e2.getTipo() == 2); // O valor deixou de ser 2 e passou a ser 10
    }

    /**
     * Test of setTipo method, of class Exame.
     */
    @Test
    public void testSetTipo() {
        assertEquals(1, e1.getTipo());
        e1.setTipo(10); //Alterando matricula para 10
        assertTrue(e1.getTipo() == 10); //Certificando que o tipo alterou
        assertFalse(e1.getTipo() != 10); //Caso não aja mais nenhuma alteração o tipo deve permanecer valor 10
    }  
}
