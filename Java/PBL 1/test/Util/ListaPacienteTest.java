/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

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
public class ListaPacienteTest {
    
    private ListaPaciente lista;
    private Paciente p1;
    private Paciente p2;
    private Paciente p3;
    
    public ListaPacienteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        lista = new ListaPaciente();
        p1 = new Paciente("Pedro", true, 1);
        p2 = new Paciente("Everton", false, 2);
        p3 = new Paciente("Henrique", true, 3);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of len method, of class ListaPaciente.
     */
    @Test
    public void testLen() {
        assertEquals(0, lista.len()); //Como a fila esta vazia a função len deve retornar 0
        lista.inserir(p1);
        lista.inserir(p2);
        assertTrue(lista.len() == 2); //Agora que foram adicionados dois elementos, a função deve retornar 2
        lista.remover(1);
        assertFalse(lista.len() != 1); //Como um elemento foi removido a função não pode retornar valor diferente de 1
    }

    /**
     * Test of inserir method, of class ListaExame.
     */
    @Test
    public void testInserir() {
        lista.inserir(p1);
        assertEquals(1, lista.len()); //Se a função len retornar 1, significa que um objeto foi adionado a lista
        lista.inserir(p2);
        lista.inserir(p3);
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
        lista.inserir(p1);
        lista.inserir(p2);
        lista.inserir(p3);
        assertNotNull(lista.remover(1));//Como um exame com o Tipo 1 foi adicionado na lista, ele deve ser removido
        //normalmente, retornando o objeto p1
        assertEquals(2, lista.len()); //Se a função remover um objeto corretamente, o seu tamanho diminuira pra 2
        assertNull(lista.remover(4)); //Ao tentar remover um objeto que nao se encontra na lista, essa função
        // deve retornar null
    }

    /**
     * Test of buscar method, of class ListaExame.
     */
    @Test
    public void testBuscar() {
        lista.inserir(p1);
        lista.inserir(p2);
        lista.inserir(p3);
        Paciente p4 = lista.buscar(1);
        assertSame(p1, p4); //Verificando se a função esta retornando o objeto corretamente
        assertNull(lista.buscar(4)); //Ao tentar buscar um objeto inexistente, essa função retorna null
    }
 
}
