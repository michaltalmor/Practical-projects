/*
 * Zombie.h
 *
 *  Created on: Dec 25, 2018
 *      Author: ise
 */

#ifndef ZOMBIE_H_
#define ZOMBIE_H_

#include <string>
#include "Creature.h"
using namespace std;

class Zombie : public Creature {

public:
	Zombie();
	~Zombie();
	bool attack(Creature& opCreature);

};


#endif /* ZOMBIE_H_ */
