package app311.varela.cesar.app311;

import android.content.Context;
import android.widget.Toast;

public final class MetodosGlobales {


    public static String CEDULA_USUARIO_ACTIVO = "";

    public static void toastMessage(Context context, String message)
    {
        Toast.makeText(context, message,Toast.LENGTH_SHORT).show();
    }
}
