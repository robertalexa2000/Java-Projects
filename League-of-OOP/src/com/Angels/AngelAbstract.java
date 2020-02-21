package com.Angels;

import com.Heroes.Hero;
import com.Observer.GreatMagician;
import com.Observer.LegalOperations;
import com.Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class AngelAbstract implements Angel {
    private int row, column;
    private List<Observer> observers;

    public AngelAbstract(final int row, final int column) {
        this.row = row;
        this.column = column;
        observers = new ArrayList<>();

        // attach the observer and notify it that an angel has been spawned
        attach(GreatMagician.getInstance());
        notify(null, LegalOperations.angelSpawned);
    }

    @Override
    public final void notify(final Hero hero, final LegalOperations legalOperation) {
        List<Observer> observersAttached = getObservers();
        for (Observer observer : observersAttached) {
            observer.update(this, hero, legalOperation);
        }
    }

    @Override
    public final void attach(final Observer observer) {
        observers.add(observer);
    }

    @Override
    public final void detach(final Observer observer) {
        observers.remove(observer);
    }

    public final List<Observer> getObservers() {
        return observers;
    }

    public final int getColumn() {
        return column;
    }

    public final int getRow() {
        return row;
    }
}
