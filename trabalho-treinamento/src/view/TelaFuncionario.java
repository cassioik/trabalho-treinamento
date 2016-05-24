package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Hashtable;

import adt.DataADT;
import adt.MatriculaADT;
import adt.NomeADT;
import adt.SetorADT;
import adt.TelefoneADT;
import util.Leitor;

/**
 * @author CASSIO IZUMI KRUGER
 *
 */
public class TelaFuncionario {
	private static int sMatricula = 0;
	private static String sNome = "";
	private static String sSetor = "";
	private static long sTelefone;
	private static LocalDate sDataNascimento;
	private static Hashtable<String, String> sCadastro = new Hashtable<>();

	public static void processar() {
		lerArquivo();

		while (true) {
			System.out.println();
			System.out.println("Manutenção de Funcionários");
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
			switch (tOpcao) {
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

	private static void processarInclusao() {
		System.out.println();
		System.out.println("Manutenção de Funcionário");
		System.out.println("Inclusão de Funcionário");
		System.out.println();

		sMatricula = lerMatricula();
		if (sMatricula == 0)
			return;

		sNome = lerNome();
		if (sNome == null || sNome.isEmpty())
			return;

		sSetor = lerSetor();
		if (sSetor == null || sSetor.isEmpty())
			return;

		sTelefone = lerTelefone();
		if (sTelefone == 0)
			return;

		sDataNascimento = lerDataNascimento();
		if (sDataNascimento == null)
			return;

		System.out.println();
		char tConf = Leitor.readChar("Confirma (s/n)... : ");
		System.out.println();

		if (Character.toUpperCase(tConf) == 'S') {
			System.out.println("Usuário cadastrado com sucesso");
			System.out.println();
			System.out.println("Matrícula............ : " + sMatricula);
			System.out.println("Nome................. : " + sNome);
			System.out.println("Setor................ : " + sSetor);
			System.out.println("Telefone............. : " + sTelefone);
			System.out.println("Data de Nascimento... : " + sDataNascimento.format(DataADT.FORMATO_DATA));

			sCadastro.put(Integer.toString(sMatricula),
					sNome + "\b" + sSetor + "\b" + sTelefone + "\b" + sDataNascimento);
		} else
			System.out.println("Operação não realizada...");
	}

	private static void processarAlteracao() {
		System.out.println();
		System.out.println("Manutenção de Funcionários");
		System.out.println("Alteração de Funcionário");
		System.out.println();

		sMatricula = lerMatricula();
		if (sMatricula == 0)
			return;

		if (!sCadastro.containsKey(Integer.toString(sMatricula))) {
			System.out.println("Matricula não cadastrada");
			return;
		}

		recuperarFuncionario(sMatricula);

		System.out.println("Funcionário a ser alterado");
		System.out.println();
		System.out.println("Matrícula............ : " + sMatricula);
		System.out.println("Nome................. : " + sNome);
		System.out.println("Setor................ : " + sSetor);
		System.out.println("Telefone............. : " + sTelefone);
		System.out.println("Data de Nascimento... : " + sDataNascimento.format(DataADT.FORMATO_DATA));

		System.out.println();
		sNome = lerNome();
		if (sNome == null || sNome.isEmpty())
			return;

		sSetor = lerSetor();
		if (sSetor == null || sSetor.isEmpty())
			return;

		sTelefone = lerTelefone();
		if (sTelefone == 0)
			return;

		sDataNascimento = lerDataNascimento();
		if (sDataNascimento == null)
			return;

		System.out.println();
		char tConf = Leitor.readChar("Confirma (s/n)... : ");
		System.out.println();

		if (Character.toUpperCase(tConf) == 'S') {
			System.out.println("Funcionário alterado com sucesso");
			System.out.println();
			System.out.println("Matrícula............ : " + sMatricula);
			System.out.println("Nome................. : " + sNome);
			System.out.println("Setor................ : " + sSetor);
			System.out.println("Telefone............. : " + sTelefone);
			System.out.println("Data de Nascimento... : " + sDataNascimento.format(DataADT.FORMATO_DATA));

			sCadastro.put(Integer.toString(sMatricula),
					sNome + "\b" + sSetor + "\b" + sTelefone + "\b" + sDataNascimento);
		} else
			System.out.println("Operação não realizada...");
	}

	private static void processarExclusao() {
		System.out.println();
		System.out.println("Manutenção de Funcionários");
		System.out.println("Exclusão de Funcionário");
		System.out.println();

		sMatricula = lerMatricula();
		if (sMatricula == 0)
			return;

		if (!sCadastro.containsKey(Integer.toString(sMatricula))) {
			System.out.println("Matricula não cadastrada");
			return;
		}

		recuperarFuncionario(sMatricula);

		System.out.println("Funcionário a ser excluído");
		System.out.println();
		System.out.println("Matrícula............ : " + sMatricula);
		System.out.println("Nome................. : " + sNome);
		System.out.println("Setor................ : " + sSetor);
		System.out.println("Telefone............. : " + sTelefone);
		System.out.println("Data de Nascimento... : " + sDataNascimento.format(DataADT.FORMATO_DATA));

		System.out.println();
		char tConf = Leitor.readChar("Confirma (s/n)... : ");
		System.out.println();

		if (Character.toUpperCase(tConf) == 'S') {
			System.out.println("Funcionário excluído com sucesso");
			System.out.println();
			System.out.println("Matrícula............ : " + sMatricula);
			System.out.println("Nome................. : " + sNome);
			System.out.println("Setor................ : " + sSetor);
			System.out.println("Telefone............. : " + sTelefone);
			System.out.println("Data de Nascimento... : " + sDataNascimento.format(DataADT.FORMATO_DATA));

			sCadastro.remove(String.valueOf(sMatricula));
		} else
			System.out.println("Operação não realizada...");
	}

	private static void processarConsulta() {
		System.out.println();
		System.out.println("Manutenção de Funcionários");
		System.out.println("Consulta de Funcionário");
		System.out.println();

		sMatricula = lerMatricula();
		if (sMatricula == 0)
			return;

		if (!sCadastro.containsKey(Integer.toString(sMatricula))) {
			System.out.println("Matricula não cadastrada");
			return;
		}

		recuperarFuncionario(sMatricula);

		System.out.println("Funcionário cadastrado");
		System.out.println();
		System.out.println("Matrícula............ : " + sMatricula);
		System.out.println("Nome................. : " + sNome);
		System.out.println("Setor................ : " + sSetor);
		System.out.println("Telefone............. : " + sTelefone);
		System.out.println("Data de Nascimento... : " + sDataNascimento.format(DataADT.FORMATO_DATA));
	}

	private static void processarRelacao() {
		System.out.println();
		System.out.println("Manutenção de Funcionários");
		System.out.println("Relação de Funcionários");
		System.out.println();

		System.out.println("Funcionários cadastrados");
		for (String tMatricula : sCadastro.keySet()) {
			recuperarFuncionario(Integer.parseInt(tMatricula));
			System.out.printf("%-10s - %-10s - %-10s - %-10s - %10s%n", sMatricula, sNome, sSetor, sTelefone,
					sDataNascimento.format(DataADT.FORMATO_DATA));
		}
	}

	/**
	 * @return Retorna numero de matricula digitada pelo usuario
	 */
	private static int lerMatricula() {
		while (true) {
			int tMatricula = Leitor.readInt("Matrícula............ : ", sMatricula);
			if (tMatricula == 0)
				break;
			String tErro = MatriculaADT.consistir(tMatricula);
			if (tErro.isEmpty())
				return tMatricula;
			else {
				System.out.println("Erro................. : " + tErro);
				System.out.println();
			}
		}
		return 0;
	}

	/**
	 * @return Retorna nome digitado pelo usuario
	 */
	private static String lerNome() {
		while (true) {
			String tNome = Leitor.readString("Nome................. : ", sNome).trim();
			if (tNome.isEmpty())
				break;
			String tErro = NomeADT.consistir(tNome);
			if (tErro.isEmpty())
				return tNome;
			else {
				System.out.println("Erro................. : " + tErro);
				System.out.println();
			}
		}
		return null;
	}

	/**
	 * @return Retorna setor digitado pelo usuario
	 */
	private static String lerSetor() {
		while (true) {
			String tSetor = Leitor.readString("Setor................ : ", sSetor).trim();
			if (tSetor.isEmpty())
				break;
			String tErro = SetorADT.consistir(tSetor);
			if (tErro.isEmpty())
				return tSetor;
			else {
				System.out.println("Erro................. : " + tErro);
				System.out.println();
			}
		}
		return null;
	}

	/**
	 * @return Retorna telefone digitado pelo usuario
	 */
	private static Long lerTelefone() {
		while (true) {
			Long tTelefone = Leitor.readLong("Telefone............. : ", sTelefone);
			if (tTelefone == 0)
				break;
			String tErro = TelefoneADT.consistir(tTelefone);
			if (tErro.isEmpty())
				return tTelefone;
			else {
				System.out.println("Erro................. : " + tErro);
				System.out.println();
			}
		}
		return null;
	}

	/**
	 * @return Retorna data de nascimento digitada pelo usuario
	 */
	private static LocalDate lerDataNascimento() {
		while (true) {
			String tDataNascimento = Leitor.readString("Data de Nascimento... : ", String.valueOf(sDataNascimento));
			if (tDataNascimento.isEmpty())
				break;
			String tErro = DataADT.consistir(tDataNascimento);
			if (tErro.isEmpty())
				return LocalDate.parse(tDataNascimento, DataADT.FORMATO_DATA);
			else {
				System.out.println("Erro................. : " + tErro);
				System.out.println();
			}
		}
		return null;
	}

	private static void recuperarFuncionario(int pMatricula) {
		String tDados = sCadastro.get(String.valueOf(pMatricula));
		if (tDados == null)
			return;
		String[] tCampos = tDados.split("\b");

		sMatricula = pMatricula;
		sNome = tCampos[0];
		sSetor = tCampos[1];
		sTelefone = Long.parseLong(tCampos[2]);
		sDataNascimento = LocalDate.parse(tCampos[3]);
	}

	private static void limparDados() {
		sMatricula = 0;
		sNome = "";
		sSetor = "";
		sTelefone = 0;
		sDataNascimento = null;
	}

	private static void lerArquivo() {
		File tArquivo = new File("CadastroFuncionario");
		if (!tArquivo.exists())
			return;

		try {
			BufferedReader tCad = new BufferedReader(new FileReader(tArquivo));
			while (true) {
				String tLinha = tCad.readLine();
				if (tLinha == null)
					break;
				String[] tCampos = tLinha.split("\b");
				sCadastro.put(tCampos[0], tCampos[1] + "\b" + tCampos[2] + "\b" + tCampos[3] + "\b" + tCampos[4]);
			}
			tCad.close();
		} catch (IOException tExcept) {
			tExcept.printStackTrace();
			System.out.println();
			System.out.println("Programa cancelado no processo de leitura do arquivo");
			System.exit(9);
		}
	}

	private static void gravarArquivo() {
		File tArquivo = new File("CadastroFuncionario");

		try {
			PrintWriter tCad = new PrintWriter(tArquivo);
			for (String tMatricula : sCadastro.keySet()) {
				String tDados = sCadastro.get(tMatricula);
				tCad.println(tMatricula + "\b" + tDados);
			}
			tCad.close();
		} catch (IOException tExcept) {
			tExcept.printStackTrace();
			System.out.println();
			System.out.println("Programa cancelado no processo de gravação do arquivo");
			System.exit(9);
		}
	}

}
