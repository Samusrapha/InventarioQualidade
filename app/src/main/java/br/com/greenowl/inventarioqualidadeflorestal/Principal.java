package br.com.greenowl.inventarioqualidadeflorestal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.greenowl.inventarioqualidadeflorestal.App.MessageBox;
import br.com.greenowl.inventarioqualidadeflorestal.Database.Database;
import br.com.greenowl.inventarioqualidadeflorestal.Database.Parse;
import br.com.greenowl.inventarioqualidadeflorestal.Dominio.Entidades.Contato;
import br.com.greenowl.inventarioqualidadeflorestal.Dominio.Repositorioformulario;

//import com.firebase.client.Firebase;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, AdapterView.OnItemClickListener {

    //Firebase objectRef;


    /*Nome da tabela, a função 'final' significa que essa String vai ser usada como constante*/
    public static final String PAR_TALHOES = "TALHOES";
    /*LIST VIEW DOS TALHOES*/
    private ListView lsttalhoes;
    private ArrayAdapter<Contato> adptalhoes;
    private br.com.greenowl.inventarioqualidadeflorestal.Database.Database Database;
    private br.com.greenowl.inventarioqualidadeflorestal.Database.Parse Parse;
    private SQLiteDatabase Conn;
    private Repositorioformulario repositorioContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  Firebase.setAndroidContext(this);
       // objectRef=new Firebase("https://datafauna-9aa2b.firebaseio.com/");



        setContentView(R.layout.activity_principal);
        lsttalhoes = (ListView) findViewById(R.id.lsttalhoes);
        // btnAdicionar.setOnClickListener(this);
        lsttalhoes.setOnItemClickListener(this);

        try {
            Database = new Database(this);
            Conn = Database.getWritableDatabase();
            repositorioContato = new Repositorioformulario(Conn);
            adptalhoes = repositorioContato.buscacontatos(this);
            lsttalhoes.setAdapter(adptalhoes);
            //FiltraDados filtraDados = new FiltraDados(adptalhoes);
            //edtPesquisa.addTextChangedListener(filtraDados);
        } catch (SQLException ex) {
            MessageBox.show(this, "Erro", "Erro ao criar o banco " + ex.getMessage());
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.help);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Popup.class);
                startActivityForResult(intent, 0);

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

       @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//-------------------------------------------------------------------------------------------
// botao importar
        if (id == R.id.bt_import) {
            Parse importar = new Parse();
            ArrayList<Contato> contatos = importar.Importardados(this.getApplicationContext());
            Database = new Database(this);
            Conn = Database.getWritableDatabase();
            Repositorioformulario dbxml = new Repositorioformulario(Conn);
            for (int i = 0; i < contatos.size(); i++) {
                dbxml.inserir(contatos.get(i));
            }
            try {
                Database = new Database(this);
                Conn = Database.getWritableDatabase();
                repositorioContato = new Repositorioformulario(Conn);
                adptalhoes = repositorioContato.buscacontatos(this);
                lsttalhoes.setAdapter(adptalhoes);
            } catch (SQLException ex) {
                MessageBox.show(this, "Erro", "Erro ao criar o banco " + ex.getMessage());
            }
//-------------------------------------------------------------------------------------------
// botao exportar

        } else if (id == R.id.nav_gallery) {
            Database = new Database(this);
            Conn = Database.getWritableDatabase();
            Repositorioformulario dbxml = new Repositorioformulario(Conn);
            ArrayAdapter<Contato> contato = dbxml.buscacontatos(this);
            Parse exportar = new Parse();
            ArrayList<Contato> Lcontato = exportar.AdaptertoList(contato);
            exportar.Exportarxml(Lcontato);
            Toast.makeText(this, "Exportado com sucess XD", Toast.LENGTH_SHORT);

//-------------------------------------------------------------------------------------------
// botao calcular

        } else if (id == R.id.nav_slideshow) {

            Intent it = new Intent(this, Expandable.class);
            startActivityForResult(it, 0);

        } else if (id == R.id.nav_manage) {


        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {
        }

//-------------------------------------------------------------------------------------------
// botao inserir demo

        else if (id == R.id.nav_demo) {
           final Database adddemo = new Database(this);
            new AlertDialog.Builder(this)
                    .setTitle("Baixar informações demonstração")
                    .setMessage("Deseja apagar as informações e prencher com os dados demonstração?")
                    .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            adddemo.adddemo();
                            lsttalhoes = (ListView) findViewById(R.id.lsttalhoes);
                            finish();
                            startActivity(getIntent());

                        }
                    })
                    .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View view) {

        Intent it = new Intent(this, Formulario.class);
        startActivityForResult(it, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        adptalhoes = repositorioContato.buscacontatos(this);

        lsttalhoes.setAdapter(adptalhoes);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Contato contato = adptalhoes.getItem(position);

        Intent it = new Intent(this, Formulario.class);
        it.putExtra(PAR_TALHOES, contato);

        startActivityForResult(it, 0);
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.d(this.getClass().getName(), "back button pressed");


            new AlertDialog.Builder(this)
                    .setTitle("Sair do aplicativo")
                    .setMessage("Quer mesmo sair?")
                    .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            finish();
                            System.exit(0);
                            //android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    })
                    .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        return super.onKeyDown(keyCode, event);
    }


/*
    private class FiltraDados implements TextWatcher
    {
        private ArrayAdapter<Contato> arrayAdapter;

        private FiltraDados(ArrayAdapter<Contato> arrayAdapter)
        {
            this.arrayAdapter = arrayAdapter;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            arrayAdapter.getFilter().filter(s);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }*/
}
