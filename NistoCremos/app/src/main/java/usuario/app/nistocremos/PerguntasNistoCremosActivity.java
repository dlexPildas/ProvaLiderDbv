package usuario.app.nistocremos;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.*;
import android.view.*;
import android.widget.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Random;

import usuario.app.nistocremos.Model.CapituloNaoEncontradoException;
import usuario.app.nistocremos.Model.Questao;

public class PerguntasNistoCremosActivity extends AppCompatActivity {
    //Atributos
    Button botaoVoltar, botaoResposta, botaoProxima, botaoPesquisar;
    RadioGroup opcoes;
    RadioButton a, b, c, d;
    TextView pergunta;
    EditText textoNumero;
    ProgressBar barraScore;
    LinkedList<Questao> listaQuestoes= new LinkedList<>();
    Questao questao;
    Random gerador = new Random();
    int acertos, erros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_nisto_cremos);


        //
        a = (RadioButton) findViewById(R.id.rbA);
        b = (RadioButton) findViewById(R.id.rbB);
        c = (RadioButton) findViewById(R.id.rbC);
        d = (RadioButton) findViewById(R.id.rbD);
        pergunta = (TextView) findViewById(R.id.textoPergunta);
        textoNumero = (EditText)findViewById(R.id.textoEscolhaCapitulo);
        barraScore = (ProgressBar) findViewById(R.id.barraPontos);
        botaoResposta =  (Button) findViewById(R.id.botaoResposta);
        botaoVoltar = (Button)findViewById(R.id.botaoFlutuanteVoltar);
        botaoProxima = (Button)findViewById(R.id.botaoProximaPergunta);
        botaoPesquisar = (Button)findViewById(R.id.botaoPesquisar);



        //Action do botão Pesquisar
        botaoPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Altera os valores das variáveis erros, acertos e barraScore para 0
                acertos=0; erros=0; barraScore.setProgress(0);
                barraScore.setMax(27);
                String num = (String) textoNumero.getText().toString();//Pega o valor digitado e coloca na string
                if(!num.isEmpty()){ //Verifica se String está vazia
                    int n = Integer.parseInt(num); //converte o valor da string em inteiro
                    try {
                        lerArquivo(n); //Chama o método para ler o capítulo o capítulo escolhido
                        int i=gerador.nextInt(listaQuestoes.size())+1;
                        questao = listaQuestoes.get(i);//Atribue uma questão da lista a variável "questao"
                        listaQuestoes.remove(i);
                        //Atribua a questão nas variáveis
                        a.setText(questao.getAlternativas(0));
                        b.setText(questao.getAlternativas(1));
                        c.setText(questao.getAlternativas(2));
                        d.setText(questao.getAlternativas(3));
                        pergunta.setText(questao.getPergunta());
                    } catch (CapituloNaoEncontradoException e) { //Se houve alguma exceção ele capitura
                        AlertDialog.Builder dialogo = new AlertDialog.Builder(PerguntasNistoCremosActivity.this);
                        dialogo.setTitle("Opa!");
                        dialogo.setMessage(e.getMessage());
                        dialogo.setNeutralButton("ok", null);
                        dialogo.show();
                    }
                }else{ //Se a string estiver vazia lança uma mensagem de erro
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(PerguntasNistoCremosActivity.this);
                    dialogo.setTitle("Opa!");
                    dialogo.setMessage("Informe o número do capítulo");
                    dialogo.setNeutralButton("ok", null);
                    dialogo.show();
                }
            }
        });

        //Action do botão Próxima
        botaoProxima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barraScore.incrementProgressBy(1);
                //Verifica se a lista não está vazia e adiciona uma nova questão
                if(!listaQuestoes.isEmpty() ) {
                    int i = gerador.nextInt(listaQuestoes.size());
                    questao = listaQuestoes.remove(i);
                    a.setText(questao.getAlternativas(0));
                    b.setText(questao.getAlternativas(1));
                    c.setText(questao.getAlternativas(2));
                    d.setText(questao.getAlternativas(3));
                    pergunta.setText(questao.getPergunta());
                }else{
                    //Verifica o total de acertos e erros e gera uma mensagem
                    String mensagem;
                    if(acertos==erros)
                        mensagem = "Tá na média!Não se acomode.";
                    else if (acertos>erros)
                        mensagem = "Continue assim!";
                    else
                        mensagem = "Estude mais!";
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(PerguntasNistoCremosActivity.this);
                    dialogo.setTitle("Avaliação!");
                    dialogo.setMessage("Acertos = "+acertos+"\nErros = "+erros+"\n"+mensagem);
                    dialogo.setNeutralButton("ok", null);
                    dialogo.show();
                }
            }
        });
        //Action do botão resposta
        botaoResposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Verifica se a resposta está ou não correta e lança uma mensagem
                boolean resposta = false;
                if(a.isChecked() && a.getText().equals(questao.getCorreta())) {
                    resposta = true;
                }
                else if(b.isChecked() && b.getText().equals(questao.getCorreta())) {
                    resposta = true;
                }
                else if(c.isChecked() && c.getText().equals(questao.getCorreta())) {
                    resposta = true;
                }
                else if(d.isChecked() && d.getText().equals(questao.getCorreta())) {
                    resposta = true;
                }
                AlertDialog.Builder dialogo = new AlertDialog.Builder(PerguntasNistoCremosActivity.this);
                dialogo.setTitle("Resposta");
                if (resposta==true) {
                    dialogo.setMessage("Correta");
                    acertos++;
                }else {
                    dialogo.setMessage("Errada");
                    erros++;
                }
                dialogo.setNeutralButton("ok",null);
                dialogo.show();


            }
        });
        //Action do botão voltar
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerguntasNistoCremosActivity.this.finish(); //fecha a tela
            }
        });

    }


    //método para ler o arquivo
    public void lerArquivo(int num) throws CapituloNaoEncontradoException{
        listaQuestoes.removeAll(listaQuestoes); //Remove todas as questões que tinha na lista
        try {
            //Veriica se tem perguntas para o capítulo podendo ou não lança uma exceção
            if (num<1 || num>3){
                CapituloNaoEncontradoException e;
                throw e=new CapituloNaoEncontradoException();
            }else{
                AssetManager assetManager = getResources().getAssets();
                InputStream inputStream = assetManager.open("Capitulo"+num+".txt");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String linha;
                while( (linha = bufferedReader.readLine())!=null){
                    String quebra[] = linha.split(";");
                    String alternativas[] = {quebra[1], quebra[2], quebra[3], quebra[4]};
                    Questao questao = new Questao(quebra[0],alternativas, quebra[5]);
                    listaQuestoes.add(questao);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
