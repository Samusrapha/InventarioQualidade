package br.com.greenowl.inventarioqualidadeflorestal;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;

import br.com.greenowl.inventarioqualidadeflorestal.Database.CalculaNota;
import br.com.greenowl.inventarioqualidadeflorestal.Database.Database;
import br.com.greenowl.inventarioqualidadeflorestal.Database.Parse;
import br.com.greenowl.inventarioqualidadeflorestal.Dominio.Entidades.Contato;
import br.com.greenowl.inventarioqualidadeflorestal.Dominio.Repositorioformulario;
import br.com.greenowl.inventarioqualidadeflorestal.Util.ChildInfo;
import br.com.greenowl.inventarioqualidadeflorestal.Util.CustomAdapter;
import br.com.greenowl.inventarioqualidadeflorestal.Util.GrupoFazenda;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Expandable extends AppCompatActivity {


    private LinkedHashMap<String, GrupoFazenda> subjects = new LinkedHashMap<String, GrupoFazenda>();
    private ArrayList<GrupoFazenda> fazList = new ArrayList<GrupoFazenda>();

    private CustomAdapter listAdapter;
    private ExpandableListView simpleExpandableListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // add data for displaying in expandable list view
        loadData();

        //get reference of the ExpandableListView
        simpleExpandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        // create the adapter by passing your ArrayList data
        listAdapter = new CustomAdapter(Expandable.this, fazList);
        // attach the adapter to the expandable list view
        simpleExpandableListView.setAdapter(listAdapter);

        //expand all the Groups
        expandAll();

        // setOnChildClickListener listener for child row click
      /*  simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //get the group header
                GrupoFazenda headerInfo = fazList.get(groupPosition);
                //get the child info
                ChildInfo detailInfo =  headerInfo.getFazendaList().get(childPosition);
                //display it or do something with it
                Toast.makeText(getBaseContext(), " Clicked on :: " + headerInfo.getName()
                        + "/" + detailInfo.getName(), Toast.LENGTH_LONG).show();
                return false;
            }
        });*/
        // setOnGroupClickListener listener for group heading click
     /*   simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                GrupoFazenda headerInfo = fazList.get(groupPosition);
                //display it or do something with it
                Toast.makeText(getBaseContext(), " Header is :: " + headerInfo.getName(),
                        Toast.LENGTH_LONG).show();

                return false;
            }
        });*/


    }

    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.expandGroup(i);
        }
    }

    //method to collapse all groups
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.collapseGroup(i);
        }
    }

    //load some initial data into out list
    private void loadData(){
        final Database Database;
        final SQLiteDatabase Conn;
        Database = new Database(this);
        Conn = Database.getWritableDatabase();
        Repositorioformulario dbxml = new Repositorioformulario(Conn);
        ArrayAdapter<Contato> contato = dbxml.buscacontatos(this);
        Parse calcular = new Parse();
        CalculaNota cnota = new CalculaNota();
        ArrayList<Contato> Lcontato = calcular.AdaptertoList(contato);
        double rstsobrevivencia = 0;
        double rstdensidade = 0;
        double rsthomogeneidade = 0;
        double rstaspecto = 0;
        double rstmato = 0;
        double rstformiga = 0;
        double rstpragas = 0;
        double rstconservacao = 0;
        double nfazenda = 0;
        DecimalFormat decim = new DecimalFormat("0.000");


        ArrayList<String> sumfaz = new ArrayList<String>();
        ArrayList<String> fazadd = new ArrayList<String>();





        for(int i = 0; i < Lcontato.size(); i++)

        {
            nfazenda=0;
            if (!fazadd.contains(Lcontato.get(i).getFAZENDA())) {

                rstsobrevivencia = cnota.sobrevivencia(Lcontato, Lcontato.get(i).getFAZENDA());
                rstdensidade = cnota.densidade(Lcontato, Lcontato.get(i).getFAZENDA());
                rsthomogeneidade= cnota.homogeneidade(Lcontato, Lcontato.get(i).getFAZENDA());
                rstaspecto = cnota.aspecto(Lcontato, Lcontato.get(i).getFAZENDA());
                rstmato = cnota.mato(Lcontato, Lcontato.get(i).getFAZENDA());
                rstformiga = cnota.formiga(Lcontato, Lcontato.get(i).getFAZENDA());
                rstpragas = cnota.pragas(Lcontato, Lcontato.get(i).getFAZENDA());
                rstconservacao = cnota.conservacao(Lcontato, Lcontato.get(i).getFAZENDA());

                nfazenda+=(
                        (rstsobrevivencia*3)+
                                (rstdensidade*2)+
                                (rsthomogeneidade*2)+
                                (rstaspecto*3)+
                                (rstmato*3)+
                                (rstformiga*2)+
                                (rstpragas*1)+
                                (rstconservacao*2))/(5.400000005);

                addProduct(Lcontato.get(i).getFAZENDA()+decim.format(nfazenda),"Sobrevivencia - " + Double.toString(rstsobrevivencia));
                addProduct(Lcontato.get(i).getFAZENDA()+decim.format(nfazenda), "Densidade - " + Double.toString(rstdensidade));
                addProduct(Lcontato.get(i).getFAZENDA()+decim.format(nfazenda), "Homogeneidade - " + Double.toString(rsthomogeneidade));
                addProduct(Lcontato.get(i).getFAZENDA()+decim.format(nfazenda), "Aspecto Nutricional - " + Double.toString(rstaspecto));
                addProduct(Lcontato.get(i).getFAZENDA()+decim.format(nfazenda), "Mato Copetição  - " + Double.toString(rstmato));
                addProduct(Lcontato.get(i).getFAZENDA()+decim.format(nfazenda), "Formiga - " + Double.toString(rstformiga));
                addProduct(Lcontato.get(i).getFAZENDA()+decim.format(nfazenda), "Pragas e Outros- " + Double.toString(rstpragas));
                addProduct(Lcontato.get(i).getFAZENDA()+decim.format(nfazenda), "Conservação do Solo - " + Double.toString(rstconservacao));

           }



            fazadd.add(Lcontato.get(i).getFAZENDA());

        }
    }




    //here we maintain our products in various departments
    private int addProduct(String fazenda, String talhao){

        int groupPosition = 0;

        //check the hash map if the group already exists
        GrupoFazenda headerInfo = subjects.get(fazenda);
        //add the group if doesn't exists
        if(headerInfo == null){
            headerInfo = new GrupoFazenda();
            headerInfo.setName(fazenda);
            subjects.put(fazenda, headerInfo);
            fazList.add(headerInfo);
        }

        //get the children for the group
        ArrayList<ChildInfo> productList = headerInfo.getFazendaList();
        //size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;

        //create a new child and add that to the group
        ChildInfo detailInfo = new ChildInfo();
        detailInfo.setSequence(String.valueOf(listSize));
        detailInfo.setName(talhao);
        productList.add(detailInfo);
        headerInfo.setFazendaList(productList);

        //find the group position inside the list
        groupPosition = fazList.indexOf(headerInfo);
        return groupPosition;


        //-------------------------------------------------------------------------------------------------------

   }

















}
