package adt;

public class ContaUsuarioADT
{

    public static String consistir(String pConta)
    {
        if (pConta == null)
            return "Conta não pode ser nula";
        if (pConta.length() < 4 || pConta.length() > 10)
            return "Conta deve ter entre 4 e 10 caracteres";
        for (int i=0; i < pConta.length(); i++)
        {
            char tCh = pConta.charAt(i);
            if (!Character.isLetterOrDigit(tCh))
                return "Conta somente deve ter letras e dígitos";
        }
        return "";
    }

}
