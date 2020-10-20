/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Model.Medico;

/**
 *
 * @author Eduardo
 */
public class NoMedico {
    private Medico medico;
    private NoMedico prox;

    public NoMedico(Medico medico){
        this.medico = medico;
    }
    
    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public NoMedico getProx() {
        return prox;
    }

    public void setProx(NoMedico prox) {
        this.prox = prox;
    }

}
