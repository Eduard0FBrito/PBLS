/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Util.ListaExame;
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
public class PacienteTest {
    
    private Paciente P1;
    private Paciente P2;
    private Paciente P3;
    private Exame E1;
    private Exame E2;
    private ListaExame lista;
    
    public PacienteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
       P1 = new Paciente("Tony", true, 1);
       P2 = new Paciente("Moises", false, 2);
       P3 = new Paciente("Digoio", false, 3);
       E1 = new Exame(1);
       E1 = new Exame(2);
       lista = P1.getLista();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getMatricula method, of class Paciente.
     */
    @Test
    public void testGetMatricula() {
        assertEquals(1, P1.getMatricula()); //Como a matricula é gerada de forma automatica a cada nova instancia é somado
        // mais um, como p1 foi o primeiro paciente instanciado sua matricula é 1
        assertTrue(P2.getMatricula() == 2); //P2 tem como numero de matricula 2
        assertFalse(P3.getMatricula() != 3); //Dessa mesma forma p3 nao pode apresentar matricula diferente de 3
    }

    /**
     * Test of setMatricula method, of class Paciente.
     */
    @Test
    public void testSetMatricula() {
        assertEquals(1, P1.getMatricula()); //Como p1 foi o primeiro instaciando sua matricula é 1
        P1.setMatricula(10); //Alterando matricula para 10
        assertTrue(P1.getMatricula() == 10); //Certificando que a matricula alterou
        assertFalse(P1.getMatricula() != 10); //Caso não aja mais nenhuma alteração a matricula continua no valor 10
    }

    /**
     * Test of getNome method, of class Paciente.
     */
    @Test
    public void testGetNome() {
        assertEquals("Tony", P1.getNome()); // Garantindo que a função retorna o nome que passei por parametro
        P1.setNome("Moises"); //P2 tem como numero de matricula 1
        assertEquals("Moises",P1.getNome()); // Garantindo que a função retorna o nome que passei por parametro
        assertTrue(P1.getNome() == "Moises"); //Caso não seja alterado, o valor deve permanecer o mesmo
    }

    /**
     * Test of setNome method, of class Paciente.
     */
    @Test
    public void testSetNome() {
        assertEquals("Moises", P2.getNome()); //
        P2.setNome("Guto"); //Alterando matricula para 10
        assertTrue(P2.getNome() == "Guto"); //Certificando que a matricula alterou
        assertFalse(P2.getNome() != "Guto"); //Caso não aja mais nenhuma alteração a matricula continua no valor 10
    }

    /**
     * Test of getLista method, of class Paciente.
     */
    @Test
    public void testGetLista() {
       lista.inserir(E1);
       lista.inserir(E2);
       assertTrue(lista.len() == 2); //Ao adicionar 2 exames na lista do paciente a função tamanho deve retornar 2
    }

    /**
     * Test of setLista method, of class Paciente.
     */

    /**
     * Test of isPrioritario method, of class Paciente.
     */
    @Test
    public void testIsPrioritario() {
        assertTrue(P1.isPrioritario()); //Como P1 é prioritario a função deve retornar true
        assertFalse(P2.isPrioritario()); //Como P2 não é, deve retornar falso
        P2.setPrioritario(true); //Alterando P2 para prioritario
        assertEquals(true, P2.isPrioritario()); //Dessa forma a função deve retornar true
    }

    /**
     * Test of setPrioritario method, of class Paciente.
     */
    @Test
    public void testSetPrioritario() {
        assertFalse(P2.isPrioritario()); //Como P2 não é, deve retornar falso
        P2.setPrioritario(true); //Alterando P2 para prioritario
        assertEquals(true, P2.isPrioritario()); //Dessa forma a função deve retornar true
        assertTrue(P2.isPrioritario()); // P2 deve permanecer prioritario ate que isso seja alterado novamente
    }
}
