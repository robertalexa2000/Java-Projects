package com.Visitor;

import com.Heroes.Hero;
import com.Heroes.Knight;
import com.Heroes.Wizard;
import com.Heroes.Pyromancer;
import com.Heroes.Rogue;
import com.Map.LandTypes;

public class VisitorSecondAbility implements Visitor {
    @Override
    public final int visit(final Knight k, final Hero h, final LandTypes landType,
                           final int baseDamage1, final int baseDamage2,
                           final int oppDamage1, final int oppDamage2) {
        return k.slamBaseDamage(landType);
    }

    @Override
    public final int visit(final Pyromancer p, final Hero h, final LandTypes landType,
                           final int baseDamage1, final int baseDamage2,
                           final int oppDamage1, final int oppDamage2) {
        return p.igniteBaseDamage(landType);
    }

    @Override
    public final int visit(final Rogue r, final Hero h, final LandTypes landType,
                           final int baseDamage1, final int baseDamage2,
                           final int oppDamage1, final int oppDamage2) {
        return r.paralysisBaseDamage(landType);
    }

    @Override
    public final int visit(final Wizard w, final Hero h, final LandTypes landType,
                           final int baseDamage1, final int baseDamage2,
                           final int oppDamage1, final int oppDamage2) {
        return 0;
    }
}
