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
import javax.annotation.PostConstruct;
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
    private GruposAlumnosFacadeLocal FCDEGrupAlum;    
    private GruposAlumnos objeGrupAlum;
    private boolean guardar;
    private List<GruposAlumnos> listGrupAlum = null;
    
    @PostConstruct
    public void init()
    {
        this.objeGrupAlum = new GruposAlumnos();
        this.guardar = true;
        //ConsTodo();
    }
      
    public GruposAlumnosBean() {
    }

    public GruposAlumnosFacadeLocal getFCDEGrupAlum() {
        return FCDEGrupAlum;
    }

    public void setFCDEGrupAlum(GruposAlumnosFacadeLocal FCDEGrupAlum) {
        this.FCDEGrupAlum = FCDEGrupAlum;
    }

    public GruposAlumnos getObjeGrupAlum() {
        return objeGrupAlum;
    }

    public void setObjeGrupAlum(GruposAlumnos objeGrupAlum) {
        this.objeGrupAlum = objeGrupAlum;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }

    public List<GruposAlumnos> getListGrupAlum() {
        return listGrupAlum;
    }

    public void setListGrupAlum(List<GruposAlumnos> listGrupAlum) {
        this.listGrupAlum = listGrupAlum;
    }
}
