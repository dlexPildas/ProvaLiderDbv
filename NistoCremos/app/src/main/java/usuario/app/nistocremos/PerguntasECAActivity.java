package usuario.app.nistocremos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;






public class PerguntasECAActivity extends AppCompatActivity {
    FloatingActionButton botaoFlutuanteVoltar;
    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_eca);
        pdfView = (PDFView)findViewById(R.id.pdfECA);
        botaoFlutuanteVoltar = (FloatingActionButton)findViewById(R.id.botaoFlutuanteVoltar);

        //Carrega o pdf
        pdfView.fromAsset("EstatutoCriancaAdolescente.pdf").load();

        //Action do botao Flutuante
        botaoFlutuanteVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerguntasECAActivity.this.finish(); //Fecha a tela
            }
        });
    }
}
