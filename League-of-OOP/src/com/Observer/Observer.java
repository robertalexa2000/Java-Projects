package com.Observer;

import com.Angels.Angel;
import com.Heroes.Hero;

public interface Observer {
    // the method handles all notifications received from an angel which sends itself as a parameter
    void update(Angel angel, Hero hero, LegalOperations legalOperation);

    // the method handles all notifications received from hero operations.
    // the first hero performs an action on the second (kills him) and sends the notification
    void update(Hero hero1, Hero hero2, LegalOperations legalOperation);
}
