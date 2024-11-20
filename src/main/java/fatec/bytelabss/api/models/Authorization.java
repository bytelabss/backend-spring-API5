package fatec.bytelabss.api.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "autorizacoes")
public class Authorization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuarios_autorizacoes", joinColumns = @JoinColumn(name = "autorizacao_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private Set<User> users;

    public Authorization() {
    }

    public Authorization(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @JsonCreator
    public static Authorization fromString(String name) {
        return new Authorization(name);
    }
}
