/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Model.Exame;

/**
 *
 * @author Eduardo
 */
public class ListaExameDAO {
    
    private static ListaExame lista = new ListaExame();
    
    public static int len(){
        return lista.len();
    }
    
    public static void add(Exame exame){
        lista.inserir(exame);
    }
    
    public static Exame remove(int tipo){
        return lista.remover(tipo);
    }
    
    public static Exame buscar(int tipo){
        return lista.buscar(tipo);
    }
    
    public static String exibeLista(){
        return lista.exibeLista();
    }
}
