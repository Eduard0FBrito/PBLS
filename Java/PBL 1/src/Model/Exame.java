/*******************************************************************************
Autor: Eduardo Fernandes de Brito Pereira
Componente Curricular: MI de Programação
Concluido em: 23/11/2019
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum 
trecho de código de outro colega ou de outro autor, tais como provindos de livros e 
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

package Model;

/**
 * A classe <b>Exame</b> define um tipo de dado abstrato  que representa um exame medico no sistema.
 * @author Eduardo Fernandes de Brito Pereira
 */
public class Exame {
    
    private int tipo;
    
    /**
     * Construtor da classe <b>Exame</b>
     * @param tipo 
     */
    public Exame(int tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Recupera o tipo do Exame
     * @return Tipo de Exame
     */
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString(){
        String completo = "Exame: Tipo "+tipo;
        return completo;
    }

}
