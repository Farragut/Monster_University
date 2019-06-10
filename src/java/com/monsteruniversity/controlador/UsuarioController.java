package com.monsteruniversity.controlador;

import com.monsteruniversity.modelo.Usuario;
import com.monsteruniversity.controlador.util.JsfUtil;
import com.monsteruniversity.controlador.util.JsfUtil.PersistAction;
import com.monsteruniversity.facade.UsuarioFacade;
import com.monsteruniversity.modelo.Opciones;
import java.io.IOException;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;

@Named("usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    private String usuario = null;
    private String password = null;
    private Usuario usuarioAutenticado;
    private DualListModel<Opciones> opciones = new DualListModel<>();

    @EJB
    private com.monsteruniversity.facade.UsuarioFacade ejbFacade;
    @EJB
    private com.monsteruniversity.facade.OpcionesFacade opcFacade;
    private List<Usuario> items = null;
    private List<Opciones> opcSource;
    private List<Opciones> opcTarget;
    private Usuario selected;

    @PostConstruct
    public void init() {
        //Names Access
        opcSource = opcFacade.findAll();
        opcTarget = new ArrayList<>();
        if (opcSource != null) {
            this.opciones = new DualListModel<>(opcSource, opcTarget);
        }
    }

    public UsuarioController() {
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

    public void setOpciones(DualListModel<Opciones> opciones) {
        this.opciones = opciones;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public DualListModel<Opciones> getOpciones() {
        return opciones;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getSelected() {
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuarioFacade getFacade() {
        return ejbFacade;
    }

    public Usuario prepareCreate() {
        selected = new Usuario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Usuario> getItems() {
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

    public Usuario getUsuario(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Usuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Usuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");
            return controller.getUsuario(getKey(value));
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
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getUsuId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuario.class.getName()});
                return null;
            }
        }

    }

    public boolean login() throws NoSuchAlgorithmException {
        usuarioAutenticado = ejbFacade.iniciarSesion(usuario, generateHash(password));
        if (usuarioAutenticado != null) {
            return true;
        } else {
            return false;
        }
    }

    public void verficarAccceso() throws IOException, NoSuchAlgorithmException {
        if (login()) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/MonsterPrimefaces5/faces/index.xhtml");
        } else {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos inválidos", "Verifique su usuario y contraseña"));
        }
    }

    public String generateHash(String pass) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(pass.getBytes());
        byte[] b = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b1 : b) {
            sb.append(Integer.toHexString(b1 & 0xff).toString());
        }
        return sb.toString();
    }

    public void verificarSesion() throws IOException {
        System.out.println("Usuario: " + usuario);
        if (usuario == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/MonsterPrimefaces5/faces/login.xhtml");
        }
    }

    public void terminarSesion() throws IOException {
        usuario = null;
        FacesContext.getCurrentInstance().getExternalContext().redirect("/MonsterPrimefaces5/faces/login.xhtml");
    }
    
    public void mostrar() {
        System.out.println("Opciones: "+opcTarget.size());
        for (int i=0; i<opcTarget.size();i++){
            System.out.println("Opcion: "+opcTarget.get(i).getOpcNombre());
        }
    }

}
