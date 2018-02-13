package usuario.app.nistocremos.Model;

/**
 * Created by Dlex on 14/08/2017.
 */

public class Questao {
    private String pergunta;
    private String [] alternativas;
    private String correta;

    public Questao(String pergunta, String[] alternativas, String correta) {
        this.pergunta = pergunta;
        this.alternativas = alternativas;
        this.correta = correta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getAlternativas(int num) {
        return alternativas[num];
    }

    public void setAlternativas(String[] alternativas) {
        this.alternativas = alternativas;
    }

    public String getCorreta() {
        return correta;
    }

    public void setCorreta(String correta) {
        this.correta = correta;
    }
}
