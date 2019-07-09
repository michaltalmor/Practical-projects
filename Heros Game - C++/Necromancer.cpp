/*
 * Nacromancer.cpp
 *
 *  Created on: Dec 25, 2018
 *      Author: ise
 */

#include "Necromancer.h"

Necromancer::~Necromancer() {

	for (int i=0 ; i< 5; i++){
		delete creaturesList[i];
	}
	delete [] creaturesList;


}

bool Necromancer::specialAbility() {

	this->creaturesList[0]->addCount(1);
	return true;

}


bool Necromancer::specialAbility(Hero& other){
	return false;
}
