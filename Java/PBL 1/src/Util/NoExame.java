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
public class NoExame {
    private Exame exame;
    private NoExame prox;

    public NoExame(Exame exame){
        this.exame = exame;
    }
    
    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    public NoExame getProx() {
        return prox;
    }

    public void setProx(NoExame prox) {
        this.prox = prox;
    }

}