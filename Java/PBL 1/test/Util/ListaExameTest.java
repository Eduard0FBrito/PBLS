/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Model.Exame;
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
public class ListaExameTest {
    
    private ListaExame lista;
    private Exame e1;
    private Exame e2;
    private Exame e3;
    
    public ListaExameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        lista = new ListaExame();
        e1 = new Exame(1);
        e2 = new Exame(2);
        e3 = new Exame(3);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of len method, of class ListaExame.
     */
    @Test
    public void testLen() {
        assertEquals(0, lista.len()); //Como a fila esta vazia a função len deve retornar 0
        lista.inserir(e1);
        lista.inserir(e2);
        assertTrue(lista.len() == 2); //Agora que foram adicionados dois elementos, a função deve retornar 2
        lista.remover(1);
        assertFalse(lista.len() != 1); //Como um elemento foi removido a função não pode retornar valor diferente de 1
    }

    /**
     * Test of inserir method, of class ListaExame.
     */
    @Test
    public void testInserir() {
        lista.inserir(e1);
        assertEquals(1, lista.len()); //Se a função len retornar 1, significa que um objeto foi adionado a lista
        lista.inserir(e2);
        lista.inserir(e3);
        assertNotNull(lista.remover(1)); //Como um exame com o Tipo 1 foi adicionado na lista, ele deve ser removido
        //normalmente, retornando um true
        assertFalse(lista.len() != 2); //Se os tres objetos forem inseridos corretamente e depois um objeto for removido
        // não tem como o tamanho da lista ser diferente de 2
    }

    /**
     * Test of remover method, of class ListaExame.
     */
    @Test
    public void testRemover() {
        lista.inserir(e1);
        lista.inserir(e2);
        lista.inserir(e3);
        Exame e4 = lista.remover(1);
        assertSame(e1,e4);//Como um exame com o Tipo 1 foi adicionado na lista, ele deve ser removido
        //normalmente, retornando o objeto e1
        assertEquals(2, lista.len()); //Se a função remover um objeto corretamente, o seu tamanho diminuira pra 2
        assertNull(lista.remover(4)); //Ao tentar remover um objeto que nao se encontra na lista, essa função
        // deve retornar false
    }

    /**
     * Test of buscar method, of class ListaExame.
     */
    @Test
    public void testBuscar() {
        lista.inserir(e1);
        lista.inserir(e2);
        lista.inserir(e3);
        Exame e4 = lista.buscar(1);
        assertSame(e1, e4); //Verificando se a função esta retornando o objeto corretamente
        assertNull(lista.buscar(4)); //Ao tentar buscar um objeto inexistente, essa função retorna null
    }
 
}
