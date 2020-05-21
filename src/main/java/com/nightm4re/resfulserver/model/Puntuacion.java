/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.resfulserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Nightm4re
 */
@Entity
@JsonIdentityInfo(
   generator = ObjectIdGenerators.PropertyGenerator.class,
   property = "cantidad")
@Table(name = "puntuacion")
public class Puntuacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Range(min=1)
    @Column(name = "cantidad")
    private int cantidad;    
    
    @ManyToOne
    @JoinColumn
    private Usuario usuario;    

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @JsonBackReference
    public Usuario getUsuario() {
        return usuario;
    }
    

    public Long getId() {
        return id;
    }

    public int getCantidad () {
        return cantidad;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCantidad (int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Puntuacion{" + "id=" + id + ", cantidad=" + cantidad + ", usuario=" + usuario + '}';
    }
}
