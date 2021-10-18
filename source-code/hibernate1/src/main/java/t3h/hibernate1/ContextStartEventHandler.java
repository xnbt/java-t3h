package t3h.hibernate1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

import javax.sql.DataSource;
import java.util.logging.Logger;

public class ContextStartEventHandler implements ApplicationListener<ContextStartedEvent> {
    private final static Logger LOGGER = Logger.getLogger(ContextStartEventHandler.class.getName());

    @Autowired
    private DataSource dataSource;

    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        System.out.println("context start application " + dataSource);
    }
}
