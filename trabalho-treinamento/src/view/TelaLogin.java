package view;

import util.Leitor;

/**
 * Classe responsável pela validação do Usuário no sistema.<br>
 *
 * @author José Augusto Martins Nieviadonski
 *
 */
public class TelaLogin
{
    public static void realizarLogin()
    {
        while (true)
        {
            System.out.println();
            System.out.println("Validação de Usuário");
            System.out.println();

            String tConta = Leitor.readString("Conta......... : ").trim();
            if (tConta.isEmpty())
            {
                System.out.println();
                System.out.println("Sistema encerrado");
                System.exit(9);
            }
            String tSenha = Leitor.readString("Senha......... : ").trim();

            System.out.println();
            if (tConta.equals("admin") && tSenha.equals("admin"))
            {
                System.out.println("Usuário validado com sucesso");
                return;
            }
            else
            {
                System.out.println("Usuário inválido, reentre...");
            }
        }
    }
}
