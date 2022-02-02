package com.bullethell.game.ecs.Systems;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.bullethell.game.BulletHellMain;
import com.bullethell.game.ecs.IEntityListener;
import com.bullethell.game.ecs.GameObject;
import com.bullethell.game.ecs.Tag;
import com.bullethell.game.ecs.components.SpriteRenderer;
import com.bullethell.game.ecs.components.Transform;

public class RenderingSystem extends IteratingSystem implements IEntityListener {

    private SpriteBatch batch;
    private static OrthographicCamera camera;
    
    public static OrthographicCamera Main()
    {
        return camera;
    }
    
    public RenderingSystem(Tag tag)
    {
        gameObjects = new Array<GameObject>();
        this.tag = tag; 
        camera = new OrthographicCamera();
        camera.setToOrtho(false, BulletHellMain.width, BulletHellMain.height);
    }


    @Override
    public void start() {
        batch = new SpriteBatch();
    }

    @Override
    public void update()
    {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for(GameObject object : gameObjects) {
            processGameObject(object);
        }
        batch.end();
    }
    
    @Override
    public void processGameObject(GameObject gameObject) {
        Transform transform = gameObject.getTransform();
        SpriteRenderer spriteRenderer = gameObject.getComponent(SpriteRenderer.class);
        Sprite sprite = spriteRenderer.getSprite();
        
        if(sprite == null)
            return;

        sprite.setSize(transform.getScale().x, transform.getScale().y);
        sprite.setPosition(transform.getPosition().x, transform.getPosition().y);
        
        sprite.draw(batch);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public IEntityListener getEntityListener() {
        return this;
    }
    
    public void gameObjectAdded(GameObject gameObject) {
        if (gameObjects == null)
            gameObjects = new Array<GameObject>();
        if(!tag.isMemberOf(gameObject))
            return;
        gameObjects.add(gameObject);
    }

    public void gameObjectRemoved(final GameObject gameObject) {
       
        gameObjects.removeValue(gameObject, true);
    }
}
