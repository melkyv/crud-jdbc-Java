package exercicio7;

public class Produto {
    private int id;
    private String nome;
    private float preco;
    private int quantidade;
    
    int getId() {
    	return this.id;
    }
    void setId(int id) {
    	this.id = id;
    }
    
    String getNome() {
    	return this.nome;
    }
    void setNome(String nome) {
    	this.nome = nome;
    }
    
    float getPreco() {
    	return this.preco;
    }
    void setPreco(float preco) {
    	this.preco = preco;
    }
    
    int getQuantidade() {
    	return this.quantidade;
    }
    void setQuantidade(int qtd) {
    	this.quantidade = qtd;
    }
}
