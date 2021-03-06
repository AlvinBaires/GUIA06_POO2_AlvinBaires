/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.AlumnosFacadeLocal;
import com.sv.udb.modelo.Alumnos;
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
 * @author REGISTRO
 */
@Named(value = "alumnosBean")
@ViewScoped
public class AlumnosBean implements Serializable{
    @EJB
    private AlumnosFacadeLocal FCDEAlum;    
    private Alumnos objeAlum;
    private List<Alumnos> listAlum;
    private boolean guardar;
    private Logger logger = Logger.getLogger(AlumnosBean.class);
    
    public Alumnos getObjeAlum() {
        return objeAlum;
    }

    public void setObjeAlum(Alumnos objeAlum) {
        this.objeAlum = objeAlum;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<Alumnos> getListAlum() {
        return listAlum;
    }
    
    /**
     * Creates a new instance of AlumnosBean
     */
    
    public AlumnosBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeAlum = new Alumnos();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEAlum.create(this.objeAlum);
            logger.info("Guardado: " + this.objeAlum.getNombAlum()+" " + this.objeAlum.getApelAlum());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            this.listAlum.add(this.objeAlum);
            this.limpForm();            
        }
        catch(Exception ex)
        {
            logger.error("Error al guardar: ", ex);
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
    }
    
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance();
        try
        {
            this.listAlum.remove(this.objeAlum);
            FCDEAlum.edit(this.objeAlum);
            logger.info("Modificado: "+this.objeAlum.getNombAlum()+" "+this.objeAlum.getApelAlum());
            this.listAlum.add(this.objeAlum);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
        }
        catch(Exception ex)
        {
            logger.error("Error al modificar: ",ex);
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
    }
    
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEAlum.remove(this.objeAlum);
            this.listAlum.remove(this.objeAlum);
            logger.info("Eliminado: " + this.objeAlum.getNombAlum()+" " +this.objeAlum.getApelAlum());
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
        }
        catch(Exception ex)
        {
            logger.error("Error al eliminar: ", ex);
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
    }
    
    public void consTodo()
    {
        try
        {
            this.listAlum = FCDEAlum.findAll();
        }
        catch(Exception ex)
        {
            logger.error("Error al consultar registros ", ex);
            ex.printStackTrace();
        }
    }
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiAlumPara"));
        try
        {
            this.objeAlum = FCDEAlum.find(codi);
            this.guardar = false;
            logger.info("Consultado " + this.objeAlum.getNombAlum()+" "+this.objeAlum.getApelAlum());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s %s", this.objeAlum.getNombAlum(), this.objeAlum.getApelAlum()) + "')");
        }
        catch(Exception ex)
        {
            logger.error("Error al consultar registro",ex);
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
    }
}
