/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Model.Paciente;

/**
 *
 * @author Eduardo
 */
public class ListaPacienteDAO {
    
    private static ListaPaciente lista = new ListaPaciente();
    
    public static int len(){
        return lista.len();
    }
    
    public static void add(Paciente pac){
        lista.inserir(pac);
    }
    
    public static Paciente remover(int matricula){
        return lista.remover(matricula);
    }
    
    public static Paciente buscar(int matricula){
        return lista.buscar(matricula);
    }
    
    public static String exibirLista(){
        return lista.exibeLista();
    }
    
    public static void pacAguardando(int tipo){
        lista.pacAguardando(tipo);
    }
    
    public static void realizarExame(int tipo){
        lista.realizarExame(tipo);
    }
}
