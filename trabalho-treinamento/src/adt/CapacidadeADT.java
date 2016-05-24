package adt;

public class CapacidadeADT {
	public static String consistir(int pCapacidade) {
		if (pCapacidade <= 0)
			return "Capacidade precisa ser maior que zero";
		return "";
	}
}
