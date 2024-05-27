package domain.alertas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class AlertaAdapterFactory {

    private static AlertaAdapterFactory INSTANCE;
    private IAlertaAdapter adapter;

    private AlertaAdapterFactory() {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(new File("configuração.properties")));

            String output = "domain.alertas" + properties.getProperty("alertaAdapter");

            try {
                Class<IAlertaAdapter> klass = (Class<IAlertaAdapter>) Class.forName(output);
                Constructor<IAlertaAdapter> cons = klass.getConstructor();
                this.adapter = cons.newInstance();
            } catch (Exception e) {
                System.out.println("Error");
                adapter = new SysOutAlertaAdapter();
            }

        } catch (IOException e) {
            adapter = new SysOutAlertaAdapter();
        }
    }

    public static AlertaAdapterFactory getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new AlertaAdapterFactory();
        }
        return INSTANCE;
    }

    public IAlertaAdapter getAdapter() {
        return adapter;
    }
}
