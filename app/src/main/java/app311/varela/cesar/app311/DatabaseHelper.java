package app311.varela.cesar.app311;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "USUARIO";
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

        String createTable= "CREATE TABLE "+TABLE_NAME+
                            " ("+COL_CEDULA+" INTEGER PRIMARY KEY, "+
                            COL_NOMBRE+ " TEXT NOT NULL, "+
                            COL_EMAIL+ " TEXT NOT NULL UNIQUE, "+
                            COL_TELEFONO+ " INTEGER NOT NULL UNIQUE, "+
                            COL_PASSWORD+ " TEXT NOT NULL UNIQUE); ";

        db.execSQL(createTable);

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
}
