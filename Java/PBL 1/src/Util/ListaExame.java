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
public class ListaExame {
    
    private NoExame primeiro;
    
    public int len(){
        NoExame aux = primeiro;
        int i = 0;
        while (aux != null){
            aux = aux.getProx();
            i++;
        }
        return i;
    }
    
    public void inserir(Exame exame){
        NoExame no = new NoExame(exame);
        if(primeiro==null){
            primeiro = no;
        }else{
            NoExame aux = primeiro;
            while(aux.getProx()!=null){
                aux = aux.getProx();
            }
            aux.setProx(no);
        }
    }
    
    public Exame remover(int tipo){
        NoExame anterior = null;
        NoExame aux = this.primeiro;
        while(aux != null && aux.getExame().getTipo() != tipo){
            anterior = aux;
            aux = aux.getProx();
        }
        if(aux == null){
            return null;
        }
        if(anterior == null){
            this.primeiro = aux.getProx();
            return aux.getExame();
        }else{
            anterior.setProx(aux.getProx());
            return aux.getExame();
        }
    }
    
    /**Buscar um Exame, passando seu tipo
     * @param tipo
     * @return */
    public Exame buscar(int tipo){
        for(NoExame aux = this.primeiro; aux != null; aux = aux.getProx()){
            if(aux.getExame().getTipo() == tipo){
                return aux.getExame();
            }
        }
        return null; /* nao achou o elemento*/
    }
    
    public String exibeLista(){
        String str = "------------------------\nLista de Exames: \n";
        for (NoExame no = primeiro; no != null; no = no.getProx()){
            str+= "\n"+ no.getExame()+"\n";
            if (no.getProx() == null){
            str += "------------------------";
            }
        }
        return str;
    }
}