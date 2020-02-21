package com.Visitor;

import com.Heroes.Hero;
import com.Heroes.Knight;
import com.Heroes.Wizard;
import com.Heroes.Pyromancer;
import com.Heroes.Rogue;
import com.Map.LandTypes;

public interface Visitor {
    int visit(Knight k, Hero h, LandTypes landType,
              int baseDamage1, int baseDamage, int oppDamage1, int oppDamage2);
    int visit(Pyromancer p, Hero h, LandTypes landType,
              int baseDamage1, int baseDamage2, int oppDamage1, int oppDamage2);
    int visit(Wizard w, Hero h, LandTypes landType,
                int baseDamage1, int baseDamage2, int oppDamage1, int oppDamage2);
    int visit(Rogue r, Hero h, LandTypes landType,
              int baseDamage1, int baseDamage2, int oppDamage1, int oppDamage2);
}
