package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import adt.CodigoADT;
import adt.DocumentoADT;
import adt.EmpresaADT;
import adt.NomeADT;
import adt.TelefoneADT;
import util.Leitor;

/**
 * @author CASSIO IZUMI KRUGER
 *
 */
public class TelaInstrutor {
	private static int sCodigo = 0;
	private static String sNome = "";
	private static String sEmpresa = "";
	private static long sTelefone;
	private static String sDocumento = "";
	private static String sTipoDocumento = "";
	private static Hashtable<Integer, String> sCadastro = new Hashtable<>();
	
	public static void processar() {
		lerArquivo();

		while (true) {
			System.out.println();
			System.out.println("Manutenção de Instrutores");
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
		System.out.println("Manutenção de Instrutores");
		System.out.println("Inclusão de Instrutor");
		System.out.println();

		sCodigo = lerCodigo();
		if (sCodigo == 0)
			return;

		sNome = lerNome();
		if (sNome == null || sNome.isEmpty())
			return;

		sEmpresa = lerEmpresa();
		if (sEmpresa == null || sEmpresa.isEmpty())
			return;

		sTelefone = lerTelefone();
		if (sTelefone == 0)
			return;

		sDocumento = lerDocumento();
		if (sDocumento == null || sDocumento.isEmpty())
			return;
		
		sTipoDocumento = lerTipoDocumento();
		if (sTipoDocumento == null || sTipoDocumento.isEmpty())
			return;

		System.out.println();
		char tConf = Leitor.readChar("Confirma (s/n)... : ");
		System.out.println();

		if (Character.toUpperCase(tConf) == 'S') {
			System.out.println("Usuário cadastrado com sucesso");
			System.out.println();
			System.out.println("Código............... : " + sCodigo);
			System.out.println("Nome................. : " + sNome);
			System.out.println("Empresa.............. : " + sEmpresa);
			System.out.println("Telefone............. : " + sTelefone);
			System.out.println("Documento............ : " + sDocumento);
			System.out.println("Tipo de Documento.... : " + sTipoDocumento);

			sCadastro.put(sCodigo, sNome + "\b" + sEmpresa + "\b" + sTelefone + "\b" + sDocumento + "\b" + sTipoDocumento);
		} else
			System.out.println("Operação não realizada...");
	}
	
	private static void processarAlteracao() {
		System.out.println();
		System.out.println("Manutenção de Instrutores");
		System.out.println("Alteração de Instrutor");
		System.out.println();

		sCodigo = lerCodigo();
		if (sCodigo == 0)
			return;

		if (!sCadastro.containsKey(sCodigo)) {
			System.out.println("Código não cadastrado");
			return;
		}

		recuperarInstrutores(sCodigo);

		System.out.println("Instrutor a ser alterado");
		System.out.println();
		System.out.println("Código............... : " + sCodigo);
		System.out.println("Nome................. : " + sNome);
		System.out.println("Empresa.............. : " + sEmpresa);
		System.out.println("Telefone............. : " + sTelefone);
		System.out.println("Documento............ : " + sDocumento);
		System.out.println("Tipo de Documento.... : " + sTipoDocumento);

		System.out.println();
		sNome = lerNome();
		if (sNome == null || sNome.isEmpty())
			return;

		sEmpresa = lerEmpresa();
		if (sEmpresa == null || sEmpresa.isEmpty())
			return;

		sTelefone = lerTelefone();
		if (sTelefone == 0)
			return;

		sDocumento = lerDocumento();
		if (sDocumento == null || sDocumento.isEmpty())
			return;
		
		sTipoDocumento = lerTipoDocumento();
		if (sTipoDocumento == null || sTipoDocumento.isEmpty())
			return;

		System.out.println();
		char tConf = Leitor.readChar("Confirma (s/n)... : ");
		System.out.println();

		if (Character.toUpperCase(tConf) == 'S') {
			System.out.println("Instrutor alterado com sucesso");
			System.out.println();
			System.out.println("Código............... : " + sCodigo);
			System.out.println("Nome................. : " + sNome);
			System.out.println("Empresa.............. : " + sEmpresa);
			System.out.println("Telefone............. : " + sTelefone);
			System.out.println("Documento............ : " + sDocumento);
			System.out.println("Tipo de Documento.... : " + sTipoDocumento);

			sCadastro.put(sCodigo, sNome + "\b" + sEmpresa + "\b" + sTelefone + "\b" + sDocumento + "\b" + sTipoDocumento);
		} else
			System.out.println("Operação não realizada...");
	}
	
	private static void processarExclusao() {
		System.out.println();
		System.out.println("Manutenção de Instrutores");
		System.out.println("Exclusão de Instrutor");
		System.out.println();

		sCodigo = lerCodigo();
		if (sCodigo == 0)
			return;

		if (!sCadastro.containsKey(sCodigo)) {
			System.out.println("Código não cadastrado");
			return;
		}

		recuperarInstrutores(sCodigo);

		System.out.println("Instrutor a ser excluído");
		System.out.println();
		System.out.println("Código............... : " + sCodigo);
		System.out.println("Nome................. : " + sNome);
		System.out.println("Empresa.............. : " + sEmpresa);
		System.out.println("Telefone............. : " + sTelefone);
		System.out.println("Documento............ : " + sDocumento);
		System.out.println("Tipo de Documento.... : " + sTipoDocumento);

		System.out.println();
		char tConf = Leitor.readChar("Confirma (s/n)... : ");
		System.out.println();

		if (Character.toUpperCase(tConf) == 'S') {
			System.out.println("Instrutor excluído com sucesso");
			System.out.println();
			System.out.println("Código............... : " + sCodigo);
			System.out.println("Nome................. : " + sNome);
			System.out.println("Empresa.............. : " + sEmpresa);
			System.out.println("Telefone............. : " + sTelefone);
			System.out.println("Documento............ : " + sDocumento);
			System.out.println("Tipo de Documento.... : " + sTipoDocumento);

			sCadastro.remove(sCodigo);
		} else
			System.out.println("Operação não realizada...");
	}
	
	private static void processarConsulta() {
		System.out.println();
		System.out.println("Manutenção de Instrutores");
		System.out.println("Consulta de Instrutor");
		System.out.println();

		sCodigo = lerCodigo();
		if (sCodigo == 0)
			return;

		if (!sCadastro.containsKey(sCodigo)) {
			System.out.println("Código não cadastrado");
			return;
		}

		recuperarInstrutores(sCodigo);

		System.out.println("Instrutor cadastrado");
		System.out.println();
		System.out.println("Código............... : " + sCodigo);
		System.out.println("Nome................. : " + sNome);
		System.out.println("Empresa.............. : " + sEmpresa);
		System.out.println("Telefone............. : " + sTelefone);
		System.out.println("Documento............ : " + sDocumento);
		System.out.println("Tipo de Documento.... : " + sTipoDocumento);
	}
	
	private static void processarRelacao() {
		System.out.println();
		System.out.println("Manutenção de Instrutores");
		System.out.println("Relação de Instrutores");
		System.out.println();

		System.out.println("Instrutores cadastradas");
		for (Integer tCodigo : sCadastro.keySet()) {
			recuperarInstrutores(tCodigo);
			System.out.printf("%-10d - %-10s - %-10s - %10d - %10s - %10s%n", sCodigo, sNome, sEmpresa, sTelefone, sDocumento, sTipoDocumento);
		}
	}
	
	/**
	 * @return Retorna código digitado pelo usuario
	 */
	private static int lerCodigo() {
		while (true) {
			int tCodigo = Leitor.readInt("Código............... : ", sCodigo);
			if (tCodigo == 0)
				break;
			String tErro = CodigoADT.consistir(tCodigo);
			if (tErro.isEmpty())
				return tCodigo;
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
			String tNome = Leitor.readString("Nome................. : ", sNome);
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
	 * @return Retorna empresa digitado pelo usuario
	 */
	private static String lerEmpresa() {
		while (true) {
			String tEmpresa = Leitor.readString("Empresa.............. : ", sEmpresa);
			if (tEmpresa.isEmpty())
				break;
			String tErro = EmpresaADT.consistir(tEmpresa);
			if (tErro.isEmpty())
				return tEmpresa;
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
	 * @return Retorna documeto digitado pelo usuario
	 */
	private static String lerDocumento() {
		while (true) {
			String tDocumento = Leitor.readString("Documento............ : ", sDocumento);
			if (tDocumento.isEmpty())
				break;
			String tErro = DocumentoADT.consistir(tDocumento);
			if (tErro.isEmpty())
				return tDocumento;
			else {
				System.out.println("Erro................. : " + tErro);
				System.out.println();
			}
		}
		return null;
	}
	
	/**
	 * @return Retorna tipo de documeto digitado pelo usuario
	 */
	private static String lerTipoDocumento() {
		while (true) {
			String tTipoDocumento = Leitor.readString("Tipo de Documento.... : ", sTipoDocumento);
			if (tTipoDocumento.isEmpty())
				break;
			String tErro = DocumentoADT.consistirTipo(tTipoDocumento.toUpperCase());
			if (tErro.isEmpty())
				return tTipoDocumento.toUpperCase();
			else {
				System.out.println("Erro................. : " + tErro);
				System.out.println();
			}
		}
		return null;
	}
	
	private static void recuperarInstrutores(int pCodigo) {
		String tDados = sCadastro.get(pCodigo);
		if (tDados == null)
			return;
		String[] tCampos = tDados.split("\b");

		sCodigo = pCodigo;
		sNome = tCampos[0];
		sEmpresa = tCampos[1];
		sTelefone = Long.parseLong(tCampos[2]);
		sDocumento = tCampos[3];
		sTipoDocumento = tCampos[4];
	}
	
	private static void limparDados() {
		sCodigo = 0;
		sNome = null;
		sEmpresa = null;
		sTelefone = 0;
		sDocumento = null;
		sTipoDocumento = null;
	}

	private static void lerArquivo() {
		File tArquivo = new File("CadastroInstrutores");
		if (!tArquivo.exists())
			return;

		try {
			BufferedReader tCad = new BufferedReader(new FileReader(tArquivo));
			while (true) {
				String tLinha = tCad.readLine();
				if (tLinha == null)
					break;
				String[] tCampos = tLinha.split("\b");
				sCadastro.put(Integer.parseInt(tCampos[0]), tCampos[1] + "\b" + tCampos[2] + "\b" + tCampos[3] + "\b" + tCampos[4] + "\b" + tCampos[5]);
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
		File tArquivo = new File("CadastroInstrutores");

		try {
			PrintWriter tCad = new PrintWriter(tArquivo);
			for (Integer tCodigo : sCadastro.keySet()) {
				String tDados = sCadastro.get(tCodigo);
				tCad.println(tCodigo + "\b" + tDados);
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
