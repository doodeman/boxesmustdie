import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;



public class Projectile extends Box
{

	public Projectile(float xpos, float ypos, float size, Boxesmustdie application, boolean isEnemy) 
	{
		super(xpos, ypos, size, application, 1, isEnemy);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() 
	{
		if (this.isEnemy)
		{
			this.xpos -= 10;
		}
		else
		{
			this.xpos += 10;
		}
		for (Box b : application.shapes)
		{
			if (this.hasCollided(b))
			{
				if (!this.isEnemy && b.isEnemy)
				{
					b.hitpoints -= 1; 
					application.destroyed.add(this);
					break;
				}
				if (this.isEnemy && !b.isEnemy)
				{
					b.hitpoints -= 1; 
					application.destroyed.add(this); 
					break;
				}
			}
		}
		
		
	}
	
	
	@Override
	public void display()
	{
		Gdx.gl11.glLoadIdentity();
		Random r = application.random;
        Gdx.gl11.glColor4f(r.nextFloat(), r.nextFloat(), 1f, 1f);
        
        Gdx.gl11.glPushMatrix();
        Gdx.gl11.glTranslatef(xpos, ypos, 0);
        Gdx.gl11.glScalef(size,size,0);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
        Gdx.gl11.glPopMatrix();
	}
}
