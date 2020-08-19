package com.baxixiaomi.studynotes.test.chapter9.MultipleInheritance;

public class Adventure {
    public static void t(CanSwim swim) {
        swim.swim();
    }

    public static void u(CanFly fly) {
        fly.fly();
    }

    public static void v(CanFight fight) {
        fight.fight();
    }

    public static void w(ActionCharacter actionCharacter) {
        actionCharacter.fight();
    }

    public static void main(String[] args) {
        Hero hero = new Hero();
        t(hero);
        u(hero);
        v(hero);
        w(hero);
    }
}
