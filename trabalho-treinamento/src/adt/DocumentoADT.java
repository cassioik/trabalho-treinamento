package adt;

public class DocumentoADT {
	public static String consistir(String pDocumento) {
		if (pDocumento == null || pDocumento.isEmpty())
			return "Documento não pode ser vazio";
		return "";
	}
	
	public static String consistirTipo(String pTipoDocumento) {
		//NA ESPECIFICAÇÃO DO TRABALHO, CADASTRO DE INSTRUTORES, TIPO DE DOCUMENTO
		//Descrição - Tipo do documento de identificação. Deve ser RG, PAS, TRAB ou á somente. 
		//Não entendi o que seria o documento "á", por isso não coloquei o mesmo no teste
		if (pTipoDocumento.equals("RG") || pTipoDocumento.equals("PAS") || pTipoDocumento.equals("TRAB"))
			return "";
		return "Tipo de documentos aceitos: RG, PAS ou TRAB";
	}
}
