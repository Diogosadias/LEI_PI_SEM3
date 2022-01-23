#include "is_refrigerated.h"
#include "calculate_energy.h"
#include "main.h"
#include "review_energy_needed.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>


int review_energy_needed(CargoManifest *ptr_manifests, int current_energy_units, int size)
{
    FILE *fptr;

    fptr = fopen("Energy_Results.txt", "w+");

    if (fptr == NULL)
    {
        printf("Error!");
        exit(1);
    }
    int container_id;
    int x_coord;
    int y_coord;
    int z_coord;
    double total_energy = 0;
    double energy_given = 0;
    double container_energy = 0;
    int i;

    for (i = 0; i < size; i++)
    {
        x_coord = ptr_manifests->x;
        y_coord = ptr_manifests->y;
        z_coord = ptr_manifests->z;

        container_energy = calculate_energy(ptr_manifests, x_coord, y_coord, z_coord);
        //guardar num ficheiro
        if (container_energy != 0 ){
        container_id = ptr_manifests->containerID;
        fprintf(fptr, "container_id: %d\n", container_id);
        fprintf(fptr, "x_coord: %d\n", x_coord);
        fprintf(fptr, "y_coord: %d\n", y_coord);
        fprintf(fptr, "z_coord: %d\n", z_coord);
        fprintf(fptr, "Energy needed for the container: %0.2f\n", container_energy);
        fprintf(fptr, "\n");
        }
        //printf("%0.02f\n", container_energy);

        total_energy = total_energy + container_energy;

        ptr_manifests++;
    }
    

    //calculo da energia produzida pelo gerador durante uma hora
    energy_given = 75000 * 3600 * current_energy_units;

    if (energy_given < total_energy)
    {
        fprintf(fptr, "The energy units produced by the generatores is %0.02f J and is not enough to provide energy to the containers that need %0.02f J.\n", energy_given, total_energy);
        fclose(fptr);
        return 0;
    }
    else if (energy_given >= total_energy)
    {
        fprintf(fptr, "The energy units produced by the generatores is %0.02f J and is enough to provide energy to the containers that need %0.02f J.\n", energy_given, total_energy);
        fclose(fptr);
        return 1;
    }

    fclose(fptr);    
    return -1;
}



/*
void create_file(CargoManifest *ptr_manifests, double container_energy)
{
    FILE *fptr;
    int container_id = ptr_manifests->containerID;
    int x_coord = ptr_manifests->x;
    int y_coord = ptr_manifests->y;
    int z_coord = ptr_manifests->z;
    // use appropriate location if you are using MacOS or Linux
    fptr = fopen("Energy_Results.txt", "w+");

    if (fptr == NULL)
    {
        printf("Error!");
        exit(1);
    }
    fprintf(fptr, "######Container_id_%d######\n", container_id);
    fprintf(fptr, "x_coord: %d\n", x_coord);
    fprintf(fptr, "y_coord: %d\n", y_coord);
    fprintf(fptr, "z_coord: %d\n", z_coord);
    fprintf(fptr, "Energy needed for the container: %0.2f\n", container_energy);
    fprintf(fptr, "\n");
    fclose(fptr);
}
*/