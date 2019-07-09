/*
 * Vampire.h
 *
 *  Created on: Dec 25, 2018
 *      Author: ise
 */

#ifndef VAMPIRE_H_
#define VAMPIRE_H_

#include <string>
#include "Creature.h"
using namespace std;

class Vampire : public Creature {
public:
	Vampire();
	~Vampire();
	bool attack(Creature& opCreature);

};


#endif /* VAMPIRE_H_ */
