package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "billing_account")
public class BillingAccount {

    private long id;

    private String login;

    private String email;

    public BillingAccount(String login, String email) {
        this.login = login;
        this.email = email;
    }

    public BillingAccount() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillingAccount that = (BillingAccount) o;
        return id == that.id &&
                Objects.equals(login, that.login) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, login, email);
    }

    @Override
    public String toString() {
        return "BillingAccount{" +
            "id=" + id +
            ", login='" + login + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
