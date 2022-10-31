package com.knowledge.spring.loan.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "loaner")
public class Loaner {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String loanerName;
    @Column(name = "surname")
    private String loanerSurname;
    @Column(name = "father_name")
    private String fatherName;
    @Column(name = "birthdate")
    private Date birthdate;
    @Column(name = "afm")
    private long afm;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoanerName() {
        return loanerName;
    }

    public void setLoanerName(String loanerName) {
        this.loanerName = loanerName;
    }

    public String getLoanerSurname() {
        return loanerSurname;
    }

    public void setLoanerSurname(String loanerSurname) {
        this.loanerSurname = loanerSurname;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public long getAfm() {
        return afm;
    }

    public void setAfm(long afm) {
        this.afm = afm;
    }
}
