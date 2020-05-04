import java.util.Comparator;

public class Priority implements Comparator<Road>
{

	@Override
	public int compare(Road r1, Road r2) 
	{
		if(r1.getWeight() < r2.getWeight())
		{
			return -1;
		}
		else if(r1.getWeight() > r2.getWeight())
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
}

