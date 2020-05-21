/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.resfulserver.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Nightm4re
 */
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "nick")
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nick", length = 255, unique = true, nullable = false)
    private String nick;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "nombre")
    @ManyToMany
    @JoinTable(
            name = "usuarios_grupos",
            joinColumns = {
                @JoinColumn(name = "id_grupo")},
            inverseJoinColumns = {
                @JoinColumn(name = "id_usuario")
            })
    private Set<Grupo> grupos;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = false,
            mappedBy = "usuario"
    )
    private Set<Puntuacion> puntuaciones;

    @Column(name = "estado")
    private String estado;

    public Long getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public Set<Grupo> getGrupos() {
        return grupos;
    }

    @JsonManagedReference
    public Set<Puntuacion> getPuntuaciones() {
        return puntuaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setGrupos(Set<Grupo> grupos) {
        this.grupos = grupos;
    }

    public void setPuntuaciones(Set<Puntuacion> puntuaciones) {
        this.puntuaciones = puntuaciones;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
