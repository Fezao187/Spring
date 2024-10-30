package net.javaguidelines.spring_annotations.configProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppPropertiesDemo {
    @Autowired
    private AppProperties appProperties;

    public void display() {
        System.out.println(
                appProperties.getName()+"\n"+
                appProperties.getDescription()+"\n"+
                appProperties.getUploadDir()+"\n"+
                appProperties.getSecurity().getUsername()+"\n"+
                appProperties.getSecurity().getPassword()+"\n"+
                appProperties.getSecurity().getRoles()+"\n"+
                appProperties.getSecurity().isEnabled()+"\n"+
                appProperties.getSecurity().getPermissions());
    }
}
