package com.monsteruniversity.controlador;

import com.monsteruniversity.modelo.RubroPresupuestal;
import com.monsteruniversity.controlador.util.JsfUtil;
import com.monsteruniversity.controlador.util.JsfUtil.PersistAction;
import com.monsteruniversity.facade.RubroPresupuestalFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("rubroPresupuestalController")
@SessionScoped
public class RubroPresupuestalController implements Serializable {

    @EJB
    private com.monsteruniversity.facade.RubroPresupuestalFacade ejbFacade;
    private List<RubroPresupuestal> items = null;
    private RubroPresupuestal selected;

    public RubroPresupuestalController() {
    }

    public RubroPresupuestal getSelected() {
        return selected;
    }

    public void setSelected(RubroPresupuestal selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RubroPresupuestalFacade getFacade() {
        return ejbFacade;
    }

    public RubroPresupuestal prepareCreate() {
        selected = new RubroPresupuestal();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("RubroPresupuestalCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("RubroPresupuestalUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("RubroPresupuestalDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<RubroPresupuestal> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
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

    public RubroPresupuestal getRubroPresupuestal(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<RubroPresupuestal> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<RubroPresupuestal> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = RubroPresupuestal.class)
    public static class RubroPresupuestalControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RubroPresupuestalController controller = (RubroPresupuestalController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "rubroPresupuestalController");
            return controller.getRubroPresupuestal(getKey(value));
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
            if (object instanceof RubroPresupuestal) {
                RubroPresupuestal o = (RubroPresupuestal) object;
                return getStringKey(o.getRubId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), RubroPresupuestal.class.getName()});
                return null;
            }
        }

    }

}
