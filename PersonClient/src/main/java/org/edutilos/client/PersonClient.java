package org.edutilos.client;

/**
 * Created by Nijat Aghayev on 13.03.20.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.edutilos.model.Person;
import org.edutilos.register.GlobalRegister;
import org.edutilos.service.definition.PersonService;
import org.osgi.framework.*;

import java.util.Arrays;
import java.util.List;

public class PersonClient extends Application implements BundleActivator, ServiceListener {

    private BundleContext ctx;
    private ServiceReference serviceReference;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(MainSceneProvider.getMainScene());
        primaryStage.show();
    }

    public void start(BundleContext ctx) {
        this.ctx = ctx;
        try {
            ctx.addServiceListener(this, "(objectclass=" + PersonService.class.getName() + ")");
        } catch (InvalidSyntaxException ise) {
            ise.printStackTrace();
        }
    }

    public void stop(BundleContext bundleContext) {
        if (serviceReference != null) {
            ctx.ungetService(serviceReference);
        }
        this.ctx = null;
        Platform.exit();
    }

    public void serviceChanged(ServiceEvent serviceEvent) {
        int type = serviceEvent.getType();
        switch (type) {
            case (ServiceEvent.REGISTERED):
                System.out.println("Notification of service registered.");
                serviceReference = serviceEvent.getServiceReference();
                PersonService service = (PersonService) (ctx.getService(serviceReference));
                GlobalRegister.registerPersonService(service);
                launch();
                break;
            case (ServiceEvent.UNREGISTERING):
                System.out.println("Notification of service unregistered.");
                ctx.ungetService(serviceEvent.getServiceReference());
                // For testing purposes
                GlobalRegister.getPersonService().disconnect();
                break;
            default:
                break;
        }
    }

    private void loadSampleData() {
        List<Person> personList = Arrays.asList(
                new Person("1", "foo", "bar", 10, 100.0, true),
                new Person("2", "foo 2", "bar 2", 20, 200.0, false),
                new Person("3", "foo 3", "bar 3", 30, 300.0, true)
        );

        personList.forEach(one-> {
            GlobalRegister.getPersonService().create(one);
        });

    }
}
