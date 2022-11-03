package entity;

import java.sql.Date;

public class Funcionario {

    int id;
    String nome;
    Date dtAniversario;
    String foto;
    int idSetor;
    String setor;

    public int getId() {
        return id;
    }

    public Date getDtAniversario() {
        return dtAniversario;
    }

    public void setDtAniversario(Date dtAniversario) {
        this.dtAniversario = dtAniversario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
}