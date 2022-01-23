#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <string.h>
#include "main.h"
#include "is_refrigerated.h"
#include "calculate_energy.h"

#define MAX_ROWS 20 // positions of x from 0 to 20
#define MAX_COLUMNS 20 // positions y from 0 to 20
#define MAX_LAYERS 21 // positions z from -10 to 10
#define FILE_NAME "CargoManifests.txt"

int size = 4;
long energyGeneration;
CargoManifest *ptr_manifests;

int main() {

    read_file();
    
    //Depois de todas as funções feitas, dar free à memória
    free(ptr_manifests);
}

void read_file() {
    
    int containerID;
    int x;
    int y;
    int z;
    double containerTemperature;
    double containerWidth;
    double containerHeight;
    double containerLength;
    double grossWeight;
    double thickness;
    int isItRefrigerated;
    char materials[28];
    double thermalConductivity;

    FILE *file;
    int numLinhas = 0;
    int i;

    /*
    * Ler o ficheiro
    */
    file = fopen(FILE_NAME, "r"); //read

    if (file == NULL) {
    printf("Error: opening cargo manifests file.\n");
    exit(1);
    
    } else {

        char line[105]; //size da struct + "\0"

        /**
         * Contar quantas linhas o ficheiro tem
         * 
         */
        while (fgets(line, sizeof(line), file) != NULL) {
            numLinhas++;
        }
        rewind(file); // restaura o pointer no inicio do file outra vez
        
        /**
         * Ignorar a primeira linha do ficheiro, energy generation
         * (por enquanto)
         */
        fgets(line, sizeof(line), file);

        /**
         * Reservar memória dinâmica para os Cargo Manifests
         * (cada linha que corresponde a um cargo manifest * o tamanho da respetiva struct)
         */
        ptr_manifests = ( CargoManifest *) malloc ((numLinhas - 1) * sizeof(CargoManifest));

        /**
         * Ciclo para aquisição dos dados do ficheiro e respetivas verificações de valores
         * 
         */
        for (i = 0; i < numLinhas - 1; i++) {

            fgets(line, sizeof(line), file);

            char *element = strtok(line, ",");
            containerID = atoi(element);

            if(containerID < 0){
                printf("Error: invalid container ID");
                exit(1);
            }

            element = strtok(NULL, ",");
            x = atoi(element);

            if(x >= MAX_ROWS){
                printf("Error: x can only be between 0 and %d\n.",MAX_ROWS-1);
                exit(1);
            }

            element = strtok(NULL, ",");
            y = atoi(element);

            if(y >= MAX_COLUMNS){
                printf("Error: y can only be between 0 and %d\n.",MAX_COLUMNS-1);
                exit(1);
            }

            element = strtok(NULL, ",");
            z = atoi(element) + (MAX_LAYERS/2); 
            /*converter para um número entre 0 e 20, visto que é impossível utilizar valores negativos, por exemplo:
            * em vec[x][y][10], o contentor esta na posição z = 0 
            * em vec[x][y][0], o contentor esta na posição z = -10
            * em vec[x][y][20], o contentor esta na posição z = 10
            */
                
            if(z < 0 || z > MAX_LAYERS-1){
                printf("Error:z can only be between %d and %d\n.",0-(MAX_LAYERS/2),(MAX_LAYERS/2));
                exit(1);
            }

            element = strtok(NULL, ",");
            containerTemperature = atof(element);

            element = strtok(NULL, ",");
            containerWidth = atof(element);

            if(containerWidth < 0){
                printf("Error: the container width can´t be below 0.\n");
                exit(1);
            }

            element = strtok(NULL, ",");
            containerHeight = atof(element);

            if(containerHeight < 0){
                printf("Error: the container height can´t be below 0.\n");
                exit(1);
            }

            element = strtok(NULL, ",");
            containerLength = atof(element);

            if(containerLength < 0){
                printf("Error: the container length can´t be below 0.\n");
                exit(1);
            }

            element = strtok(NULL, ",");
            grossWeight = atof(element);

            if(grossWeight < 0){
                printf("Error: the container gross weight can´t be below 0.\n");
                exit(1);
            }

            element = strtok(NULL, ",");
            thickness = atof(element);

            if(thickness < 0){
                printf("Error: the thickness can´t be below 0.\n");
                exit(1);
            }
            
            element = strtok(NULL, ",");
            isItRefrigerated = atoi(element);

            if(isItRefrigerated != 0 && isItRefrigerated != 1){
                printf("Error: invalid container refrigerated value.\n");
                exit(1);
            }

            element = strtok(NULL, ",");
            strcpy(materials, element);
            
            element = strtok(NULL, ",");
            thermalConductivity = atof(element);
            
             if(thermalConductivity < 0){
                printf("Error: the thermal conductivity can´t be below 0.\n");
                exit(1);
            }

            /**
             * Através do ptr manifests, mover cada linha do ficheiro para uma struct 
             * 
             */
            (ptr_manifests + i)->containerID = containerID;
            (ptr_manifests + i)->x = x;
            (ptr_manifests + i)->y = y;
            (ptr_manifests + i)->z = z;
            (ptr_manifests + i)->containerTemperature = containerTemperature;
            (ptr_manifests + i)->containerWidth = containerWidth;
            (ptr_manifests + i)->containerHeight = containerHeight;
            (ptr_manifests + i)->containerLength = containerLength;
            (ptr_manifests + i)->grossWeight = grossWeight;
            (ptr_manifests + i)-> thickness = thickness;
            (ptr_manifests + i)->isItRefrigerated = isItRefrigerated;
            strcpy((ptr_manifests + i)->materials, materials);
            (ptr_manifests + i)->thermalConductivity = thermalConductivity;
        }
        
        fclose(file);
        printf(" Container_id = %d\n X = %d\n Y = %d\n Z = %d\n\n",ptr_manifests->containerID,ptr_manifests->x,ptr_manifests->y,ptr_manifests->z);
        
        
        double retorno;
        retorno = calculate_energy(ptr_manifests,3,3,13);

        printf (" Energia =%f\n\n", retorno);


        // US 4111

        int current_energy_generation_units = 1;

	    int is_not;
	    printf("\n");
	    is_not = review_energy_needed(ptr_manifests, current_energy_generation_units, size);
        if(is_not == 1){
            printf("Current energy units are not enough to provide energy to the containers.\n");

        } else if(is_not == 0){
            printf("Current energy units are enough to provide energy to the containers.\n");
        }

    }

}

