/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.resfulserver.controllers;

/**
 *
 * @author Nightm4re
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.nightm4re.resfulserver.model.Puntuacion;
import com.nightm4re.resfulserver.exceptions.RecordNotFoundException;
import com.nightm4re.resfulserver.services.PuntuacionService;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
 
@RestController
@RequestMapping("/puntuacion")
public class PuntuacionServiceController
{
    @Autowired
    PuntuacionService service;
 
    @GetMapping
    public ResponseEntity<Set<Puntuacion>> getAllComentarios() {
        Set<Puntuacion> list = service.getAllPuntuacions();
 
        return new ResponseEntity<Set<Puntuacion>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Puntuacion> getComentarioById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
    	Puntuacion entity = service.getPuntuacionById(id);
 
        return new ResponseEntity<Puntuacion>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/search/{cantidad}")
    public ResponseEntity<Set<Puntuacion>> getComentariosByTitle(@PathVariable("cantidad") int cantidad) {
    	Set<Puntuacion> list = service.getPuntuacionByCantidad(cantidad);
 
        return new ResponseEntity<Set<Puntuacion>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
   @PostMapping
    public ResponseEntity<Puntuacion> createComentario(@Valid @RequestBody Puntuacion myComentario){
    	Puntuacion created = service.createPuntuacion(myComentario);
        return new ResponseEntity<Puntuacion>(created, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<Puntuacion> UpdateComentario(@Valid @RequestBody Puntuacion myComentario)
                                                    throws RecordNotFoundException {
    	Puntuacion updated = service.createPuntuacion(myComentario);
        return new ResponseEntity<Puntuacion>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteComentarioById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deletePuntuacionById(id);
        return HttpStatus.ACCEPTED;
    }
 
}
