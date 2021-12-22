#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "calculate_slots.h"

#define MAX_ROWS 20 // positions of x from 0 to 20
#define MAX_COLUMNS 20 // positions y from 0 to 20
#define MAX_LAYERS 21 // positions z from -10 to 10
#define FILE_NAME "Cargo_Manifest.txt"

int num = MAX_ROWS * MAX_COLUMNS * MAX_LAYERS;

int x = 0, y = 0, z = 0;

int vec[MAX_ROWS][MAX_COLUMNS][MAX_LAYERS];

int *ptrVec = &vec[0][0][0];

int main() {

    FILE *file;
    int manifest_id;

/*
 * Inicializar a matriz com todos os valores a 0
 */
for (x = 0; x < MAX_ROWS; x++) {
    for (y = 0; y < MAX_COLUMNS; y++) {
        for (z = 0; z < MAX_LAYERS; z++) {
           vec[x][y][z] = 0;
        }
    }
}

/*
 * Ler o ficheiro
 */
    file = fopen(FILE_NAME, "r"); //read

    if (file == NULL) {
        printf("Error: opening cargo manifest file\n");
        exit(1);

    } else {

        char line[400];

        while (fgets(line, sizeof(line), file) != NULL) {

            char *element = strtok(line, ",");
            manifest_id = atoi(element);
            element = strtok(NULL, ",");
            x = atoi(element);

            if(x >= MAX_ROWS){
				printf("Error: x can only be between 0 and %d\n",MAX_ROWS-1);
				exit(1);
			}

            element = strtok(NULL, ",");
            y = atoi(element);

              if(y >= MAX_COLUMNS){
				printf("Error: y can only be between 0 and %d\n",MAX_COLUMNS-1);
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
				printf("Error:z can only be between %d and %d\n",0-(MAX_LAYERS/2),(MAX_LAYERS/2));
				exit(1);
			}

            vec[x][y][z] = manifest_id;
		    printf("Matriz [%d][%d][%d]:%d\n",x,y,z,vec[x][y][z]);
        }

        fclose(file);
        
        long retorno =0;
        retorno = calculate_slots();
		printf("---------------------------------------------\n");
        printf("O long retornado da US314 é %ld\n", retorno);

    }
}
