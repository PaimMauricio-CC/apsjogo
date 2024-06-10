package br.ies.aps.jogooito.modelo.DAO;

import java.sql.SQLException;

import org.junit.Test;

import br.ies.aps.jogooito.modelo.Jogador;
import br.ies.aps.jogooito.modelo.Tabuleiro;
import br.ies.aps.jogooito.modelo.DAO.SalvaNovoJogoBancoDAO;

public class TesteSalvaNovoJogoBanco {
	@Test
	public void testaSalvarNovoJogoBanco() throws SQLException {
		Tabuleiro tabuleiro = new Tabuleiro();
		Jogador jogador = new Jogador("Mau");		
		
		SalvaNovoJogoBancoDAO salvarNovoJogoBancoDAO = new SalvaNovoJogoBancoDAO(tabuleiro, jogador);
		salvarNovoJogoBancoDAO.salvar();
	}
 
}
