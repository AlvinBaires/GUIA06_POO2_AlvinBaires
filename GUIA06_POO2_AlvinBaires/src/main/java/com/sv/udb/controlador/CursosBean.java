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
@Named(value = "cursosBean")
@ViewScoped
public class CursosBean implements Serializable{
    @EJB
    private CursosFacadeLocal FCDECursos;    
    private Cursos objeCurs;
    private List<Cursos> listCurs;
    private boolean guardar;
    private Logger logger = Logger.getLogger(CursosBean.class);
    /**
     * Creates a new instance of CursosBean
     */
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDECursos.create(this.objeCurs);
            logger.info("Guardado: " + this.objeCurs.getNombCurs());
            this.listCurs.add(this.objeCurs);
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
            this.listCurs.remove(this.objeCurs); //Limpia el objeto viejo
            FCDECursos.edit(this.objeCurs);
            this.listCurs.add(this.objeCurs); //Agrega el objeto modificado
            logger.info("Modificado: " + this.objeCurs.getNombCurs());
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
        }
        catch(Exception ex)
        {
            logger.error("Error al modificar",ex);
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
    }
    
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDECursos.remove(this.objeCurs);
            this.listCurs.remove(this.objeCurs);
            logger.info("Error" + this.objeCurs.getNombCurs());
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
        }
        catch(Exception ex)
        {
            logger.error("Error al eliminar: ", ex);
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
    }
    
    public CursosBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeCurs = new Cursos();
        this.guardar = true;        
    }
    
    public void consTodo()
    {
        try
        {
            this.listCurs = FCDECursos.findAll();
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
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiPara"));
        try
        {
            this.objeCurs = FCDECursos.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeCurs.getNombCurs()) + "')");
            logger.info("Consultado " + this.objeCurs.getNombCurs());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            logger.error("Error al consultar", ex);
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
    }

    public List<Cursos> getListCurs() {
        return listCurs;
    }

    public void setListCurs(List<Cursos> listCurs) {
        this.listCurs = listCurs;
    }

    public Cursos getObjeCurs() {
        return objeCurs;
    }

    public void setObjeCurs(Cursos objeCurs) {
        this.objeCurs = objeCurs;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }
    
    
        
}
