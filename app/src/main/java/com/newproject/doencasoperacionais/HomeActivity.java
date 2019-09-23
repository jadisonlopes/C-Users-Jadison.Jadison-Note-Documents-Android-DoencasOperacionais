package com.newproject.doencasoperacionais;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeActivity extends Activity {

    private ImageView IconAjuda;
    private EditText EditPesquisa;
    private TextView Insta;
    private FrameLayout FrameAjuda;
    private ListView List;
    private ArrayList<Object> Array;
    public static SQLiteDatabase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    IconAjuda    = (ImageView) findViewById(R.id.idImage2);
    Insta        = (TextView) findViewById(R.id.idTextPropaganda2);
    FrameAjuda   = (FrameLayout) findViewById(R.id.idFrame1);
    List         = (ListView) findViewById(R.id.idList1);
    EditPesquisa = (EditText) findViewById(R.id.idEdit1);

    dataBase = CreateDataBase();
    IconAjuda.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (FrameAjuda.getVisibility() == View.GONE) {
                FrameAjuda.setVisibility(View.VISIBLE);
            }else{
                FrameAjuda.setVisibility(View.GONE);
            }
        }
    });

    Insta.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://www.instagram.com/jadisonlopess/"));
            startActivity(i);
        }
    });

    EditPesquisa.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                PreencheList();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
//                PreencheList();
        }

        @Override
        public void afterTextChanged(Editable s) {
            PreencheList();
        }
    });
}

    private SQLiteDatabase CreateDataBase(){
        SQLiteDatabase bancoDados = openOrCreateDatabase("Jobsafety",MODE_APPEND,null);
//        bancoDados.execSQL("DROP TABLE IF EXISTS doencas;");
        bancoDados.execSQL("CREATE TABLE IF NOT EXISTS doencas (doenca VARCHAR(80), descricao VARCHAR(300)," +
                "tratamento VARCHAR(300), sintoma VARCHAR(300), funcao VARCHAR(300));");
        dataBase = bancoDados;
        if (SQL("A").getCount()<=0) {
            bancoDados.execSQL("INSERT INTO doencas (doenca,descricao,tratamento,sintoma,funcao) VALUES ('Ler Dort','A sigla LER significa lesões " +
                    "por esforço repetitivo, sendo também denominada como disturbios oesteomusculares relacionados ao trabalho.\nDORT são " +
                    "doenças caracterizadas pelo desgaste de estruturas do sistema do musculo esquelético que atingem varias categorias.','Fisioterapia " +
                    "ocupacional.','Dor " +
                    "localizada, desconforto físico no final do dia, cansaço excessivo, formigamento nas extremidades e enchaço local.'" +
                    ",'Esforços repetitivos disturbios esteomoleculares.');");

            bancoDados.execSQL("INSERT INTO doencas (doenca,descricao,tratamento,sintoma,funcao) VALUES ('Depressão','É um transtorno de humor, uma sindrome em que " +
                    "a principal queixa apresentada pelos pacientes é o humor depressivo e ás vezes estável durante a maior parte do dia.','Consiste no uso de antidepressivo.','" +
                    "Ansiedade, apatia, culpa, descontentamento geral, mudanças de humor, solidão, tristeza, tédio, perda de interesse ou prazer nas atividades.','');");

            bancoDados.execSQL("INSERT INTO doencas (doenca,descricao,tratamento,sintoma,funcao) VALUES ('Dermatose','É uma doença do trabalho que se caracteriza alterações na " +
                    "pele e na mucosa do trabalhador, em razão da sua exposição a determinados agentes nocivos durante o desempenho de suas atividades laboriais, como graxa ou óleo " +
                    "mecânico por exemplo','O tratamento mais comum é feito atravez de antibioticos e outros medicamentos, como tranquilizantes e esteróides.','Dermatite de " +
                    "contato, ulcerações, infecções e cânceres.','');");

            bancoDados.execSQL("INSERT INTO doencas (doenca,descricao,tratamento,sintoma,funcao) VALUES ('Surdez Temporária ou Definitiva','Caracterizada pela perda da sensibilidade " +
                    "auditiva em razão da intensa e prolongada exposição a ruídos.','-','Se em estágio avançado a surdez pode se tornar irreverssivel.','Quando a exposição ao ruído " +
                    "e de forma súbita e muito intensa, pode ocorrer o trauma acústico.');");
        }
        return bancoDados;
    }

    public void PreencheList(){
        Array = adicionar();
        ArrayAdapter adapter = new Adapter(HomeActivity.this, Array);
        List.setAdapter(adapter);
    }

    private ArrayList<Object> adicionar() {
        ArrayList<Object> ListArray = new ArrayList<Object>();
        Object e = null;

        Cursor Consulta = SQL(EditPesquisa.getText().toString());

        while (Consulta.getCount() > Consulta.getPosition() ){
            e = new Object(Consulta.getString(Consulta.getColumnIndex("doenca")),
                    Consulta.getString(Consulta.getColumnIndex("descricao")),
                    Consulta.getString(Consulta.getColumnIndex("sintoma")),
                    Consulta.getString(Consulta.getColumnIndex("tratamento")),
                    Consulta.getString(Consulta.getColumnIndex("funcao")));
            Consulta.moveToNext();
            ListArray.add(e);
        }
        return ListArray;
    }

    public static Cursor SQL(String doenca){
        String S = "";
        if (!doenca.equals("")){
            S=S+" AND lower(doenca) like lower('%"+doenca+"%') ";
        }
        else{
            S=S+" AND 1=2 ";
        }
        S="SELECT * FROM doencas WHERE 1=1 "+S+" ORDER BY doenca; ";
        Cursor cursor = dataBase.rawQuery(S,null);
        cursor.moveToFirst();
        return cursor;
    }
}