package tasnimghanmi.grp1.callerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ContactManager {
    SQLiteDatabase db=null;
    Context con;
    ContactManager(Context con)
    {
        this.con=con;
    }

    //ouvrir bd
    public void ouvrir()
    {
       ContactHelper helper=new ContactHelper(con,"Contacts.db",null,1);
       db=helper.getWritableDatabase();
    }

    //ajout element à la bd
    public long ajout(String nom,String pseudo,String num)
    {
      long a=0;
      //hashmap qui va contenir key-value
      ContentValues values=new ContentValues();
      values.put(ContactHelper.col_nom,nom);
      values.put(ContactHelper.col_pseudo,pseudo);
      values.put(ContactHelper.col_num,num);
      a= db.insert(ContactHelper.TableName,null,values); //return nb of inserted elements
      return a;
    }

    public ArrayList<Contact> getAllContacts()
    {
        ArrayList<Contact> l=new ArrayList<Contact>();
        Cursor c=db.query(ContactHelper.TableName,
                new String[]{ContactHelper.col_id,
                             ContactHelper.col_nom,
                             ContactHelper.col_pseudo,
                             ContactHelper.col_num},
                null,null,null,null,null);
        //point to the first element
        c.moveToFirst();
        while (!c.isAfterLast()) {
            //retrieve fields
            int id = c.getInt(0);
            String nom = c.getString(1);
            String pseudo = c.getString(2);
            String num = c.getString(3);

            l.add(new Contact(id, nom, pseudo, num));
        }

        return l;
    }

    public void supprimer()
    {

    }

    public void fermer()
    {

    }
}
