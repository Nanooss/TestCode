package com.mygdx.game.controller;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.ModelData.EnemyData;
import com.mygdx.game.model.Actor;
import com.mygdx.game.model.EnemyActor;
import com.mygdx.game.model.PlayableActor;
import com.mygdx.game.model.Weapon;

public class CollisionDetection implements ContactListener {
    private PlayableActor player;
    Weapon weapon;
    EnemyActor enemy;
    int hit_counter = -1;
    public CollisionDetection(PlayableActor user, Weapon weapon, EnemyActor enemy){
        this.player = user;
        this.weapon=weapon;
        this.enemy = enemy;
    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
            if (contact.getFixtureA().getBody().getUserData() == enemy) {
                System.out.println("Hit");
                weapon.hit();
                enemy.getData().die();
            }
            hit_counter += 1;
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
