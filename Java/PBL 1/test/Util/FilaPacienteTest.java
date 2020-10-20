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
public class FilaPacienteTest {
    
    private FilaPaciente fila;
    private Paciente p1;
    private Paciente p2;
    private Paciente p3;
    private Paciente p4;
    
    public FilaPacienteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        fila = new FilaPaciente();
        p1 = new Paciente("Rodrigo", false, 1);
        p2 = new Paciente("Pedro", false, 2);
        p3 = new Paciente("Rafael", true, 3);
        p4 = new Paciente("Lucas", true, 4);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of len method, of class FilaPaciente.
     */
    @Test
    public void testLen() {
        assertEquals(0, fila.len()); //Como a fila esta vazia a função len deve retornar 0
        fila.inserir(p1);
        fila.inserir(p2);
        assertTrue(fila.len() == 2); //Agora que foram adicionados dois elementos, a função deve retornar 2
        fila.remover();
        assertFalse(fila.len() != 1); //Como um elemento foi removido a função não pode retornar valor diferente de 1
    }

    /**
     * Test of inserir method, of class FilaPaciente.
     */
    @Test
    public void testInserir() {
        fila.inserir(p1);
        assertEquals(1, fila.len()); //Se a função len retornar 1, significa que um objeto foi adionado a lista
        fila.inserir(p3);
        fila.inserir(p4);
        Paciente p5 = fila.remover();
        assertSame(p3, p5); //Como o paciente p3 é prioritario ele deve ficar no inicio da lista, logo a função remover deve 
        //retornar o mesmo paciente p3
        Paciente p6 = fila.remover();
        assertTrue(p6 == p4); //verificando se os prioritarios estão sendo incluidos na frente dos nao prioritarios, 
        //o paciente p4 deve vim logo apos o paciente p3
    }

    /**
     * Test of remover method, of class FilaPaciente.
     */
    @Test
    public void testRemover() {
        assertEquals(null, fila.remover()); // Como a fila esta vazia o metodo remover retorna null
        fila.inserir(p1);
        fila.inserir(p2);
        Paciente p5 = fila.remover();
        assertSame(p1,p5); //Como os dois pacientes que foram adicionados são não prioritarios, o primeiro a entrar
        // deve ser o primeiro a sair normalmente
        fila.inserir(p3); //Um paciente prioritario foi adicionado, consequentemente ele tomou a frente da fila
        Paciente p6 = fila.remover();
        assertTrue(p3 == p6); //O paciente prioritario deve ser retirado primeiro 
    }


    private Paciente Paciente(String rodrigo, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
