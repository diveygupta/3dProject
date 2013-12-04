//************************************************************************
//  Vivarium Class. 
//    This is the main object that coordinates the update among the
//    creatures of the vivarium.
//************************************************************************
// Comments :
//    This is meant for demonstration. PLEASE plan out your own vivarium
//    creatures, display hierarchy, data structures, etc. No teapots 
//    allowed :).
//
// History :
//    5 Mar 2008. Translated from c code by Tai-Peng Tian. Original by
//    Stan Sclaroff.


import javax.media.opengl.*;
import com.jogamp.opengl.util.*;
import java.util.*;

public class Vivarium
{
  private Tank tank;
  private Teapot teapot;
  private Butterfly butterfly;
  private Butterfly butterfly2;
  public static ArrayList<Fish> creaturelist = new ArrayList();
  
  
  public ArrayList<food> foodlist = new ArrayList();
   
  private Butterfly current;
  private Butterfly current2;
  public static boolean hasfood=false;

  private Fish myfish;
  private Fish myfish2;
  
  public static bfish mybfish;
  
  
  public static food myfood;
  public float dprod (Vec v1, Vec v2)
  {
	  float result = v1.x *v2.x + v1.y*v2.y + v1.z* v2.z;
	  return result;
	  
	  
  }
  public Vivarium()
  {
	
	
	  //red fish
	for (int i=0; i< 10 ;i++)
	{	Random rn = new Random();
		float mypos = rn.nextFloat()*2 +-1;
		myfish= new Fish(mypos, mypos, mypos);
		//myfish = new Fish(i, 0, 0);
	    creaturelist.add(myfish);
		
	}
	
	//blue fish
	
	//mybfish= new bfish(-1, -1, -1);
	
	
    tank = new Tank( 4.0f, 4.0f, 4.0f );

  }

  public void init( GL2 gl )
  {
	//initialize red fish
	Iterator it = creaturelist.iterator();
	while (it.hasNext())
	{
		myfish=(Fish) it.next();
		myfish.init(gl);
	}
	
	//initialize blue fish
	//mybfish.init(gl);
	
	
	
	
	
	
    tank.init( gl );
   // teapot.init( gl );
  }

  public void update( GL2 gl )
  {
    int size=foodlist.size();
    
    //update the red fish
    
	Iterator it = creaturelist.iterator();
	while (it.hasNext())
	{
		myfish=(Fish) it.next();
		myfish.update(gl);
		
	}
	
	//update the blue fish
	//mybfish.update(gl);
		
	
	//if you a food is being added
	if (PA3.addfood==true)
	{
			
		//create new food and initialize it
		//hasfood=true
		myfood= new food(0,-1);
		myfood.init(gl);
		myfood.draw(gl);
		hasfood=true;
		
		PA3.addfood=false;
	}
	
	if (hasfood==true)
	{	//if has food=true then check for fish/food collisions (fish eating the food)
		for (int i =0; i<creaturelist.size(); i++)
		{
			myfish= (Fish) creaturelist.get(i);
			if (myfish.myBS.detectO(myfood.bs)==1 && myfish.alive==true)
			{
				hasfood=false;
			}
		}
		//update the food
		if (hasfood==true)
		myfood.update(gl);
	}
	
	
	
	
	//check for collisions between the redfish and blue fish
	//for (int j=0; j<creaturelist.size(); j++)
	//{	myfish=creaturelist.get(j);	
		
		//if (myfish.myBS.detectO(mybfish.myBS)==1)
			//myfish.alive=false; //if there is a collision the red fish that collided is now dead
		
	//}	
	
  	//check for collisions among redfish
	Vec cp= new Vec();
	Vec cp2 = new Vec();
	//intial velocity vectors
	Vec V1 = new Vec();
	Vec V2 = new Vec();
	//normal and tangent vectors
	Vec Vn1 = new Vec();
	Vec Vn2 = new Vec();
	Vec Vt1 = new Vec();
	Vec Vt2 = new Vec();
	//final velocity vectors
	Vec V1f = new Vec();
	Vec V2f = new Vec();
	
	for (int i= 0; i<creaturelist.size()-1; i++)
	{	
		for (int j=i+1; j<creaturelist.size(); j++)
		{
			myfish= (Fish) creaturelist.get(i);
			myfish2= (Fish) creaturelist.get(j);
			if (myfish.myBS.detectO(myfish2.myBS)==1)
			{//Collision!
				//calculate collision plane
				cp.x = myfish.x-myfish2.x;
				cp.y = myfish.y-myfish2.y;
				cp.z = myfish.z-myfish2.z;
				cp.norm();
				cp2.x = -cp.x;
				cp2.y = -cp.y;
				cp2.z = -cp.z;
				
				//Normal vector 1
				V1.x = myfish.dir_x;
				V1.y = myfish.dir_y;
				V1.z = myfish.dir_z;
				float temp = dprod(V1, cp2);
				Vn1 = cp2.mult(temp);
						
				//Normal vector 2
				V2.x = myfish2.dir_x;
				V2.y = myfish2.dir_y;
				V2.z = myfish2.dir_z;
				temp = dprod(V2, cp);
				Vn2 = cp.mult(temp);
				
				//tangent vector 1
				Vt1 = Vn1.sub(V1);
				
				//tangent vector 2
				Vt2 = Vn2.sub(V2);
				
				V1f = Vt1.add(Vn2);
				V2f = Vt2.add(Vn1);
				
				System.out.println("Collision");
				myfish.collide(V1f);
				myfish2.collide(V2f);
			}
			
		
		}			
		
	}
	//update the tank
    tank.update( gl );

  }

  public void draw( GL2 gl )
  {
    
    
	Iterator it = creaturelist.iterator();
	//draw the redfish
	while (it.hasNext())
	{	
		
		myfish=(Fish) it.next();
		if (myfish.alive==true)
		myfish.draw(gl);
	}
	
	
	//draw the blue fish
	//mybfish.draw(gl);
	
	
	//draw the food if it is in the tank
	if (hasfood==true)
	myfood.draw(gl);

    tank.draw( gl );
   
  }
}
