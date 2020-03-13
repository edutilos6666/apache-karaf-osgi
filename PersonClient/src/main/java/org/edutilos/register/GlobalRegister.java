package org.edutilos.register;

import org.edutilos.service.definition.PersonService;

/**
 * Created by Nijat Aghayev on 13.03.20.
 */
public class GlobalRegister {
    private static PersonService personService;
    public static void registerPersonService(PersonService personService) {
        if(GlobalRegister.personService == null)
            GlobalRegister.personService = personService;
    }

    public static PersonService getPersonService() {
        return personService;
    }
}
