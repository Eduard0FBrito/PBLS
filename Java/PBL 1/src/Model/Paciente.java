/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Util.ListaExame;

/**
 *
 * @author Eduardo
 */
public class Paciente{
    
    private int matricula;
    private String nome;
    private boolean prioritario;
    private ListaExame listaExames;

    public Paciente(String nome, boolean prioritario, int matricula) {
        this.matricula = matricula;
        this.nome = nome;
        this.prioritario = prioritario;
        this.listaExames = new ListaExame();
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ListaExame getLista() {
        return listaExames;
    }

    public void setLista(ListaExame lista) {
        this.listaExames = lista;
    }

    public boolean isPrioritario() {
        return prioritario;
    }

    public void setPrioritario(boolean prioritario) {
        this.prioritario = prioritario;
    }
    
    
    @Override
    public String toString(){
        String completo = "Nome do Paciente: "+nome+"\nNº de Matricula: "+matricula;
        return completo;
    }
    
}
