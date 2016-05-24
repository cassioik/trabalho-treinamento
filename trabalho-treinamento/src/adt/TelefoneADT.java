package adt;

public class TelefoneADT {

	public static String consistir(long pTelefone) {
		// código sem transformar long em String
		// if (pTelefone < 10000000 || pTelefone > 999999999)
		// return "Telefone inválido";

		// transforma pTelefone em String para testar se é válido
		String tTelefone = String.valueOf(pTelefone);

		// verifica se telefone possui 8 ou 9 digitos
		if (!tTelefone.matches("\\d{8,9}"))
			return "Telefone inválido";

		return "";
	}
}
