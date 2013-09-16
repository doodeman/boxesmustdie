
public class Overlord extends Box
{
	boolean goingup; 
	public Overlord(float xpos, float ypos, float size, Boxesmustdie application, int hitpoints) 
	{
		super(xpos, ypos, size, application, hitpoints, true);
	}

	@Override
	public void update() 
	{
		if (this.hitpoints == 0)
		{
			application.destroyed.add(this);
		}
		
		System.out.println("Boss ypos = " + this.ypos);
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
}
