package com.monsteruniversity.controlador;

import com.monsteruniversity.modelo.OrdcSolicitud;
import com.monsteruniversity.controlador.util.JsfUtil;
import com.monsteruniversity.controlador.util.JsfUtil.PersistAction;
import com.monsteruniversity.facade.OrdcSolicitudFacade;

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

@Named("ordcSolicitudController")
@SessionScoped
public class OrdcSolicitudController implements Serializable {

    @EJB
    private com.monsteruniversity.facade.OrdcSolicitudFacade ejbFacade;
    private List<OrdcSolicitud> items = null;
    private OrdcSolicitud selected;

    public OrdcSolicitudController() {
    }

    public OrdcSolicitud getSelected() {
        return selected;
    }

    public void setSelected(OrdcSolicitud selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getOrdcSolicitudPK().setSolId(selected.getSolicitud().getSolId());
        selected.getOrdcSolicitudPK().setBienId(selected.getBien().getBienId());
        selected.getOrdcSolicitudPK().setOrdcId(selected.getOrdenContractual().getOrdcId());
    }

    protected void initializeEmbeddableKey() {
        selected.setOrdcSolicitudPK(new com.monsteruniversity.modelo.OrdcSolicitudPK());
    }

    private OrdcSolicitudFacade getFacade() {
        return ejbFacade;
    }

    public OrdcSolicitud prepareCreate() {
        selected = new OrdcSolicitud();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("OrdcSolicitudCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("OrdcSolicitudUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("OrdcSolicitudDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<OrdcSolicitud> getItems() {
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

    public OrdcSolicitud getOrdcSolicitud(com.monsteruniversity.modelo.OrdcSolicitudPK id) {
        return getFacade().find(id);
    }

    public List<OrdcSolicitud> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<OrdcSolicitud> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = OrdcSolicitud.class)
    public static class OrdcSolicitudControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OrdcSolicitudController controller = (OrdcSolicitudController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ordcSolicitudController");
            return controller.getOrdcSolicitud(getKey(value));
        }

        com.monsteruniversity.modelo.OrdcSolicitudPK getKey(String value) {
            com.monsteruniversity.modelo.OrdcSolicitudPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.monsteruniversity.modelo.OrdcSolicitudPK();
            key.setSolId(Integer.parseInt(values[0]));
            key.setOrdcId(Integer.parseInt(values[1]));
            key.setBienId(Integer.parseInt(values[2]));
            return key;
        }

        String getStringKey(com.monsteruniversity.modelo.OrdcSolicitudPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getSolId());
            sb.append(SEPARATOR);
            sb.append(value.getOrdcId());
            sb.append(SEPARATOR);
            sb.append(value.getBienId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof OrdcSolicitud) {
                OrdcSolicitud o = (OrdcSolicitud) object;
                return getStringKey(o.getOrdcSolicitudPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), OrdcSolicitud.class.getName()});
                return null;
            }
        }

    }

}
