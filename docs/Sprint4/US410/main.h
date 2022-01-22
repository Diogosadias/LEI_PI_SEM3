#ifndef MAIN_H
#define MAIN_H
	
    typedef struct {
        int containerID;                //0 offset
        int x;                          //4 offset
        int y;                          //8 offset
        int z;                          //12 offset
        double containerTemperature;    //16 offset
        double containerWidth;          //24 offset
        double containerHeight;         //32 offset
        double containerLength;         //40 offset
        double grossWeight;             //48 offset
        double thickness;               //56 offset
        int isItRefrigerated;           //64 offset     (1 caso seja, 0 caso não seja)
        char materials[28];             //68 offset
        double thermalConductivity;		//96 offset
										//104 offset
                                        
    } CargoManifest;
	
	void read_file();

#endif
