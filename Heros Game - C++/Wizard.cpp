/*
 * Wizard.cpp
 *
 *  Created on: Dec 25, 2018
 *      Author: ise
 */


#include "Wizard.h"

Wizard::Wizard() {

	this->attackPower = 8;
	this->defensePower = 2;
	this->price = 150;
	this->count = 0;
	this->totalAttack = count * attackPower;
	this->totalDefense = count * defensePower;
	this->name = "Wizard";

}

Wizard::~Wizard() {

}

bool Wizard::attack(Creature& opCreature) {


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

