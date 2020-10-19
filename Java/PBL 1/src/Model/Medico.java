/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Util.FilaPaciente;
import Util.ListaPaciente;
import java.util.Random;


/**
 *
 * @author Eduardo
 */
public class Medico {
    
    private int CRM;
    private String nome;
    private FilaPaciente pacientes;
    private ListaPaciente atendidos;

    public Medico(String nome, int crm){
        this.CRM = crm;
        this.nome = nome;
        this.nome = nome;
        this.pacientes = new FilaPaciente();
        this.atendidos = new ListaPaciente();
    }

    public int getCRM() {
        return CRM;
    }

    public void setCRM(int CRM) {
        this.CRM = CRM;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public FilaPaciente getPacientes() {
        return pacientes;
    }

    public void setPacientes(FilaPaciente pacientes) {
        this.pacientes = pacientes;
    }

    public ListaPaciente getAtendidos() {
        return atendidos;
    }

    public void setAtendidos(ListaPaciente atendidos) {
        this.atendidos = atendidos;
    }
    
    public int realizarAtendimento(){
        Random gerador = new Random();
        int num = gerador.nextInt(4);
        return num;
    }
    
    @Override
    public String toString(){
        String completo = "Nome do Medico: "+nome+"\nCRM: "+CRM;
        return completo;
    }
}
