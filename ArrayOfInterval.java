import java.util.ArrayList;


public class ArrayOfInterval {
	  public ArrayList<interval> ballListX = new ArrayList<interval>();
	  public ArrayList<interval> ballListY = new ArrayList<interval>();
	  public ArrayList<interval> ballListZ = new ArrayList<interval>();
	  
	  public void sortIntervalArray(){
		  insertionSort();
	  }

	  public ArrayList<int[]> checkCollision2(){
		  sortIntervalArray();
		  return checkOverlap();
	  }
	  
	  public boolean checkEach(char axis){
		  boolean ret = false;
		  switch(axis){
		  	case 'X':{
		  		checkOverlap(ballListX);
		  		break;
		  	}
		  	case 'Y':{
		  		checkOverlap(ballListY);
		  		break;
		  	}
		  	case 'Z':{
		  		checkOverlap(ballListZ);
		  		break;
		  	}
		  	default:{
		  		System.out.println("error occurs in checkEach funcion in file ArrayOfInterval.java");
		  	}
		  }
		  return ret;
	  }
	  
	public void checkOverlap(ArrayList<interval> list)
	{
		ArrayList<int[]> overlapList = new ArrayList<>();
		for(int i=0;i<list.size();i++)
		{
			for(int j=i;j<list.size();j++)
			{
				if(list.get(i).endPoint>list.get(j).startPoint)
				{
					int[] a = new int[2];
					a[0]=i;
					a[1]=j;
					overlapList.add(a);
				}
				else
					break;
			}
		}
		
	}
	
	public ArrayList<int[]> checkOverlap()
	{	// O(n^2) in worst case
		ArrayList<int[]> overlapList = new ArrayList<>();
		for(int i=0;i<ballListX.size();i++)
		{
			for(int j=i+1;j<ballListX.size();j++)
			{
				if(ballListX.get(i).endPoint>ballListX.get(j).startPoint &&
				   ballListY.get(i).endPoint>ballListY.get(j).startPoint &&
				   ballListZ.get(i).endPoint>ballListZ.get(j).startPoint)
				{
					int[] a = new int[2];
					a[0]=ballListX.get(i).id;
					a[1]=ballListX.get(j).id;
					overlapList.add(a);
				}
				else
					break;
			}
		}
		return overlapList;
	}
	
	public void insertionSort()
	{	// O(n) in general cases
		int i, j;
		float midPoint, startPointX, endPointX, startPointY, endPointY, startPointZ, endPointZ;
		int idX, idY, idZ;
		for (i = 1; i < ballListX.size(); i++)
		{
    	  	midPoint = (ballListX.get(i).startPoint + ballListX.get(i).endPoint)/2;
    	  	startPointX = ballListX.get(i).startPoint;
    	  	endPointX = ballListX.get(i).endPoint;
    	  	idX =  ballListX.get(i).id;
    	  	startPointY = ballListY.get(i).startPoint;
    	  	endPointY = ballListY.get(i).endPoint;
    	  	idY =  ballListY.get(i).id;
    	  	startPointZ = ballListZ.get(i).startPoint;
    	  	endPointZ = ballListZ.get(i).endPoint;
    	  	idZ =  ballListZ.get(i).id;
			j = i;
			while (j > 0 && (ballListX.get(j-1).startPoint + ballListX.get(j-1).endPoint)/2 > midPoint)
			{
				ballListX.get(j).startPoint = ballListX.get(j-1).startPoint;
				ballListX.get(j).endPoint = ballListX.get(j-1).endPoint;
				ballListX.get(j).id = ballListX.get(j-1).id;
				ballListY.get(j).startPoint = ballListY.get(j-1).startPoint;
				ballListY.get(j).endPoint = ballListY.get(j-1).endPoint;
				ballListY.get(j).id = ballListY.get(j-1).id;
				ballListZ.get(j).startPoint = ballListZ.get(j-1).startPoint;
				ballListZ.get(j).endPoint = ballListZ.get(j-1).endPoint;
				ballListZ.get(j).id = ballListZ.get(j-1).id;
				j--;
			}
			ballListX.get(j).startPoint = startPointX;
			ballListX.get(j).endPoint = endPointX;
			ballListX.get(j).id = idX;
			ballListY.get(j).startPoint = startPointY;
			ballListY.get(j).endPoint = endPointY;
			ballListY.get(j).id = idY;
			ballListZ.get(j).startPoint = startPointZ;
			ballListZ.get(j).endPoint = endPointZ;
			ballListZ.get(j).id = idZ;
	    }
		/*for (i = 0; i < ballListX.size(); i++)
		{
			System.out.printf("%f\n", (ballListX.get(i).startPoint+ballListX.get(i).endPoint)/2);
		}*/
	}
}
