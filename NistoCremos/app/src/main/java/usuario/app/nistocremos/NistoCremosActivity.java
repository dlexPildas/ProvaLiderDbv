package usuario.app.nistocremos;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

import usuario.app.nistocremos.Model.Model;
import usuario.app.nistocremos.Model.Questao;

public class NistoCremosActivity extends AppCompatActivity {
    //Atributos
    Button botaoNistoCremos, botaoECA, botaoLivro;
    Intent telaNC, telaECA, telaLivroNC, telaSobre, telaMensagem;
    MenuItem menuSobre;
    LinkedList<Questao> listaQuestao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nisto_cremos);
        botaoNistoCremos = (Button) findViewById(R.id.botaoNistoCremos);
        botaoECA = (Button) findViewById(R.id.botaoECA);
        botaoLivro = (Button) findViewById(R.id.botaoLivroNistoCremos);
        menuSobre = (MenuItem) findViewById(R.id.sobre);


        //botao para mudar para tela Perguntas Nisto Cremos
        botaoNistoCremos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telaNC = new Intent(NistoCremosActivity.this, PerguntasNistoCremosActivity.class);
                startActivity(telaNC);
            }
        });
        //Botão para mudar para tela Livro
        botaoLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telaLivroNC = new Intent(NistoCremosActivity.this, LivroPDFNistoCremosActivity.class);
                startActivity(telaLivroNC);
            }
        });
        //Botão para mudar para tela ECA
        botaoECA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telaECA = new Intent(NistoCremosActivity.this, PerguntasECAActivity.class);
                startActivity(telaECA);
            }
        });

        //Botão para mudar para tela "sobre"


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.mensagem);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Estude! Prova de Líder vem aí", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nisto_cremos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if (id == R.id.sobre){
            telaSobre = new Intent(NistoCremosActivity.this, SobreActivity.class);
            startActivity(telaSobre);
        }else if(id == R.id.Mensagem){
            telaMensagem = new Intent(NistoCremosActivity.this, MensagemActivity.class);
            startActivity(telaMensagem);
        }


        return super.onOptionsItemSelected(item);
    }




}
