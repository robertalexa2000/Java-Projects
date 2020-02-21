package com.Observer;

import com.Heroes.Hero;

public interface Subject {
    // the pattern necessary for implementing an Observer design pattern
    void attach(Observer observer);
    void detach(Observer observer);
    void notify(Hero hero, LegalOperations legalOperation);
}
