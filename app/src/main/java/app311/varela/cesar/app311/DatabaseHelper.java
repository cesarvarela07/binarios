package app311.varela.cesar.app311;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "TEST";
    public static final String COL_CEDULA = "CEDULA";
    public static final String COL_NOMBRE = "NOMBRE";
    public static final String COL_EMAIL = "EMAIL";
    public static final String COL_TELEFONO = "TELEFONO";
    public static final String COL_PASSWORD = "PASSWORD";


    public DatabaseHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /*Crea las tablas*/

        ConfiguracionSQLite.createTable(db);

        /*
        String createTable= "CREATE TABLE "+TABLE_NAME+
                " ("+COL_CEDULA+" INTEGER PRIMARY KEY, "+
                COL_NOMBRE+ " TEXT NOT NULL, "+
                COL_EMAIL+ " TEXT NOT NULL UNIQUE, "+
                COL_TELEFONO+ " INTEGER NOT NULL UNIQUE, "+
                COL_PASSWORD+ " TEXT NOT NULL UNIQUE); ";

        db.execSQL(createTable);
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addData( ArrayList<String> listaParametros,ArrayList<String> listaColumnas,String tableName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < listaColumnas.size(); i++)
        {
            contentValues.put(listaColumnas.get(i),listaParametros.get(i));
        }
        long result = db.insert(tableName, null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public ArrayList<String> getValues(String table, String cedula, ArrayList<String> listaCampos) {
        ArrayList<String> values = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + table, null);

        if(cursor.moveToFirst()) {
            do {
                for (int i = 0; i < listaCampos.size(); i++)
                    values.add(cursor.getString(cursor.getColumnIndex(listaCampos.get(i).toString())));
/*              values.add(cursor.getString(cursor.getColumnIndex(listaCampos.get(1).toString())));
                values.add(cursor.getString(cursor.getColumnIndex(listaCampos.get(2).toString())));
                values.add(cursor.getString(cursor.getColumnIndex(listaCampos.get(3).toString())));
                values.add(cursor.getString(cursor.getColumnIndex(listaCampos.get(4).toString())));

                values.add(cursor.getString(cursor.getColumnIndex(listaCampos.get(5).toString())));
                values.add(cursor.getString(cursor.getColumnIndex(listaCampos.get(6).toString())));
                values.add(cursor.getString(cursor.getColumnIndex(listaCampos.get(7).toString())));
                values.add(cursor.getString(cursor.getColumnIndex(listaCampos.get(8).toString())));
                values.add(cursor.getString(cursor.getColumnIndex(listaCampos.get(9).toString())));

                values.add(cursor.getString(cursor.getColumnIndex(listaCampos.get(10).toString())));
                values.add(cursor.getString(cursor.getColumnIndex(listaCampos.get(11).toString())));
                values.add(cursor.getString(cursor.getColumnIndex(listaCampos.get(12).toString())));*/

            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return values;
    }

/*
    public ArrayList<String> getValues(String table, String cedula) {
        ArrayList<String> InfoContactoList = new ArrayList();
        String selectQuery = "SELECT * FROM " + table + " WHERE CEDULA = " + cedula;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //if TABLE has rows
        if (cursor.moveToFirst()) {
            //Loop through the table rows
            do {
                MovieDetailsVO movieDetails = new MovieDetailsVO();
                movieDetails.setMovieId(cursor.getInt(0));
                movieDetails.setMovieName(cursor.getString(1));
                movieDetails.setGenre(cursor.getString(2));
                movieDetails.setYear(cursor.getInt(3));
                movieDetails.setRating(cursor.getDouble(4));

                //Add movie details to list
                movieDetailsList.add(movieDetails);
            } while (cursor.moveToNext());
        }
        db.close();
        return movieDetailsList;
    }
    */
}
