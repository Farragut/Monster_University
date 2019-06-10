package com.monsteruniversity.controlador;

import com.monsteruniversity.modelo.SolicitudDetalle;
import com.monsteruniversity.controlador.util.JsfUtil;
import com.monsteruniversity.controlador.util.JsfUtil.PersistAction;
import com.monsteruniversity.facade.SolicitudDetalleFacade;

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

@Named("solicitudDetalleController")
@SessionScoped
public class SolicitudDetalleController implements Serializable {

    @EJB
    private com.monsteruniversity.facade.SolicitudDetalleFacade ejbFacade;
    private List<SolicitudDetalle> items = null;
    private SolicitudDetalle selected;

    public SolicitudDetalleController() {
    }

    public SolicitudDetalle getSelected() {
        return selected;
    }

    public void setSelected(SolicitudDetalle selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getSolicitudDetallePK().setSolId(selected.getSolicitud().getSolId());
        selected.getSolicitudDetallePK().setBienProvId(selected.getBienProveedor().getBienProvId());
    }

    protected void initializeEmbeddableKey() {
        selected.setSolicitudDetallePK(new com.monsteruniversity.modelo.SolicitudDetallePK());
    }

    private SolicitudDetalleFacade getFacade() {
        return ejbFacade;
    }

    public SolicitudDetalle prepareCreate() {
        selected = new SolicitudDetalle();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SolicitudDetalleCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SolicitudDetalleUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SolicitudDetalleDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SolicitudDetalle> getItems() {
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

    public SolicitudDetalle getSolicitudDetalle(com.monsteruniversity.modelo.SolicitudDetallePK id) {
        return getFacade().find(id);
    }

    public List<SolicitudDetalle> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SolicitudDetalle> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = SolicitudDetalle.class)
    public static class SolicitudDetalleControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SolicitudDetalleController controller = (SolicitudDetalleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "solicitudDetalleController");
            return controller.getSolicitudDetalle(getKey(value));
        }

        com.monsteruniversity.modelo.SolicitudDetallePK getKey(String value) {
            com.monsteruniversity.modelo.SolicitudDetallePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.monsteruniversity.modelo.SolicitudDetallePK();
            key.setSolId(Integer.parseInt(values[0]));
            key.setBienProvId(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.monsteruniversity.modelo.SolicitudDetallePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getSolId());
            sb.append(SEPARATOR);
            sb.append(value.getBienProvId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof SolicitudDetalle) {
                SolicitudDetalle o = (SolicitudDetalle) object;
                return getStringKey(o.getSolicitudDetallePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SolicitudDetalle.class.getName()});
                return null;
            }
        }

    }

}
