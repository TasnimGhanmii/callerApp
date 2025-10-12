package tasnimghanmi.grp1.callerapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//permet de déclarer un bd et les tables/champs a l'interieure
public class ContactHelper extends SQLiteOpenHelper {

    public static final String TableName="Contact";
    public static final String col_id="ID";
    public static final String col_nom="Nom";
    public static final String col_pseudo="Pseudo";
    public static final String col_num="Num";

    String req="create table "+TableName+" ("+col_id+" Integer Primary Key Autoincrement,"
            +col_nom+" Text not null,"
            +col_pseudo+" Text not null,"
            +col_num+" Text not null) ";




                                                             //nom fichier bd
    public ContactHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
                         //permet d'exec des reqs
    public void onCreate(SQLiteDatabase db) {
        //exec lors de la 1er ouverture de la bd quand le fichier n'existe pas
        db.execSQL(req);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //modif version
        db.execSQL("drop table "+TableName);
        onCreate(db);
    }
}
