#include <stdio.h>
#include "is_refrigerated.h"
#include "calculate_energy.h"
#include "main.h"
#include <string.h>

double calculate_energy(CargoManifest *ptr_manifests, int x_coord, int y_coord, int z_coord){
    
    int retorno;
    int size =4;
    double condutividade_termica, espessura, largura, comprimento, altura, temperatura, area, resistencia, potencia, energia;
	int paredes_quadradas=2;
	int paredes_retangulares=4;                            
    double temperatura_ambiente = 20.0;
    double tempo = 3600.0;
    
    retorno = is_refrigerated(ptr_manifests,x_coord,y_coord,z_coord);
    if (retorno == -1 || retorno == 0){
    return ((double) retorno);

    }
  for (int i = 0; i < size - 1; i++) {

					if ((((ptr_manifests + i)->x) == x_coord) && (((ptr_manifests + i)->y) == y_coord) && (((ptr_manifests + i)->z) == z_coord) ){
		
					condutividade_termica = ((ptr_manifests + i)->thermalConductivity);
					espessura = ((ptr_manifests + i)->thickness);
					largura = ((ptr_manifests + i)->containerWidth);
					altura = ((ptr_manifests + i)->containerHeight);
					largura = ((ptr_manifests + i)->containerWidth);
					comprimento = ((ptr_manifests + i)->containerLength);
					temperatura = ((ptr_manifests + i)->containerTemperature);
					area = paredes_retangulares*largura*comprimento+paredes_quadradas*largura*altura;
					
					resistencia = espessura / (condutividade_termica*area);
					
					potencia = (temperatura_ambiente-temperatura) / resistencia;
					
					energia = potencia * tempo;
					
					return energia;
					
					
				}
			   }
			   return 0;
			}


