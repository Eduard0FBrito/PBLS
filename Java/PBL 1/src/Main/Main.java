/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Model.Exame;
import Model.Medico;
import Model.Paciente;
import Util.FilaPaciente;
import Util.ListaExameDAO;
import Util.ListaMedico;
import Util.ListaPacienteDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int crm = 3;
        int matricula = 1;
        
        Exame e1 = new Exame(1);
        Exame e2 = new Exame(2);
        Exame e3 = new Exame(3);
        ListaExameDAO.add(e1);
        ListaExameDAO.add(e2);
        ListaExameDAO.add(e3);
        
        ListaMedico listaMed = new ListaMedico();
        boolean continuar = true;
        
        while (continuar){
            int escolha;
            escolha = Integer.parseInt(JOptionPane.showInputDialog("\n1 - Medicos.\n2 - Pacientes.\n"
                    + "3 - Exames.\n4 - Sair do programa.\n\nQual sua escolha?"));
            if (escolha == 4){
                JOptionPane.showMessageDialog(null, "Finalizando...");
                continuar = false;
            } else if (escolha == 1){
                int opcao;
                opcao = Integer.parseInt(JOptionPane.showInputDialog("\n1 - Listar Medicos.\n2 - Adicionar Novo Medico.\n"
                        + "3 - Editar Nome.\n4 - Listar Pacientes Atendidos.\n5 - Listar Pacientes Esperando.\n6 - Realizar Atendimento.\n"
                        + "7 - Voltar.\n\nQual sua escolha?"));
                if (opcao == 1){
                    System.out.println(listaMed.exibeLista());
                } else if (opcao == 2){
                    String nome;
                    nome = JOptionPane.showInputDialog("Digite o nome do Medico:");
                    Medico med = new Medico(nome, crm);
                    listaMed.inserir(med);
                    crm++;
                    JOptionPane.showMessageDialog(null, "Medico " +med.getNome()+ "\nCRM "+med.getCRM()+"\nSeja Bem-Vindo.");
                } else if (opcao == 3){
                    int cod;
                    String nome;
                    cod = Integer.parseInt(JOptionPane.showInputDialog("Digite o CRM do Medico que deseja alterar o nome:"));
                    if (listaMed.buscar(cod) == null){
                        JOptionPane.showMessageDialog(null, "CRM não encontrado, tente outro.");
                    } else {
                        nome = JOptionPane.showInputDialog("Digite o novo nome:");
                        listaMed.buscar(cod).setNome(nome);
                        JOptionPane.showMessageDialog(null, "Nome alterado com sucesso.");
                    }
                } else if (opcao == 4){
                    int cod;
                    cod = Integer.parseInt(JOptionPane.showInputDialog("Digite o CRM do Medico que deseja visualizar os atendidos:"));
                    if (listaMed.buscar(cod) == null){
                        JOptionPane.showMessageDialog(null, "CRM não encontrado, tente outro.");
                    } else {
                        Medico aux = listaMed.buscar(cod);
                        JOptionPane.showMessageDialog(null, "Medico " +aux.getNome()+ " ja entendeu os seguintes pacientes: ");
                        System.out.println(aux.getAtendidos().exibeLista());
                    }
                } else if (opcao == 5){
                    int cod;
                    cod = Integer.parseInt(JOptionPane.showInputDialog("Digite o CRM do Medico que deseja visualizar os atendidos:"));
                    if (listaMed.buscar(cod) == null){
                        JOptionPane.showMessageDialog(null, "CRM não encontrado, tente outro.");
                    } else {
                        Medico aux = listaMed.buscar(cod);
                        JOptionPane.showMessageDialog(null, "Medico " +aux.getNome()+ ", os seguintes pacientes estão a sua espera: ");
                        System.out.println(aux.getPacientes().exibeLista());
                    }
                } else if (opcao == 6){
                    int cod;
                    cod = Integer.parseInt(JOptionPane.showInputDialog("Digite o CRM do Medico que ira atender:"));
                    Medico medAux = listaMed.buscar(cod);
                    if (medAux == null){
                        JOptionPane.showMessageDialog(null, "CRM não encontrado, tente outro.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Aguarde um momento...");
                        FilaPaciente filaAux = medAux.getPacientes();
                        int tamanho = filaAux.len();
                        if (tamanho == 0){
                            JOptionPane.showMessageDialog(null, "Esse medico ja atendeu todos seus pacientes.");
                        } else {
                            for (int i = 0; i < tamanho; i++){
                                int examesSolicitados = medAux.realizarAtendimento();
                                Paciente pacAux = filaAux.remover();
                                    for (int j = 1; j <= examesSolicitados; j++) {
                                        Exame exameAux = ListaExameDAO.buscar(j);
                                        pacAux.getLista().inserir(exameAux);
                                    }
                                    if(pacAux.getLista().len() == 0){
                                        medAux.getAtendidos().inserir(pacAux);
                                    } else {
                                        medAux.getAtendidos().inserir(pacAux);
                                        ListaPacienteDAO.add(pacAux);
                                    }
                            }
                            JOptionPane.showMessageDialog(null, "Pacientes atendidos e devidamente encaminhados.");
                        }
                    }
                } else if (opcao == 7) {
                    JOptionPane.showMessageDialog(null, "Voltando...");
                
                } else {
                    JOptionPane.showMessageDialog(null, "Escolha invalida, tente novamente.");
                }
            } else if (escolha == 2){
                int opcao;
                opcao = Integer.parseInt(JOptionPane.showInputDialog("\n1 - Adicionar novo Paciente.\n2 - Listar Exames.\n3 - Voltar"));
                if (opcao == 1){
                    String nome;
                    nome = JOptionPane.showInputDialog("Digite o nome do paciente: ");
                    Object [] prio = {"Prioritario", "Não-Prioritario"};
                    String prioridade = String.valueOf(JOptionPane.showInputDialog(
                    null, "Escolha:","Determine a Prioridade",JOptionPane.QUESTION_MESSAGE, null, prio, prio[0]));
                    FilaPaciente filaAux = listaMed.menorFila().getPacientes();
                    
                    if ("Prioritario".equals(prioridade)){
                        Paciente pac = new Paciente(nome, true, matricula);
                        filaAux.inserir(pac);
                        JOptionPane.showMessageDialog(null, pac+"\nCadastrado e devidamente encaminhado.");
                    } else {
                        Paciente pac = new Paciente(nome, false, matricula);
                        filaAux.inserir(pac);
                        JOptionPane.showMessageDialog(null, pac+"\nCadastrado e devidamente encaminhado.");
                    }
                    matricula++;
                } else if (opcao == 2){
                    int cod;
                    cod = Integer.parseInt(JOptionPane.showInputDialog("Digite a Matricula do Paciente que deseja visualizar os Exames: "));
                    Paciente aux = ListaPacienteDAO.buscar(cod);
                    if (aux == null){
                        JOptionPane.showMessageDialog(null, "Matricula não encontrada, tente outra.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Paciente deve realizar os seguntes exames: ");
                        System.out.println(aux.getLista().exibeLista());
                    }
                } else if (opcao == 3){
                    JOptionPane.showMessageDialog(null, "Voltando.");
                } else {
                    JOptionPane.showMessageDialog(null, "Escolha invalida, tente novamente.");
                }
            } else if (escolha == 3){
                int opcao;
                opcao = Integer.parseInt(JOptionPane.showInputDialog("\n1 - Listar Pacientes Aguardando Exame.\n2 - Realizar Exame.\n3 - Voltar."));
                if (opcao == 1){
                    Object [] exames = {"Tipo 1", "Tipo 2", "Tipo 3"};
                    String tipo = String.valueOf(JOptionPane.showInputDialog(
                    null, "Escolha:","Determine o tipo de Exame",JOptionPane.QUESTION_MESSAGE, null, exames, exames[0]));
                    
                    JOptionPane.showMessageDialog(null, "Aguarde um momento.");
                    
                    if ("Tipo 1".equals(tipo)){
                        ListaPacienteDAO.pacAguardando(1);
                    } else if ("Tipo 2".equals(tipo)){
                        ListaPacienteDAO.pacAguardando(2);
                    } else {
                        ListaPacienteDAO.pacAguardando(3);
                    }
                } else if (opcao == 2){
                    Object [] exames = {"Tipo 1", "Tipo 2", "Tipo 3"};
                    String tipo = String.valueOf(JOptionPane.showInputDialog(
                    null, "Escolha:","Determine o tipo de Exame",JOptionPane.QUESTION_MESSAGE, null, exames, exames[0]));
                    
                    JOptionPane.showMessageDialog(null, "Aguarde um momento...");
                    
                    if ("Tipo 1".equals(tipo)){
                        ListaPacienteDAO.realizarExame(1);
                        JOptionPane.showMessageDialog(null, "Exames TIPO 1 realizados com sucesso e pacientes devidamente encaminhados.");
                    } else if ("Tipo 2".equals(tipo)){
                        ListaPacienteDAO.realizarExame(2);
                        JOptionPane.showMessageDialog(null, "Exames TIPO 2 realizados com sucesso e pacientes devidamente encaminhados.");
                    } else {
                        ListaPacienteDAO.realizarExame(3);
                        JOptionPane.showMessageDialog(null, "Exames TIPO 3 realizados com sucesso e pacientes devidamente encaminhados.");
                    }
                } else if (opcao == 3){
                    JOptionPane.showMessageDialog(null, "Voltando.");
                } else {
                    JOptionPane.showMessageDialog(null, "Escolha invalida, tente novamente.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Escolha invalida, tente novamente.");
            }
        }
    }
}