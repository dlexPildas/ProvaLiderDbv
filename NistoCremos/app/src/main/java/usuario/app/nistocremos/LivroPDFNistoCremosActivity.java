package usuario.app.nistocremos;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.content.Intent;
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

import com.github.barteksc.pdfviewer.PDFView;

public class LivroPDFNistoCremosActivity extends AppCompatActivity {
    FloatingActionButton botaoFlutuanteVoltar;
    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livro_pdfnisto_cremos);
        pdfView = (PDFView) findViewById(R.id.pdfLivroNC);
        botaoFlutuanteVoltar = (FloatingActionButton)findViewById(R.id.botaoFlutuanteVoltar);

        pdfView.fromAsset("PDFlivroNistoCremos.pdf").load(); //coloca o livro na tela


        //Action do botao Flutuante
        botaoFlutuanteVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               LivroPDFNistoCremosActivity.this.finish(); //Fecha a tela
            }
        });
    }
}
