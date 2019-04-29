package app311.varela.cesar.app311;

import android.database.sqlite.SQLiteDatabase;

public final class ConfiguracionSQLite {

    /*------------------Datos para guardar el registro de cada usuario*/
    public static final String TB_NAME_REGISTRO_USUARIO = "USUARIO";
    public static final String TB_RU_CEDULA             = "CEDULA";
    public static final String TB_RU_NOMBRE             = "NOMBRE";
    public static final String TB_RU_APELLIDO1          = "APELLIDO1";
    public static final String TB_RU_APELLIDO2          = "APELLIDO2";
    public static final String TB_RU_PROVINCIA          = "PROVINCIA";
    public static final String TB_RU_CANTON             = "CANTON";
    public static final String TB_RU_DISTRITO           = "DISTRITO";
    public static final String TB_RU_CORREO             = "CORREO";
    public static final String TB_RU_CELULAR            = "CELULAR";
    public static final String TB_RU_PASSWORD           = "PASSWORD";
    public static final String TB_RU_COD_ACTIVACION     = "COD_ACTIVACION";
    public static final String TB_RU_ESTADO             = "ESTADO";
    public static final String TB_RU_DIRECCION          = "DIRECCION";

    public static final String CREATE_TABLE_USUARIO     =   " CREATE TABLE IF NOT EXISTS "+TB_NAME_REGISTRO_USUARIO +" " +
                                                            " ( "+TB_RU_CEDULA  +" INTEGER PRIMARY KEY, "+
                                                                TB_RU_NOMBRE    + " TEXT NOT NULL, "+
                                                                TB_RU_APELLIDO1 + " TEXT NOT NULL , "+
                                                                TB_RU_APELLIDO2 + " TEXT NOT NULL , "+
                                                                TB_RU_PROVINCIA + " TEXT NOT NULL , " +
                                                                TB_RU_CANTON    + " TEXT NOT NULL , " +
                                                                TB_RU_DISTRITO  + " TEXT NOT NULL , " +
                                                                TB_RU_CORREO    + " TEXT NOT NULL UNIQUE, " +
                                                                TB_RU_CELULAR   + " TEXT NOT NULL UNIQUE, " +
                                                                TB_RU_PASSWORD  + " TEXT NOT NULL , " +
                                                                TB_RU_COD_ACTIVACION+ " TEXT NOT NULL UNIQUE, " +
                                                                TB_RU_ESTADO    + " TEXT NOT NULL, " +
                                                                TB_RU_DIRECCION + " TEXT NOT NULL  " +
                                                            "); ";

    /*------------------Datos para guardar el registro de cada usuario*/

    /*------------------Datos para guardar el registro de cada incidencia*/
    public static final String TB_NAME_REGISTRO_INCIDENCIA  = "INCIDENCIA";
    public static final String TB_RI_INCIDENCIA_ID          = "INCIDENCIA_ID";
    public static final String TB_RI_CEDULA                 = "CEDULA";
    public static final String TB_RI_CATEGORIA              = "CATEGORIA";
    public static final String TB_RI_EMPRESA                = "EMPRESA";
    public static final String TB_RI_PROVINCIA              = "PROVINCIA";
    public static final String TB_RI_CANTON                 = "CANTON";
    public static final String TB_RI_DISTRITO               = "DISTRITO";
    public static final String TB_RI_DIRECCION              = "DIRECCION";
    public static final String TB_RI_DETALLE                = "ESTADO";
    public static final String TB_RI_ESTADO                 = "ESTADO";
    //public static final String TB_RI_GPS                  = "PASSWORD";
    //public static final String TB_RI_IMAGEN               = "COD_ACTIVACION";

    public static final String CREATE_TABLE_INCIDENCIA     =   " CREATE TABLE IF NOT EXISTS "+TB_NAME_REGISTRO_INCIDENCIA +" " +
                                                            " ( "+TB_RI_INCIDENCIA_ID   +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                                                TB_RI_CEDULA            + " INTEGER NOT NULL , "+
                                                                TB_RI_CATEGORIA         + " TEXT NOT NULL , "+
                                                                TB_RI_EMPRESA           + " TEXT NOT NULL , "+
                                                                TB_RI_PROVINCIA         + " TEXT NOT NULL ," +
                                                                TB_RI_CANTON            + " TEXT NOT NULL ," +
                                                                TB_RI_DISTRITO          + " TEXT NOT NULL ," +
                                                                TB_RI_DIRECCION         + " TEXT NOT NULL UNIQUE, " +
                                                                TB_RI_DETALLE           + " TEXT NOT NULL UNIQUE, " +
                                                                TB_RI_ESTADO            + " TEXT NOT NULL " +
                                                            "); ";
    /*------------------Datos para guardar el registro de cada incidencia*/





    public static void createTable(SQLiteDatabase db)
    {
      try {

          db.execSQL(CREATE_TABLE_USUARIO);

          db.execSQL(CREATE_TABLE_INCIDENCIA);


      }catch (Exception e){

      }
    }

    public static void dropTable(SQLiteDatabase db)
    {
        try {

            db.execSQL("DROP TABLE IF EXISTS "+TB_NAME_REGISTRO_INCIDENCIA);

            db.execSQL("DROP TABLE IF EXISTS "+TB_NAME_REGISTRO_USUARIO);


        }catch (Exception e){

        }
    }

}
