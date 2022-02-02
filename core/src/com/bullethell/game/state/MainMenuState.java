package com.bullethell.game.state;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.bullethell.game.BulletHellMain;
import com.bullethell.game.ecs.Engine;
import com.bullethell.game.ecs.GameObject;
import com.bullethell.game.ecs.Systems.BehaviourSystem;
import com.bullethell.game.ecs.Systems.PhysicsSystem;
import com.bullethell.game.ecs.Systems.RenderingSystem;
import com.bullethell.game.ecs.Tag;
import com.bullethell.game.ecs.components.Behaviour;
import com.bullethell.game.ecs.components.BoxCollider;
import com.bullethell.game.ecs.components.Collider;
import com.bullethell.game.ecs.components.Health;
import com.bullethell.game.gameobjects.GameObjectContainer;
import com.bullethell.game.gameobjects.PlayerBehaviour;
import com.bullethell.game.ecs.components.SpriteRenderer;
import com.bullethell.game.ecs.components.Transform;

public class MainMenuState extends State{

    public MainMenuState(GameStateHandler handler) {
        
        super(handler);
    }
    private Engine engine;

    @Override
    public void update() {
       engine.update();
    }

    @Override
    public void onCreate() {
        engine = new Engine();
        engine.addSystemToEngine(new RenderingSystem(Tag.create(SpriteRenderer.class, Transform.class)));
        engine.addSystemToEngine(new BehaviourSystem(Tag.create(Behaviour.class)));
        engine.addSystemToEngine(new PhysicsSystem(1/60.0f, Tag.create(Collider.class)));
        
        GameObject obj = new GameObject();
        obj.addComponent(PlayerBehaviour.class);
        obj.getTransform().setPosition(new Vector3(100, 50, 0));
        obj.addComponent(SpriteRenderer.class);
        obj.setName("player");
        BoxCollider collider = (BoxCollider) obj.addComponent(BoxCollider.class);

        GameObject enemyManager = GameObjectContainer.EnemyManager();
        engine.addGameObject(enemyManager);

        engine.addGameObject(obj);
        engine.start();
    }

    @Override
    public void dispose() {
        engine.dispose();
    }

}
