package adt;

public class DescricaoADT {
	public static String consistir(String pDescricao) {
		if (pDescricao == null || pDescricao.isEmpty())
			return "Descrição não pode ser vazio";
		return "";
	}
}
