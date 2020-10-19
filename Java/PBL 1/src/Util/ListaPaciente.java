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
public class ListaPaciente {
    private NoPaciente primeiro;

    public int len(){
        NoPaciente aux = primeiro;
        int i = 0;
        while(aux != null){
            aux = aux.getProx();
            i++;
        }
        return i;
    }
    
    public void inserir(Paciente paciente){
        NoPaciente no = new NoPaciente(paciente);
        if(primeiro==null){
            primeiro = no;
        }else{
            NoPaciente aux = primeiro;
            while(aux.getProx()!=null){
                aux = aux.getProx();
            }
            aux.setProx(no);
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
    
    /**Buscar um paciente, passando a matricula
     * @param matricula
     * @return */
    public Paciente buscar(int matricula){
        for(NoPaciente aux = this.primeiro; aux != null; aux = aux.getProx()){
            if(aux.getPaciente().getMatricula()== matricula){
                return aux.getPaciente();
            }
        }
        return null; /* nao achou o elemento*/
    }
        
    public String exibeLista(){
        String str = "------------------------\nLista de Pacientes: \n";
        for (NoPaciente no = primeiro; no != null; no = no.getProx()){
            str+= "\n"+ no.getPaciente()+"\n";
            if (no.getProx() == null){
                str += "------------------------";
            }
        }
        return str;
    }
    
    public void pacAguardando(int tipo){
        int contador = 0;
        String str = "------------------------\nPacientes Aguardando Exame do Tipo "+tipo+": \n";
        for (NoPaciente no = primeiro; no != null; no = no.getProx()){
            if (no.getPaciente().getLista().buscar(tipo) != null){
                contador++;
                str += "\n" +contador+"º a ser atendido:"+"\n"+no.getPaciente()+"\n";
            }
            if (no.getProx() == null){
                str += "------------------------";
            }
        }
        System.out.println(str);
        System.out.println("Um total de "+contador+" pacientes aguardando esse tipo de exame.");
    }
    
    public void realizarExame(int tipo){
        for(NoPaciente no = primeiro; no != null; no = no.getProx()){
            if (no.getPaciente().getLista().buscar(tipo) != null){
                no.getPaciente().getLista().remover(tipo);
                if(no.getPaciente().getLista().len() == 0){
                    this.remover(no.getPaciente().getMatricula());
                }
            }
        }
    }
}
