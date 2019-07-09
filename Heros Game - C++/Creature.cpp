/*
 * Creature.cpp
 *
 *  Created on: Dec 25, 2018
 *      Author: ise
 */

#include "Creature.h"

Creature::Creature() {

	this->attackPower = 0;
	this->defensePower = 0;
	this->price = 0;
	this->count = 0;
	this->totalAttack = 0;
	this->totalDefense = 0;
	this->name = "";

}

Creature::~Creature() {

}

void Creature::printDetails() {

	cout << "Attack level: " << this->attackPower << ", Defense level: " << this->defensePower << endl;

}

void Creature::addCount(int num) {

	if (num >= 0)
		count = count + num;

}

int Creature::getCount() {

	return count;

}

string Creature::getName (){

	return this->name;
}

int Creature::getDefensePower(){

	return this->defensePower;

}
	int Creature::getTotalDefense(){
		return (this->defensePower*this->count);
	}
void Creature::setCount(int c){
		if (c>=0)
			this->count=c;
	}

int Creature::getPrice() {
	return this->price;
}
