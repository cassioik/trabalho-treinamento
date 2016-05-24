package adt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class DataADT {
	public final static DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/uuuu")
			.withResolverStyle(ResolverStyle.STRICT);

	public static String consistir(String pDataNascimento) {
		if (pDataNascimento.length() != 10)
			return "Informar data no padrão dd/mm/aaaa";
		try {
			LocalDate.parse(pDataNascimento, FORMATO_DATA);
		} catch (Exception e) {
			return "Data inválida";
		}
		return "";
	}
}
