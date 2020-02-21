package com.Heroes;

import com.Map.LandTypes;
import com.Visitor.Visitor;

public interface HeroElement {
    int accept(Visitor v, Hero h, LandTypes landType,
               int baseDamage1, int baseDamage2, int oppDamage1, int oppDamage2);
    void move(int x, int y);
    float pyromancerFireblastRaceModifier();
    float pyromancerIgniteRaceModifier();
    float knightExecuteRaceModifier();
    float knightSlamRaceModifier();
    float wizardDrainRaceModifier();
    float wizardDeflectRaceModifier();
    float rogueBackstabRaceModifier();
    float rogueParalysisRaceModifier();
}
