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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Laboratorio
 */
@Named(value = "profesoresBean")
@ViewScoped
public class ProfesoresBean implements Serializable{
    @EJB
    private ProfesoresFacadeLocal FCDEProfesores;    
    private Profesores objeProf;
    private List<Profesores> listProf;
    private boolean guardar;
    private Logger logger = Logger.getLogger(ProfesoresBean.class);
    /**
     * Creates a new instance of ProfesoresBean
     */
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEProfesores.create(this.objeProf);
            this.listProf.add(this.objeProf);
            logger.info("Guardado " +this.objeProf.getNombProf() + " " + this.objeProf.getApelProf());
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        }
        catch(Exception ex)
        {
            logger.error("Error al guardar", ex);
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
    }
    
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listProf.remove(this.objeProf); //Limpia el objeto viejo
            FCDEProfesores.edit(this.objeProf);
            this.listProf.add(this.objeProf); //Agrega el objeto modificado
            logger.info("Modificado " +this.objeProf.getNombProf() + " " + this.objeProf.getApelProf());
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
        }
        catch(Exception ex)
        {
            logger.error("Error al modificar ", ex);
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
    }
    
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEProfesores.remove(this.objeProf);
            this.listProf.remove(this.objeProf);
            logger.info("Eliminado" +this.objeProf.getNombProf() + " " + this.objeProf.getApelProf());
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
        }
        catch(Exception ex)
        {
            logger.error("Error al eliminar ", ex);
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
    }
    
    public ProfesoresBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeProf = new Profesores();
        this.guardar = true;        
    }
    
    public void consTodo()
    {
        try
        {
            this.listProf = FCDEProfesores.findAll();
        }
        catch(Exception ex)
        {
            logger.error("Error al consultar todo ",ex);
            ex.printStackTrace();
        }
    }
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiPara"));
        try
        {
            this.objeProf = FCDEProfesores.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s %s", this.objeProf.getNombProf(), this.objeProf.getApelProf()) + "')");
            logger.info("Consultado " +this.objeProf.getNombProf() + " " + this.objeProf.getApelProf());
        }
        catch(Exception ex)
        {
            logger.error("Error al consultar ", ex);
            ex.printStackTrace();
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
    }

    public Profesores getObjeProf() {
        return objeProf;
    }

    public void setObjeProf(Profesores objeProf) {
        this.objeProf = objeProf;
    }

    public List<Profesores> getListProf() {
        return listProf;
    }

    public void setListProf(List<Profesores> listProf) {
        this.listProf = listProf;
    }

        
    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }
    
    
        
}
