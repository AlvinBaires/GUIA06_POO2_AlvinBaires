/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.AlumnosFacadeLocal;
import com.sv.udb.modelo.Alumnos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.primefaces.context.RequestContext;

/**
 *
 * @author REGISTRO
 */
@Named(value = "alumnosBean")
@ViewScoped
@ManagedBean
public class AlumnosBean implements Serializable{
    @EJB
    private AlumnosFacadeLocal FCDEalumnosFacade;    
    private Alumnos objeAlum;
    private boolean guardar;
    private List<Alumnos> alumList = null;

    public Alumnos getObjeAlum() {
        return objeAlum;
    }

    public void setObjeAlum(Alumnos objeAlum) {
        this.objeAlum = objeAlum;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public List<Alumnos> getAlumList() {
        return alumList;
    }

    public void setAlumList(List<Alumnos> alumList) {
        this.alumList = alumList;
    }
    
    public AlumnosBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.objeAlum = new Alumnos();
        this.guardar = true;
        ConsTodo();
    }
    
    public void ConsTodo() {
        try 
        {
            this.alumList=FCDEalumnosFacade.findAll();
        } 
        catch (Exception ex) 
        {

        }
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance();
        try
        {
            FCDEalumnosFacade.create(this.objeAlum);
            this.objeAlum = new Alumnos();
            ConsTodo();
            ctx.execute("setMessage('MESS_SUCC', 'Alerta', 'Datos guardados exitosamente');");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar datos.');");
        }
        finally
        {
            
        }
    }
    
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturar el contexto
        try
        {
            FCDEalumnosFacade.edit(this.objeAlum);
            ConsTodo();
            this.objeAlum = new Alumnos();
            ctx.execute("setMessage('MESS_SUCC', 'Alerta', 'Registro modificado exitosamente.');");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar registro.');");
        }
    }
    
    public void elim(int codi)
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturar el contexto
        try
        {
            FCDEalumnosFacade.remove(this.objeAlum);
            ConsTodo();
            this.objeAlum = new Alumnos();
            ctx.execute("setMessage('MESS_SUCC', 'Alerta', 'Registro eliminado exitosamente.');");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar registro.');");
        }
    }
    
    public void cons(int codi)
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturar el contexto
        //int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nombreParametro"));
        try
        {            
            this.objeAlum = FCDEalumnosFacade.find(codi);
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s %s", this.objeAlum.getNombAlum(), this.objeAlum.getApelAlum()) + "')");
        }
        catch(Exception ex)
        {            
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar datos.');");
            ex.printStackTrace();
        }
        finally
        {            
            
        }
    }
}
