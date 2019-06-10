package com.monsteruniversity.controlador;

import com.monsteruniversity.modelo.SalidaBien;
import com.monsteruniversity.controlador.util.JsfUtil;
import com.monsteruniversity.controlador.util.JsfUtil.PersistAction;
import com.monsteruniversity.facade.SalidaBienFacade;

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

@Named("salidaBienController")
@SessionScoped
public class SalidaBienController implements Serializable {

    @EJB
    private com.monsteruniversity.facade.SalidaBienFacade ejbFacade;
    private List<SalidaBien> items = null;
    private SalidaBien selected;

    public SalidaBienController() {
    }

    public SalidaBien getSelected() {
        return selected;
    }

    public void setSelected(SalidaBien selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getSalidaBienPK().setSalId(selected.getSalidaAlmacen().getSalidaAlmacenPK().getSalId());
        selected.getSalidaBienPK().setBienProvId(selected.getBienProveedor().getBienProvId());
        selected.getSalidaBienPK().setEntId(selected.getSalidaAlmacen().getSalidaAlmacenPK().getEntId());
        selected.getSalidaBienPK().setProvId(selected.getSalidaAlmacen().getSalidaAlmacenPK().getProvId());
    }

    protected void initializeEmbeddableKey() {
        selected.setSalidaBienPK(new com.monsteruniversity.modelo.SalidaBienPK());
    }

    private SalidaBienFacade getFacade() {
        return ejbFacade;
    }

    public SalidaBien prepareCreate() {
        selected = new SalidaBien();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SalidaBienCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SalidaBienUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SalidaBienDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SalidaBien> getItems() {
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

    public SalidaBien getSalidaBien(com.monsteruniversity.modelo.SalidaBienPK id) {
        return getFacade().find(id);
    }

    public List<SalidaBien> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SalidaBien> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = SalidaBien.class)
    public static class SalidaBienControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SalidaBienController controller = (SalidaBienController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "salidaBienController");
            return controller.getSalidaBien(getKey(value));
        }

        com.monsteruniversity.modelo.SalidaBienPK getKey(String value) {
            com.monsteruniversity.modelo.SalidaBienPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.monsteruniversity.modelo.SalidaBienPK();
            key.setBienProvId(Integer.parseInt(values[0]));
            key.setProvId(Integer.parseInt(values[1]));
            key.setEntId(Integer.parseInt(values[2]));
            key.setSalId(Integer.parseInt(values[3]));
            return key;
        }

        String getStringKey(com.monsteruniversity.modelo.SalidaBienPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getBienProvId());
            sb.append(SEPARATOR);
            sb.append(value.getProvId());
            sb.append(SEPARATOR);
            sb.append(value.getEntId());
            sb.append(SEPARATOR);
            sb.append(value.getSalId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof SalidaBien) {
                SalidaBien o = (SalidaBien) object;
                return getStringKey(o.getSalidaBienPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SalidaBien.class.getName()});
                return null;
            }
        }

    }

}
