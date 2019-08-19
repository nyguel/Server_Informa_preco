package entidade;

public class Usuario {
	private int id;
    private String email;
    private String senha;    

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
    public Usuario(){
        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
