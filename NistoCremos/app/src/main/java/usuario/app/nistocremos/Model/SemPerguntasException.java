package usuario.app.nistocremos.Model;

/**
 * Created by Dlex on 29/08/2017.
 */

public class SemPerguntasException extends Exception {
    public SemPerguntasException() {
        super("Não temos perguntas para esse capítulo");
    }
}
