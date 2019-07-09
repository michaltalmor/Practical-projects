/*
 * Creature.h
 *
 *  Created on: Dec 25, 2018
 *      Author: ise
 */

#ifndef CREATURE_H_
#define CREATURE_H_

#include <string>
#include <iostream>
using namespace std;


class Creature {

protected:
	int attackPower;
	int defensePower;
	int price;
	int count;
	int totalAttack;
	int totalDefense;
	string name;

public:
	Creature();
	virtual ~Creature();
	void printDetails();
	virtual bool attack(Creature& opCreature)=0;
	void addCount(int num);
	int getCount();
	string getName();
	int getDefensePower();
	int getTotalDefense();
	void setCount(int c);
	int getPrice();

};



#endif /* CREATURE_H_ */
