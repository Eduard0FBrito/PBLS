/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Util.FilaPaciente;
import Util.ListaPaciente;
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
public class MedicoTest {
    
    private Medico m1;
    private Medico m2;
    private Medico m3;
    private Paciente p1;
    private Paciente p2;
    
    public MedicoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        m1 = new Medico("Moises",1);
        m2 = new Medico("Galego",2);
        m3 = new Medico("Nailson",3);
        p1 = new Paciente("Praxedes", true, 1);
        p2 = new Paciente("Robert", false, 2);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCRM method, of class Medico.
     */
    @Test
    public void testGetCRM() {
        assertEquals(1,m1.getCRM());
        assertFalse(m2.getCRM() == 1);
        m3.setCRM(10);
        assertTrue(m3.getCRM() == 10);
    }

    /**
     * Test of setCRM method, of class Medico.
     */
    @Test
    public void testSetCRM() {
        assertEquals(1, m1.getCRM()); //Como m1 foi o primeiro instaciando sua matricula é 1
        m1.setCRM(10); //Alterando CRM para 10
        assertTrue(m1.getCRM() == 10); //Certificando que o CRM foi alterada
        assertFalse(m1.getCRM() != 10); //Caso não aja mais nenhuma alteração o CRM continua no valor 10
    }

    /**
     * Test of getNome method, of class Medico.
     */
    @Test
    public void testGetNome() {
        assertEquals("Moises", m1.getNome()); // Garantindo que a função retorna o nome que passei por parametro
        m1.setNome("Luiza"); //P2 tem como numero de matricula 1
        assertEquals("Luiza", m1.getNome()); // Garantindo que a função retorna o nome que passei por parametro
        assertTrue("Luiza".equals(m1.getNome())); //Caso não seja alterado, o valor deve permanecer o mesmo
    }

    /**
     * Test of setNome method, of class Medico.
     */
    @Test
    public void testSetNome() {
        assertEquals("Galego", m2.getNome()); //
        m2.setNome("Emilia"); //Alterando matricula para 10
        assertTrue("Emilia".equals(m2.getNome())); //Certificando que o nome alterou
        assertFalse(!"Emilia".equals(m2.getNome())); //Caso não aja mais nenhuma alteração o nome continua 
    }

    /**
     * Test of getPacientes method, of class Medico.
     */
    @Test
    public void testGetPacientes() {
        FilaPaciente fila = m1.getPacientes();
        fila.inserir(p1);
        fila.inserir(p2);
        assertEquals(2,m1.getPacientes().len());
        Paciente p3 = m1.getPacientes().remover();
        assertSame(p1, p3);
    }  
}
