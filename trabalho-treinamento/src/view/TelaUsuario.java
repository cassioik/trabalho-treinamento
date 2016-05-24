package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import adt.ContaUsuarioADT;
import adt.MatriculaADT;
import adt.SenhaUsuarioADT;
import util.Leitor;

public class TelaUsuario
{
    private static String sConta = "";
    private static String sSenha = "";
    private static int    sMatricula = 0;
    private static Hashtable<String, String> sCadastro = new Hashtable<>();

    public static void processar()
    {
        lerArquivo();
        while (true)
        {
            System.out.println();
            System.out.println("Manutenção de Usuários");
            System.out.println(" 1 - Incluir");
            System.out.println(" 2 - Alterar");
            System.out.println(" 3 - Excluir");
            System.out.println(" 4 - Consultar");
            System.out.println(" 5 - Listar");
            System.out.println(" 9 - Fim");
            System.out.println();

            int tOpcao = Leitor.readInt("Entre com a opção desejada : ");
            if (tOpcao == 9)
                break;

            limparDados();
            switch (tOpcao)
            {
                case 1:
                    processarInclusao();
                    break;
                case 2:
                    processarAlteracao();
                    break;
                case 3:
                    processarExclusao();
                    break;
                case 4:
                    processarConsulta();
                    break;
                case 5:
                    processarRelacao();
                    break;
                default:
                    System.out.println("Opção inválida. Reentre...");
                    break;
            }
        }
        gravarArquivo();
    }

    private static void processarInclusao()
    {
        System.out.println();
        System.out.println("Manutenção de Usuários");
        System.out.println("Inclusão de Usuário");
        System.out.println();

        sConta = lerConta();
        if (sConta == null || sConta.isEmpty())
            return;

        sSenha = lerSenha();
        if (sSenha == null || sSenha.isEmpty())
            return;

        sMatricula = lerMatricula();
        if (sMatricula == 0)
            return;

        System.out.println();
        char tConf = Leitor.readChar("Confirma (s/n)... : ");
        System.out.println();

        if (Character.toUpperCase(tConf) == 'S')
        {
            System.out.println("Usuário cadastrado com sucesso");
            System.out.println();
            System.out.println("Conta......... : " + sConta);
            System.out.println("Senha......... : " + sSenha);
            System.out.println("Matrícula..... : " + sMatricula);

            sCadastro.put(sConta, sSenha + "\b" + sMatricula);
        }
        else
            System.out.println("Operação não realizada...");
    }

    private static void processarAlteracao()
    {
        System.out.println();
        System.out.println("Manutenção de Usuários");
        System.out.println("Alteração de Usuário");
        System.out.println();

        sConta = lerConta();
        if (sConta == null || sConta.isEmpty())
            return;

        if (!sCadastro.containsKey(sConta))
        {
            System.out.println("Conta não cadastrada");
            return;
        }

        recuperarUsuario(sConta);

        System.out.println("Usuário a ser alterado");
        System.out.println();
        System.out.println("Conta......... : " + sConta);
        System.out.println("Senha......... : " + sSenha);
        System.out.println("Matrícula..... : " + sMatricula);

        System.out.println();
        sSenha = lerSenha();
        if (sSenha == null || sSenha.isEmpty())
            return;

        sMatricula = lerMatricula();
        if (sMatricula == 0)
            return;

        System.out.println();
        char tConf = Leitor.readChar("Confirma (s/n)... : ");
        System.out.println();

        if (Character.toUpperCase(tConf) == 'S')
        {
            System.out.println("Usuário alterado com sucesso");
            System.out.println();
            System.out.println("Conta......... : " + sConta);
            System.out.println("Senha......... : " + sSenha);
            System.out.println("Matrícula..... : " + sMatricula);

            sCadastro.put(sConta, sSenha + "\b" + sMatricula);
        }
        else
            System.out.println("Operação não realizada...");
    }

    private static void processarExclusao()
    {
        System.out.println();
        System.out.println("Manutenção de Usuários");
        System.out.println("Exclusão de Usuário");
        System.out.println();

        sConta = lerConta();
        if (sConta == null || sConta.isEmpty())
            return;

        if (!sCadastro.containsKey(sConta))
        {
            System.out.println("Conta não cadastrada");
            return;
        }

        recuperarUsuario(sConta);

        System.out.println("Usuário a ser excluído");
        System.out.println();
        System.out.println("Conta......... : " + sConta);
        System.out.println("Senha......... : " + sSenha);
        System.out.println("Matrícula..... : " + sMatricula);

        System.out.println();
        char tConf = Leitor.readChar("Confirma (s/n)... : ");
        System.out.println();

        if (Character.toUpperCase(tConf) == 'S')
        {
            System.out.println("Usuário excluído com sucesso");
            System.out.println();
            System.out.println("Conta......... : " + sConta);
            System.out.println("Senha......... : " + sSenha);
            System.out.println("Matrícula..... : " + sMatricula);

            sCadastro.remove(sConta);
        }
        else
            System.out.println("Operação não realizada...");
    }

    private static void processarConsulta()
    {
        System.out.println();
        System.out.println("Manutenção de Usuários");
        System.out.println("Consulta de Usuário");
        System.out.println();

        sConta = lerConta();
        if (sConta == null || sConta.isEmpty())
            return;

        if (!sCadastro.containsKey(sConta))
        {
            System.out.println("Conta não cadastrada");
            return;
        }

        recuperarUsuario(sConta);

        System.out.println("Usuário cadastrado");
        System.out.println();
        System.out.println("Conta......... : " + sConta);
        System.out.println("Senha......... : " + sSenha);
        System.out.println("Matrícula..... : " + sMatricula);
    }

    private static void processarRelacao()
    {
        System.out.println();
        System.out.println("Manutenção de Usuários");
        System.out.println("Relação de Usuários");
        System.out.println();

        System.out.println("Usuários cadastrados");
        for (String tConta : sCadastro.keySet())
        {
            recuperarUsuario(tConta);
            System.out.printf("%-10s - %-10s - %10d%n", sConta, sSenha, sMatricula);
        }
    }

    private static String lerConta()
    {
        // Código sem consistência
        // return Leitor.readString("Conta......... : ", sConta).trim();

        // Código com consistência
        while (true)
        {
            String tConta = Leitor.readString("Conta......... : ", sConta).trim();
            if (tConta.isEmpty())
                break;
            String tErro = ContaUsuarioADT.consistir(tConta);
            if (tErro.isEmpty())
                return tConta;
            else
            {
                System.out.println("Erro.......... : " + tErro);
                System.out.println();
            }
        }
        return null;
    }

    private static String lerSenha()
    {
        // Código sem consistência
        // return Leitor.readString("Senha......... : ", sSenha).trim();

        // Código com consistência
        while (true)
        {
            String tSenha = Leitor.readString("Senha......... : ", sSenha).trim();
            if (tSenha.isEmpty())
                break;
            String tErro = SenhaUsuarioADT.consistir(tSenha);
            if (tErro.isEmpty())
                return tSenha;
            else
            {
                System.out.println("Erro.......... : " + tErro);
                System.out.println();
            }
        }
        return null;
    }

    private static int lerMatricula()
    {
        // Código sem consistência
        // return Leitor.readInt("Matrícula..... : ", sMatricula);

        // Código com consistência
        while (true)
        {
            int tMatricula = Leitor.readInt("Matrícula..... : ", sMatricula);
            if (tMatricula == 0)
                break;
            String tErro = MatriculaADT.consistir(tMatricula);
            if (tErro.isEmpty())
                return tMatricula;
            else
            {
                System.out.println("Erro.......... : " + tErro);
                System.out.println();
            }
        }
        return 0;
    }

    private static void recuperarUsuario(String pConta)
    {
        String tDados = sCadastro.get(pConta);
        if (tDados == null)
            return;
        String[] tCampos = tDados.split("\b");

        sConta = pConta;
        sSenha = tCampos[0];
        sMatricula = Integer.parseInt(tCampos[1]);
    }

    private static void limparDados()
    {
        sConta = "";
        sSenha = "";
        sMatricula = 0;
    }

    private static void lerArquivo()
    {
        File tArquivo = new File("CadastroUsuario");
        if (!tArquivo.exists())
            return;

        try
        {
            BufferedReader tCad = new BufferedReader(new FileReader(tArquivo));
            while(true)
            {
                String tLinha = tCad.readLine();
                if (tLinha == null)
                    break;
                String[] tCampos = tLinha.split("\b");
                sCadastro.put(tCampos[0], tCampos[1] + "\b" + tCampos[2]);
            }
            tCad.close();
        }
        catch (IOException tExcept)
        {
            tExcept.printStackTrace();
            System.out.println();
            System.out.println("Programa cancelado no processo de leitura do arquivo");
            System.exit(9);
        }
     }

    private static void gravarArquivo()
    {
        File tArquivo = new File("CadastroUsuario");

        try
        {
            PrintWriter tCad = new PrintWriter(tArquivo);
            for (String tConta : sCadastro.keySet())
            {
                String tDados = sCadastro.get(tConta);
                tCad.println(tConta + "\b" + tDados);
            }
            tCad.close();
        }
        catch (IOException tExcept)
        {
            tExcept.printStackTrace();
            System.out.println();
            System.out.println("Programa cancelado no processo de gravação do arquivo");
            System.exit(9);
        }
     }
}
