/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.GruposFacadeLocal;
import com.sv.udb.modelo.Grupos;
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
@Named(value = "gruposBean")
@ViewScoped
@ManagedBean
public class GruposBean implements Serializable{
    @EJB
    private GruposFacadeLocal FCDEGrupos;    
    private List<Grupos> listGrup;
    
    @PostConstruct
    public void init()
    {
        this.listGrup = FCDEGrupos.findAll();
    }
    
    public GruposBean() {
        
    }    

    public List<Grupos> getListGrup() {
        return listGrup;
    }

    public void setListGrup(List<Grupos> listGrup) {
        this.listGrup = listGrup;
    }
    
    
}
