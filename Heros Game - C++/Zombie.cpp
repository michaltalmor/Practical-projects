/*
 * Zombie.cpp
 *
 *  Created on: Dec 25, 2018
 *      Author: ise
 */

#include "Zombie.h"

Zombie::Zombie() {

	this->attackPower = 2;
	this->defensePower = 5;
	this->price = 50;
	this->count = 0;
	this->totalAttack = count * attackPower;
	this->totalDefense = count * defensePower;
	this->name = "Zombie";

}

Zombie::~Zombie() {

}

bool Zombie::attack(Creature& opCreature) {

	if (opCreature.getName() == "Archer") {

		if (count * attackPower*2 - opCreature.getTotalDefense() >= 0) {
			opCreature.setCount(0);
		}

		else {

			int deadCreaturesNum = (count * attackPower*2) / opCreature.getDefensePower();
			if (opCreature.getCount() - deadCreaturesNum < 0)
				opCreature.setCount(0);
			else
				opCreature.setCount(opCreature.getCount()-deadCreaturesNum);
		}
	}
	else
	{
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
	}

	return true;
}


