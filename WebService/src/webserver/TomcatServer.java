package webserver;

import java.io.File;
import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class TomcatServer {

    public static final int TOMCAT_PORT = 8085;
    public static final String URL = "http://localhost:8085/webApp/rest";
    public static final String HELLO_URL = URL + "/validate";

    public TomcatServer() {
    }

    public void start() throws ServletException, LifecycleException {
        ResourceConfig config = new ResourceConfig();
        config.packages("validate", "webserver");
        config.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL, "INFO");

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(TOMCAT_PORT);

        Context context = tomcat.addWebapp("/webApp", new File("./WebContent").getAbsolutePath());
        Tomcat.addServlet(context, "jersey", new ServletContainer(config));
        context.addServletMappingDecoded("/rest/*", "jersey");
        tomcat.start();
        tomcat.getServer().await();
    }

    public static void main(String[] args) throws ServletException, LifecycleException {
        new TomcatServer().start();
    }
}
