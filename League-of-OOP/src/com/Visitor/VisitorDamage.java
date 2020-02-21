package com.Visitor;

import com.Heroes.Hero;
import com.Heroes.Knight;
import com.Heroes.Wizard;
import com.Heroes.Pyromancer;
import com.Heroes.Rogue;
import com.Map.LandTypes;

public class VisitorDamage implements Visitor {

    @Override
    public final int visit(final Knight k, final Hero h, final LandTypes landType,
                           final int baseDamage1, final int baseDamage2,
                           final int oppDamage1, final int oppDamage2) {
        if (h.getHp() == 0) {
            return 0;
        }

        int damage1 = k.execute(h, baseDamage1);
        int damage2 = k.slam(h, baseDamage2);
        h.setHp(h.getHp() - damage1 - damage2);

        return 0;
    }

    @Override
    public final int visit(final Pyromancer p, final Hero h, final LandTypes landType,
                           final int baseDamage1, final int baseDamage2,
                           final int oppDamage1, final int oppDamage2) {
        if (h.getHp() == 0) {
            return 0;
        }

        int damage1 = p.fireblast(h, baseDamage1);
        int damage2 = p.ignite(h, landType, baseDamage2);
        h.setHp(h.getHp() - damage1 - damage2);

        return 0;
    }

    @Override
    public final int visit(final Rogue r, final Hero h, final LandTypes landType,
                           final int baseDamage1, final int baseDamage2,
                           final int oppDamage1, final int oppDamage2) {
        if (h.getHp() == 0) {
            return 0;
        }

        int damage1 = r.backstab(h, baseDamage1);
        int damage2 = r.paralysis(h, landType, baseDamage2);
        h.setHp(h.getHp() - damage1 - damage2);

        return 0;
    }

    @Override
    public final int visit(final Wizard w, final Hero h, final LandTypes landType,
                             final int baseDamage1, final int baseDamage2,
                             final int oppDamage1, final int oppDamage2) {
        if (h.getHp() == 0) {
            return 0;
        }

        int damage1 = w.drain(h, landType);
        int damage2 = w.deflect(h, landType, oppDamage1 + oppDamage2);
        h.setHp(h.getHp() - damage1 - damage2);

        return 0;
    }
}
