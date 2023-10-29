package errors;

public class ErroClasseInvalida extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ErroClasseInvalida(String mensagemErro) {
		super(mensagemErro);
	}

}
