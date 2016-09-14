/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Alumnos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
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
        this.alumList = this.ConsTodo();
    }
    
    public List<Alumnos> ConsTodo() {
        List<Alumnos> resp = new ArrayList<>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("POOPU");
        EntityManager em = emf.createEntityManager();
        try 
        {
            TypedQuery<Alumnos> query = em.createNamedQuery("Alumnos.findAll", Alumnos.class);
            resp = query.getResultList();
        } 
        catch (Exception ex) 
        {

        }
        return resp;
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturar el contexto
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("POOPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try
        {
            em.persist(this.objeAlum);
            tx.commit();
            this.guardar = true;
            this.alumList = this.ConsTodo();
            this.objeAlum = new Alumnos();
            ctx.execute("setMessage('MESS_SUCC', 'Alerta', 'Datos guardados exitosamente');");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar datos.');");
            tx.rollback();
            ex.printStackTrace();
        }
        finally
        {
            em.close();
            emf.close();            
        }
    }
    
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturar el contexto
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("POOPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try
        {
            em.merge(objeAlum);
            tx.commit();
            this.alumList = this.ConsTodo();
            this.objeAlum = new Alumnos();
            ctx.execute("setMessage('MESS_SUCC', 'Alerta', 'Registro modificado exitosamente.');");
        }
        catch(Exception ex)
        {
            tx.rollback();
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar registro.');");
        }
        em.close();
        emf.close();
    }
    
    public void elim(int codi)
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturar el contexto
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("POOPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try
        {
            Alumnos obj = em.find(Alumnos.class, codi);
            em.remove(obj);
            tx.commit();
            this.alumList = this.ConsTodo();
            this.objeAlum = new Alumnos();
            ctx.execute("setMessage('MESS_SUCC', 'Alerta', 'Registro eliminado exitosamente.');");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar registro.');");
            tx.rollback();
        }
        em.close();
        emf.close();
    }
    
    public void cons(int codi)
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturar el contexto
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("POOPU");
        EntityManager em = emf.createEntityManager();
        try
        {
            ctx.execute("setMessage('MESS_SUCC', 'Alerta', 'Registro consultado.');");
            this.objeAlum = em.find(Alumnos.class, codi);
        }
        catch(Exception ex)
        {            
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar datos.');");
            ex.printStackTrace();
        }
        finally
        {            
            em.close();
            emf.close();            
        }
    }
}
