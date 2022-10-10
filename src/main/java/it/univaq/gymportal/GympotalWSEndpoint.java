package it.univaq.gymportal;

import javax.xml.ws.Endpoint;
import javax.xml.ws.handler.Handler;
import java.util.List;

public class GympotalWSEndpoint {


    public static void main(String[] args) {

        // Note that \\ is for escaping a single "\"
        final String SERVICE_JAR_FILE_WIN = "target\\register-office-ws.jar";
        final String SERVICE_JAR_FILE_UNIX_LIKE = "target/register-office-ws.jar";
        final String SERVICE_ENDPOINT_CLASS = "it.univaq.registeroffice.RegisterOfficeWSEndpoint";
        final String SERVICE_URL = "http://localhost:8282/myRegisterOfficeWS";

        if (args.length <= 1) {

            System.out.println("Note that you need to package the service into a jar file as first through, "
                    + "e.g., mvn verify or mvn package" + "\n");

            System.out.println("Usage:       java -cp "
                    + "<targetJarFile> "
                    + "<serviceEndpointClass> "
                    + "<publishURL>" + " "
                    + "[enableSOAPHandler]" + "\n");

            // java -cp target\register-office-ws.jar it.univaq.registeroffice.RegisterOfficeWSEndpoint http://localhost:8282/myRegisterOfficeWS [enableSOAPHandler]
            System.out.println("[WIN]:       java -cp "
                    + SERVICE_JAR_FILE_WIN + " "
                    + SERVICE_ENDPOINT_CLASS + " "
                    + SERVICE_URL + " "
                    + "[enableSOAPHandler]" + "\n");

            // java -cp target/register-office-ws.jar it.univaq.registeroffice.RegisterOfficeWSEndpoint http://localhost:8282/myRegisterOfficeWS [enableSOAPHandler]
            System.out.println("[UNIX-like]: java -cp "
                    + SERVICE_JAR_FILE_UNIX_LIKE + " "
                    + SERVICE_ENDPOINT_CLASS + " "
                    + SERVICE_URL + " "
                    + "[enableSOAPHandler]" + "\n");

            System.exit(-1);
        }

        // Without SOAP handler
//        if (args.length == 2 && args[1].equals("enableSOAPHandler")) {
//            Endpoint ep = Endpoint.create(new RegisterOfficeWSImpl());
//            List<Handler> handlerChain = ep.getBinding().getHandlerChain();
//            handlerChain.add(new SOAPLoggingHandler());
//            ep.getBinding().setHandlerChain(handlerChain);
//            ep.publish(args[0]);
//        } else {
//            Endpoint.publish(args[0], new RegisterOfficeWSImpl());
//        }

        // With handler
        System.out.println("░▒▓▓▓██▓▒▒░░");
        System.out.println("░▒▓▓▓██▓ »»»» Published Endpoint at URL " + args[0]);
        System.out.println("░▒▓▓▓██▓▒▒░░");
        System.out.println("░▒▓▓▓██▓ »»»» WSDL at URL " + args[0] + "?wsdl");
        System.out.println("░▒▓▓▓██▓▒▒░░");
        System.out.println("░▒▓▓▓██▓ »»»» XSD at URL " + args[0] + "?xsd=1");
        System.out.println("░▒▓▓▓██▓▒▒░░\n");
    }


}
