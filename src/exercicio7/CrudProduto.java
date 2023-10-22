package exercicio7;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudProduto {
    
	public void inserirProduto(Produto produto) {
	    String sql = "INSERT INTO produto (id, nome, preco, quantidade) VALUES (?, ?, ?, ?)";
	    PreparedStatement pS = null;
	    
	    try {
			pS = Conexao.getConexao().prepareStatement(sql);
			pS.setInt(1, produto.getId());
			pS.setString(2, produto.getNome());
			pS.setFloat(3, produto.getPreco());
			pS.setInt(4, produto.getQuantidade());
			pS.execute();
			System.out.println("Produto inserido com sucesso!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
            	if(pS != null) {
					pS.close();
				}
				if(Conexao.getConexao() != null) {
					Conexao.getConexao().close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void buscarProduto(String nome) {
		String sql = "SELECT * FROM produto WHERE nome = ?";
	    PreparedStatement pS = null;
	    ResultSet resultSet = null;
	    Produto produto = null;
	    
	    try {
			pS = Conexao.getConexao().prepareStatement(sql);
			pS.setString(1, nome);
			resultSet = pS.executeQuery();
			
			if (resultSet.next()) {
                produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setPreco(resultSet.getFloat("preco"));
                produto.setQuantidade(resultSet.getInt("quantidade"));
                
                System.out.println("Produto encontrado:");
                System.out.println("ID: " + produto.getId());
                System.out.println("Nome: " + produto.getNome());
                System.out.println("Preço: " + produto.getPreco());
                System.out.println("Quantidade: " + produto.getQuantidade());
            } else {
            	System.out.println("Produto não encontrado no banco de dados");
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
            	if(pS != null) {
					pS.close();
				}
				if(Conexao.getConexao() != null) {
					Conexao.getConexao().close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}  
	}
	
	public void atualizarProduto(int id, String nome, float preco, int quantidade) {
		String sql = "UPDATE produto SET nome= ?, preco= ?, quantidade= ? WHERE id= ?";
		PreparedStatement pS = null;
		
		try {
			pS = Conexao.getConexao().prepareStatement(sql);
			pS.setString(1, nome);
			pS.setFloat(2, preco);
			pS.setInt(3, quantidade);
			pS.setInt(4, id);
			pS.execute();
			System.out.println("Produto atualizado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
            	if(pS != null) {
					pS.close();
				}
				if(Conexao.getConexao() != null) {
					Conexao.getConexao().close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}  	
	}
	
	public void excluirProduto(int id) {
		String sql = "DELETE FROM produto WHERE id = ?";
		PreparedStatement pS= null;
		
		try {
			pS = Conexao.getConexao().prepareStatement(sql);
			pS.setInt(1, id);
			pS.execute();
			System.out.println("Produto excluido com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
            	if(pS != null) {
					pS.close();
				}
				if(Conexao.getConexao() != null) {
					Conexao.getConexao().close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}  	
	}
}
