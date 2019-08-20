package controlador;


import entidades.Usuario;
import java.io.UnsupportedEncodingException;
//import java.util.HashSet;
import java.util.Properties;
//import java.util.Set;
//import javax.activation.DataHandler;
//import javax.activation.FileDataSource;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mailer {
//String para, String sujeto, String mensaje, String password, String correo
    public static String send(Usuario usua2) throws UnsupportedEncodingException {

        final String user = "proyectoomcomc@gmail.com"
                + "";//cambiará en consecuencia al servidor utilizado
        final String pass = "Gaes3Proyectoomc";

        String nuevoMensaje = "<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">Correo de Prueba" + "</h1>" + "<img src='https://lh3.googleusercontent.com/oEdehpVCY1RbeNe0S0_Mlc-9E4WmyuD7C5xdsrdHzDlpvEFnoTZQBqqSesPb1gjcz0G8dom01bA28OnL5cK6=w1366-h657'/ style=\"float: left;\"><p>" + usua2.getContrasenia() + "<br>\n"
                + "<p style=\"text-align: center; color: #307EDF\">\n"
                + "</p> \n"
                + "<br>\n"
                + "<p style=\"color:#0C0;font-weight: bold;\" > Gracias por formar parte de nuestra comunidad. </p> ";
//1st paso) Obtener el objeto de sesión

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
//    Archivos adjuntos
            BodyPart texto = new MimeBodyPart();
            texto.setContent(nuevoMensaje, "text/html");
            
//          BodyPart adjunto = new MimeBodyPart();
//          adjunto.setDataHandler(new DataHandler(new FileDataSource("d:/cartagena.txt")));
//          adjunto.setFileName("cartagena.txt");

            MimeMultipart multiparte = new MimeMultipart();
            multiparte.addBodyPart(texto);
//            multiparte.addBodyPart(adjunto);

            MimeMessage message = new MimeMessage(session);
            
            message.setFrom(new InternetAddress(user, "Proyecto OMC"));
            
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress(usua2.getCorreo())); //RECEPTOR
            
            message.setSubject("Recupeacion de contraseña"); //ASUNTO
            
            message.setContent(multiparte, "text/html; charset=utf-8"); //CONTENIDO

// 3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "SE HA ENVIADO EL CORREO";
    }
}
