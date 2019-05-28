package region.model;

import region.fCDC.Circle;

public interface Model {

	double dirNW(Circle a, Circle b);
	double dirN(Circle a, Circle b);
	double dirNE(Circle a, Circle b);
	double dirW(Circle a, Circle b);
	double near(Circle a, Circle b);
	double dirE(Circle a, Circle b);
	double dirSW(Circle a, Circle b);
	double dirS(Circle a, Circle b);
	double dirSE(Circle a, Circle b);
	
	

}
