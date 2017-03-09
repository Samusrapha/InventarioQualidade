package br.com.greenowl.inventarioqualidadeflorestal.Dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import br.com.greenowl.inventarioqualidadeflorestal.Dominio.Entidades.Contato;

import java.util.Date;

/**
 * Created by Raphael on 05/03/2016.
 */
public class    Repositorioformulario {


    private SQLiteDatabase conn;

    public Repositorioformulario(SQLiteDatabase conn)
    {
        this.conn=conn;
    }


    /**
     * METODO ALTERAR NO BANCO
     */

    private ContentValues preencheContentValues (Contato contato){

        ContentValues values = new ContentValues();
        values.put(Contato.FAZENDA, contato.getFAZENDA());
        values.put(Contato.TALHAO, contato.getTALHAO());
        values.put(Contato.MATERIAL, contato.getMATERIAL());
        values.put(Contato.AREA, contato.getAREA());
        values.put(Contato.DATA, contato.getDATA().getTime());
        values.put(Contato.SOBREVIVENCIA, contato.getSOBREVIVENCIA());
        values.put(Contato.DENSIDADE, contato.getDENSIDADE());
        values.put(Contato.HOMOGENEIDADE, contato.getHOMOGENEIDADE());
        values.put(Contato.ASPECTO, contato.getASPECTO());
        values.put(Contato.MATO, contato.getMATO());
        values.put(Contato.FORMIGA, contato.getFORMIGA());
        values.put(Contato.PRAGAS, contato.getPRAGAS());
        values.put(Contato.CONSERVACAO, contato.getCONSERVACAO());

        return values;

    }



    public void excluir (long id)
    {
        conn.delete("FORMULARIO", " _id =?", new String[]{String.valueOf(id) });
    }

    /**
     * JOGA preencheContentValues no alterar para update
     */
    public void alterar (Contato contato){

        ContentValues values = preencheContentValues(contato);

        conn.update(Contato.TABELA, values, "_id =?", new String[]{String.valueOf(contato.getId())});

    }

    /**
     * JOGA preencheContentValues no alterar para insert
     */
    public void inserir (Contato contato){

        ContentValues values = preencheContentValues(contato);

        conn.insertOrThrow(Contato.TABELA, null, values);

    }



    public ArrayAdapter<Contato> buscacontatos(Context context )
    {
        ArrayAdapter<Contato> adpcontatos = new ArrayAdapter<Contato>( context, android.R.layout.simple_list_item_1);



        Cursor cursor = conn.query(Contato.TABELA ,null,null,null,null,null,null,null);

        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();

            do {

            Contato contato = new Contato();
                contato.setId(cursor.getLong(cursor.getColumnIndex(Contato.ID)));
                contato.setFAZENDA(cursor.getString(cursor.getColumnIndex(Contato.FAZENDA)));
                contato.setTALHAO(cursor.getString(cursor.getColumnIndex(Contato.TALHAO)));
                contato.setMATERIAL(cursor.getString(cursor.getColumnIndex(Contato.MATERIAL)));
                contato.setAREA(cursor.getString(cursor.getColumnIndex(Contato.AREA)));
                contato.setSOBREVIVENCIA(cursor.getString(cursor.getColumnIndex(Contato.SOBREVIVENCIA)));
                contato.setHOMOGENEIDADE(cursor.getString(cursor.getColumnIndex(Contato.HOMOGENEIDADE)));
                contato.setASPECTO(cursor.getString(cursor.getColumnIndex(Contato.ASPECTO)));
                contato.setMATO(cursor.getString(cursor.getColumnIndex(Contato.MATO)));
                contato.setFORMIGA(cursor.getString(cursor.getColumnIndex(Contato.FORMIGA)));
                contato.setPRAGAS(cursor.getString(cursor.getColumnIndex(Contato.PRAGAS)));
                contato.setCONSERVACAO(cursor.getString(cursor.getColumnIndex(Contato.CONSERVACAO)));
                contato.setDENSIDADE(cursor.getString(cursor.getColumnIndex(Contato.DENSIDADE)));
                contato.setDATA(new Date(cursor.getLong(cursor.getColumnIndex(Contato.DATA))));



                adpcontatos.add(contato);

                }while (cursor.moveToNext());
        }
        return adpcontatos;





    }

}
