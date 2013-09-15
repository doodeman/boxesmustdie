import java.util.List;



public class Projectile extends Box
{

	public Projectile(float xpos, float ypos, float size, List<Box> otherShapes) 
	{
		super(xpos, ypos, size, otherShapes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() 
	{
		this.xpos -= 10;
	}

}
