package rest.config;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import rest.GameRest;
import rest.HangManRest;
import rest.MemoRest;
import rest.MillionaireRest;
import rest.Rest;
import rest.RozsypankaRest;
import rest.SpellRest;
import rest.WordSearchRest;
import rest.ZwierzRest;

public class Config extends ResourceConfig {

    public Config()
    {
        super(Rest.class, 
              GameRest.class, 
              MemoRest.class, 
              MillionaireRest.class, 
              RozsypankaRest.class, 
              WordSearchRest.class, 
              HangManRest.class, 
              SpellRest.class, 
              ZwierzRest.class);

        register(RolesAllowedDynamicFeature.class);
        register(MultiPartFeature.class);
    }

}
