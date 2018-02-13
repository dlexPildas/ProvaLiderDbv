package usuario.app.nistocremos.Model;

/**
 * Created by Dlex on 28/08/2017.
 */

public class CapituloNaoEncontradoException extends Exception {
    public CapituloNaoEncontradoException(){
        super ("Não temos perguntas para esse capítulo!");
    }
}
