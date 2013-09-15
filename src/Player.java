

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Player extends Box
{
	boolean dead;
	
	public Player(float xpos, float ypos, float size, List<Box> shapes) 
	{
		super(xpos, ypos, size, shapes);
		dead = false; 
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() 
	{
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
            this.xpos += 2;
        }
        if(Gdx.input.isKeyPressed(Keys.LEFT)){
            this.xpos -= 2;
        }
        if(Gdx.input.isKeyPressed(Keys.UP)){
            this.ypos += 2;
        }
        if(Gdx.input.isKeyPressed(Keys.DOWN)){
            this.ypos -= 2;
        }
        if(Gdx.input.isKeyPressed(Keys.SPACE)){
        	spawnProjectile(); 
        }
	}
	
	public void spawnProjectile()
	{
		Projectile projectile = new Projectile(this.xpos, this.ypos, 4, otherShapes);
		otherShapes.add(projectile);
	}
}
