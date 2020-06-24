package br.mack.ps2;
import br.mack.ps2.dao.EconomiaDAOMySQL;
import br.mack.ps2.resource.InterfaceUsuarioEconomia;
import br.mack.ps2.resource.TrendsResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App extends Application<Configuration> {
    public static void main( String[] args ) {
        try {
            (new App()).run(args);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "hello world";
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        AssetsBundle assetsBundle = new AssetsBundle("/site","/",   "index.html");
        bootstrap.addBundle(assetsBundle);
        super.initialize(bootstrap);
    }


    @Override
    public void run(Configuration configuration, Environment environment){


        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);


        cors.setInitParameter("allowedOrigins","*");
        cors.setInitParameter("allowedHeaders","X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods","OPTIONS,GET,PUT,POST,DELETE,HEAD");

        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class),true,"");