package adt;

public class CargaHorariaADT {
	public static String consistir(int pCargaHoraria) {
		if (pCargaHoraria <= 0)
			return "Carga horaria precisa ser maior que zero";
		return "";
	}
}
