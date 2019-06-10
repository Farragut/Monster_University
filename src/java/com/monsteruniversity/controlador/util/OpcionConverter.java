/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsteruniversity.controlador.util;

import com.monsteruniversity.modelo.Opciones;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Reaper
 */
@FacesConverter("opcionConverter")
public class OpcionConverter implements Converter {

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        Integer id = (value instanceof Opciones) ? ((Opciones) value).getOpcId()
                : null;
        return (id != null) ? String.valueOf(id) : null;
    }

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
        Opciones opcion = new Opciones();
        opcion.setOpcId(Integer.parseInt(value));
        return opcion;
    }
}
