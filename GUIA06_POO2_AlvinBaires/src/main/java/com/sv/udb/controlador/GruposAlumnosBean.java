/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.*;
import com.sv.udb.modelo.*;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Laboratorio
 */
@Named(value = "gruposAlumnosBean")
@ViewScoped
@ManagedBean
public class GruposAlumnosBean implements Serializable{
    @EJB
    private GruposAlumnosFacadeLocal FCDEalumnosFacade;    
    private GruposAlumnos objeGrupAlum;
    private boolean guardar;
    private List<GruposAlumnos> listGrupAlum = null;
      
    public GruposAlumnosBean() {
    }
    
}
