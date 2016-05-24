package adt;

public class SenhaUsuarioADT
{

    public static String consistir(String pSenha)
    {
        if (pSenha == null)
            return "Senha não pode ser nula";
        if (pSenha.length() < 4 || pSenha.length() > 10)
            return "Senha deve ter entre 4 e 10 caracteres";
        for (int i=0; i < pSenha.length(); i++)
        {
            char tCh = pSenha.charAt(i);
            if (tCh <= ' ' || tCh > '~')
                return "Senha somente deve ter somente caracteres digitáveis";
        }
        return "";
    }

}
