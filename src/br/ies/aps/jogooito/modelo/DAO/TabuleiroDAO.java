package br.ies.aps.jogooito.modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ies.aps.jogooito.modelo.Tabuleiro;

public class TabuleiroDAO {
    private Integer idTabuleiro;

    private Integer campo_cima_esquerda;
    private Integer campo_cima_meio;
    private Integer campo_cima_direita;
    private Integer campo_meio_esquerda;
    private Integer campo_meio_meio;
    private Integer campo_meio_direita;
    private Integer campo_baixo_esquerda;
    private Integer campo_baixo_meio;
    private Integer campo_baixo_direita;

    public TabuleiroDAO(Tabuleiro tabuleiro) {
        this.campo_cima_esquerda = tabuleiro.getCampoCimaEsquerda();
        this.campo_cima_meio = tabuleiro.getCampoCimaMeio();
        this.campo_cima_direita = tabuleiro.getCampoCimaDireita();
        this.campo_meio_esquerda = tabuleiro.getCampoMeioEsquerda();
        this.campo_meio_meio = tabuleiro.getCampoMeio();
        this.campo_meio_direita = tabuleiro.getCampoMeioDireita();
        this.campo_baixo_esquerda = tabuleiro.getCampoBaixoEsquerda();
        this.campo_baixo_meio = tabuleiro.getCampoBaixoMeio();
        this.campo_baixo_direita = tabuleiro.getCampoBaixoDireita();
    }

    public void incluirBanco() {
        String sql = "INSERT INTO tabuleiro (campo_cima_esquerda, campo_cima_meio, campo_cima_direita, " +
                "campo_meio_esquerda, campo_meio_meio, campo_meio_direita, " +
                "campo_baixo_esquerda, campo_baixo_meio, campo_baixo_direita) " +
                "VALUES (?,?,?,?,?,?,?,?,?) RETURNING id;";

        try (Connection conexao = FabricaConexao.getConexao();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, campo_cima_esquerda);
            statement.setInt(2, campo_cima_meio);
            statement.setInt(3, campo_cima_direita);
            statement.setInt(4, campo_meio_esquerda);
            statement.setInt(5, campo_meio_meio);
            statement.setInt(6, campo_meio_direita);
            statement.setInt(7, campo_baixo_esquerda);
            statement.setInt(8, campo_baixo_meio);
            statement.setInt(9, campo_baixo_direita);

            try (ResultSet resultado = statement.executeQuery()) {
                if (resultado.next()) {
                    idTabuleiro = resultado.getInt("id");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao incluir tabuleiro no banco de dados", e);
        }
    }

    public void atualizarBanco(Integer idTabuleiro) {
        String sql = "UPDATE tabuleiro SET campo_cima_esquerda = ?, campo_cima_meio = ?, campo_cima_direita = ?, " +
                "campo_meio_esquerda = ?, campo_meio_meio = ?, campo_meio_direita = ?, " +
                "campo_baixo_esquerda = ?, campo_baixo_meio = ?, campo_baixo_direita = ? " +
                "WHERE id = ?;";

        try (Connection conexao = FabricaConexao.getConexao();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, campo_cima_esquerda);
            statement.setInt(2, campo_cima_meio);
            statement.setInt(3, campo_cima_direita);
            statement.setInt(4, campo_meio_esquerda);
            statement.setInt(5, campo_meio_meio);
            statement.setInt(6, campo_meio_direita);
            statement.setInt(7, campo_baixo_esquerda);
            statement.setInt(8, campo_baixo_meio);
            statement.setInt(9, campo_baixo_direita);
            statement.setInt(10, idTabuleiro);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar tabuleiro no banco de dados", e);
        }
    }

    public Integer getIdTabuleiro() {
        return idTabuleiro;
    }
}
