package com.monsteruniversity.controlador;

import com.monsteruniversity.modelo.EntradaAlmacen;
import com.monsteruniversity.controlador.util.JsfUtil;
import com.monsteruniversity.controlador.util.JsfUtil.PersistAction;
import com.monsteruniversity.facade.EntradaAlmacenFacade;

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

@Named("entradaAlmacenController")
@SessionScoped
public class EntradaAlmacenController implements Serializable {

    @EJB
    private com.monsteruniversity.facade.EntradaAlmacenFacade ejbFacade;
    private List<EntradaAlmacen> items = null;
    private EntradaAlmacen selected;

    public EntradaAlmacenController() {
    }

    public EntradaAlmacen getSelected() {
        return selected;
    }

    public void setSelected(EntradaAlmacen selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getEntradaAlmacenPK().setProvId(selected.getProveedor().getProvId());
    }

    protected void initializeEmbeddableKey() {
        selected.setEntradaAlmacenPK(new com.monsteruniversity.modelo.EntradaAlmacenPK());
    }

    private EntradaAlmacenFacade getFacade() {
        return ejbFacade;
    }

    public EntradaAlmacen prepareCreate() {
        selected = new EntradaAlmacen();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EntradaAlmacenCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EntradaAlmacenUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EntradaAlmacenDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<EntradaAlmacen> getItems() {
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

    public EntradaAlmacen getEntradaAlmacen(com.monsteruniversity.modelo.EntradaAlmacenPK id) {
        return getFacade().find(id);
    }

    public List<EntradaAlmacen> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<EntradaAlmacen> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = EntradaAlmacen.class)
    public static class EntradaAlmacenControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EntradaAlmacenController controller = (EntradaAlmacenController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "entradaAlmacenController");
            return controller.getEntradaAlmacen(getKey(value));
        }

        com.monsteruniversity.modelo.EntradaAlmacenPK getKey(String value) {
            com.monsteruniversity.modelo.EntradaAlmacenPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.monsteruniversity.modelo.EntradaAlmacenPK();
            key.setProvId(Integer.parseInt(values[0]));
            key.setEntId(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.monsteruniversity.modelo.EntradaAlmacenPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getProvId());
            sb.append(SEPARATOR);
            sb.append(value.getEntId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof EntradaAlmacen) {
                EntradaAlmacen o = (EntradaAlmacen) object;
                return getStringKey(o.getEntradaAlmacenPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), EntradaAlmacen.class.getName()});
                return null;
            }
        }

    }

}
