package tasnimghanmi.grp1.callerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ContactManager {
    private SQLiteDatabase db;
    private final Context con;

    public ContactManager(Context con) {
        this.con = con;
    }

    // open DB
    public void ouvrir() {
        ContactHelper helper = new ContactHelper(con, "Contacts.db", null, 1);
        db = helper.getWritableDatabase();
    }

    // close DB
    public void fermer() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

    public long ajout(String nom, String pseudo, String num) {
        ContentValues values = new ContentValues();
        values.put(ContactHelper.col_nom, nom);
        values.put(ContactHelper.col_pseudo, pseudo);
        values.put(ContactHelper.col_num, num);
        return db.insert(ContactHelper.TableName, null, values);
    }

    public int updateContact(int id, String nom, String pseudo, String num) {
        ContentValues values = new ContentValues();
        values.put(ContactHelper.col_nom, nom);
        values.put(ContactHelper.col_pseudo, pseudo);
        values.put(ContactHelper.col_num, num);
        return db.update(ContactHelper.TableName, values, ContactHelper.col_id + "=?", new String[]{String.valueOf(id)});
    }

    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> list = new ArrayList<>();
        Cursor c = db.query(ContactHelper.TableName,
                new String[]{ContactHelper.col_id, ContactHelper.col_nom, ContactHelper.col_pseudo, ContactHelper.col_num},
                null, null, null, null, ContactHelper.col_nom + " ASC");

        if (c.moveToFirst()) {
            do {
                int id = c.getInt(0);
                String nom = c.getString(1);
                String pseudo = c.getString(2);
                String num = c.getString(3);
                list.add(new Contact(id, nom, pseudo, num));
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }


    public void supprimerContact(int id) {
        db.delete(ContactHelper.TableName, ContactHelper.col_id + "=?", new String[]{String.valueOf(id)});
    }
}
