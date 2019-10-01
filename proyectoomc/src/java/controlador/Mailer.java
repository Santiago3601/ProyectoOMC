package controlador;

import entidades.Alquiler;
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

        String nuevoMensaje = " <style>\n"
                + "@import url('https://fonts.googleapis.com/css?family=Questrial&display=swap');\n"
                + "</style>  <h1 style=\"font-size: 20px; font-family: 'Questrial', sans-serif; color:#307C66;  \">Nueva Contraseña" + "</h1>" + "<p> Ingresas con: " + usua2.getCorreo() + "<br></br>" + " Tu contraseña es:" + usua2.getContrasenia() + "<br></br>" + "<br></br>" + "<br></br>" + "<img src='i.imgur.com/LU6DAe3.png'/ style=\"float: left; height: 50px; widght : auto;\">\n"
                + "<p style=\"text-align: center; color: #307C66\">\n"
                + "</p> \n"
                + "<br>\n"
                + "<p style=\"color:#0C0;font-weight: bold;\" > </p> ";
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

    public static String mantenimiento(Usuario usua2) throws UnsupportedEncodingException {

        final String user = "proyectoomcomc@gmail.com"
                + "";//cambiará en consecuencia al servidor utilizado
        final String pass = "Gaes3Proyectoomc";

        String nuevoMensaje = "<h1 style=\"font-size: 20px; color:#307C66; font-weight: bold; text-transform: uppercase ; \">Mantenimiento" + "</h1>" + "<img src='i.imgur.com/LU6DAe3.png'/ style=\"float: left; height: 50px; widght : auto;\"><p>El sitema estara en mantenimiento<br>\n"
                + "<p style=\"text-align: center; color: #307C66\">\n"
                + "</p> \n"
                + "<br>\n"
                + "<p style=\"color:#307C66;font-weight: bold;\" ></p> ";
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

            message.setSubject("AVISO IMPORTANTE"); //ASUNTO

            message.setContent(multiparte, "text/html; charset=utf-8"); //CONTENIDO

// 3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "SE HA ENVIADO EL CORREO";
    }

    public static String sendConfirmation(Usuario usua2) throws UnsupportedEncodingException {

        final String user = "proyectoomcomc@gmail.com"
                + "";//cambiará en consecuencia al servidor utilizado
        final String pass = "Gaes3Proyectoomc";

        String nuevoMensaje = " <style>\n"
                + "@import url('https://fonts.googleapis.com/css?family=Questrial&display=swap');\n"
                + "</style>  <h1 style=\"font-size: 20px; font-family: 'Questrial', sans-serif; color:#307C66;  \">Hola, " + usua2.getNombre() + "<br></br>" + " acabaste de registrarte en el sistema de Organización  y Mantenimiento Cryogas (OMC) <br></br>" + "<br></br>" + "<img src='i.imgur.com/LU6DAe3.png'/ style=\"float: left; height: 50px; widght : auto;\">\n"
                + "<p style=\"text-align: center; color: #307C66\">\n"
                + "</p> \n"
                + "<br>\n";
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

            message.setSubject("Registro dentro del sistema"); //ASUNTO

            message.setContent(multiparte, "text/html; charset=utf-8"); //CONTENIDO

// 3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "SE HA ENVIADO EL CORREO";
    
    }
    


    public static String alquilerProgramado(Alquiler alq) throws UnsupportedEncodingException {

        final String user = "proyectoomcomc@gmail.com"
                + "";//cambiará en consecuencia al servidor utilizado
        final String pass = "Gaes3Proyectoomc";

        String nuevoMensaje = " <style>\n"
                + "@import url('https://fonts.googleapis.com/css?family=Questrial&display=swap');\n"
                + "</style>  <h1 style=\"font-size: 20px; font-family: 'Questrial', sans-serif; color:#307C66;  \">Hola, " + alq.getSolicitudIdSolicitud().getClienteIdCliente().getUsuarioId().getNombre() + "<br></br>" + " El siguiente correo se envía con fin de notificarle que el dia " + alq.getFechaDeEntrega().getDate() +"/"+ alq.getFechaDeEntrega().getMonth() + "/" + alq.getFechaDeEntrega().getYear() + " se le hara entrega del cilindro de oxigeno que usted solicito <br></br>" + "<br></br>" + "<img src='i.imgur.com/LU6DAe3.png'/ style=\"float: left; height: 50px; widght : auto;\">\n"
                + "<p style=\"text-align: center; color: #307C66\">\n"
                + "</p> \n"
                + "<br>\n";
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

            message.addRecipient(Message.RecipientType.BCC, new InternetAddress(alq.getSolicitudIdSolicitud().getClienteIdCliente().getUsuarioId().getCorreo())); //RECEPTOR

            message.setSubject("Envio de cilindro"); //ASUNTO

            message.setContent(multiparte, "text/html; charset=utf-8"); //CONTENIDO

// 3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "SE HA ENVIADO EL CORREO";
    }
    
    
    public static String mantenimiento2(Usuario usua2) throws UnsupportedEncodingException {

        final String user = "proyectoomcomc@gmail.com"
                + "";//cambiará en consecuencia al servidor utilizado
        final String pass = "Gaes3Proyectoomc";

        String nuevoMensaje = "<h1 style=\"font-size: 20px; color:#307C66; font-weight: bold; text-transform: uppercase ; \">Mantenimiento" + "</h1>" + "<img src='i.imgur.com/LU6DAe3.png'/ style=\"float: left; height: 50px; widght : auto;\"><p>Gracias por la espera el sistema ya esta disponible de nuevo<br>\n"
                + "<p style=\"text-align: center; color: #307C66\">\n"
                + "</p> \n"
                + "<br>\n"
                + "<p style=\"color:#0C0;font-weight: bold;\" ></p> ";
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

            message.setSubject("AVISO IMPORTANTE"); //ASUNTO

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


