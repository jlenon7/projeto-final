package com.flashcursos.model.service;

<<<<<<< HEAD
import java.time.LocalDate;
import java.time.Month;
=======
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.validation.ConstraintViolationException;
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
=======
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.TransactionSystemException;
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a

import com.flashcursos.model.entity.TipoUsuarioEnum;
import com.flashcursos.model.entity.Usuario;
import com.flashcursos.model.repository.UsuarioRepository;
import com.flashcursos.model.service.UsuarioService;

public class UsuarioTests extends AbstractIntegrationTests {

<<<<<<< HEAD
	/*
	 * Atributos 👍😂
	 */
	
	// Services
	@Autowired
	private UsuarioService usuarioService;
	// Repositories
	@Autowired
	private UsuarioRepository usuarioRepository;

	/*
	 * Tests 😎💪🏻
	 */
	
	// Cadastrar um Usuário 🥳🖐
		@Test
		@WithUserDetails("jlenon7@hotmail.com")
		@Sql( {	"/dataset/truncate.sql",
			   "/dataset/usuarios.sql" })
		public void cadastrarUsuarioMustPass()
		{
			Usuario usuario = new Usuario();
			usuario.setNome("João Lenon Lopes");
			usuario.setCpf("092.862.989-90");
			usuario.setNascimento(LocalDate.of(1990, Month.JANUARY, 1));
			usuario.setCelular("(45) 99955-3219");
			usuario.setEmail("lenonsec7@mailinator.com");
			usuario.setTipousuario(TipoUsuarioEnum.PROFESSOR);
			this.usuarioService.cadastrarUsuario(usuario);
			Assert.assertNotNull(usuario);
			Assert.assertNotNull(usuario.getId());

		}
		
		// Falta fazer os testes com campos obrigatórios null
		
	// Ativando o usuário 🥳🖐
		@Test
		@WithUserDetails("jlenon7@hotmail.com")
		@Sql( { "/dataset/truncate.sql",
			   "/dataset/usuarios.sql" })
		public void ativarUsuarioMustPass(){
			this.usuarioService.ativarUsuario("123456", "123456", "f786c907-032e-451b-ac93-8508dec75a3d");

			Usuario usuarioAtivo = this.usuarioRepository.findByEmailIgnoreCase("jlenon7@hotmail.com");
			Assert.assertEquals(true, usuarioAtivo.getDisponivel());
		}
		
		// Falta fazer testes com token inválido, token null, token vencido, senha null, senhas diferentes
		
	// Esqueci minha senha 🥳🖐
		@Test
		@Sql( { "/dataset/truncate.sql",
			   "/dataset/usuarios.sql" })
		public void enviarEmailRecuperarSenhaUsuarioMustPass(){
			this.usuarioService.enviarEmailRecuperarSenhaUsuario("jlenon7@hotmail.com");
			Usuario usuario = this.usuarioRepository.findByEmailIgnoreCase( "jlenon7@hotmail.com" );
			
			Assert.assertNotNull( usuario);
			Assert.assertNotNull( usuario.getPasswordResetToken());
			Assert.assertNotNull( usuario.getPasswordResetTokenExpiration());
		}
		
		//Faltou testes com email que não existe e sem passar email
		
	// Redefinir minha senha 🥳🖐
		@Test()
		@Sql( { "/dataset/truncate.sql",
			   "/dataset/usuarios.sql"
			})
		public void redefinirSenhaMustPass(){
			this.usuarioService.redefinirSenha("123456","123456", "f786c907-032e-451b-ac93-8508dec75a3d");
		}

		//Falta testes sem passar token, com token inválido, com token vencido, com senhas que não conferem, com senha null

	// Pegar usuário autenticado 🥳🖐
		@Test
		@WithUserDetails("jlenon7@hotmail.com")
		@Sql({ "/dataset/truncate.sql",
			   "/dataset/usuarios.sql" })
		public void pegarUsuarioAutenticadoTestMustPass() {
			Usuario usuario = this.usuarioService.getAuthenticatedUser();
			Assert.assertNotNull(usuario);
			Assert.assertNotNull(usuario.getId());
			
		}
	// Alterar minha conta 🥳🖐
		
	// Remover usuário 🥳🖐
	@Test
	@Sql({ "/dataset/truncate.sql", 
		  "/dataset/usuarios.sql" })
=======
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	/**
	 * ====================================== LISTAR ===========================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void listarUsuariosMustPass() {
		List<Usuario> usuarios = this.usuarioService.listarUsuarios();
		Assert.assertEquals(usuarios.size(), 2);

	}

	/**
	 * ====================================== CADASTRAR ===========================================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void cadastrarUsuarioMustPass() {
		Usuario usuario = new Usuario(1003L);
		usuario.setNome("João Lenon Lopes");
		usuario.setCpf("092.862.989-90");
		usuario.setNascimento(LocalDate.of(1990, Month.JANUARY, 1));
		usuario.setEmail("lenonsec7@gmail.com");
		usuario.setSenha("daledeledeledolly");
		usuario.setCelular("(45) 99955-3219");
		usuario.setTipousuario(TipoUsuarioEnum.ALUNO);
		usuario.setDisponivel(true);
		usuarioService.cadastrarUsuario(usuario);

		Assert.assertNotNull(usuario.getId());

	}

	@Test(expected = DataIntegrityViolationException.class)
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void cadastrarUsuarioMustFailEmailDuplicado() {
		Usuario usuario = new Usuario();
		usuario.setEmail("jlenon7@hotmail.com");
		usuario.setSenha("12345");
		usuario.setTipousuario(TipoUsuarioEnum.ALUNO);

		usuarioService.cadastrarUsuario(usuario);

	}

	@Test(expected = ConstraintViolationException.class)
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void cadastrarUsuarioMustFailEmailEmBranco() {
		Usuario usuario = new Usuario();
		usuario.setEmail("");
		usuario.setSenha("12345");
		usuario.setTipousuario(TipoUsuarioEnum.ALUNO);

		this.usuarioService.cadastrarUsuario(usuario);
	}

	/**
	 * 
	 * ======================== ATUALIZAR ============================
	 */

	@Test
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void atualizarUsuarioMustPass() {
		Usuario usuario = this.usuarioRepository.findById(1000L).orElse(null);
		usuario.setEmail("lenonsec7@hotmail.com");
		usuario.setSenha("123456");
		usuario.setTipousuario(TipoUsuarioEnum.PROFESSOR);

		usuarioService.atualizarUsuario(usuario);

	}

	@Test(expected = DataIntegrityViolationException.class)
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void atualizarUsuarioMustFailEmailDuplicado() {
		Usuario usuario = this.usuarioRepository.findById(1000L).orElse(null);
		usuario.setEmail("jlenon7@hotmail.com");
		usuario.setSenha("12345");
		usuario.setTipousuario(TipoUsuarioEnum.ADMIN);
	
		usuarioService.atualizarUsuario(usuario);

	}

	@Test(expected = ConstraintViolationException.class)
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void atualizarUsuarioMustFailEmailEmBranco() {
		Usuario usuario = new Usuario();
		usuario.setEmail("");
		usuario.setSenha("12345");
		usuario.setTipousuario(TipoUsuarioEnum.ADMIN);

		this.usuarioService.cadastrarUsuario(usuario);
	}

	/**
	 * ================== DETALHAR ===============================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void detalharUsuarioMustPass() {
		Usuario usuario = this.usuarioService.detalharUsuario(1000L);

		Assert.assertNotNull(usuario);
		Assert.assertNotNull(usuario.getId());
		Assert.assertEquals(usuario.getEmail(), "jlenon7@hotmail.com");

	}
	@Test(expected = IllegalArgumentException.class)
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
	public void detalharUsuarioMustFailIdNaoExiste() {
		Usuario usuario = this.usuarioService.detalharUsuario(1L);
		
	}
	/**
	 * =================== EXCLUIR ===============================
	 */
	@Test
	@Sql({ "/dataset/truncate.sql", "/dataset/usuarios.sql" })
>>>>>>> 5183df69de3f300ef7223ed3b0f53fb20aa36e4a
	public void removerUsuarioMustPass() {
		this.usuarioService.removerUsuario(1000);
		Usuario usuario = this.usuarioRepository.findById(1000L).orElse(null);
		Assert.assertNull(usuario);
	}
	

}
