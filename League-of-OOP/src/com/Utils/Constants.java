package com.Utils;

public final class Constants {
    private Constants() {
    }

    public static final int XP_CONSTANT = 200;
    public static final int XP_MULTIPLIER = 40;
    public static final int PERCENTAGE = 100;
    public static final int XP_LEVEL_UP = 250;
    public static final int XP_LEVEL_UP_MULTIPLIER = 50;

    public static class Heroes {
        public static class Knight {
            public static final float HP_FIRST_STRATEGY = -0.2f;
            public static final float BONUS_FIRST_STRATEGY = 0.5f;
            public static final float HP_SECOND_STRATEGY = 0.25f;
            public static final float BONUS_SECOND_STRATEGY = -0.2f;
            public static final float STRATEGY_LOWER_LIMIT = 1 / 3f;
            public static final float STRATEGY_HIGHER_LIMIT = 0.5f;
            public static final int KNIGHT_HP = 900;
            public static final int KNIGHT_HP_PER_LEVEL = 80;
            public static final int KNIGHT_EXECUTE_BASE_DAMAGE = 200;
            public static final int KNIGHT_EXECUTE_DAMAGE_PER_LEVEL = 30;
            public static final float KNIGHT_EXECUTE_HP_LIMIT = 0.2f;
            public static final int KNIGHT_EXECUTE_MAX_LEVEL = 40;
            public static final float KNIGHT_EXECUTE_MAX_LEVEL_VALUE = 0.4f;
            public static final float KNIGHT_EXECUTE_ROGUE_MODIFIER = 0.15f;
            public static final float KNIGHT_EXECUTE_KNIGHT_MODIFIER = 0f;
            public static final float KNIGHT_EXECUTE_PYROMANCER_MODIFIER = 0.1f;
            public static final float KNIGHT_EXECUTE_WIZARD_MODIFIER = -0.2f;
            public static final int KNIGHT_SLAM_BASE_DAMAGE = 100;
            public static final int KNIGHT_SLAM_PER_LEVEL = 40;
            public static final float KNIGHT_SLAM_ROGUE_MODIFIER = -0.2f;
            public static final float KNIGHT_SLAM_KNIGHT_MODIFIER = 0.2f;
            public static final float KNIGHT_SLAM_PYROMANCER_MODIFIER = -0.1f;
            public static final float KNIGHT_SLAM_WIZARD_MODIFIER = 0.05f;
            public static final float KNIGHT_LAND_MODIFIER = 0.15f;
            public static final int KNIGHT_OVERTIME_ROUNDS = 1;
        }

        public static class Pyromancer {
            public static final float HP_FIRST_STRATEGY = -0.25f;
            public static final float BONUS_FIRST_STRATEGY = 0.7f;
            public static final float HP_SECOND_STRATEGY = 1 / 3f;
            public static final float BONUS_SECOND_STRATEGY = -0.3f;
            public static final float STRATEGY_LOWER_LIMIT = 0.25f;
            public static final float STRATEGY_HIGHER_LIMIT = 1 / 3f;
            public static final int HP = 500;
            public static final int PYROMANCER_HP_PER_LEVEL = 50;
            public static final int PYROMANCER_FIREBLAST_BASE_DAMAGE = 350;
            public static final int PYROMANCER_FIREBLAST_DAMAGE_PER_LEVEL = 50;
            public static final float PYROMANCER_FIREBLAST_ROGUE_MODIFIER = -0.2f;
            public static final float PYROMANCER_FIREBLAST_KNIGHT_MODIFIER = 0.2f;
            public static final float PYROMANCER_FIREBLAST_PYROMANCER_MODIFIER = -0.1f;
            public static final float PYROMANCER_FIREBLAST_WIZARD_MODIFIER = 0.05f;
            public static final int PYROMANCER_IGNITE_BASE_DAMAGE = 150;
            public static final int PYROMANCER_IGNITE_DAMAGE_PER_LEVEL = 20;
            public static final float PYROMANCER_IGNITE_ROGUE_MODIFIER = -0.2f;
            public static final float PYROMANCER_IGNITE_KNIGHT_MODIFIER = 0.2f;
            public static final float PYROMANCER_IGNITE_PYROMANCER_MODIFIER = -0.1f;
            public static final float PYROMANCER_IGNITE_WIZARD_MODIFIER = 0.05f;
            public static final float PYROMANCER_LAND_MODIFIER = 0.25f;
            public static final int PYROMANCER_OVERTIME_ROUNDS = 2;
            public static final int PYROMANCER_OVERTIME_DAMAGE = 50;
            public static final int PYROMANCER_OVERTIME_DAMAGE_PER_LEVEL = 30;
        }

        public static class Rogue {
            public static final float HP_FIRST_STRATEGY = -1 / 7f;
            public static final float BONUS_FIRST_STRATEGY = 0.4f;
            public static final float HP_SECOND_STRATEGY = 0.5f;
            public static final float BONUS_SECOND_STRATEGY = -0.1f;
            public static final float STRATEGY_LOWER_LIMIT = 1 / 7f;
            public static final float STRATEGY_HIGHER_LIMIT = 1 / 5f;
            public static final int ROGUE_HP = 600;
            public static final int ROGUE_HP_PER_LEVEL = 40;
            public static final int ROGUE_BACKSTAB_BASE_DAMAGE = 200;
            public static final int ROGUE_BACKSTAB_DAMAGE_PER_LEVEL = 20;
            public static final int ROGUE_BACKSTAB_CRITICAL_ROUND = 3;
            public static final float ROGUE_BACKSTAB_CRITICAL_MULTIPLIER = 1.5f;
            public static final float ROGUE_LAND_MODIFIER = 0.15f;
            public static final float ROGUE_BACKSTAB_ROGUE_MODIFIER = 0.2f;
            public static final float ROGUE_BACKSTAB_KNIGHT_MODIFIER = -0.1f;
            public static final float ROGUE_BACKSTAB_PYROMANCER_MODIFIER = 0.25f;
            public static final float ROGUE_BACKSTAB_WIZARD_MODIFIER = 0.25f;
            public static final float ROGUE_PARALYSIS_ROGUE_MODIFIER = -0.1f;
            public static final float ROGUE_PARALYSIS_KNIGHT_MODIFIER = -0.2f;
            public static final float ROGUE_PARALYSIS_PYROMANCER_MODIFIER = 0.2f;
            public static final float ROGUE_PARALYSIS_WIZARD_MODIFIER = 0.25f;
            public static final int ROGUE_PARALYSIS_BASE_DAMAGE = 40;
            public static final int ROGUE_PARALYSIS_DAMAGE_PER_LEVEL = 10;
            public static final int ROGUE_PARALYSIS_BASIC_OVERTIME = 3;
            public static final int ROGUE_PARALYSIS_ENHANCED_OVERTIME = 6;
            public static final float ANGEL_BONUS_MODIFER = 0.00001f;
        }

        public static class Wizard {
            public static final float HP_FIRST_STRATEGY = -0.1f;
            public static final float BONUS_FIRST_STRATEGY = 0.6f;
            public static final float HP_SECOND_STRATEGY = 1f / 5f;
            public static final float BONUS_SECOND_STRATEGY = -0.2f;
            public static final float STRATEGY_LOWER_LIMIT = 0.25f;
            public static final float STRATEGY_HIGHER_LIMIT = 0.5f;
            public static final int WIZARD_HP = 400;
            public static final int WIZARD_HP_PER_LEVEL = 30;
            public static final float WIZARD_DRAIN_BASE_PERCENTAGE = 0.2f;
            public static final float WIZARD_DRAIN_PERCENTAGE_PER_LEVEL = 0.05f;
            public static final float WIZARD_DRAIN_BASE_HP_CONSTANT = 0.3f;
            public static final float WIZARD_DRAIN_ROGUE_MODIFIER = -0.2f;
            public static final float WIZARD_DRAIN_KNIGHT_MODIFIER = 0.2f;
            public static final float WIZARD_DRAIN_PYROMANCER_MODIFIER = -0.1f;
            public static final float WIZARD_DRAIN_WIZARD_MODIFIER = 0.05f;
            public static final float WIZARD_DEFLECT_BASE_PERCENTAGE = 0.35f;
            public static final float WIZARD_DEFLECT_LEVEL_PERCENTAGE = 0.02f;
            public static final float WIZARD_DEFLECT_MAX_PERCENTAGE = 0.7f;
            public static final float WIZARD_DEFLECT_ROGUE_MODIFIER = 0.2f;
            public static final float WIZARD_DEFLECT_KNIGHT_MODIFIER = 0.4f;
            public static final float WIZARD_DEFLECT_PYROMANCER_MODIFIER = 0.3f;
            public static final float WIZARD_LAND_MODIFIER = 0.1f;
        }
    }

    public static class Angels {
        public static class DamageAngel {
            public static final float KNIGHT_BONUS = 0.15f;
            public static final float PYROMANCER_BONUS = 0.2f;
            public static final float ROGUE_BONUS = 0.3f;
            public static final float WIZARD_BONUS = 0.4f;
        }

        public static class Dracula {
            public static final float KNIGHT_BONUS = -0.2f;
            public static final int KNIGHT_HP = -60;
            public static final float PYROMANCER_BONUS = -0.3f;
            public static final int PYROMANCER_HP = -40;
            public static final float ROGUE_BONUS = -0.1f;
            public static final int ROGUE_HP = -35;
            public static final float WIZARD_BONUS = -0.4f;
            public static final int WIZARD_HP = -20;
        }

        public static class XPAngel {
            public static final int KNIGHT = 45;
            public static final int PYROMANCER = 50;
            public static final int ROGUE = 40;
            public static final int WIZARD = 60;
        }

        public static class SmallAngel {
            public static final float KNIGHT_BONUS = 0.1f;
            public static final int KNIGHT_HP = 10;
            public static final float PYROMANCER_BONUS = 0.15f;
            public static final int PYROMANCER_HP = 15;
            public static final float ROGUE_BONUS = 0.05f;
            public static final int ROGUE_HP = 20;
            public static final float WIZARD_BONUS = 0.1f;
            public static final int WIZARD_HP = 25;
        }

        public static class GoodBoy {
            public static final float KNIGHT_BONUS = 0.4f;
            public static final int KNIGHT_HP = 20;
            public static final float PYROMANCER_BONUS = 0.5f;
            public static final int PYROMANCER_HP = 30;
            public static final float ROGUE_BONUS = 0.4f;
            public static final int ROGUE_HP = 40;
            public static final float WIZARD_BONUS = 0.3f;
            public static final int WIZARD_HP = 50;
        }

        public static class LifeGiver {
            public static final int KNIGHT = 100;
            public static final int PYROMANCER = 80;
            public static final int ROGUE = 90;
            public static final int WIZARD = 120;
        }

        public static class LevelUpAngel {
            public static final float KNIGHT = 0.1f;
            public static final float PYROMANCER = 0.2f;
            public static final float ROUGE = 0.15f;
            public static final float WIZARD = 0.25f;
        }

        public static class DarkAngel {
            public static final int KNIGHT = -40;
            public static final int PYROMANCER = -30;
            public static final int ROGUE = -10;
            public static final int WIZARD = -20;
        }

        public static class Spawner {
            public static final int KNIGHT = 200;
            public static final int PYROMANCER = 150;
            public static final int ROGUE = 180;
            public static final int WIZARD = 120;
        }
    }
}
