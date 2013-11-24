import com.jogamp.opengl.util.*;
import javax.media.opengl.*;
import javax.media.opengl.glu.*;

import java.util.*;
import java.io.IOException;

public class bfish {
	
	public float x, y,z;
	private GLU glu = new GLU();
	private int fish_obj, body, tail;
	  
	private float dir_x, dir_y, dir_z;
	private float speed;
	  
	private float angle;
	private float rot_dir;
	private float rot_speed;
	private int nextmove;
	private int prevcollide=0;
	private boolean cancollide =true;
	
	private float reallign;
	public BoundingSphere myBS;
	
	
	
	  public bfish(float xx, float yy, float zz )
	  {
	    x=xx;
	    y=yy;
	    z=zz;
	    
	    
	    fish_obj=body=tail=0;
	    
	    dir_x=1;
	    dir_y=0;
	    dir_z=0;
	    speed=.01f;
	    nextmove=1;
	    
	    angle=0;
	    rot_speed=5;
	    rot_dir=1;
	    
	  }
	  
	  public void init( GL2 gl )
	  {
	  
	    //bounding sphere for collisions with other fish
	    myBS = new BoundingSphere(x,y,z, .4f);
	    
	    create_body(gl);
	    create_tail(gl);
	    
	    fish_obj = gl.glGenLists(1);

	    gl.glNewList( fish_obj, GL2.GL_COMPILE );
	    construct_disp_list( gl ); 
	    gl.glEndList();
	  }
	  
	  
	  
	  
	  public void update( GL2 gl )
	  {	
		  //only collide if cancollide is true
		if (prevcollide==0)
			cancollide=true;
		else
			prevcollide--;
		//choose new random movement at start of program
		if (nextmove==1)
		{
			choosemovement();
			nextmove--;
		}
		else
		findfood();  //look for red fish to eat
		

		
	  	//move fish
		translate();
		//flap tail back and forth
		flap_tail();
		
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
	  { 	float myangle;
		  	gl.glTranslatef(x, y, z);
		  
		    gl.glPushMatrix();
		    allignfish();
		    //orient the fish in the proper direction
		    gl.glRotatef(180+reallign, 0, 1, 0);
		    gl.glCallList(body);
		    gl.glPopMatrix();
		    
		    gl.glPushMatrix();
		    allignfish();
		    //orient the fish in the proper direction
		    gl.glRotatef(180+reallign, 0, 1, 0);
		    gl.glRotatef(angle, 0, 1, 0);
		    gl.glCallList(tail);
		   
		    gl.glPopMatrix();
		
	    
	  }
	  private void translate()
	  {
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
		  
		  myBS.setpos(x, y, z);
	  }
	  private void allignfish()
	  {
		  //allign the fish in the direction he is facing 
		  double mag = Math.sqrt(Math.pow(dir_x, 2)+Math.pow(dir_z,2));
	  
		  if(dir_x == 0)	 
			  reallign = 0; 
		  
		  else if(dir_x>0)
		  //tan = opp/adj
			  reallign = (float)Math.toDegrees(Math.atan( (dir_z/dir_x)/mag ));
		  else
		  {
			  reallign = 180+(float)Math.toDegrees(Math.atan( (dir_z/dir_x)/mag) );
		  }
	  
		  reallign*= -1;
	 
	  	}
	  private void flap_tail()
	  {	
		
		 if (angle>10)
			 rot_dir=-1;
		 if (angle<-10)
			 rot_dir=1;
		 

		 angle+= rot_dir*rot_speed;
	  }
	  
	  public void findfood()
	  {
		  //move toward the red fish
		  
		  for (int i =0; i <Vivarium.creaturelist.size(); i++)
		  {
			  if (Vivarium.creaturelist.get(i).alive==true)
			  {
		
				  float xx= Vivarium.creaturelist.get(i).x;
				  float yy= Vivarium.creaturelist.get(i).y;
				  float zz= Vivarium.creaturelist.get(i).z;
		  
				  float distance = (float)Math.sqrt( Math.pow( (double)this.x-xx, 2) +  Math.pow( (double)this.y-yy, 2) +  Math.pow( (double)this.z-zz, 2) );
				  //if the fish is close choose new direction
				  if (distance<4f)
				  {
					  dir_x= xx-this.x;
					  dir_y= yy-this.y;
					  dir_z= zz-this.z;
					  return;
				  }
			  }
		  }
	  }
	  
	  
	  public void collide()
	  {	
	
		  
	  
		  
	  }
	  
	  
	  public void choosemovement()
	  {
		  

		
		  //Choose a new random movement
		  Random rn = new Random();
		  dir_x=rn.nextFloat();
		  dir_y=rn.nextFloat();
		  dir_z=rn.nextFloat();
  
		  
	  }
	  
	  private void create_body( GL2 gl)
	  {	//generate a sphere for the body and then scale it to look like a fish
	    body = gl.glGenLists(1);
	    
	    gl.glNewList( body, GL2.GL_COMPILE);
	    gl.glPushMatrix();
	   
	 
	   
	    
	    GLUquadric myquad = glu.gluNewQuadric();
	    glu.gluQuadricDrawStyle( myquad, GLU.GLU_FILL );
	    glu.gluQuadricNormals( myquad, GLU.GLU_SMOOTH );

	    gl.glColor3f( 0f, 0f, 1f );
	    gl.glScalef(1.2f,1f,0.4f);
	    glu.gluSphere(myquad,.2f,20,20);
	   
	    
	    gl.glPopMatrix();
	    gl.glEndList();
	    
	  }

	  private void create_tail( GL2 gl)
	  {	//generate a sphere for the tail and scale it to like like a fish tail
	    tail = gl.glGenLists(1);
	    
	    gl.glNewList( tail, GL2.GL_COMPILE);
	    gl.glPushMatrix();
	   
	    gl.glTranslatef(.2f, 0, 0);
	    gl.glRotatef(90, 0, 1, 0);
	    
	    GLUquadric myquad = glu.gluNewQuadric();
	    glu.gluQuadricDrawStyle( myquad, GLU.GLU_FILL );
	    glu.gluQuadricNormals( myquad, GLU.GLU_SMOOTH );

	    gl.glColor3f( 0f, 0f, 1f );
	    gl.glScalef(.2f,1f,0.4f);
	    
	    
	    glu.gluSphere(myquad,.2f,20,20);
	    
	    
	    gl.glPopMatrix();
	    gl.glEndList();
	    
	  }
	  
	  
}
