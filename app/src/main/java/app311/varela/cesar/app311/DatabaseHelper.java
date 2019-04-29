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
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        ConfiguracionSQLite.dropTable(sqLiteDatabase);
        onCreate(sqLiteDatabase);
    }

    public boolean updateData( ArrayList<String> listaParametros,ArrayList<String> listaColumnas,String tableName,String id,String Key)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < listaColumnas.size(); i++)
        {
            contentValues.put(listaColumnas.get(i),listaParametros.get(i));
        }
        long result = db.update(tableName,contentValues,Key + " = " + id,null);//.insert(tableName, null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
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

    public String existeCorreo(String table, String correo) {

        String indCorreoValido = "";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT CASE WHEN COUNT(0) > 0 THEN 'Y' ELSE 'N' END AS RESULTADO FROM '" + table +"' WHERE CORREO = '" +correo +"' " , null);

        if(cursor.moveToFirst()) {
            indCorreoValido = cursor.getString(cursor.getColumnIndex("RESULTADO"));
        }

        cursor.close();
        db.close();
        return indCorreoValido;
    }

    public String obtenerIdCedulaUusario(String table, String correo) {

        String cedulaUsuario = "";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT CEDULA FROM '" + table +"' WHERE CORREO = '" +correo +"' " , null);

        if(cursor.moveToFirst()) {

            cedulaUsuario = cursor.getString(cursor.getColumnIndex("CEDULA"));

        }

        cursor.close();
        db.close();
        return cedulaUsuario;
    }

    public ArrayList<String> getValues(String table, String key,String whereParameter, ArrayList<String> listaCampos) {
        ArrayList<String> values = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + table + " WHERE " + key + " = " + whereParameter, null);

        if(cursor.moveToFirst()) {
            do {
                for (int i = 0; i < listaCampos.size(); i++)
                    values.add(cursor.getString(cursor.getColumnIndex(listaCampos.get(i).toString())));
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
