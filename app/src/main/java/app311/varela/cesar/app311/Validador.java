package app311.varela.cesar.app311;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validador {

    public static boolean email(String pEmail)
    {
        boolean valido = true;
        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        // El email a validar
        String email = pEmail;

        Matcher mather = pattern.matcher(email);

        if (mather.find() != true)
            valido = false;//System.out.println("El email ingresado no es válido.");

        return valido;
    }

}
