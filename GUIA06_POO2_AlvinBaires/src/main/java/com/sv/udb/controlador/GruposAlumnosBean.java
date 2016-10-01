/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.GruposAlumnosFacadeLocal;
import com.sv.udb.modelo.GruposAlumnos;
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
@Named(value = "gruposAlumnosBean")
@ViewScoped
public class GruposAlumnosBean implements Serializable{
    @EJB
    private GruposAlumnosFacadeLocal FCDEGruposAlumnos;    
    private GruposAlumnos objeGrupAlum;
    private List<GruposAlumnos> listGrupAlum;
    private Logger logger = Logger.getLogger(GruposAlumnosBean.class);
    private boolean guardar;
    
    /**
     * Creates a new instance of GruposAlumnosBean
     */

    public GruposAlumnos getObjeGrupAlum() {
        return objeGrupAlum;
    }

    public void setObjeGrupAlum(GruposAlumnos objeGrupAlum) {
        this.objeGrupAlum = objeGrupAlum;
    }

    public List<GruposAlumnos> getListGrupAlum() {
        return listGrupAlum;
    }

    public void setListGrupAlum(List<GruposAlumnos> listGrupAlum) {
        this.listGrupAlum = listGrupAlum;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }
    public GruposAlumnosBean() {
        
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeGrupAlum = new GruposAlumnos();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEGruposAlumnos.create(this.objeGrupAlum);
            this.listGrupAlum.add(this.objeGrupAlum);
            logger.info("Guardado " + this.objeGrupAlum.getCodiAlum().getNombAlum() + " " +this.objeGrupAlum.getCodiAlum().getNombAlum() + " en " + this.objeGrupAlum.getCodiGrup().getNombGrup());
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        }
        catch(Exception ex)
        {
            logger.error("Erro al guardar", ex);
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
    }
    
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listGrupAlum.remove(this.objeGrupAlum); //Limpia el objeto viejo
            FCDEGruposAlumnos.edit(this.objeGrupAlum);
            this.listGrupAlum.add(this.objeGrupAlum); //Agrega el objeto modificado
            logger.info("Modificado " + this.objeGrupAlum.getCodiAlum().getNombAlum() + " " +this.objeGrupAlum.getCodiAlum().getNombAlum() + " en " + this.objeGrupAlum.getCodiGrup().getNombGrup());
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
        }
        catch(Exception ex)
        {
            logger.error("Erro al modificar", ex);
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
    }
    
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEGruposAlumnos.remove(this.objeGrupAlum);
            this.listGrupAlum.remove(this.objeGrupAlum);
            logger.info("Eliminado " + this.objeGrupAlum.getCodiAlum().getNombAlum() + " " +this.objeGrupAlum.getCodiAlum().getNombAlum() + " en " + this.objeGrupAlum.getCodiGrup().getNombGrup());
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
        }
        catch(Exception ex)
        {
            logger.error("Erro al Eliminar", ex);
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
    }
    
    public void consTodo()
    {
        try
        {
            this.listGrupAlum = FCDEGruposAlumnos.findAll();
        }
        catch(Exception ex)
        {
            logger.error("Erro al consultar todo", ex);
            ex.printStackTrace();
        }
    }    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiGrupAlumPara"));
        try
        {
            this.objeGrupAlum = FCDEGruposAlumnos.find(codi);
            this.guardar = false;
            logger.info("Consultado " + this.objeGrupAlum.getCodiAlum().getNombAlum() + " " +this.objeGrupAlum.getCodiAlum().getNombAlum() + " en " + this.objeGrupAlum.getCodiGrup().getNombGrup());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado')");
        }
        catch(Exception ex)
        {
            logger.error("Erro al consultar", ex);
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
    }
    
}
