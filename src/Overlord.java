import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;


public class Overlord extends Box
{
	boolean goingup; 
	boolean charging; 
	int chargingDuration, chargeCountdown; 
	
	public Overlord(float xpos, float ypos, float size, Boxesmustdie application, int hitpoints) 
	{
		super(xpos, ypos, size, application, hitpoints, true);
		charging = false;
		chargeCountdown = 150;
	}

	@Override
	public void update() 
	{
		if (chargeCountdown == 0 && charging == false)
		{
			charging = true; 
			chargingDuration = 0;
		}
		
		if (charging)
		{
			chargingDuration += 1;
			if (chargingDuration == 20)
			{
				System.out.println("firing");
				spawnProjectile(); 
				charging = false;
				chargeCountdown = 150;
			}
		}
		else
		{
			chargeCountdown -= 1;
		}
		
		if (this.hitpoints == 0)
		{
			application.destroyed.add(this);
		}
		
		if (this.xpos > 600)
		{
			this.xpos -= 1; 
		}
		if (this.xpos < 600)
		{
			this.xpos += 1; 
		}
		if (this.xpos == 600)
		{
			if (application.player.ypos > this.ypos)
			{
				this.ypos += 1;
			}
			if (application.player.ypos < this.ypos)
			{
				this.ypos -= 1;
			}
		}
	}
	
	public void spawnProjectile()
	{
		Projectile projectile = new Projectile(this.xpos, this.ypos, this.size, application, true);
		application.toInsert.add(projectile);
	}
	
	@Override
	public void display()
	{
		Gdx.gl11.glLoadIdentity();
		if (charging && application.framecount%2 == 0)
		{
	        Gdx.gl11.glColor4f(1f, 1f, 0f, 0f);
		}
        
        Gdx.gl11.glPushMatrix();
        Gdx.gl11.glTranslatef(xpos, ypos, 0);
        Gdx.gl11.glScalef(size,size,0);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
        Gdx.gl11.glPopMatrix();
	}
}
