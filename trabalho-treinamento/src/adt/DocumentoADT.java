package adt;

public class DocumentoADT {
	public static String consistir(String pDocumento) {
		if (pDocumento == null || pDocumento.isEmpty())
			return "Documento n�o pode ser vazio";
		return "";
	}
	
	public static String consistirTipo(String pTipoDocumento) {
		//NA ESPECIFICA��O DO TRABALHO, CADASTRO DE INSTRUTORES, TIPO DE DOCUMENTO
		//Descri��o - Tipo do documento de identifica��o. Deve ser RG, PAS, TRAB ou � somente. 
		//N�o entendi o que seria o documento "�", por isso n�o coloquei o mesmo no teste
		if (pTipoDocumento.equals("RG") || pTipoDocumento.equals("PAS") || pTipoDocumento.equals("TRAB"))
			return "";
		return "Tipo de documentos aceitos: RG, PAS ou TRAB";
	}
}
