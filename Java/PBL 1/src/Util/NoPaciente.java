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
public class NoPaciente {
    private Paciente paciente;
    private NoPaciente prox;

    public NoPaciente(Paciente paciente){
        this.paciente = paciente;
    }
    
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public NoPaciente getProx() {
        return prox;
    }

    public void setProx(NoPaciente prox) {
        this.prox = prox;
    }

}
