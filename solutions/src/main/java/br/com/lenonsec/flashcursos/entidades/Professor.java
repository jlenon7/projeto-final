package br.com.lenonsec.flashcursos.entidades;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

public class Professor extends Usuario{
	
	/**
	 * 😎👌😎👌😎👌😎👌😎👌😎👌😎👌Entidades😎👌😎👌😎👌😎👌😎👌😎👌😎
	 */
	
	@NotNull
	@Enumerated( EnumType.ORDINAL )
	private AreaConhecimentoEnum areaConhecimento;
	
	/**
	 * 😎👌😎👌😎👌😎👌😎👌😎👌😎👌Métodos😎👌😎👌😎👌😎👌😎👌😎👌😎👌
	 */

}
