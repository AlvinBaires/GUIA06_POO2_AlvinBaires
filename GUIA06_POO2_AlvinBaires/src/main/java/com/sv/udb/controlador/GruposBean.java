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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Mauricio
 */
@Named(value = "gruposBean")
@ViewScoped
public class GruposBean implements Serializable{

    @EJB
    private GruposFacadeLocal FCDEGrupos;    
    private List<Grupos> listGrup;
    private boolean guardar;
    private Grupos objeGrup;
    private Logger logger = Logger.getLogger(GruposBean.class);
    /**
     * Creates a new instance of GruposBean
     */
    public GruposBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeGrup = new Grupos();
        this.guardar = true;        
    }

    public List<Grupos> getListGrup() {
        return listGrup;
    }

    public void setListGrup(List<Grupos> listGrup) {
        this.listGrup = listGrup;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }

    public Grupos getObjeGrup() {
        return objeGrup;
    }

    public void setObjeGrup(Grupos objeGrup) {
        this.objeGrup = objeGrup;
    }
    
    
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEGrupos.create(this.objeGrup);
            this.listGrup.add(this.objeGrup);
            logger.info("Guardado " + this.objeGrup.getNombGrup());
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        }
        catch(Exception ex)
        {
            logger.error("Error al guardar ", ex);
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
    }
    
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listGrup.remove(this.objeGrup); //Limpia el objeto viejo
            FCDEGrupos.edit(this.objeGrup);
            this.listGrup.add(this.objeGrup); //Agrega el objeto modificado
            logger.info("Modificado " + this.objeGrup.getNombGrup());
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
            FCDEGrupos.remove(this.objeGrup);
            this.listGrup.remove(this.objeGrup);
            logger.info("Eliminado " + this.objeGrup.getNombGrup());
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
        }
        catch(Exception ex)
        {
            logger.error("Error al eliminar", ex);
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
    }
    
    public void consTodo()
    {
        try
        {
            this.listGrup = FCDEGrupos.findAll();
        }
        catch(Exception ex)
        {
            logger.error("Error al consultar todo", ex);
            ex.printStackTrace();
        }
    }
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiPara"));
        try
        {
            this.objeGrup = FCDEGrupos.find(codi);
            this.guardar = false;
            logger.info("Consultado" + this.objeGrup.getNombGrup());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeGrup.getNombGrup()) + "')");
        }
        catch(Exception ex)
        {
            logger.error("Error al consultar", ex);
            ex.printStackTrace();
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
    }
    
    
    
}
