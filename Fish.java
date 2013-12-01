import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import java.util.*;

//Creates a new fish object (small red fish). These fish are capable of bumping into each other and choosing a new direction,
//They will also move towards food when the food is dropped into the tank
public class Fish {
	
	public float x, y,z;
	private GLU glu = new GLU();
	private int fish_obj, body;
	  
	private float dir_x, dir_y, dir_z;
	private float speed;

	private int nextmove;
	private int prevcollide=0;
	private boolean cancollide =true;
	
<<<<<<< HEAD
	private float reallign;
	public BoundingSphere myBS;
	public boolean alive;
	
	public float radius = 0.15f;
	public int id;
	
	
	
	  public Fish(float xx, float yy, float zz, int newId)
=======
	// Qiang - 11.29
	public float radius = 0.15f;
	public int id;
	
	  public Fish(float xx, float yy, float zz, int id)
>>>>>>> 358418682edfa712e8009e965890bc8b929c2329
	  {	//position
	    x=xx;
	    y=yy;
	    z=zz;
	    fish_obj=body=0;
	    dir_x=1;
	    dir_y=0;
	    dir_z=0;
	    speed=.03f;
<<<<<<< HEAD
	    nextmove=1;
	    
	    
	    alive=true;
	    angle=0;
	    rot_speed=5;
	    rot_dir=1;
	    
	    id = newId;
=======
	    nextmove=1;   
	    this.id=id;
>>>>>>> 358418682edfa712e8009e965890bc8b929c2329
	  }
	  // Qiang - 11.29
	  
	  public void init( GL2 gl )
	  {	    
	    create_body(gl);
	    
	    fish_obj = gl.glGenLists(1);

	    gl.glNewList( fish_obj, GL2.GL_COMPILE );
	    construct_disp_list( gl ); 
	    gl.glEndList();
	  }
	  
	  public void update( GL2 gl )
	  {
		if (nextmove==1)
		{	//if first move choose random movement
			choosemovement();
			nextmove--;
		}
		
		translate();
		
		gl.glNewList( fish_obj, GL2.GL_COMPILE );
		construct_disp_list( gl ); 
		gl.glEndList();
	  }
	  
	  public void draw( GL2 gl )
	  {
	    gl.glPushMatrix();
	    gl.glCallList( fish_obj );
	    gl.glPopMatrix();
	  }
	
	  
	  private void construct_disp_list( GL2 gl)
	  {
		  gl.glTranslatef(x, y, z);
		  gl.glPushMatrix();
		  gl.glCallList(body);
		  gl.glPopMatrix();
	  }
	  
	  private void translate()
	  {
		  //move the fish
		  if (x>2)
			  dir_x=-1;
		  
		  if (x<-2)
			  dir_x=1;
		  
		  if (y>2)
			  dir_y=-1;
		  
		  if (y<-2)
			  dir_y=1;
		  
		  if (z>2)
			  dir_z=-1;
		  
		  if (z<-2)
			  dir_z=1;
		
		  x+= dir_x*speed;
		  y+= dir_y*speed;
		  z+= dir_z*speed;
	  }
	  
	  //handles collision with other red fish
	  public void collide()
	  {	//only coollide if both fish are alive
		  if (cancollide)
		  {
			  if (prevcollide>2)
			  {	//if you have just collided then pick a random direction and move that way without changing direction
				  choosemovement();
				  cancollide=false;
				  prevcollide=6;
			  }
			  else if (prevcollide==0)
			  {		//otherwise choose a random x and flip the y and z directions
				  Random rn = new Random();
				  dir_x=rn.nextFloat();
				  dir_y=-dir_y;
				  dir_z=-dir_z;
			  }
			  prevcollide+=2;
		  }
	  }
	  
	  public void choosemovement()
	  {
		  //Choose a new random movement
		  Random rn = new Random();
		  //random direction between -1 and 1
		  dir_x=rn.nextFloat() * 2 + -1 ;
		  dir_y=rn.nextFloat() * 2 + -1;
		  dir_z=rn.nextFloat() * 2 +-1;
	  }
	  
	  // Qiang - 11.29
	  // For debug
	  public void choosemovement2()
	  {
		  dir_x=1;
		  dir_y=0;
		  dir_z=0;
	  }
	// Qiang - 11.29
	  
	  private void create_body( GL2 gl)
	  {	//generate a sphere and scale it to make it look like a fish body
	    body = gl.glGenLists(1);
	    
	    gl.glNewList( body, GL2.GL_COMPILE);
	    gl.glPushMatrix();
	    
	    GLUquadric myquad = glu.gluNewQuadric();
	    glu.gluQuadricDrawStyle( myquad, GLU.GLU_FILL );
	    glu.gluQuadricNormals( myquad, GLU.GLU_SMOOTH );

	    gl.glColor3f( 1f, 0f, 0f );
	    glu.gluSphere(myquad,.15f,20,20);
	    
	    gl.glPopMatrix();
	    gl.glEndList();
	    
	  }	  
}

