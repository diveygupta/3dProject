import java.util.ArrayList;


public class ArrayOfInterval {
	  public ArrayList<interval> ballListX = new ArrayList<interval>();
	  public ArrayList<interval> ballListY = new ArrayList<interval>();
	  public ArrayList<interval> ballListZ = new ArrayList<interval>();
	  
	  public void sortIntervalArray(){
		  
	  }
	  
	  public boolean checkCollision(){
		  if(checkEach('X') && checkEach('Y') && checkEach('Z'))
			  return true;
		  else 
			  return false;
	  }
	  
	  public boolean checkEach(char axis){
		  boolean ret = false;
		  switch(axis){
		  	case 'X':{
		  		break;
		  	}
		  	case 'Y':{
		  		break;
		  	}
		  	case 'Z':{
		  		break;
		  	}
		  	default:{
		  		System.out.println("error occurs in checkEach funcion in file ArrayOfInterval.java");
		  	}
		  }
		  return ret;
	  }
	  
	  
	  
}
