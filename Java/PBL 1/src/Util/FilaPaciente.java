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
public class FilaPaciente{
    private NoPaciente primeiro;
    private NoPaciente ultimoPrio;
    
    public int len(){
        NoPaciente aux = primeiro;
        int i = 0;
        while (aux != null){
            aux = aux.getProx();
            i++;
        } 
        return i;
    }

    public void inserir(Paciente paciente) {
        NoPaciente novoNo = new NoPaciente(paciente);
        NoPaciente aux = this.primeiro;
        NoPaciente auxPrio = this.ultimoPrio;
        if(aux != null){
            if(paciente.isPrioritario()){
               if(auxPrio == null){
                   if (this.len() == 0){
                        this.primeiro = novoNo;
                        this.ultimoPrio = novoNo;
                   } else {
                       novoNo.setProx(this.primeiro);
                       this.primeiro = novoNo;
                       this.ultimoPrio = novoNo;
                   }
               } else {
                   novoNo.setProx(auxPrio.getProx());
                   auxPrio.setProx(novoNo);
                   this.ultimoPrio = novoNo;
               }
            } else {
                while(aux.getProx() != null){
                    aux = aux.getProx();
                }
                aux.setProx(novoNo);
            }
        } else { 
            if(paciente.isPrioritario()){
                this.ultimoPrio = novoNo;
            }
            this.primeiro = novoNo;
            novoNo.setProx(null);
        } 
    }
    
    public Paciente remover(int matricula){
        NoPaciente anterior = null;
        NoPaciente aux = this.primeiro;
        while(aux != null && aux.getPaciente().getMatricula() != matricula){
            anterior = aux;
            aux = aux.getProx();
        }
        if(aux == null){
            return null;
        }
        if(anterior == null){
            this.primeiro = aux.getProx();
            return aux.getPaciente();
        }else{
            anterior.setProx(aux.getProx());
            return aux.getPaciente();
        }
    }
    
     /**
     * Remoção no inicio
     * @return 
     */
    public Paciente remover() {
        if (primeiro != null) {
            Paciente paciente = primeiro.getPaciente();
            primeiro = primeiro.getProx();
            return paciente;
        }
        System.out.println("A fila esta vazia.");
        return null;
    }
    
    public Paciente buscar(int matricula){
        for(NoPaciente aux = this.primeiro; aux != null; aux = aux.getProx()){
            if(aux.getPaciente().getMatricula()== matricula){
                return aux.getPaciente();
            }
        }
        return null; /* nao achou o elemento*/
    }
    
    public String exibeLista(){
        String str = "------------------------\nFila de Pacientes: \n";
        for (NoPaciente no = primeiro; no != null; no = no.getProx()){
            str+= "\n"+ no.getPaciente()+"\n";
            if (no.getProx() == null){
                str += "------------------------";
            }
        }
        return str;
    }
}
