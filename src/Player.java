

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Player extends Box
{
	boolean dead;
	Boxesmustdie application; 
	
	public Player(float xpos, float ypos, float size, List<Box> shapes, Boxesmustdie application) 
	{
		super(xpos, ypos, size, application);
		this.application = application;
		dead = false; 
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void display()
	{
		Gdx.gl11.glLoadIdentity();
        Gdx.gl11.glColor4f(0f, 1f, 0f, 0f);
        
        Gdx.gl11.glPushMatrix();
        Gdx.gl11.glTranslatef(xpos, ypos, 0);
        Gdx.gl11.glScalef(size,size,0);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
        Gdx.gl11.glPopMatrix();
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
        
        for (Box s: application.shapes)
    	{
    		if (this.hasCollided(s))
    		{
    			application.collisioncount += 1;
    			System.out.println(application.collisioncount);
    			this.dead = true; 
    			application.destroyed.add(this);
    		}
    	}
	}
}
