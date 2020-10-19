/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Model.Medico;
import Model.Paciente;
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
public class ListaMedicoTest {
    
    private ListaMedico lista;
    private Medico m1;
    private Medico m2;
    private Medico m3;
    
    public ListaMedicoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        lista = new ListaMedico();
        m1 = new Medico("Pedro", 3);
        m2 = new Medico("Rafael", 4);
        m3 = new Medico("Rodrigo", 5);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of len method, of class ListaMedico.
     */
    @Test
    public void testLen() {
        assertEquals(2, lista.len()); //Como a fila esta vazia a função len deve retornar 0
        lista.inserir(m1);
        lista.inserir(m1);
        assertTrue(lista.len() == 4); //Agora que foram adicionados dois elementos, a função deve retornar 2
        lista.remover(1);
        assertFalse(lista.len() != 3); //Como um elemento foi removido a função não pode retornar valor diferente de 1
    }

    /**
     * Test of inserir method, of class ListaExame.
     */
    @Test
    public void testInserir() {
        lista.inserir(m1);
        assertEquals(3, lista.len()); //Se a função len retornar 3, significa que um objeto foi adionado a lista
        lista.inserir(m2);
        lista.inserir(m3);
        assertNotNull(lista.remover(1)); //Como um exame com o Tipo 1 foi adicionado na lista, ele deve ser removido
        //normalmente, retornando um true
        assertFalse(lista.len() != 4); //Se os tres objetos forem inseridos corretamente e depois um objeto for removido
        // não tem como o tamanho da lista ser diferente de 2
    }

    /**
     * Test of remover method, of class ListaExame.
     */
    @Test
    public void testRemover() {
        lista.inserir(m1);
        lista.inserir(m2);
        lista.inserir(m3);
        assertNotNull(lista.remover(1));//Como um medico com o crm 1 foi adicionado na lista, ele deve ser removido
        // retornando true
        assertEquals(4, lista.len()); //Se a função remover um objeto corretamente, o seu tamanho diminuira pra 2
        assertNull(lista.remover(7)); //Ao tentar remover um objeto que nao se encontra na lista, essa função
        // deve retornar false
    }

    /**
     * Test of buscar method, of class ListaExame.
     */
    @Test
    public void testBuscar() {
        lista.inserir(m1);
        lista.inserir(m2);
        lista.inserir(m3);
        Medico m4 = lista.buscar(3);
        assertSame(m1, m4); //Verificando se a função esta retornando o objeto corretamente
        assertNull(lista.buscar(9)); //Ao tentar buscar um objeto inexistente, essa função retorna null
    }
 
}