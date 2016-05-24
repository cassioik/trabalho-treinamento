package adt;

public class EnderecoADT {
	public static String consistir(String pEndereco) {
		if (pEndereco == null || pEndereco.isEmpty())
			return "Endereço não pode ser vazio";
		return "";
	}
}
