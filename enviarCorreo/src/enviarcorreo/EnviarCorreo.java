/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enviarcorreo;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


/**
 *
 * @author Sendoa
 */
public class EnviarCorreo {
    
    public static void main(String[] args) {
        
        String emisor = "sendoa82@gmail.com";   //Desde donde se va a mandar el mensaje
        String contra = "yrqoriipcrjxspub";     //Contraseña de aplicacion que nos da gmail
        String receptor = "sendoa.aldama@gmail.com";    //Receptor del mensaje
        
        // Configuración de las propiedades del servidor SMTP de Gmail
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session;
        session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emisor, contra);
            }
        });

        try {
            // Creación del mensaje de correo electrónico
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emisor));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
            message.setSubject("Rol de Agricultura de precision");
            message.setText("Hola ***, le informamos mediante este correo de que se a registrado correctamente con el correo *** y que dentro de un tiempo el administrador se pondra en contacto con usted para que le indiques que rol quieres que te asigne y el te inidicara las tarifas y se lo asignara. "
                    + "\nPara que se haga una pequeña idea de las tarifas serian estas: "
                    + "\n- Piloto: 10€ mes. "
                    + "\n- Agricultor: 10€ mes. "
                    + "\nUn cordial abrazo, Agricultura de Precision.");

            // Envío del mensaje
            Transport.send(message);

            System.out.println("Correo electrónico enviado satisfactoriamente.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        
    }

}
