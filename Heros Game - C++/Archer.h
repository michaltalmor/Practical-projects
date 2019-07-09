/*
 * Archer.h
 *
 *  Created on: Dec 25, 2018
 *      Author: ise
 */

#ifndef ARCHER_H_
#define ARCHER_H_

#include <string>
#include "Creature.h"
using namespace std;

class Archer : public Creature {

public:
	Archer();
	~Archer();
	bool attack(Creature& opCreature);

};


#endif /* ARCHER_H_ */
