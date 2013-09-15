

import java.util.List;

public class EnemyBox extends Box
{
	public EnemyBox(float xpos, float ypos, float size, List<Box> otherShapes)
	{
		super(xpos, ypos, size, otherShapes);
		this.otherShapes = otherShapes; 
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() 
	{
		this.xpos -= 2;
	}
}
