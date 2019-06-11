package com.monsteruniversity.controlador;

import com.monsteruniversity.modelo.PerfilOpciones;
import com.monsteruniversity.controlador.util.JsfUtil;
import com.monsteruniversity.controlador.util.JsfUtil.PersistAction;
import com.monsteruniversity.facade.PerfilOpcionesFacade;
import com.monsteruniversity.modelo.Opciones;
import com.monsteruniversity.modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.DualListModel;

@Named("perfilOpcionesController")
@SessionScoped
public class PerfilOpcionesController implements Serializable {

    @EJB
    private com.monsteruniversity.facade.OpcionesFacade opcFacade;
    @EJB
    private com.monsteruniversity.facade.PerfilOpcionesFacade ejbFacade;

    private DualListModel<Opciones> opciones = new DualListModel<>();
    private List<PerfilOpciones> items = null;
    private List<Opciones> opcSource;
    private List<Opciones> opcTarget;
    private PerfilOpciones selected;

    @PostConstruct
    public void init() {
        //Names Access
        opcSource = opcFacade.findAll();
        opcTarget = new ArrayList<>();
        if (opcSource != null) {
            this.opciones = new DualListModel<>(opcSource, opcTarget);
        }
    }

    public PerfilOpcionesController() {
    }

    public PerfilOpciones getSelected() {
        return selected;
    }

    public void setSelected(PerfilOpciones selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PerfilOpcionesFacade getFacade() {
        return ejbFacade;
    }

    public PerfilOpciones prepareCreate() {
        selected = new PerfilOpciones();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PerfilOpcionesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void savePickList() {
        for (Opciones opcion : opcTarget) {
            selected.setOpcId(opcion);
            create();
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PerfilOpcionesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PerfilOpcionesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PerfilOpciones> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public DualListModel<Opciones> getOpciones() {
        return opciones;
    }

    public void setOpciones(DualListModel<Opciones> opciones) {
        this.opciones = opciones;
    }

    public List<Opciones> getOpcSource() {
        return opcSource;
    }

    public void setOpcSource(List<Opciones> opcSource) {
        this.opcSource = opcSource;
    }

    public List<Opciones> getOpcTarget() {
        return opcTarget;
    }

    public void setOpcTarget(List<Opciones> opcTarget) {
        this.opcTarget = opcTarget;
    }
    public ArrayList<Opciones> opcionesPerfil(Usuario user){
        ArrayList<Opciones> opcionesPerfil = new ArrayList<>();
        
        items = ejbFacade.findAll();
        for(PerfilOpciones perOpc : items){
            if(perOpc.getPerId().getPerId()==user.getPerId().getPerId()){
                opcionesPerfil.add(perOpc.getOpcId());
            }
        }
        return opcionesPerfil;
    }
    public void mostrar() {
        System.out.println("Opciones: " + opcTarget.size());
        for (int i = 0; i < opcTarget.size(); i++) {
            System.out.println("Opcion: " + opcTarget.get(i).getOpcNombre());
        }
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public PerfilOpciones getPerfilOpciones(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<PerfilOpciones> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PerfilOpciones> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PerfilOpciones.class)
    public static class PerfilOpcionesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PerfilOpcionesController controller = (PerfilOpcionesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "perfilOpcionesController");
            return controller.getPerfilOpciones(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PerfilOpciones) {
                PerfilOpciones o = (PerfilOpciones) object;
                return getStringKey(o.getPerOpcId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PerfilOpciones.class.getName()});
                return null;
            }
        }

    }

}
