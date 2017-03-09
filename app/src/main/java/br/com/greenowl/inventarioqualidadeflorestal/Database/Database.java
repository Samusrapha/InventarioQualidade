package br.com.greenowl.inventarioqualidadeflorestal.Database;

/**
 * Created by Raphael on 03/03/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public Database(Context context)
    {
        super(context, "COLETOR ",null,1);


    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(scriptsql.getCreateContato());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void adddemo (){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM FORMULARIO");
        db.execSQL("INSERT INTO FORMULARIO VALUES ( 1,'AURORA','0030203','VT05/ EXP_M2','27.1',2,2,0,2,0,0,0,0,3 )");
        db.execSQL("INSERT INTO FORMULARIO VALUES ( 2,'AURORA','0030213','AEC_0144','13.16',3,3,3,3,3,3,3,3,3)");
        db.execSQL("INSERT INTO FORMULARIO VALUES ( 3,'AURORA','0030223','G00510','26.09',3,0,0,0,0,0,0,0,3)");
        db.execSQL("INSERT INTO FORMULARIO VALUES ( 4,'AURORA','0030233','LW07','18.53',3,3,3,3,3,3,3,3,3)");
        db.execSQL("INSERT INTO FORMULARIO VALUES ( 5,'COLINA','2290061','AEC_0144','17.92',3,3,3,3,1,3,0,3,3)");
        db.execSQL("INSERT INTO FORMULARIO VALUES ( 6,'COLINA','2290071','AEC_0144','13.92',0,0,0,0,0,0,0,0,3)");
        db.execSQL("INSERT INTO FORMULARIO VALUES ( 7,'COLINA','2290081','AEC_0144','10.68',0,0,0,0,0,0,0,0,3)");
        db.execSQL("INSERT INTO FORMULARIO VALUES ( 8,'COLINA','2290091','AEC_0144','18.34',0,0,0,0,0,0,0,0,3)");
            }
    }
;

