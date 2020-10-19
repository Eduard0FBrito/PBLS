/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Model.Medico;
import Model.Paciente;

/**
 *
 * @author Eduardo
 */
public class ListaMedico {
    private NoMedico primeiro;
    
    public ListaMedico(){
        Medico m1 = new Medico("Eduardo", 1);
        Medico m2 = new Medico("Moises", 2);
        this.inserir(m1);
        this.inserir(m2);
    }

    public int len(){
        NoMedico aux = primeiro;
        int i = 0;
        while(aux != null){
            aux = aux.getProx();
            i++;
        }
        return i;
    }
    
    public void inserir(Medico medico){
        NoMedico no = new NoMedico(medico);
        if(primeiro==null){
            primeiro = no;
        }else{
            NoMedico aux = primeiro;
            while(aux.getProx()!=null){
                aux = aux.getProx();
            }
            aux.setProx(no);
        }
    }
    
    public Medico remover(int crm){
        NoMedico anterior = null;
        NoMedico aux = this.primeiro;
        while(aux != null && aux.getMedico().getCRM() != crm){
            anterior = aux;
            aux = aux.getProx();
        }
        if(aux == null){
            return null;
        }
        if(anterior == null){
            this.primeiro = aux.getProx();
            return aux.getMedico();
        }else{
            anterior.setProx(aux.getProx());
            return aux.getMedico();
        }
    }
    
    /**Buscar um paciente, passando a matricula
     * @param crm
     * @return */
	public Medico buscar(int crm){
            for(NoMedico aux = this.primeiro; aux != null; aux = aux.getProx()){
                if(aux.getMedico().getCRM()== crm){
                    return aux.getMedico();
                }
            }
            return null; /* nao achou o elemento*/
        }
        
    public String exibeLista(){
        String str = "------------------------\nLista de Medicos:";
        for (NoMedico no = primeiro; no != null; no = no.getProx()){
            str+= "\n"+ no.getMedico()+"\n";
            if (no.getProx() == null){
                str += "------------------------";
            }
        }
        return str;
    }
    
    public Medico menorFila(){
        NoMedico menor = primeiro;
        for(NoMedico aux = this.primeiro; aux != null; aux = aux.getProx()){
            if(aux.getMedico().getPacientes().len() < menor.getMedico().getPacientes().len()){
                menor = aux;
                }
            }
        return menor.getMedico();
    }
}
