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
import java.util.Properties;
import java.util.Random;
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
import javax.faces.context.Flash;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DualListModel;

@Named("usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    private boolean cambioPass = false;
    private String usuario = null;
    private String password = null;
    private Usuario usuarioAutenticado;
    private DualListModel<Opciones> opciones = new DualListModel<>();
    private String newPassword = null;
    private String antPassword = null;
    private String repPassword = null;
    @Inject
    private PerfilOpcionesController perfilOpcionController;

    @EJB
    private com.monsteruniversity.facade.UsuarioFacade ejbFacade;
    @EJB
    private com.monsteruniversity.facade.OpcionesFacade opcFacade;
    private List<Usuario> items = null;
    private List<Opciones> opcSource;
    private ArrayList<Opciones> opcionesPerfil;
    private List<Opciones> opcTarget;
    private Usuario selected;
    private Usuario usuarioPass;

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

    public String getNewPassword() {
        return newPassword;
    }

    public String getRepPassword() {
        return repPassword;
    }

    public void setRepPassword(String repPassword) {
        this.repPassword = repPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getAntPassword() {
        return antPassword;
    }

    public void setAntPassword(String antPassword) {
        this.antPassword = antPassword;
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
        try {
            selected.setUsuPassword(generateHash(password));
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
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

    public Usuario obtenerDatosUsu() throws NoSuchAlgorithmException {
        usuarioPass = ejbFacade.obtenerEmpId(usuario);
        return usuarioPass;
    }

    public String randomPass() {
        Random r = new Random();
        int valorDado = r.nextInt();
        System.out.println("PASS: " + valorDado);
        return valorDado + "";
    }

    public void cambiarPass() {
        try {
            String pass;
            System.out.println("CAMBIO PASS: " + cambioPass);
            selected = obtenerDatosUsu();
            if (!cambioPass) {
                pass = randomPass();
                selected.setUsuPassword(generateHash(pass));
                enviarConGMail("rudelhuancas04@gmail.com", "Cambio de contraseña", "Su contraseña ha sido cambiada\nSu contraseña temporal es:" + pass + "\nSi no fue realizado por ud. contáctese con el administrador.");
                cambioPass = true;
                persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
                usuario = null;
                FacesContext facesContext = FacesContext.getCurrentInstance();
                Flash flash = facesContext.getExternalContext().getFlash();
                flash.setKeepMessages(true);
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contraseña reseteada", "La contraseña ha sido reseteada"));
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Monster_University/faces/login.xhtml");
            } else {
                cambioPass = false;
                pass = generateHash(password);
                selected.setUsuPassword(pass);
                persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
                usuario = null;
                FacesContext facesContext = FacesContext.getCurrentInstance();
                Flash flash = facesContext.getExternalContext().getFlash();
                flash.setKeepMessages(true);
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contraseña cambiada", "La contraseña ha sido cambiada con éxito"));
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Monster_University/faces/login.xhtml");
            }

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
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

    public boolean opcionCheck(int opcion) {
        opcionesPerfil = perfilOpcionController.opcionesPerfil(this.usuarioAutenticado);
        for (Opciones auxOpciones : opcionesPerfil) {
            if (auxOpciones.getOpcId() == opcion) {
                return true;
            }
        }
        return false;
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
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/index.xhtml");
        } else {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos inválidos", "Verifique su usuario y contraseña"));
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
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/login.xhtml");
        }
    }

    public void terminarSesion() throws IOException {
        usuario = null;
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Monster_University/");
    }

    public void mostrar() {
        System.out.println("Opciones: " + opcTarget.size());
        for (int i = 0; i < opcTarget.size(); i++) {
            System.out.println("Opcion: " + opcTarget.get(i).getOpcNombre());
        }
    }

    public void enviarConGMail(String destinatario, String asunto, String cuerpo) {
        // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
        String remitente = "mfnavarrete3";  //Para la dirección nomcuenta@gmail.com
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", "Semperfi1");    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(remitente));
            InternetAddress[] addresses = InternetAddress.parse(destinatario);//one or more addresses
            message.addRecipients(Message.RecipientType.TO, addresses);
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, "Semperfi1");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            me.printStackTrace();   //Si se produce un error
        }
    }

    public boolean validaCedula(String x) {
        int suma = 0;
        if (x.length() == 9) {
            System.out.println("Ingrese su cedula de 10 digitos");
            return false;
        } else {
            int a[] = new int[x.length() / 2];
            int b[] = new int[(x.length() / 2)];
            int c = 0;
            int d = 1;
            for (int i = 0; i < x.length() / 2; i++) {
                a[i] = Integer.parseInt(String.valueOf(x.charAt(c)));
                c = c + 2;
                if (i < (x.length() / 2) - 1) {
                    b[i] = Integer.parseInt(String.valueOf(x.charAt(d)));
                    d = d + 2;
                }
            }

            for (int i = 0; i < a.length; i++) {
                a[i] = a[i] * 2;
                if (a[i] > 9) {
                    a[i] = a[i] - 9;
                }
                suma = suma + a[i] + b[i];
            }
            int aux = suma / 10;
            int dec = (aux + 1) * 10;
            if ((dec - suma) == Integer.parseInt(String.valueOf(x.charAt(x.length() - 1)))) {
                return true;
            } else if (suma % 10 == 0 && x.charAt(x.length() - 1) == '0') {
                return true;
            } else {
                return false;
            }
        }
    }

    public void seleccionPass() throws IOException {
        if (cambioPass) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Monster_University/faces/newPass.xhtml");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Monster_University/faces/mailPass.xhtml");
        }
    }

}
