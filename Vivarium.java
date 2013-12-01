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
import java.util.*;

public class Vivarium
{
  private Tank tank;
  public static ArrayList<Fish> creaturelist = new ArrayList<Fish>();

  private Fish myfish;
  private Fish myfish2;

  public ArrayOfInterval array = new ArrayOfInterval();
  
  //ball list x, y and z
  interval myInterval = new interval();
  public ArrayOfInterval array = new ArrayOfInterval();
  
  // For debug
  //private int count=0;
  
  public Vivarium()
  {
	
<<<<<<< HEAD
	
	  //red fish
	for (int i=0; i< 300 ;i++)
	{
		Random rn = new Random();
		float mypos = rn.nextFloat()*2 - 2;
		myfish= new Fish(mypos, mypos, mypos, i);
		creaturelist.add(myfish);
    
		interval intvx = new interval();
		intvx.startPoint = mypos-myfish.radius;
		intvx.endPoint = mypos+myfish.radius;
		intvx.id = i;
    	array.ballListX.add(intvx);
    
    	interval intvy = new interval();
    	intvx.startPoint = mypos-myfish.radius;
    	intvx.endPoint = mypos+myfish.radius;
    	intvy.id = i;
    	array.ballListY.add(intvy);
    
    	interval intvz = new interval();
    	intvx.startPoint = mypos-myfish.radius;
    	intvx.endPoint = mypos+myfish.radius;
    	intvz.id = i;
    	array.ballListZ.add(intvz);
=======
	// Qiang - 11.29
	//red fish
	for (int i=0; i< 2;i++)
	{	Random rn = new Random();
		float mypos = rn.nextFloat()*2 - 2;
		myfish= new Fish(mypos, mypos, mypos, i);
	    creaturelist.add(myfish);
	    
	    interval intvx = new interval();
	    intvx.startPoint = mypos-myfish.radius;
	    intvx.endPoint = mypos+myfish.radius;
	    intvx.id = i;
	    array.ballListX.add(intvx);
	    
	    interval intvy = new interval();
	    intvx.startPoint = mypos-myfish.radius;
	    intvx.endPoint = mypos+myfish.radius;
	    intvy.id = i;
	    array.ballListY.add(intvy);
	    
	    interval intvz = new interval();
	    intvx.startPoint = mypos-myfish.radius;
	    intvx.endPoint = mypos+myfish.radius;
	    intvz.id = i;
	    array.ballListZ.add(intvz);
>>>>>>> 358418682edfa712e8009e965890bc8b929c2329
	}
	
	// For dubug
	  /*
	myfish= new Fish(0, 0, 0, 0);
    creaturelist.add(myfish);
    
    interval intvx = new interval();
    intvx.startPoint = -0.15f;
    intvx.endPoint = 0.15f;
    intvx.id = 0;
    array.ballListX.add(intvx);
    
    interval intvy = new interval();
    intvy.startPoint = -0.15f;
    intvy.endPoint = 0.15f;
    intvy.id = 0;
    array.ballListY.add(intvy);
    
    interval intvz = new interval();
    intvz.startPoint = -0.15f;
    intvz.endPoint = 0.15f;
    intvz.id = 0;
    array.ballListZ.add(intvz);
    
    myfish= new Fish(0.5f, 0, 0, 1);
    creaturelist.add(myfish);
    
    intvx = new interval();
    intvx.startPoint = 0.5f-0.15f;
    intvx.endPoint = 0.5f+0.15f;
    intvx.id = 1;
    array.ballListX.add(intvx);
    
    intvy = new interval();
    intvx.startPoint = 0.5f-0.15f;
    intvx.endPoint = 0.5f+0.15f;
    intvx.id = 1;
    array.ballListY.add(intvy);
    
    intvz = new interval();
    intvx.startPoint = 0.5f-0.15f;
    intvx.endPoint = 0.5f+0.15f;
    intvx.id = 1;
    array.ballListZ.add(intvz);*/
	// Qiang - 11.29
	
	//blue fish
	
	//mybfish= new bfish(-1, -1, -1);
	
	
    tank = new Tank( 4.0f, 4.0f, 4.0f );

  }

  public void init( GL2 gl )
  {
	//initialize red fish
	Iterator<Fish> it = creaturelist.iterator();
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
	  // For debug
	  //count++;
    
    //update the red fish
<<<<<<< HEAD
    //old version
=======
    
    // Qiang - 11.29
>>>>>>> 358418682edfa712e8009e965890bc8b929c2329
    /*
	Iterator it = creaturelist.iterator();
	while (it.hasNext())
	{
		myfish=(Fish) it.next();
<<<<<<< HEAD
		myfish.update(gl);
		
	}
	*/
    //new version of update : 
    //Qiang's version  O(n^2)
    /*
	for(int i=0;i<creaturelist.size();i++)
	{
		myfish=creaturelist.get(i);
		myfish.update(gl);
		for(int j=0;j<array.ballListX.size();j++)
		{
			if(array.ballListX.get(j).id==myfish.id)
			{
				float radius = myfish.radius;
				array.ballListX.get(j).startPoint = myfish.x - radius;
				array.ballListX.get(j).endPoint = myfish.x + radius;
				array.ballListY.get(j).startPoint = myfish.y - radius;
				array.ballListY.get(j).endPoint = myfish.y + radius;
				array.ballListZ.get(j).startPoint = myfish.z - radius;
				array.ballListZ.get(j).endPoint = myfish.z + radius;
			}
		}
	}
	*/
    /*--------------------linghao's version   : O(n)------------------*/
	//update X, Y, Z seperately
    updateBallList(array.ballListX, 'X');
    updateBallList(array.ballListY, 'Y');
    updateBallList(array.ballListZ, 'Z');
	//update the blue fish
	//mybfish.update(gl);
		
	
	//if you a food is being added
	if (PA3.addfood==true)
=======
		myfish.update2(gl);
	}*/
	
    // O(n^2) Need improve
	for(int i=0;i<creaturelist.size();i++)
>>>>>>> 358418682edfa712e8009e965890bc8b929c2329
	{
		myfish=creaturelist.get(i);
		myfish.update(gl);
		for(int j=0;j<array.ballListX.size();j++)
		{
			if(array.ballListX.get(j).id==myfish.id)
			{
				float radius = myfish.radius;
				array.ballListX.get(j).startPoint = myfish.x - radius;
				array.ballListX.get(j).endPoint = myfish.x + radius;
				array.ballListY.get(j).startPoint = myfish.y - radius;
				array.ballListY.get(j).endPoint = myfish.y + radius;
				array.ballListZ.get(j).startPoint = myfish.z - radius;
				array.ballListZ.get(j).endPoint = myfish.z + radius;
			}
		}
	}
	// Qiang - 11.29
	
	//check for collisions between the redfish and blue fish
	//for (int j=0; j<creaturelist.size(); j++)
	//{	myfish=creaturelist.get(j);	
		
		//if (myfish.myBS.detectO(mybfish.myBS)==1)
			//myfish.alive=false; //if there is a collision the red fish that collided is now dead
		
	//}	
  	//check for collisions among redfish
	//O(n2) - > O(n + m)
	//closet pair of balls
	
	/*
	for (int i= 0; i<creaturelist.size()-1; i++)
	{	
		for (int j=i+1; j<creaturelist.size(); j++)
		{
			myfish= (Fish) creaturelist.get(i);
			myfish2= (Fish) creaturelist.get(j);
			if (myfish.myBS.detectO(myfish2.myBS)==1)
			{//Collision!
				System.out.println("Collision");
				myfish.collide();
				myfish2.collide();
			}		
		}
	}*/
	
	//if(count==55)
	//	count=count;
	
	ArrayList<int[]> collisionList = array.checkCollision2();
	if(collisionList.size()!=0)
	{
		for(int i=0; i<collisionList.size(); i++)
		{
			myfish= (Fish) creaturelist.get(collisionList.get(i)[0]);
			myfish2= (Fish) creaturelist.get(collisionList.get(i)[1]);
			System.out.println("Collision");
			myfish.collide();
			myfish2.collide();
		}
	}
<<<<<<< HEAD
	*/
	//Using I-Collide algorithm: Time complexity O(n + m). n: number of balls in the program. m: number of pairs of balls that are closest
	//step 1: sort three list
	//array.sortIntervalArray();
	
	ArrayList<int[]> collisionList = array.checkCollision2();
	if(collisionList.size()!=0)
	{
		for(int i=0; i<collisionList.size(); i++)
		{
			myfish= (Fish) creaturelist.get(collisionList.get(i)[0]);
			myfish2= (Fish) creaturelist.get(collisionList.get(i)[1]);
			System.out.println("Collision");
			myfish.collide();
			myfish2.collide();
		}
	}
	//init
	//
=======
	// Qiang - 11.29

>>>>>>> 358418682edfa712e8009e965890bc8b929c2329
	//update the tank
    tank.update( gl );

  }

  public void draw( GL2 gl )
  {
    
    
	Iterator<Fish> it = creaturelist.iterator();
	//draw the redfish
	while (it.hasNext())
	{	
		myfish=(Fish) it.next();
		myfish.draw(gl);
	}

    tank.draw( gl );
   
  }
  
  public void updateBallList(ArrayList<interval> myList, char axis){
	  for(int i = 0; i < myList.size(); i++){
		  int id = myList.get(i).id;
		  Fish tempFish = creaturelist.get(id);
		  interval tempInterval = new interval();
		  tempInterval.id = id;
		  switch(axis){
		  	case('X'):{
		  		tempInterval.startPoint = tempFish.x - tempFish.radius;
		  		tempInterval.endPoint = tempFish.x + tempFish.radius;
		  		break;
		  	}
		  	case('Y'):{
		  		tempInterval.startPoint = tempFish.y - tempFish.radius;
		  		tempInterval.endPoint = tempFish.y + tempFish.radius;
		  		break;
		  	}
		  	case('Z'):{
		  		tempInterval.startPoint = tempFish.z - tempFish.radius;
		  		tempInterval.endPoint = tempFish.z + tempFish.radius;
		  		break;
		  	}
		  	default:{
		  		System.out.println("error in updateBallList in Vivarium.java");
		  	}
		  }
		 //set value
		 myList.set(i, tempInterval);
	  }
  }
}
