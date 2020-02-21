package com.Angels;

import com.Heroes.Knight;
import com.Heroes.Pyromancer;
import com.Heroes.Rogue;
import com.Heroes.Wizard;
import com.Observer.Subject;

public interface Angel extends Subject {
    // methods designed for applying bonuses and hits
    void help(Knight knight);
    void help(Pyromancer pyromancer);
    void help(Rogue rogue);
    void help(Wizard wizard);

    // necessary and sufficient methods for implementing the angels
    // they provide information for the GreatMagician
    String toString();
    int getRow();
    int getColumn();
}
