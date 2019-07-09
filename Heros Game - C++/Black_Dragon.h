/*
 * Black_Dragon.h
 *
 *  Created on: Dec 25, 2018
 *      Author: ise
 */

#ifndef BLACK_DRAGON_H_
#define BLACK_DRAGON_H_

#include <string>
#include "Creature.h"
using namespace std;

class Black_Dragon : public Creature {

public:
	Black_Dragon();
	~Black_Dragon();
	bool attack(Creature& opCreature);

};


#endif /* BLACK_DRAGON_H_ */
