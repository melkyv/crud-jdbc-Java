package exercicio7;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudVenda {

	public boolean verificarEstoque(int idProduto, int quantidadeDesejada) {
		String sql = "SELECT quantidade FROM produto WHERE id = ?";
		PreparedStatement pS = null;
		try {
			pS = Conexao.getConexao().prepareStatement(sql);
			pS.setInt(1, idProduto);
			ResultSet resultSet = pS.executeQuery();

			if (resultSet.next()) {
				int quantidadeEmEstoque = resultSet.getInt("quantidade");
				return quantidadeEmEstoque >= quantidadeDesejada;
			} else {
				System.out.println("Produto não encontrado.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public int verificarProdutoDaVenda(int id_venda) {
		String sql = "SELECT id_produto FROM venda WHERE id = ?";
		PreparedStatement pS = null;
		try {
			pS = Conexao.getConexao().prepareStatement(sql);
			pS.setInt(1, id_venda);
			ResultSet resultSet = pS.executeQuery();

			if (resultSet.next()) {
				return resultSet.getInt("id_produto");
			} else {
				System.out.println("Venda não encontrada.");
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int verificarQuantidadeVendida(int id_venda) {
		String sql = "SELECT quantidade FROM venda WHERE id = ?";
		PreparedStatement pS = null;
		try {
			pS = Conexao.getConexao().prepareStatement(sql);
			pS.setInt(1, id_venda);
			ResultSet resultSet = pS.executeQuery();

			if (resultSet.next()) {
				return resultSet.getInt("quantidade");
			} else {
				System.out.println("Venda não encontrada.");
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public void cadastrarVenda(int id, String data_de_venda, int quantidade, int id_produto) {
		PreparedStatement cadastrarPs = null;
		PreparedStatement atualizarPs = null;

		if (verificarEstoque(id_produto, quantidade)) {
			String cadastrarVendaSQL = "INSERT INTO venda (id, data_de_venda, quantidade, id_produto) VALUES (?, ?, ?, ?)";
			String atualizarProdutoSQL = "UPDATE produto SET quantidade= quantidade - ? WHERE id = ?";

			try {
				cadastrarPs = Conexao.getConexao().prepareStatement(cadastrarVendaSQL);
				atualizarPs = Conexao.getConexao().prepareStatement(atualizarProdutoSQL);

				cadastrarPs.setInt(1, id);
				cadastrarPs.setString(2, data_de_venda);
				cadastrarPs.setInt(3, quantidade);
				cadastrarPs.setInt(4, id_produto);
				cadastrarPs.executeUpdate();

				atualizarPs.setInt(1, quantidade);
				atualizarPs.setInt(2, id_produto);
				atualizarPs.executeUpdate();

				System.out.println("Venda cadastrada com sucesso.");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (cadastrarPs != null && atualizarPs != null) {
						cadastrarPs.close();
						atualizarPs.close();
					}
					if (Conexao.getConexao() != null) {
						Conexao.getConexao().close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void cancelarVenda(int id_venda) {
		int id_produto = verificarProdutoDaVenda(id_venda);
		int quantidadeVendida = verificarQuantidadeVendida(id_venda);
		PreparedStatement cancelarPs = null;
		PreparedStatement atualizarPs = null;

		String cancelarVendaSQL = "DELETE FROM venda WHERE id = ?";
		String atualizarProdutoSQL = "UPDATE produto SET quantidade= quantidade + ? WHERE id = ?";

		try {
			cancelarPs = Conexao.getConexao().prepareStatement(cancelarVendaSQL);
			atualizarPs = Conexao.getConexao().prepareStatement(atualizarProdutoSQL);

			cancelarPs.setInt(1, id_venda);
			cancelarPs.executeUpdate();

			atualizarPs.setInt(1, quantidadeVendida);
			atualizarPs.setInt(2, id_produto);
			atualizarPs.executeUpdate();

			System.out.println("Venda cancelada !");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (cancelarPs != null && atualizarPs != null) {
					cancelarPs.close();
					atualizarPs.close();
				}
				if (Conexao.getConexao() != null) {
					Conexao.getConexao().close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
