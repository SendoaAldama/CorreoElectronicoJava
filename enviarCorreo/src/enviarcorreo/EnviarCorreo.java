package enviarcorreo;

//Imports necesarios
import java.util.*;

    //Estos imports son de la libreria javax.mail.jar que hay que importar si no la tienes
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Sendoa
 * 
 */

public class EnviarCorreo {
    
    public static void main(String[] args) {    //No nos va a devolver ningun valor porque es void
        
        String emisor = "";   //Desde donde se va a mandar el mensaje
        String contra = "";     //Contraseña de aplicacion que nos da gmail en nuestra cuenta de correo
        String receptor = "";    //Receptor del mensaje
        
        // Configuración de las propiedades del servidor SMTP de Gmail
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session;
        session = Session.getDefaultInstance(props, new javax.mail.Authenticator() //Auntenticacion el correo del emisor
        {

            @Override

            protected PasswordAuthentication getPasswordAuthentication() //Metodo de comprobacion
            {

                return new PasswordAuthentication(emisor, contra);  //Nos indica si el emisor y su contraseña esta correcto
            
            }

        });

        //Entramos en el try catch del mensaje del correo electronico
        try {

            // Creación del mensaje de correo electrónico
            MimeMessage message = new MimeMessage(session); //Introduciomos la varieble de sesion donde indicamos si esta correcto el correo
            message.setFrom(new InternetAddress(emisor));   //Indicamos el emisor de correo
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));  //Inidicamos el receptor del correo
            message.setSubject("Rol de Agricultura de precision");  //Creacion del Asunto del correo
            message.setText("Hola ***, le informamos mediante este correo de que se a registrado correctamente con el correo *** y que dentro de un tiempo el administrador se pondra en contacto con usted para que le indiques que rol quieres que te asigne y el te inidicara las tarifas y se lo asignara. "
                    + "\nPara que se haga una pequeña idea de las tarifas serian estas: "
                    + "\n- Piloto: 10€ mes. "
                    + "\n- Agricultor: 10€ mes. "
                    + "\nUn cordial abrazo, Agricultura de Precision."); //Creacion del cuerpo del correo

            // Envío del mensaje
            Transport.send(message);    //

            System.out.println("Correo electrónico enviado satisfactoriamente.");

        } 
        catch (MessagingException e)   //Si hay algun error en el mensaje nos entrara aqui
        {

            throw new RuntimeException(e);
        
        }
        
    }

}
