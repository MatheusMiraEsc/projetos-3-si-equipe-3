package ingressart.teatro.model;


// Administrador.java
public class Administrador extends Pessoa {
    private boolean isAdmin;

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin(int idPessoa) {
        // Poderia validar via DAO ou lógica adicional
        return this.isAdmin;
    }
}
