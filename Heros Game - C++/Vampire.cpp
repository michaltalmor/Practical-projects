/*
 * Vampire.cpp
 *
 *  Created on: Dec 25, 2018
 *      Author: ise
 */


#include "Vampire.h"

Vampire::Vampire() {

	this->attackPower = 4;
	this->defensePower = 4;
	this->price = 80;
	this->count = 0;
	this->totalAttack = count * attackPower;
	this->totalDefense = count * defensePower;
	this->name = "Vampire";

}

Vampire::~Vampire() {

}

bool Vampire::attack(Creature& opCreature) {


	if (count * attackPower - opCreature.getTotalDefense() >= 0) {
		opCreature.setCount(0);
	}

	else {

		int deadCreaturesNum = (count * attackPower) / opCreature.getDefensePower();
		if (opCreature.getCount() - deadCreaturesNum < 0)
			opCreature.setCount(0);
		else
			opCreature.setCount(opCreature.getCount()-deadCreaturesNum);
	}

	return true;
}

