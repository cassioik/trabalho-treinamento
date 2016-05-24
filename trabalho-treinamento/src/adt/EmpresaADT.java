package adt;

public class EmpresaADT {
	public static String consistir(String pEmpresa) {
		if (pEmpresa == null || pEmpresa.isEmpty())
			return "Nome da empresa não pode ser vazio";
		return "";
	}
}
