package adt;

public class CodigoADT {
	public static String consistir(String pCodigo) {
		if (!pCodigo.matches("[a-zA-Z]{4}"))
			return "Código precisa ter 4 caracteres alfabéticos";
		return "";
	}
	
	public static String consistir(int pCodigo) {
		if (pCodigo <= 0)
			return "Código precisa ser maior que zero";
		return "";
	}
	
	public static String consistirCurso(String pCodigo) {
		if (!pCodigo.matches("[A-Z]{2}[0-9]{3}"))
			return "Siga o padrão de códigos AA999, AA - duas letras maiusculas e 999 três números";
		return "";
	}
}
