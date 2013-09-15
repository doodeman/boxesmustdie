
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.utils.BufferUtils;

public class Boxesmustdie implements ApplicationListener {
	
	public static void main(String[] arg)
	{
		//LwjglApplication(ApplicationListener listener, java.lang.String title, int width, int height, boolean useGL2) 
		new LwjglApplication(new Boxesmustdie(), "Boxes must die", 800, 600, false); 
	}
	
    // Vertex buffer.
    private FloatBuffer vertexBuffer = null;
    
    private Random random; 

    private List<Box> shapes; 
    
    private Player player; 
    
    int collisioncount; 
    
    @Override
    public void create() {
        System.out.println("Created!");
        random = new Random(); 
        shapes = new ArrayList<Box>(); 
        
        player = new Player(10, 10, 20, shapes);
        shapes.add(player);
        
        
        this.vertexBuffer = BufferUtils.newFloatBuffer(8);
        
        float[] box = new float[] {0,0, 0,20,  20,0,  20,20};
        this.vertexBuffer.put(box);
        this.vertexBuffer.rewind();
        Gdx.gl11.glVertexPointer(2, GL11.GL_FLOAT, 0, this.vertexBuffer);
        
        // Enable vertex array.
        Gdx.gl11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        
        // Select clear color for the screen.
        Gdx.gl11.glClearColor(.3f, .3f, .3f, 1f);
        
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }
    
    private void display(){
        Gdx.gl11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        Gdx.gl11.glMatrixMode(GL11.GL_MODELVIEW);
        
        for (Box s : shapes)
        {
            s.display(); 
        } 
    }
    
    private void update()
    {
    	int rand = random.nextInt(3) + 1; 
		if (rand == 1)
		{
    		float startpos = random.nextInt(600) + 1;
    		shapes.add(new EnemyBox(800, startpos, 10, shapes)); 
		}
    	
    	for (Box s: shapes)
    	{
    		s.update(); 
    	}
    	
    	for (Box s: shapes)
    	{
    		if (player.hasCollided(s))
    		{
    			collisioncount += 1;
    			System.out.println(collisioncount);
    			player.dead = true; 
    		}
    	}
    }
    
    
    
    @Override
    public void render() {
        this.display();
        this.update();
    }

    @Override
    public void resize(int width, int height) {
        // Load the Project matrix. Next commands will be applied on that matrix.
        Gdx.gl11.glMatrixMode(GL11.GL_PROJECTION);
        Gdx.gl11.glLoadIdentity();

        // Set up a two-dimensional orthographic viewing region.
        Gdx.glu.gluOrtho2D(Gdx.gl11, 0, width, 0, height);

        // Set up affine transformation of x and y from world coordinates to window coordinates
        Gdx.gl11.glViewport(0, 0, width, height);

        // Set the Modelview matrix back.
        Gdx.gl11.glMatrixMode(GL11.GL_MODELVIEW_MATRIX);    
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }
    
}

