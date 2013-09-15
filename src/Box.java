import java.util.List;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;



public abstract class Box
{
	public List<Box> otherShapes; 
	//xpos and ypos are the bottom left corner of the box
	public float xpos, ypos, size; 
	
	public Box(float xpos, float ypos, float size, List<Box> otherShapes)
	{
		this.otherShapes = otherShapes;
		this.xpos = xpos; 
		this.ypos = ypos; 
		this.size = size; 
	}
	
	public Corners getCorners()
	{
		Coordinate botleft = new Coordinate(xpos, ypos);
		Coordinate botright = new Coordinate(xpos + size, ypos);
		Coordinate topleft = new Coordinate(xpos, ypos + size); 
		Coordinate topright = new Coordinate(xpos + size, ypos + size); 
		return new Corners(topleft, topright, botleft, botright); 
	}
	
	public abstract void update();
	
	
	public void display()
	{
		Gdx.gl11.glLoadIdentity();
        Gdx.gl11.glColor4f(1f, 1f, 1f, 1f);
        
        Gdx.gl11.glPushMatrix();
        Gdx.gl11.glTranslatef(xpos, ypos, 0);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
        Gdx.gl11.glPopMatrix();
	}
	
	public boolean hasCollided(Box other)
	{
		Corners our = this.getCorners();
		Coordinate[] ourCorners = new Coordinate[4]; 
		ourCorners[0] = our.topleft;
		ourCorners[1] = our.topright;
		ourCorners[2] = our.botleft;
		ourCorners[3] = our.botright; 
		Corners theirs = other.getCorners(); 
		
		for (Coordinate c : ourCorners)
		{
			if (between(c.x, theirs.topleft.x, theirs.topright.x) && between(c.y, theirs.topright.y, theirs.botright.y))
			{
				return true; 
			}
		}
		return false; 
	}
	
	private boolean between (float target, float pos1, float pos2)
	{
		if ( (target < pos1) && (target > pos2) )
		{
			return true; 
		}
		if ( (target > pos1) && (target < pos2) )
		{
			return true; 
		}
		return false; 
	}
}
