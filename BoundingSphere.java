
public class BoundingSphere 
{
	public float xpos, ypos, zpos;
	public float r;
	public BoundingSphere(float x, float y, float z, float radius)
	{
		xpos=x;
		ypos=y;
		zpos=z;
		r=radius;
		
	}

	public int detectO(BoundingSphere BS)
	{
		float distance = (float)Math.sqrt( Math.pow( (double)this.xpos-BS.xpos, 2) +  Math.pow( (double)this.ypos-BS.ypos, 2) +  Math.pow( (double)this.zpos-BS.zpos, 2) );
		if (distance > this.r + BS.r)
			return 0;
		else
			return 1;
	}
	
	public void setpos(float x, float y, float z)
	{
		xpos=x;
		ypos=y;
		zpos=z;
	}

}
