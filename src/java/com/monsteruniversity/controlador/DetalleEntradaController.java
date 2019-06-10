package com.monsteruniversity.controlador;

import com.monsteruniversity.modelo.DetalleEntrada;
import com.monsteruniversity.controlador.util.JsfUtil;
import com.monsteruniversity.controlador.util.JsfUtil.PersistAction;
import com.monsteruniversity.facade.DetalleEntradaFacade;

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

@Named("detalleEntradaController")
@SessionScoped
public class DetalleEntradaController implements Serializable {

    @EJB
    private com.monsteruniversity.facade.DetalleEntradaFacade ejbFacade;
    private List<DetalleEntrada> items = null;
    private DetalleEntrada selected;

    public DetalleEntradaController() {
    }

    public DetalleEntrada getSelected() {
        return selected;
    }

    public void setSelected(DetalleEntrada selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getDetalleEntradaPK().setProvId(selected.getEntradaAlmacen().getEntradaAlmacenPK().getProvId());
        selected.getDetalleEntradaPK().setEntId(selected.getEntradaAlmacen().getEntradaAlmacenPK().getEntId());
        selected.getDetalleEntradaPK().setBienProvId(selected.getBienProveedor().getBienProvId());
    }

    protected void initializeEmbeddableKey() {
        selected.setDetalleEntradaPK(new com.monsteruniversity.modelo.DetalleEntradaPK());
    }

    private DetalleEntradaFacade getFacade() {
        return ejbFacade;
    }

    public DetalleEntrada prepareCreate() {
        selected = new DetalleEntrada();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DetalleEntradaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DetalleEntradaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DetalleEntradaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DetalleEntrada> getItems() {
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

    public DetalleEntrada getDetalleEntrada(com.monsteruniversity.modelo.DetalleEntradaPK id) {
        return getFacade().find(id);
    }

    public List<DetalleEntrada> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DetalleEntrada> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DetalleEntrada.class)
    public static class DetalleEntradaControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetalleEntradaController controller = (DetalleEntradaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detalleEntradaController");
            return controller.getDetalleEntrada(getKey(value));
        }

        com.monsteruniversity.modelo.DetalleEntradaPK getKey(String value) {
            com.monsteruniversity.modelo.DetalleEntradaPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.monsteruniversity.modelo.DetalleEntradaPK();
            key.setProvId(Integer.parseInt(values[0]));
            key.setEntId(Integer.parseInt(values[1]));
            key.setBienProvId(Integer.parseInt(values[2]));
            return key;
        }

        String getStringKey(com.monsteruniversity.modelo.DetalleEntradaPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getProvId());
            sb.append(SEPARATOR);
            sb.append(value.getEntId());
            sb.append(SEPARATOR);
            sb.append(value.getBienProvId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DetalleEntrada) {
                DetalleEntrada o = (DetalleEntrada) object;
                return getStringKey(o.getDetalleEntradaPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DetalleEntrada.class.getName()});
                return null;
            }
        }

    }

}
